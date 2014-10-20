package com.squid.events.model;

import java.util.Collection;

public class SearchModel extends AccountModel {
  
    private static final long serialVersionUID = -5194563972531281038L;
    

    public static final String searchSchema = "search:pub_1.0";
    
    /**
     * recall event = result of a search query
     */
    public static final String searchEventType = "search";
    
    public static final String SearchTerms = "sx:terms";
    public static final String SearchFilters = "sx:filters";
    public static final String SearchEngine = "sx:engine";
    public static final String SearchResultCount = "sx:resultCount";
    public static final String SearchResultPage = "sx:resultPage";
    public static final String SearchResultProducts = "sx:resultProducts";
    public static final String SearchResultID = "sx:resultID";

    protected SearchModel(String schemaName, String eventType) {
        super(schemaName, eventType);
    }

    public SearchModel(String eventType) {
        super(searchSchema, eventType);
    }
    
    /**
     * the search query terms
     * @param terms
     * @return
     */
    public SearchModel withTerms(String terms) {
        super.put(SearchTerms, terms);
        return this;
    }
    
    /**
     * the search query filters
     * @param filters
     * @return
     */
    public SearchModel withFilters(String filters) {
        super.put(SearchFilters, filters);
        return this;
    }
    
    /**
     * the search engine used for the query. For example: quick, advanced, ...
     * @param engine
     * @return
     */
    public SearchModel withEngine(String engine) {
        super.put(SearchEngine, engine);
        return this;
    }
    
    /**
     * the ID associated with this search query, that can be used to refer to it
     * @param engine
     * @return
     */
    public SearchModel withResultID(String ID) {
        super.put(SearchResultID, ID);
        return this;
    }
    
    /**
     * the number of result returned by the search query
     * @param size
     * @return
     */
    public SearchModel withResultCount(int count) {
        super.put(SearchResultCount, count);
        return this;
    }

    /**
     * the page index being displayed by the search
     * @param size
     * @return
     */
    public SearchModel withResultPage(int pageNumber) {
        super.put(SearchResultPage , pageNumber);
        return this;
    }

    /**
     * the list of products associated with this search
     * @param size
     * @return
     */
    public SearchModel withResultProducts(Collection<String> products) {
        super.put(SearchResultProducts , products);
        return this;
    }

}
