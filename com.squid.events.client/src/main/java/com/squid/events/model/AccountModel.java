package com.squid.events.model;

/**
 * an event model for managing publishing specific properties
 * 
 * @author sergefantino
 *
 */
public class AccountModel extends UsageModel {

    private static final long serialVersionUID = 704975215370438019L;
    
    /**
     * this is an internal ID of the user account. 
     * It must allow to retrieve account reference data.
     */
    public static final String pubAccountID = "pub:accountID";
    
    /**
     * this is a reference code that define how the user gain access to the Account
     */
    public static final String pubAuthenticationMethod = "pub:authMethod";

    public AccountModel() {
    }
    
    public AccountModel(String schemaName, String eventType) {
        super(schemaName, eventType);
    }
    
    /**
     * this is an internal ID of the user account. 
     * It must allow to retrieve account reference data.
     * @param accountID
     * @return this
     */
    public AccountModel withAccountID(String accountID) {
        super.put(pubAccountID, accountID);
        return this;
    }

    /**
     * this is a reference code that define how the user gain access to the Account
     * @param methodID
     * @return this
     */
    public AccountModel withAuthenticationMethod(String methodID) {
        super.put(pubAuthenticationMethod, methodID);
        return this;
    }

}