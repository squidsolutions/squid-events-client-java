package com.squid.events.client;

import com.squid.events.model.EventModel;

/**
 * the Event Tracker facade to the service & client
 * 
 * How to use it from the application code:
 * 
 * First application code must initialize the EventTracker with the config.
 * Note that the default config setting are production ready.
 * 
 * Once the EventTracker is initialize, it is safe to call the send() event method concurrently.
 * 
 * Before finishing, the application code must call the shutdown() method to flush the queue.
 * 
 * @author sergefantino
 *
 */
public class EventTracker {
    
    private static EventTrackerClient client;
    
    /**
     * Initialize the EventTracker with the config.
     * 
     * This method is thread-safe
     * @param config
     *          the configuration object. Note that the default configuration is production ready.
     */
    public static synchronized void initialize(Config config) {
        if (client==null) {
            client = new EventTrackerClient(config);
        } else if (!client.isRunning()) {
            // restart a new client
            client = new EventTrackerClient(config);
        }
    }
    
    /**
     * Send an event to the tracker server.
     * 
     * The method should return immediately if there is room in the queue.
     * If the queue is full, it will wait until the timeout defined in the configuration is reach.
     * 
     * This method is thread-safe.
     * 
     * @param event
     *          The event you want to submit
     */
    public static void send(EventModel event) {
        if (client!=null) {
            client.send(event);
        }
    }
    
    /**
     * Get some statistics, that is number of successful and failed attempts, and the total running time in ms. 
     * @return
     */
    public static Stats getStats() {
        if (client!=null) {
            return client.getStats();
        } else {
            return null;
        }
    }
    
    /**
     * Shutdown the EventTracker. 
     * Once that method is called, the service will stop accepting new events.
     * The method will then flush the queue. Depending of the queue status, that may take some time.
     * 
     * The method will block until the queue is empty.
     * 
     * This method is thread-safe.
     */
    public static void shutdown() {
        synchronized (client) {
            if (client!=null) {
                client.shutdown();
            }
        }
    }

}
