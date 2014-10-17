package com.squid.events.model;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * The base event model class. It defines basic information common to every events.
 * 
 * @author sergefantino
 *
 */
public class EventModel extends HashMap<String, Object> {

    /**
     * 
     */
    private static final long serialVersionUID = 5797953891086795430L; 
    
    public static final String SchemaName = "xx:schemaName";
    public static final String EventType = "xx:eventType";
    public static final String EventDate = "xx:eventDate";
    public static final String EventTimeZone = "xx:eventTimeZone";
    
    public EventModel() {
        // TODO Auto-generated constructor stub
    }
    
    public EventModel(String schemaName, String eventType) {
        put(SchemaName,schemaName);
        put(EventType,eventType);
        withEventDate(Calendar.getInstance());
    }
    
    public EventModel with(String key, Object value) {
        super.put(key, value);
        return this;
    }
    
    /**
     * the schemaName must be a reference identifier
     * @param termschemaNames
     * @return
     */
    public EventModel withSchemaName(String termschemaNames) {
        super.put(SchemaName, termschemaNames);
        return this;
    }
    
    /**
     * the type of event, must be a reference
     * @param termschemaNames
     * @return
     */
    public EventModel withEventType(String eventType) {
        super.put(EventType, eventType);
        return this;
    }
    
    /**
     * the date of the event; this one will set both the time and timezone
     * @param date
     * @return
     */
    public EventModel withEventDate(Calendar date) {
        super.put(EventDate, date.getTime().getTime());
        super.put(EventTimeZone, date.getTimeZone());
        return this;
    }

    /**
     * the date of the event; this one will only set the time
     * @param date
     * @return
     */
    public EventModel withEventDate(Date date) {
        super.put(EventDate, date.getTime());
        return this;
    }

    /**
     * the date of the event in millisecond since Epoch
     * @param date
     * @return
     */
    public EventModel withEventDate(long date) {
        super.put(EventDate, date);
        return this;
    }

}
