package com.squid.events.model;

import java.util.Collection;
import java.util.List;

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

    /**
     * type of content that has been retrieved.
     * example: Dissertation, Newspapers, Book...
     * @param contentType
     * @return
     */
    public ArticleModel withContentType(String contentType) {
        super.put(articleContentType,contentType);
        return this;
    }
    
    /**
     * International code of the language in which the content is made.
     * @param language
     * @return
     */
    public ArticleModel withLanguage(String language) {
        super.put(articleLanguage,language);
        return this;
    }
    
    /**
     * Academic subject matter the content relates to
     * @param discipline
     * @return
     */
    public ArticleModel withDiscipline(String discipline) {
        super.put(articleDiscipline,discipline);
        return this;
    }

    /**
     * Academic subject matter the content relates to; define a list of values
     * @param discipline
     * @return
     */
    public ArticleModel withDiscipline(Collection<String> disciplines) {
        super.put(articleDiscipline,disciplines);
        return this;
    }

    /**
     * Academic subject matter the content relates to; define a list of values
     * @param discipline
     * @return
     */
    public ArticleModel withDiscipline(String[] disciplines) {
        super.put(articleDiscipline,disciplines);
        return this;
    }
    
    /**
     * Name of the source from which the reference was imported
     * @param source
     * @return
     */
    public ArticleModel withReferenceSource(String source) {
        super.put(articleReferenceSource,source);
        return this;
    }
    
    /**
     * Name of the source from which the reference was imported; define a list of values
     * @param source
     * @return
     */
    public ArticleModel withReferenceSource(Collection<String> sources) {
        super.put(articleReferenceSource,sources);
        return this;
    }
    
    /**
     * Name of the source from which the reference was imported; define a list of values
     * @param source
     * @return
     */
    public ArticleModel withReferenceSource(String[] sources) {
        super.put(articleReferenceSource,sources);
        return this;
    }
    
    /**
     * Name of category of grouping of reference sources
     * @param type
     * @return
     */
    public ArticleModel withReferenceSourceType(String type) {
        super.put(articleReferenceSourceType,type);
        return this;
    }
    
    /**
     * Name of category of grouping of reference sources; define a list of values
     * @param type
     * @return
     */
    public ArticleModel withReferenceSourceType(Collection<String> types) {
        super.put(articleReferenceSourceType,types);
        return this;
    }
    
    /**
     * Name of category of grouping of reference sources; define a list of values
     * @param type
     * @return
     */
    public ArticleModel withReferenceSourceType(String[] types) {
        super.put(articleReferenceSourceType,types);
        return this;
    }
    
    /**
     * Name of the journal the article was published in
     * @param title
     * @return
     */
    public ArticleModel withPublicationTitle(String title) {
        super.put(articlePublicationTitle,title);
        return this;
    }
    
    /**
     * Identification of the database the journal belongs to
     * @param dbid
     * @return
     */
    public ArticleModel withDBID(String dbid) {
        super.put(articleDBID,dbid);
        return this;
    }
    
    /**
     * Identification of the database the journal belongs to; define a list of values
     * @param dbid
     * @return
     */
    public ArticleModel withDBID(Collection<String> dbids) {
        super.put(articleDBID,dbids);
        return this;
    }
    
    /**
     * Identification of the database the journal belongs to; define a list of values
     * @param dbid
     * @return
     */
    public ArticleModel withDBID(String[] dbids) {
        super.put(articleDBID,dbids);
        return this;
    }
    
    /**
     * When applicable: ISSN if the article is published in a periodical
     * @param issn
     * @return
     */
    public ArticleModel withISSN(String issn) {
        super.put(articleISSN,issn);
        return this;
    }
    
    /**
     * When applicable: ISSN if the article is published in a periodical; define a list of values
     * @param issn
     * @return
     */
    public ArticleModel withISSN(Collection<String> issn) {
        super.put(articleISSN,issn);
        return this;
    }
    
    /**
     * When applicable: ISSN if the article is published in a periodical; define a list of values
     * @param issn
     * @return
     */
    public ArticleModel withISSN(String[] issn) {
        super.put(articleISSN,issn);
        return this;
    }
    
    /**
     * When applicable: ISBN if the article is published in a book
     * @param isbn
     * @return
     */
    public ArticleModel withISBN(String isbn) {
        super.put(articleISBN,isbn);
        return this;
    }
    
    /**
     * When applicable: ISBN if the article is published in a book; define a list of values
     * @param isbn
     * @return
     */
    public ArticleModel withISBN(List<String> isbn) {
        super.put(articleISBN,isbn);
        return this;
    }

    /**
     * When applicable: ISBN if the article is published in a book; define a list of values
     * @param isbn
     * @return
     */
    public ArticleModel withISBN(String[] isbn) {
        super.put(articleISBN,isbn);
        return this;
    }
    
    /**
     * When applicable: Digital Object Identifier of the article
     * @param doi
     * @return
     */
    public ArticleModel withDOI(String doi) {
        super.put(articleDOI,doi);
        return this;
    }
    
    /**
     * When applicable: Digital Object Identifier of the article; define a list of values
     * @param doi
     * @return
     */
    public ArticleModel withDOI(Collection<String> doi) {
        super.put(articleDOI,doi);
        return this;
    }
    
    /**
     * When applicable: Digital Object Identifier of the article; define a list of values
     * @param doi
     * @return
     */
    public ArticleModel withDOI(String[] doi) {
        super.put(articleDOI,doi);
        return this;
    }
    
    /**
     * Internal ID of the article in your database
     * @param customID
     * @return
     */
    public ArticleModel withCustomID(String customID) {
        super.put(articleCustomID,customID);
        return this;
    }

}
