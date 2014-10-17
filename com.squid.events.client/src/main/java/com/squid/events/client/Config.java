package com.squid.events.client;

public class Config {
    
    private String endpoint = "https://events.tracker.squidanalytics.com/tracker/api/v1.0";
    
    private String appKey = "null";
    
    private String secretKey = null;
    
    private int maxFlusherCount = 1;
    
    private int queueLimit = 10000;
    
    private int batchSize = 100;
    
    // default timeout for the send() method is 10ms
    private int sendTimeout = 10;
    
    public Config() {
        // TODO Auto-generated constructor stub
    }
    
    public String getEndpoint() {
        return endpoint;
    }
    
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
    
    public Config withEndpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }
    
    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public Config withAppKey(String appKey) {
        this.appKey = appKey;
        return this;
    }
    
    public String getSecretKey() {
        return secretKey;
    }
    
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
    
    public Config withSecretKey(String secretKey) {
        this.secretKey = secretKey;
        return this;
    }

    public int getMaxFlusherCount() {
        return maxFlusherCount;
    }

    public void setMaxFlusherCount(int maxFlusherCount) {
        this.maxFlusherCount = maxFlusherCount;
    }

    public int getQueueLimit() {
        return queueLimit;
    }

    public void setQueueLimit(int queueLimit) {
        this.queueLimit = queueLimit;
    }

    public int getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    public int getSendTimeout() {
        return sendTimeout;
    }

    public void setSendTimeout(int sendTimeout) {
        this.sendTimeout = sendTimeout;
    }
    
}
