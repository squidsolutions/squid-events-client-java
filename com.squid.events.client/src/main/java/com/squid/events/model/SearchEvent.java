package com.squid.events.model;

/**
 * This event is created when a user performs a search in the application. 
 * It allows to define the search parameter and also to collect information regarding the search results.
 *
 * This event includes properties from the following models:
 *  <li>Search Model
 *  <li>Account Model
 *  <li>Usage Model
 *  
 * @author sergefantino
 *
 */
public class SearchEvent extends SearchModel {

    private static final long serialVersionUID = 330813436827661455L;

    public SearchEvent() {
        super(SearchModel.searchEventType);
    }
    
}
