package com.squid.events.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Flusher extends Thread {
    
    static final Logger logger = LoggerFactory.getLogger(Flusher.class);

    private volatile boolean go = true;
    private EventPublisher publisher;

    public Flusher(EventPublisher publisher) {
        this.publisher = publisher;
    }
    
    @Override
    public void run() {
        logger.info("start flushing events");
        while (go) {
            publisher.poll();
        }
        logger.info("stop flushing events");
    }
    
    public void shutdown() {
        this.go = false;
        try {
            join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
