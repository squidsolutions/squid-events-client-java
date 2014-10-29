package com.squid.events.client;

public class DefaultConfig {

    // default endpoint
    public static final String endpoint = "https://events.tracker.squidanalytics.com/tracker";

    // default number of parallel flushers
    public static int maxFlusherCount = 1;

    // default queue limit
    public static int queueLimit = 10000;

    // default batch size to send events to the server
    public static int batchSize = 100;

    // default timeout for the send() method is 10ms
    public static int sendTimeout = 10;
}
