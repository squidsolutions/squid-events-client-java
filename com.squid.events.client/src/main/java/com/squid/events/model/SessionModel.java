package com.squid.events.model;

public class SessionModel extends AccountModel {
    
    private static final long serialVersionUID = 8121923221818713546L;

    /**
     * the session schema defines properties associated with a new session
     * The sessionID may be use further to link events occuring in the scope of this session
     * 
     */
    public static final String sessionSchema = "session_1.0";
    
    public static final String startSessionEventType = "start";
    
    /**
     * this is a persistent ID associated with the browser (or rich application) generating the event on the client side. 
     * Usually it will be retrieve from a cookie.
     * It can be used to group multiple session with the same browser
     */
    public static final String sessionBrowserID = "ss:browserID";
    
    /**
     * this is the full referrer’s URL
     */
    public static final String sessionReferrer = "ss:referrer";
    
    /**
     * UserAgent string sent back by the client
     */
    public static final String sessionUserAgent = "ss:userAgent";
    
    protected SessionModel(String schemaName, String eventType) {
        super(schemaName, eventType);
        // TODO Auto-generated constructor stub
    }

    public SessionModel(String eventType) {
        super(sessionSchema, eventType);
    }
    
    /**
     * this is a persistent ID associated with the browser (or rich application) generating the event on the client side. 
     * Usually it will be retrieve from a cookie.
     * It can be used to group multiple session with the same browser
     * @param ID
     * @return this
     */
    public SessionModel withBrowserID(String ID) {
        super.put(sessionBrowserID,ID);
        return this;
    }
    
    /**
     * this is the full referrer’s URL
     * @param URL of the referrer
     * @return this
     */
    public SessionModel withReferrerURL(String URL) {
        super.put(sessionReferrer,URL);
        return this;
    }
    
    /**
     * UserAgent string send back by the client
     * @param agent
     * @return this
     */
    public SessionModel withUserAgent(String agent) {
        super.put(sessionUserAgent,agent);
        return this;
    }

}
