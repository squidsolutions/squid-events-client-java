package com.squid.events.client;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squid.events.model.EventModel;
import com.squid.events.model.RetrievalEvent;
import com.squid.events.model.SearchEvent;

public class TestEventModel {
    
    @Test
    public void testJson() {
        //
        ObjectMapper jackson = new ObjectMapper();
        //
        try {
            EventModel event = new EventModel("xxx", "yyy");
            event.put("test", "toto");
            String test = jackson.writeValueAsString(event);
            System.out.println(test);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            EventModel event = new RetrievalEvent()
                .withContentOwnerID("myUniversity")
                .withContentType("article")
                .withDisplayFormat("PDF")
                .withEntitlement("myUnivertsity")
                .withServerIP("127.0.0.1")
                .withClientIP("127.0.0.1")
                .withSessionID("1234")
                .withUserID("sergio")
                .withPageViewURL("http://something/somepage");
            String test = jackson.writeValueAsString(event);
            System.out.println(test);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
