package com.squid.events.model;

/**
 * define an article reference
 * 
 * @author sergefantino
 *
 */
public class ArticleModel extends EventModel {

    private static final long serialVersionUID = -3964785059754420054L;
    
    public static final String articleContentType = "art:contentType";
    
    public static final String articleLanguage = "art:language";
    
    public static final String articleDiscipline = "art:discipline";
    
    /**
     * example: article, book, journal, newspaper, abstract, ...
     */
    public static final String articleType = "art:type";
    
    /**
     * example: scopus, ebsco, google...
     */
    public static final String articleReferenceSource = "art:source";
    
    /**
     * only required if the articleReferenceSource data are not referenced
     */
    public static final String articleReferenceSourceType = "art:sourceType";
    
    /**
     * the journal, should be a reference ID
     * example: pediatrics, nature, ...
     */
    public static final String articleJournal = "art:journal";
    
    public static final String articlePublicationTitle = "art:pubTitle";
    
    public static final String articleEffectiveTitle = "art:effectiveTitle";
    
    public static final String articleDBID = "art:dbid";
    
    public static final String articleSSID = "art:ssid";
    
    public static final String articleISSN = "art:issn";
    
    public static final String articleISBN = "art:isbn";
    
    public static final String articleDOI = "art:doi";

}
