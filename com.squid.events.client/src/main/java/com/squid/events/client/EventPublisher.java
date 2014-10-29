package com.squid.events.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squid.events.commons.Base64;
import com.squid.events.commons.Constants;
import com.squid.events.commons.Signature;
import com.squid.events.model.EventModel;

/**
 * main class to publish event to a queue
 * 
 * @author sergefantino
 * 
 */
public class EventPublisher {

    static final Logger logger = LoggerFactory.getLogger(EventPublisher.class);

    private Config config;

    private LinkedBlockingQueue<EventModel> queue = null;
    
    private boolean go = true;
    
    private int max_queue = 0;
    private long min_http_ellapse = Long.MAX_VALUE;
    private long max_http_ellapse = Long.MIN_VALUE;
    private long total_http_ellapse = 0;
    private int count_http_requests = 0;
    
    private long startTime = 0;
    
    private AtomicLong pushSuccess = new AtomicLong(0);
    private AtomicLong pushFailed = new AtomicLong(0);
    private AtomicLong sendSuccess = new AtomicLong(0);
    private AtomicLong sendFailed = new AtomicLong(0);

    // the full url of the service: <endpoint>/<api>/<version>
    private String serviceURL;
    
    /**
     * create the EventPublisher with this configuration.
     * The EventPublisher will check if the configuration is valid. If not it will throw a IllegalStateException.
     * 
     * @param config
     * @throws IllegalStateException if the configuration is not valid
     */
    public EventPublisher(Config config) {
        check(config);
        this.config = config;
        this.serviceURL = checkURL(config);
        this.queue = new LinkedBlockingQueue<EventModel>(config.getQueueLimit());
        this.startTime = System.currentTimeMillis();
    }
    
    /**
     * check that the endpoint is correctly configured and that the service is up and running
     * @param config
     * @return
     */
    private String checkURL(Config config) {
        if (config.getEndpoint()==null || config.getEndpoint()=="") {
            String msg = "Event Tracker client configuration error: missing the endpoint";
            logger.info(msg);
            throw new IllegalStateException(msg);
        }
        String url = config.getEndpoint();
        if (!url.endsWith("/")) url += "/";
        url += Constants.BASE_SERVICE+"/v"+Constants.VERSION;
        checkURL(url);
        return url;
    }

    /**
     * check that the URL can accept events
     * @param url
     */
    private void checkURL(String url) {
        
    }

    /**
     * check that mandatory information are here
     * @param config2
     */
    private void check(Config config) {
        if (config.getAppKey()==null || config.getAppKey().length()==0) {
            String msg = "Event Tracker client configuration error: missing the appKey";
            logger.info(msg);
            throw new IllegalStateException(msg);
        }
        if (config.getSecretKey()==null || config.getSecretKey().length()==0) {
            String msg = "Event Tracker client configuration error: missing the secretKey";
            logger.info(msg);
            throw new IllegalStateException(msg);
        }
    }

    /**
     * Check if the publisher is running. Will return false once you call shutdown.
     * @return
     */
    public boolean isRunning() {
        return this.go;
    }
    
    /**
     * shutdown the publisher: it will stop accepting new events right away
     */
    public void shutdown() {
        this.go = false;
    }

    public boolean send(EventModel event) {
        if (go) {
            if (config.getSendTimeout()<=0) {
                // if the queue is full, fail
                return sendOffer(event);
            } else {
                // wait as much as timeout
                return sendOffer(event, config.getSendTimeout());
            }
        } else {
            return false;
        }
    }
    
    protected boolean sendOffer(EventModel event) {
        if (queue.offer(event)) {
            sendSuccess.incrementAndGet();
            return true;
        } else {
            sendFailed.incrementAndGet();
            return false;
        }
    }

    protected boolean sendOffer(EventModel event, long timeout) {
        try {
            if (!queue.offer(event, timeout, TimeUnit.MILLISECONDS)) {
                // failed
                sendFailed.incrementAndGet();
                return false;
            } else {
                sendSuccess.incrementAndGet();
                return true;
            }
        } catch (InterruptedException e) {
            sendFailed.incrementAndGet();
            return false;
        }
    }
    
    public Stats getStats() {
        long now = System.currentTimeMillis();
        return new Stats(this.pushSuccess.get(),this.sendFailed.get()+this.pushFailed.get(),now-startTime);
    }

