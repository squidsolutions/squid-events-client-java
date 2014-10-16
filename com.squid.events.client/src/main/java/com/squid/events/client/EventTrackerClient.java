package com.squid.events.client;

import java.util.ArrayList;
import java.util.Collection;

import com.squid.events.model.EventModel;

public class EventTrackerClient {

    private EventPublisher publisher;
    private Collection<Flusher> flushers = null;

    /**
     * create a new Event Tracker client using the given configuration.
     * @param config
     */
    public EventTrackerClient(Config config) {
        publisher = new EventPublisher(config);
        Flusher flusher = new Flusher(publisher);
        flushers = new ArrayList<>();
        flushers.add(flusher);
        flusher.start();
    }
    
    public void send(EventModel event) {
        publisher.send(event);
    }
    
    public long getStats() {
        if (publisher!=null) {
            return publisher.getStats();
        } else {
            return -1;
        }
    }
    
    /**
     * Properly shutdown the client, taking care to flush the queue.
     * The client will stop accepting events right away; then it will stop each flusher; then it will flush the queue.
     * 
     * The shutdown method will return when all the events are safely flushed.
     * 
     */
    public void shutdown() {
        publisher.shutdown();
        for (Flusher flusher : flushers) {
            flusher.shutdown();
        }
        publisher.flush();
    }

}
