package com.squid.events.model;

/**
 * construct a retrieval event
 * @author sergefantino
 *
 */
public class RetrievalEvent extends RetrievalModel {

    private static final long serialVersionUID = 3263181299164044911L;

    public RetrievalEvent() {
        super(RetrievalModel.retrievEventType);
    }
}
