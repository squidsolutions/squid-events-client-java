package com.squid.events.model;

/**
 * define some special kinesis field - not for client
 * 
 * @author sergefantino
 *
 */
public interface KinesisModel {
    
    /**
     * define the record sequenceID - this can be necessary to dedup events
     */
    public static final String kinesisSequenceID = "kx:seqID";
    
    /**
     * replay flag: if the event is a replay, this flag should be set to true
     */
    public static final String kinesisReplayFlag = "kx:replay";

}
