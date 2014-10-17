package com.squid.events.client;

public class Stats {
    
    private long runningTime = 0;
    private long success = 0;
    private long failure = 0;
    
    public Stats(long success, long failure, long runningTime) {
        super();
        this.success = success;
        this.failure = failure;
        this.runningTime = runningTime;
    }
    
    public long getSuccess() {
        return success;
    }
    
    public long getFailure() {
        return failure;
    }
    
    public long getRunningTime() {
        return runningTime;
    }

}
