package com.riskcare.forums.server.vo;

import java.io.Serializable;

import org.joda.time.DateTime;

public class CategoryVO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String categoryName;
    private String categoryDesc;    
    private DateTime categoryDate;
    private String categoryCreator;
    private String categoryParent; 
    
    public CategoryVO(final String categoryName, final String categoryDesc, 
            final DateTime categoryDate, final String categoryCreator, final String categoryParent) {
        this.categoryName = categoryName;
        this.categoryDesc = categoryDesc;
        this.categoryDate = categoryDate;
        this.categoryParent = categoryParent;
    }
    
    public final String getCategoryName() {
        return categoryName;
    }

    public final String getCategoryDesc() {
        return categoryDesc;
    }

    public final DateTime getCategoryDate() {
        return categoryDate;
    }

    public final String getCategoryCreator() {
        return categoryCreator;
    }

    public final String getCategoryParent() {
        return categoryParent;
    }
    
}
