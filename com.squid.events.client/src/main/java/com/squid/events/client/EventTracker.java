package com.squid.events.client;

import com.squid.events.model.EventModel;

/**
 * the Event Tracker facade to the service & client
 * 
 * @author sergefantino
 *
 */
public class EventTracker {
    
    private static EventTrackerClient client;
    
    public static synchronized void initialized(Config config) {
        if (client==null) {
            client = new EventTrackerClient(config);
        }
    }
    
    public static void send(EventModel event) {
        if (client!=null) {
            client.send(event);
        }
    }
    
    public static void shutdown() {
        if (client!=null) {
            client.shutdown();
        }
    }

}
