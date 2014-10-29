package com.squid.events.commons;

/**
 * tracker constants
 * @author sergefantino
 *
 */
public interface Constants {
    
    // current API version
    public static final String VERSION = "1.0";
    
    public static final String BASE_SERVICE = "api";
    
    public static final String APP_KEY_PARAM = "key";
    public static final String SIGNATURE_PARAM = "sig";
    public static final String APP_VERSION_PARAM = "ver";
    public static final String APP_TEST_PARAM = "g";

    // default timeout when connection to the tracker
    public static final int HTTP_TIMEOUT = 6000;
}
