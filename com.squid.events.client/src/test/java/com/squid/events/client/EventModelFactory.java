package com.squid.events.client;

import com.squid.events.model.ArticleModel;
import com.squid.events.model.EventModel;
import com.squid.events.model.RetrievalEvent;
import com.squid.events.model.SearchEvent;
import com.squid.events.model.StartSessionEvent;

public class EventModelFactory {

    public EventModel createSessionEvent() {
        EventModel event = new StartSessionEvent ()
            .withBrowserUUID ("123")
            .withUserAgent ("chrome")
            .withAccountID ("myUniversity")
            .withAuthenticationMethod ("IPRANGE")
            .withReferrerURL ("http://google.com")
            .withHttpReturnCode (202)
            .withPageViewURL ("http://myDomain.com/landing_page.html")
            .withSessionID ("1234")
            .withUserID ("Tom");
        return event;
    }

    public EventModel createSearchEvent() {
        EventModel event = new SearchEvent ()
            .withTerms("dotnet framework macos")
            .withFilters("filter1=value1;filter2=value2")
            .withResultCount(100)
            .withResultPage(1)
            .withResultID("search1")
            .withAccountID ("myUniversity")
            .withAuthenticationMethod ("IPRANGE")
            .withReferrerURL ("http://google.com")
            .withHttpReturnCode (202)
            .withPageViewURL ("http://myDomain.com/landing_page.html")
            .withSessionID ("1234")
            .withUserID ("Tom");
        return event;
    }
    
    public ArticleModel createArticleModel() {
        return 
            new ArticleModel()
            .withPublicationTitle("Advances in Database Technology — EDBT'98")
            .withReferenceSource("scopus")
            .withReferenceSourceType("web")
            .withLanguage("us")
            .withContentType("article")
            .withDOI("10.1007/BFb0101000")
            .withISBN("978-3-540-69709-1")
            .withISSN("0302-9743")
            .withDBID("springer");
    }
        
    public EventModel createRetrievalEvent() {
        EventModel event = new RetrievalEvent ()
            .withContentOwnerID("parentUniversity")
            .withEntitlement("demo")
            .withContentReferenceArticle(createArticleModel())
            .withDisplayFormat("PDF")
            .withSearchOriginID("search1")
            .withAccountID ("myUniversity")
            .withAuthenticationMethod ("IPRANGE")
            .withReferrerURL ("http://google.com")
            .withHttpReturnCode (202)
            .withPageViewURL ("http://myDomain.com/landing_page.html")
            .withSessionID ("1234")
            .withUserID ("Tom");
        return event;
    }
    
}
