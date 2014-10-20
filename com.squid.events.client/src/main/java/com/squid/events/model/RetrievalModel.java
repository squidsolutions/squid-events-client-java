package com.squid.events.model;

public class RetrievalModel extends AccountModel {

    private static final long serialVersionUID = 3263181299164044911L;
    
    public static final String retrievSchema = "retrieval:pub_1.0";
    
    public static final String retrievEventType = "display";
    
    public static final String retrievSearchOriginID = "rt:searchOriginID";
    
    /**
     * the reference of the content
     */
    public static final String retrievContentReferenceID = "rt:contentRefID";
    
    /**
     * if the content is not referenced, this allow to provide a ArticleModel description
     */
    public static final String retrievContentReferenceArticle = "rt:contentRefArticle";
    
    public static final String retrievContentType = "rt:contentType";
    
    public static final String retrievDisplayFormat = "rt:displayFormat";
    
    public static final String retrievContentOwner = "rt:contentOwner";
    
    public static final String retrievContentEntitlement = "rt:contentEntitlement";
    
    protected RetrievalModel(String schemaName, String eventType) {
        super(schemaName, eventType);
    }

    public RetrievalModel(String eventType) {
        super(retrievSchema,retrievEventType);
    }
    
    /**
     * the ID of the search that leads to the retrieval
     * @param ID
     * @return
     */
    public RetrievalModel withSearchOriginID(String ID) {
        super.put(retrievSearchOriginID,ID);
        return this;
    }
    
    public RetrievalModel withContentReferenceID(String ID) {
        super.put(retrievContentReferenceID,ID);
        return this;
    }
    
    public RetrievalModel withContentReferenceArticle(ArticleModel article) {
        super.put(retrievContentReferenceArticle,article);
        return this;
    }
    
    /**
     * the content type of the artifact retrieved
     * It should be a reference value.
     * example: ID of an image, video, article, journal…
     * @param ID
     * @return
     */
    public RetrievalModel withContentType(String type) {
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
    public RetrievalModel withDisplayFormat(String type) {
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
    public RetrievalModel withContentOwnerID(String ID) {
        super.put(retrievContentOwner,ID);
        return this;
    }
    
    /**
     * the entitlement rule for providing access to that content
     * @param entitlement
     * @return
     */
    public RetrievalModel withEntitlement(String entitlement) {
        super.put(retrievContentEntitlement,entitlement);
        return this;
    }

}
