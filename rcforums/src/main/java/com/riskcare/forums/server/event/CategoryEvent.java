package com.riskcare.forums.server.event;

import org.joda.time.DateTime;
import org.springframework.context.ApplicationEvent;

public class CategoryEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L;

    private final String categoryName;
    private final String categoryDescription;
    private final DateTime categoryCreationDate;
    private final String categoryCreator;
    private final String categoryParent;
    
    public CategoryEvent(Object source, String categoryName, String categoryDescription, 
            DateTime categoryCreationDate, String categoryCreator, String categoryParent) {
        super(source);
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.categoryCreationDate = categoryCreationDate;
        this.categoryCreator = categoryCreator;
        this.categoryParent = categoryParent;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public DateTime getCategoryCreationDate() {
        return categoryCreationDate;
    }

    public String getCategoryCreator() {
        return categoryCreator;
    }

    public String getCategoryParent() {
        return categoryParent;
    }

}
