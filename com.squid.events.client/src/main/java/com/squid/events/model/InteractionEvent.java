package com.squid.events.model;

/**
 * construct a retrieval event
 * @author sergefantino
 *
 */
public class InteractionEvent extends InteractionModel {

    private static final long serialVersionUID = 7602749874218394598L;

    public InteractionEvent() {
        super(InteractionModel.interactionEventType);
    }

}
