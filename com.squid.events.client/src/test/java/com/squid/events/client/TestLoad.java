package com.squid.events.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.squid.events.client.Config;
import com.squid.events.client.EventPublisher;
import com.squid.events.client.Flusher;
import com.squid.events.model.EventModel;

public class TestLoad {

    static {
        // force using a different log4j directory
        System.setProperty("org.slf4j.simpleLogger.defaultLogLevel","info");
    }
    
    static final Logger logger = LoggerFactory.getLogger(TestLoad.class);
    
    //private String endpoint = "http://localhost:8080/tracker/api/v1.0";
    private String endpoint = "http://default-environment-3hj9nxa3pq.elasticbeanstalk.com/api/v1.0";
    
    private String appKey= "PQ01";
    
    private String secretKey = "PQ01";

    private CountDownLatch start;
    private CountDownLatch complete;
    
    // experiment
    protected int eventsPerSecond = 10;
    protected int numberOfProducers = 50;
    protected int experieceDuration = 60*1;// 5 minutes
    
    class User extends Thread {
        
        private String userID;
        private EventTrackerClient client;
        private int size;
        
        protected int count = 0;
        protected long total = 0;
        protected long max = 0;

        public User(String userID, EventTrackerClient client, int size) {
            this.userID = userID;
            this.client = client;
            this.size = size;
        }
        
        @Override
        public void run() {
            try {
                start.await();
                logger.info("starting client "+userID);
                //Object waiter = new Object();
                //synchronized (waiter) {
                    for (int i=0;i<size;i++) {
                        long start = System.currentTimeMillis();
                        client.send(genEvent(count));
                        long stop = System.currentTimeMillis();
                        count++;
                        long ellapse = stop-start;
                        total = total+ellapse;
                        max = Math.max(max, ellapse);
                        try {
                            //waiter.wait(50);
                            Thread.sleep(1000/eventsPerSecond*numberOfProducers);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                //}
            } catch (InterruptedException e) {
                //
            } finally {
                logger.info("stopping client "+userID);
                complete.countDown();
            }
        }
        
        public void printStats() {
            if (count>0) {
                logger.info("UserID="+userID+" send() stats: count="+count+"; acg="+total/count+"ms; max="+max+"ms");
            }
        }
        
        private EventModel genEvent(int id) {
            EventModel event = new EventModel();
            event.appId = appKey;
            event.eventType = "retrieval";
            event.serverIp = "127.0.0.2";
            event.clientIp = "127.0.0.1";
            event.sessionId = userID+"_"+id;
            event.timestamp = System.currentTimeMillis();;
            event.url = "http://something/somepage";
            event.userId = userID;
            return event;
        }
        
    }
    
    @Test
    public void sendTestEvent() throws InterruptedException {
        Config config = new Config();
        config.setEndpoint(endpoint);
        config.setAppKey(appKey);
        config.setSecretKey(secretKey);
        //
        config.setMaxFlusherCount(5);
        EventTrackerClient client = new EventTrackerClient(config);
        //
        int threadCount = numberOfProducers;
        int requestCount = experieceDuration*eventsPerSecond/numberOfProducers;
        start = new CountDownLatch(1);
        complete = new CountDownLatch(threadCount);
        List<User> users = new ArrayList<>(threadCount);
        for (int i=0;i<threadCount;i++) {
            User user = new User("user"+i,client,requestCount);
            users.add(user);
            user.start();
        }
        //
        long startTime = System.currentTimeMillis();
        start.countDown();// start all users
        complete.await();
        //
        client.shutdown();
        //
        long stopTime = System.currentTimeMillis();
        long ellapse = (stopTime-startTime);
        logger.info("published "+client.getStats()+" events in "+ellapse+"ms => "+(threadCount*requestCount)/(ellapse/1000)+"event/s");
        logger.info("submitted events count="+threadCount*requestCount);
        //
        int count = 0;
        long total = 0;
        long max = 0;
        for (User user : users) {
            count += user.count;
            total += user.total;
            max = Math.max(max, user.max);
        }
        logger.info("send() stats: count="+count+"; total="+total+"; avg="+total/count+"ms; max="+max+"ms");
        //
        Assert.assertEquals(threadCount*requestCount,client.getStats());
    }

}
