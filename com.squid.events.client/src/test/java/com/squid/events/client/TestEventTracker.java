package com.squid.events.client;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestEventTracker extends EventModelFactory implements TestConfig {

    static {
        // force using a different log4j directory
        System.setProperty("org.slf4j.simpleLogger.defaultLogLevel","info");
    }
    
    static final Logger logger = LoggerFactory.getLogger(TestEventTracker.class);
    
    /**
     * test that we can send events to the server
     */
    @Test
    public void testConenction() {
        Config config = new Config(appKey,secretKey);
        config.setEndpoint(endpoint);
        EventTracker.initialize(config);
        EventTracker.send(createSessionEvent());
        EventTracker.send(createSearchEvent());
        EventTracker.send(createRetrievalEvent());
        EventTracker.shutdown();
        Stats stats = EventTracker.getStats();
        Assert.assertEquals(3, stats.getSuccess());
    }

}
