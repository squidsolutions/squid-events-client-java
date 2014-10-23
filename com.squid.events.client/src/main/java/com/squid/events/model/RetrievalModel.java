package com.squid.events.model;

/**
 * Properties to track retrieval events. It extends from the Account model.
 * @author sergefantino
 *
 */
public class RetrievalModel extends AccountModel {

    private static final long serialVersionUID = 3263181299164044911L;
    
    public static final String retrievalSchema = "retrieval:pub_1.0";
    
    public static final String retrievalDisplayEventType = "display";
    
    /**
     * identify the search that leads to that display, see SearchresultID
     */
    public static final String retrievalSearchOriginID = "rt:searchOriginID";
    
    /**
     * the reference of the content. Must be a valid reference in the meta-data source.
     */
    public static final String retrievalContentReferenceID = "rt:contentRefID";
    
    /**
     * if the content is not referenced, this allow to provide a ArticleModel description
     */
    public static final String retrievalContentReferenceArticle = "rt:contentRefArticle";
    
    /**
     * the content type of the artifact retrieved. It should be a reference value.
     */
    public static final String retrievalContentType = "rt:contentType";
    
    /**
     * reference the display format
     */
    public static final String retrievalDisplayFormat = "rt:displayFormat";
    
    /**
     * identify the accountID that owns the content for the actual viewer
     */
    public static final String retrievalContentOwner = "rt:contentOwner";
    
    /**
     * define the entitlement for that account/display
     */
    public static final String retrievalContentEntitlement = "rt:contentEntitlement";
    
    protected RetrievalModel(String schemaName, String eventType) {
        super(schemaName, eventType);
    }

    public RetrievalModel(String eventType) {
        super(retrievalSchema,eventType);
    }
    
    /**
     * identify the search that leads to that display, see SearchresultID
     * @param ID
     * @return
     */
    public RetrievalModel withSearchOriginID(String searchResultID) {
        super.put(retrievalSearchOriginID,searchResultID);
        return this;
    }
    
    /**
     * the reference of the content. Must be a valid reference in the meta-data source.
     * @param ID
     * @return
     */
    public RetrievalModel withContentReferenceID(String contentReferenceID) {
        super.put(retrievalContentReferenceID,contentReferenceID);
        return this;
    }
    
    /**
     * if the content is not referenced, this allow to provide a ArticleModel description
     * @param article
     * @return
     */
    public RetrievalModel withContentReferenceArticle(ArticleModel articleModel) {
        super.put(retrievalContentReferenceArticle,articleModel);
        return this;
    }
    
    /**
     * the content type of the artifact retrieved
     * example: image, video, article, journal…
     * @param contentType
     * @return
     */
    public RetrievalModel withContentType(String contentType) {
        super.put(retrievalContentType,contentType);
        return this;
    }
    
    /**
     * the display format value
     * example: JPEG, HTML, ABSTRACT, PDF
     * @param ID
     * @return
     */
    public RetrievalModel withDisplayFormat(String displayFormat) {
        super.put(retrievalDisplayFormat,displayFormat);
        return this;
    }
    
    /**
     * the account ID that owns the content.
     * It must be a valid reference in the meta-data source.
     * 
     * @param ID
     * @return
     */
    public RetrievalModel withContentOwnerID(String ID) {
        super.put(retrievalContentOwner,ID);
        return this;
    }
    
    /**
     * the entitlement rule for providing access to that content
     * @param entitlement
     * @return
     */
    public RetrievalModel withEntitlement(String entitlement) {
        super.put(retrievalContentEntitlement,entitlement);
        return this;
    }

}
