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
     * @throws IllegalStateException if the configuration is not valid
     */
    public EventTrackerClient(Config config) {
        publisher = new EventPublisher(config);
        flushers = new ArrayList<Flusher>();
        int flusherCount = Math.max(1, config.getMaxFlusherCount());
        for (int i=0;i<flusherCount;i++) {
            Flusher flusher = new Flusher(publisher);
            flushers.add(flusher);
            flusher.start();
        }
    }
    
    public boolean isRunning() {
        return publisher.isRunning();
    }
    
    public void send(EventModel event) {
        publisher.send(event);
    }
    
    public Stats getStats() {
        return publisher.getStats();
    }
    
    /**
     * Properly shutdown the client, taking care to flush the queue.
     * The client will stop accepting events right away; then it will stop each flusher; then it will flush the queue.
     * 
     * The shutdown method will return when all the events are safely flushed.
     * 
     */
    public void shutdown() {
        if (publisher.isRunning()) {
            publisher.shutdown();// stop accepting new events
            for (Flusher flusher : flushers) {
                flusher.shutdown();
            }
            publisher.flush();
        }
    }

}
