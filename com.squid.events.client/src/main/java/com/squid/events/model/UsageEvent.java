package com.squid.events.model;

public class UsageEvent extends EventModel {

    private static final long serialVersionUID = -216872748521850295L;
    
    public static final String usageClientIP = "ux:clientIP";
    public static final String usageSessionID = "ux:sessionID";
    public static final String usageUserID = "ux:userID";
    public static final String usagePageViewURL = "ux:pageViewURL";
    
    public UsageEvent() {
    }

    public UsageEvent(String schemaName, String eventType) {
        super(schemaName, eventType);
    }

    /**
     * the clientIP
     * @param IP
     * @return
     */
    public UsageEvent withClientIP(String IP) {
        super.put(usageClientIP, IP);
        return this;
    }

    /**
     * the session identification
     * @param ID
     * @return
     */
    public UsageEvent withSessionID(String ID) {
        super.put(usageSessionID, ID);
        return this;
    }

    /**
     * the user identification if it is not anonymous
     * @param ID
     * @return
     */
    public UsageEvent withUserID(String ID) {
        super.put(usageUserID, ID);
        return this;
    }
    
    /**
     * the page view by the user, associated with this event
     * @param url
     * @return
     */
    public UsageEvent withPageViewURL(String url) {
        super.put(usagePageViewURL, url);
        return this;
    }

}
