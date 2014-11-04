package com.squid.events.model;

import java.util.Collection;

import com.squid.events.commons.StringUtils;

/**
 * Properties to track search events. It extends from the Account model.
 * @author sergefantino
 *
 */
public class SearchModel extends AccountModel {
  
    private static final long serialVersionUID = -5194563972531281038L;
    
    public static final String searchSchema = "search:pub_1.0";
    
    /**
     * search event = result of a search query
     */
    public static final String searchEventType = "search";
    
    /**
     * Search term entered by the user
     */
    public static final String SearchTerms = "sx:terms";
    
    /**
     * Any set of key/value pairs that identify a filter & options (multiple choice supported) selected to filter the search results
     */
    public static final String SearchFilters = "sx:filters";
    
    /**
     * Type of search engine used to resolve the search
     * example: quick/basic, advanced, ...
     */
    public static final String SearchEngine = "sx:engine";
    
    /**
     * Total number of search results
     */
    public static final String SearchResultCount = "sx:resultCount";
    
    /**
     * Page number currently displayed. Should be 1 for the first result page, then 2 if the user click next. 
     * You can use the SearchResultID to group several pages within the same search.
     */
    public static final String SearchResultPage = "sx:resultPage";
    
    /**
     * 
     */
    public static final String SearchResultProducts = "sx:resultProducts";
    
    /**
     * UUID associated with the original search event. 
     * Can be used to link event initiated from the search result page, for example going to next page or reference retrieval.
     */
    public static final String SearchResultUUID = "sx:resultUUID";

    protected SearchModel(String schemaName, String eventType) {
        super(schemaName, eventType);
    }

    protected SearchModel(String eventType) {
        super(searchSchema, eventType);
    }
    
    /**
     * Search term entered by the user
     * @param terms
     * @return
     */
    public SearchModel withTerms(String terms) {
        super.put(SearchTerms, terms);
        return this;
    }
    
    /**
     * Search term entered by the user
     * @param terms
     * @return
     */
    public SearchModel withTerms(Collection<String> terms) {
        super.put(SearchTerms, StringUtils.listToString(terms, "", " ", ""));
        return this;
    }
    
    /**
     * Any set of key/value pairs that identify a filter & options (multiple choice supported) selected to filter the search results
     * @param filters
     * @return
     */
    public SearchModel withFilters(String filters) {
        super.put(SearchFilters, filters);
        return this;
    }
    
    /**
     * Type of search engine used to resolve the search
     * example: quick/basic, advanced, ...
     * @param engine
     * @return
     */
    public SearchModel withEngine(String engine) {
        super.put(SearchEngine, engine);
        return this;
    }
    
    /**
     * UUID associated with the original search event. 
     * Can be used to link event initiated from the search result page, for example going to next page or reference retrieval.
     * @param UUID
     * @return
     */
    public SearchModel withResultID(String UUID) {
        super.put(SearchResultUUID, UUID);
        return this;
    }
    
    /**
     * Total number of search results
     * @param count
     * @return
     */
    public SearchModel withResultCount(int count) {
        super.put(SearchResultCount, count);
        return this;
    }

    /**
     * Page number currently displayed. Should be 1 for the first result page, then 2 if the user click next. 
     * You can use the SearchResultID to group several pages within the same search.
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
