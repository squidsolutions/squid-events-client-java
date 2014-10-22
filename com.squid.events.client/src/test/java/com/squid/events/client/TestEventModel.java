package com.squid.events.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squid.events.model.ArticleModel;
import com.squid.events.model.EventModel;
import com.squid.events.model.RetrievalEvent;
import com.squid.events.model.RetrievalModel;
import com.squid.events.model.SearchEvent;
import com.squid.events.model.StartSessionEvent;

public class TestEventModel {
    
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
                .withClientIP("127.0.0.1")
                .withSessionID("1234")
                .withUserID("sergio")
                .withPageViewURL("http://something/somepage")
                .withServerIP("127.0.0.1");
            String test = jackson.writeValueAsString(event);
            System.out.println(test);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            ArticleModel article = new ArticleModel()
                .withContentType("article")
                .withDiscipline("science")
                // more properties here...
                ;
            EventModel event = new RetrievalEvent()
                .withContentOwnerID("myUniversity")
                .withContentType("article")
                .withDisplayFormat("PDF")
                .withEntitlement("myUnivertsity")
                .withContentReferenceArticle(article)
                .withClientIP("127.0.0.1")
                .withSessionID("1234")
                .withUserID("sergio")
                .withPageViewURL("http://something/somepage")
                .withServerIP("127.0.0.1");
            String test = jackson.writeValueAsString(event);
            System.out.println(test);
            EventModel check = jackson.readValue(test, EventModel.class);
            Object xxx = check.get(RetrievalModel.retrievContentReferenceArticle);
            Assert.assertNotNull(xxx);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // session event
        EventModel event = new StartSessionEvent()
        .withReferrerURL("https://google.com")
        .withUserAgent("chrome")
        .withAccountID("university")
        .withAuthenticationMethod("IP")
        .withSessionID("123")
        .withPageViewURL("http://myapp/landingpage")
        .withUserID("abc");
        // search event
        EventModel searchEvent = new SearchEvent()
            .withEngine("quick")
            .withFilters("format=pdf")
            .withResultCount(100)
            .withResultID("x1234")
            .withResultPage(1)
            .withTerms("some search terms")
            .withSessionID("123")
            .withUserID("abc");
    }
    

    @Test
    public void testResources() {
        int mb = 1024*1024;
        long mem = Runtime.getRuntime().freeMemory();
        long start = System.currentTimeMillis();
        List<EventModel> events = new ArrayList<EventModel>();
        int count = 10000;
        for (int i=0;i<count;i++) {
            events.add(createRetrievalEvent());
        }
        long stop = System.currentTimeMillis();
        long memX = Runtime.getRuntime().freeMemory();
        System.out.println("creating "+count+" events: "+(stop-start)+"ms");
        double latency = (stop-start)/(double)count;
        System.out.println("event creation latency = "+latency+"ms");
        System.out.println("memory: "+(mem-memX)/mb);
        //
        ObjectMapper jackson = new ObjectMapper();
        try {
            String test = jackson.writeValueAsString(events);
            System.out.println("number of bytes:"+test.getBytes().length+" bytes");
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public EventModel createRetrievalEvent() {
        ArticleModel article = new ArticleModel()
            .withContentType("article")
            .withDiscipline("science")
            // more properties here...
            ;
        EventModel event = new RetrievalEvent()
            .withContentOwnerID("myUniversity")
            .withContentType("article")
            .withDisplayFormat("PDF")
            .withEntitlement("myUnivertsity")
            .withContentReferenceArticle(article)
            .withClientIP("127.0.0.1")
            .withSessionID("1234")
            .withUserID("sergio")
            .withPageViewURL("http://something/somepage")
            .withServerIP("127.0.0.1");
        return event;
    }

}
