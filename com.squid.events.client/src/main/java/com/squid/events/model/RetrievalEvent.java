package com.squid.events.model;

public class RetrievalEvent extends UsageEvent {

    private static final long serialVersionUID = 3263181299164044911L;
    
    public static final String retrievSchema = "retriev:pub_1.0";
    
    public static final String retrievEventType = "retriev";
    
    public static final String retrievSearchOriginID = "rt:searchOriginID";
    public static final String retrievContentType = "rt:contentType";
    public static final String retrievDisplayFormat = "rt:displayFormat";
    public static final String retrievContentOwner = "rt:contentOwner";
    public static final String retrievContentEntitlement = "rt:contentEntitlement";
    
    public RetrievalEvent() {
        super(retrievSchema,retrievEventType);
    }
    
    /**
     * the ID of the search that leads to the retrieval
     * @param ID
     * @return
     */
    public RetrievalEvent withSearchOriginID(String ID) {
        super.put(retrievSearchOriginID,ID);
        return this;
    }
    
    /**
     * the content type of the artifact retrieved
     * It should be a reference value.
     * example: ID of an image, video, article, journal…
     * @param ID
     * @return
     */
    public RetrievalEvent withContentType(String type) {
        super.put(retrievContentType,type);
        return this;
    }
    
    /**
     * the display format
     * It should be a reference value.
     * example: JPEG, HTML, ABSTRACT, PDF
     * @param ID
     * @return
     */
    public RetrievalEvent withDisplayFormat(String type) {
        super.put(retrievDisplayFormat,type);
        return this;
    }
    
    /**
     * the account ID that owns the content - must be a valid account ID
     * It should be a reference value.
     * 
     * @param ID
     * @return
     */
    public RetrievalEvent withContentOwnerID(String ID) {
        super.put(retrievContentOwner,ID);
        return this;
    }
    
    /**
     * the entitlement rule for providing access to that content
     * @param entitlement
     * @return
     */
    public RetrievalEvent withEntitlement(String entitlement) {
        super.put(retrievContentEntitlement,entitlement);
        return this;
    }

}
