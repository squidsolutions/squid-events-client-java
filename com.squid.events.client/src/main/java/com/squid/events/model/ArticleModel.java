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
     * type of content that has been retrieved.
     * example: Dissertation, Newspapers, Book...
     */
    public static final String articleContentType = "art:contentType";
    
    /**
     * International code of the language in which the content is made.
     */
    public static final String articleLanguage = "art:language";
    
    /**
     * Academic subject matter the content relates to
     */
    public static final String articleDiscipline = "art:discipline";
    
    /**
     * Name of the source from which the reference was imported
     */
    public static final String articleReferenceSource = "art:source";
    
    /**
     * Name of category of grouping of reference sources
     */
    public static final String articleReferenceSourceType = "art:sourceType";
    
    /**
     * Name of the journal the article was published in
     */
    public static final String articlePublicationTitle = "art:pubTitle";
    
    /**
     * Identification of the database the journal belongs to
     */
    public static final String articleDBID = "art:dbid";
    
    /**
     * When applicable: ISSN if the article is published in a periodical
     */
    public static final String articleISSN = "art:issn";
    
    /**
     * When applicable: ISBN if the article is published in a book
     */
    public static final String articleISBN = "art:isbn";
    
    /**
     * When applicable: Digital Object Identifier of the article
     */
    public static final String articleDOI = "art:doi";
    
    /**
     * Internal ID of the article in your database
     */
    public static final String articleCustomID = "art:customID";
    
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
    
    public ArticleModel withPublicationTitle(String title) {
        super.put(articlePublicationTitle,title);
        return this;
    }
    
    public ArticleModel withDBID(String dbid) {
        super.put(articleDBID,dbid);
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
    
    public ArticleModel withCustomID(String customID) {
        super.put(articleCustomID,customID);
        return this;
    }

}
