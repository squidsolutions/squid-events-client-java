package com.squid.events.model;

/**
 * construct a start session event
 * @author sergefantino
 *
 */
public class StartSessionEvent extends SessionModel {

    private static final long serialVersionUID = 4634159884123551937L;

    public StartSessionEvent() {
        super(SessionModel.startSessionEventType);
    }

}
