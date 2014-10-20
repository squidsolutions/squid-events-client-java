package com.squid.events.client;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squid.events.model.ArticleModel;
import com.squid.events.model.EventModel;
import com.squid.events.model.RetrievalEvent;

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
            ArticleModel article = new ArticleModel();
            article.put(ArticleModel.articleContentType, "article");
            article.put(ArticleModel.articleDiscipline, "science");
            article.put(ArticleModel.articleJournal, "nature");
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
            Object xxx = check.get(RetrievalEvent.retrievContentReferenceArticle);
            Assert.assertNotNull(xxx);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