    /**
     * flush the event queue of all its content
     */
    public void flush() {
        logger.info("flush the queue");
        int size = queue.size();
        int i=0;
        while (!queue.isEmpty()) {
            List<EventModel> copy = new ArrayList<EventModel>(config.getBatchSize());
            for (int j=0 ;i < size && j <config.getBatchSize(); i++,j++) {
                copy.add(queue.poll());
            }
            if (!copy.isEmpty()) {
                sendBatchMessage(copy);
            }
        }
        // display stats
        if (count_http_requests==0) {
            logger.info("http stats: count=0");
        } else {
            double avg = count_http_requests>0?(total_http_ellapse/count_http_requests):0;
            logger.info("http stats: count="+count_http_requests+" min="+min_http_ellapse+"ms; max="+max_http_ellapse+"ms; avg="+avg+"ms; total="+total_http_ellapse+"ms");
        }
        logger.info("queue stats: max size = "+max_queue);
        logger.info("send():success="+sendSuccess.get()+"; failed="+sendFailed.get());
        logger.info("push():success="+pushSuccess.get()+"; failed="+pushFailed.get());
    }
    
    public void poll() {
        try {
            List<EventModel> batch = new ArrayList<EventModel>(config.getBatchSize());
            if (max_queue <queue.size()) {
                max_queue = queue.size();
                logger.info("queue hits new max = "+max_queue);
            }
            while (true) {
                EventModel event = queue.poll(1,TimeUnit.SECONDS);
                if (event!=null) {
                    batch.add(event);
                    if (batch.size()>=config.getBatchSize()) {
                        break;//enought to push
                    }
                } else {
                    break;// send the batch if any, and return to the flusher loop
                }
            }
            if (!batch.isEmpty()) {
                sendBatchMessage(batch);
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void sendBatchMessage(List<EventModel> events) {
        ObjectMapper jackson = new ObjectMapper();
        try {
            byte[] raw = jackson.writeValueAsBytes(events);
            String data = Base64.printBase64Binary(raw);
            String signature = Signature.calculateRFC2104HMAC(data,
                    config.getSecretKey());
            int response = doPost(serviceURL, config.getAppKey(),
                    "event-retrieval-1.0", signature, data);
            logger.info("sent "+events.size()+" events to API server");
            logger.info("API server replied with " + response);
            if (response==202) {
                pushSuccess.addAndGet(events.size());
            } else {
                pushFailed.addAndGet(events.size());
            }
        } catch (JsonProcessingException e) {
            // too bad
            logger.info("cannot write events as JSON: "
                    + e.getLocalizedMessage());
            pushFailed.addAndGet(events.size());
        }
    }

    private int doPost(String serviceURL, String appKey, String schema,
            String signature, String data) {
        URL url;
        HttpURLConnection connection = null;
        int returnCode = -1;
        try {
            // Create connection
            String request = serviceURL
                    +"?"+Constants.APP_KEY_PARAM+"="+URLEncoder.encode(appKey, "UTF-8")
                    +"&"+Constants.SIGNATURE_PARAM+"="+URLEncoder.encode(signature, "UTF-8");
            if (config.getAppVersion()!=null && config.getAppVersion()!="") {
                request += "&"+Constants.APP_VERSION_PARAM+"="+URLEncoder.encode(config.getAppVersion(), "UTF-8");
            }
            if (config.isAppTestFlag()) {
                request += "&"+Constants.APP_TEST_PARAM+"=1";
            }
            url = new URL(request);
            //
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(Constants.HTTP_TIMEOUT);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type",
                    "application/json; charset=utf-8");

            connection.setRequestProperty("Content-Length",
                    "" + Integer.toString(data.getBytes().length));

            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            // Send request
            DataOutputStream wr = new DataOutputStream(
                    connection.getOutputStream());
            wr.writeBytes(data);
            wr.flush();
            wr.close();

            // Get Response
            long start = System.currentTimeMillis();
            returnCode = connection.getResponseCode();
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            long stop = System.currentTimeMillis();
            //
            long ellapse = stop-start;
            logger.info("http replied in "+ellapse+"ms");
            min_http_ellapse = Math.min(min_http_ellapse, ellapse);
            max_http_ellapse = Math.max(max_http_ellapse, ellapse);
            total_http_ellapse += ellapse;
            count_http_requests++;
            //
            return returnCode;//response.toString();

        } catch (Exception e) {

            e.printStackTrace();
            return returnCode;

        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }
    }

}
