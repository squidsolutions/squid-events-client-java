package com.squid.events.client;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.squid.events.client.Config;
import com.squid.events.model.EventModel;
import com.squid.events.model.RetrievalEvent;

public class TestLoad implements TestConfig {

    static {
        // force using a different log4j directory
        System.setProperty("org.slf4j.simpleLogger.defaultLogLevel","info");
    }
    
    static final Logger logger = LoggerFactory.getLogger(TestLoad.class);

    private CountDownLatch start;
    private CountDownLatch complete;
    
    // experiment
    protected int eventsPerSecond = 10;// throughput
    protected int numberOfProducers = 50;
    protected int experieceDuration = 60*1;// duration in seconds
    
    class User extends Thread {
        
        private String userID;
        private int size;
        
        protected int count = 0;
        protected long total = 0;
        protected long max = 0;

        public User(String userID, int size) {
            this.userID = userID;
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
                        EventTracker.send(genEvent(count));
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
            /*
            EventModel event = new EventMode();
            event.appId = appKey;
            event.eventType = "retrieval";
            event.serverIp = "127.0.0.2";
            event.clientIp = "127.0.0.1";
            event.sessionId = userID+"_"+id;
            event.timestamp = System.currentTimeMillis();;
            event.url = "http://something/somepage";
            event.userId = userID;
            return event;
            */
            return new RetrievalEvent()
            .withContentOwnerID("myUniversity")
            .withContentType("article")
            .withDisplayFormat("PDF")
            .withEntitlement("myUnivertsity")
            .withClientIP("127.0.0.1")
            .withSessionID(userID+"_"+id)
            .withUserID(userID)
            .withPageViewURL("http://something/somepage")
            .withServerIP("127.0.0.1");
        }
        
    }
    
    @Test
    public void sendTestEvent() throws InterruptedException {
        Config config = new Config(appKey,secretKey);
        config.setEndpoint(endpoint);
        //
        config.setMaxFlusherCount(5);
        //EventTrackerClient client = new EventTrackerClient(config);
        EventTracker.initialize(config);
        //
        int threadCount = numberOfProducers;
        int requestCount = experieceDuration*eventsPerSecond/numberOfProducers;
        start = new CountDownLatch(1);
        complete = new CountDownLatch(threadCount);
        List<User> users = new ArrayList<User>(threadCount);
        for (int i=0;i<threadCount;i++) {
            User user = new User("user"+i,requestCount);
            users.add(user);
            user.start();
        }
        //
        long startTime = System.currentTimeMillis();
        start.countDown();// start all users
        complete.await();
        //
        EventTracker.shutdown();
        //
        long stopTime = System.currentTimeMillis();
        long ellapse = (stopTime-startTime);
        Stats stats = EventTracker.getStats();
        logger.info("published "+stats.getSuccess()+" events in "+ellapse+"ms => "+(threadCount*requestCount)/(ellapse/1000)+"event/s");
        logger.info("failed to publish: "+stats.getFailure()+" events");
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
        Assert.assertEquals(threadCount*requestCount,stats.getSuccess());
    }

}
