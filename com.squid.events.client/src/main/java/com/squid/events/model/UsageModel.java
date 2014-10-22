package com.squid.events.model;

/**
 * Usage event is just a generic collection of properties that can be integrated in various type of events
 * @author sergefantino
 *
 */
public class UsageModel extends EventModel {

    private static final long serialVersionUID = -216872748521850295L;
    
    /**
     * this is the client IP (v4 or v6)
     */
    public static final String usageClientIP = "ux:clientIP";
    
    /**
     * this is a persistent ID with session scope, that can be used to group events in the same session. 
     * It can be a server-side session identification, or client-side cookie
     */
    public static final String usageSessionID = "ux:sessionID";
    
    /**
     * this is an internal ID that possibly identify uniquely the user
     */
    public static final String usageUserID = "ux:userID";
    
    /**
     * this is the full URL (domain, path, query string + anchors ...) requested
     */
    public static final String usagePageViewURL = "ux:pageViewURL";
    
    /**
     * this is a persistent ID that can span multiple application server. 
     * It can be used to correlate events submitted from different systems but used to fulfill the same user request
     */
    public static final String usageTransactionID = "ux:transactionID";
    
    /**
     * HTTP return code send back by the server to the client
     */
    public static final String usageHttpReturnCode = "ux:httpReturnCode";
    
    /**
     * Any error text that would be interesting to report, send back to the client from the server
     */
    public static final String usageErrorCode = "ux:errorCode";

    public UsageModel(String schemaName, String eventType) {
        super(schemaName, eventType);
    }

    /**
     * the clientIP
     * @param IP
     * @return this
     */
    public UsageModel withClientIP(String IP) {
        super.put(usageClientIP, IP);
        return this;
    }

    /**
     * the session identification
     * @param ID
     * @return this
     */
    public UsageModel withSessionID(String ID) {
        super.put(usageSessionID, ID);
        return this;
    }

    /**
     * the user identification if it is not anonymous
     * @param ID
     * @return this
     */
    public UsageModel withUserID(String ID) {
        super.put(usageUserID, ID);
        return this;
    }
    
    /**
     * the page view by the user, associated with this event
     * @param url
     * @return this
     */
    public UsageModel withPageViewURL(String url) {
        super.put(usagePageViewURL, url);
        return this;
    }
    
    /**
     * this is a persistent ID that can span multiple application server. 
     * It can be used to correlate events submitted from different systems but used to fulfill the same user request
     * @param ID
     * @return this
     */
    public UsageModel withTransactionID(String ID) {
        super.put(usageTransactionID, ID);
        return this;
    }
    
    /**
     * HTTP return code send back by the server to the client
     * @param errorCode
     * @return this
     */
    public UsageModel withHttpReturnCode(int returnCode) {
        super.put(usageHttpReturnCode,returnCode);
        return this;
    }
    
    /**
     * Any error text that would be interesting to report, send back to the client from the server
     * @param errorCode
     * @return this
     */
    public UsageModel withErrorCode(String error) {
        super.put(usageErrorCode,error);
        return this;
    }

}
