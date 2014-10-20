package com.squid.events.model;

/**
 * define an article reference
 * 
 * @author sergefantino
 *
 */
public class ArticleModel extends EventModel {

    private static final long serialVersionUID = -3964785059754420054L;
    
    public static final String articleSchemaName = "art:pub_1.0";

    /**
     * content type 
     * <p>
     * example: article, book, journal, newspaper, abstract, ...
     */
    public static final String articleContentType = "art:contentType";
    
    public static final String articleLanguage = "art:language";
    
    public static final String articleDiscipline = "art:discipline";
    
    /**
     * reference source, if the article was been sourced from external source.
     * <p>
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
    
    public ArticleModel() {
        super(articleSchemaName);
    }

    public ArticleModel withContentType(String contentType) {
        super.put(articleContentType,contentType);
        return this;
    }
    
    public ArticleModel withLanguage(String language) {
        super.put(articleLanguage,language);
        return this;
    }
    
    public ArticleModel withDiscipline(String discipline) {
        super.put(articleDiscipline,discipline);
        return this;
    }
    
    public ArticleModel withReferenceSource(String source) {
        super.put(articleReferenceSource,source);
        return this;
    }
    
    public ArticleModel withReferenceSourceType(String type) {
        super.put(articleReferenceSourceType,type);
        return this;
    }
    
    public ArticleModel withJournal(String journalID) {
        super.put(articleJournal,journalID);
        return this;
    }
    
    public ArticleModel withPublicationTitle(String title) {
        super.put(articlePublicationTitle,title);
        return this;
    }
    
    public ArticleModel withEffectiveTitle(String title) {
        super.put(articleEffectiveTitle,title);
        return this;
    }
    
    public ArticleModel withDBID(String dbid) {
        super.put(articleDBID,dbid);
        return this;
    }
    
    public ArticleModel withSSID(String ssid) {
        super.put(articleSSID,ssid);
        return this;
    }
    
    public ArticleModel withISSN(String issn) {
        super.put(articleISSN,issn);
        return this;
    }
    
    public ArticleModel withISBN(String isbn) {
        super.put(articleISBN,isbn);
        return this;
    }
    
    public ArticleModel withDOI(String doi) {
        super.put(articleDOI,doi);
        return this;
    }

}
