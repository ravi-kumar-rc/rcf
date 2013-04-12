package com.riskcare.forums.server.vo;

import java.io.Serializable;

import org.joda.time.DateTime;

public class CategoryVO implements Serializable {

	private static final long serialVersionUID = 1L;
    
    private String categoryName;
    private String categoryDesc;    
    private DateTime categoryCreateDate;
    private String categoryCreator;
    private String categoryParent; 
    private DateTime categoryModifiedDate;
    
    public CategoryVO() {
    	
    }
    
    public CategoryVO(final String categoryName, final String categoryDesc, 
            final DateTime categoryDate, final String categoryCreator, final String categoryParent, final DateTime categoryModifiedDate) {
        this.categoryName = categoryName;
        this.categoryDesc = categoryDesc;
        this.categoryCreateDate = categoryDate;
        this.categoryCreator = categoryCreator;
        this.categoryParent = categoryParent;
        this.categoryModifiedDate = categoryModifiedDate;
    }
    
    public final String getCategoryName() {
        return categoryName;
    }

    public final String getCategoryDesc() {
        return categoryDesc;
    }

    public final DateTime getCategoryCreateDate() {
        return categoryCreateDate;
    }

    public final String getCategoryCreator() {
        return categoryCreator;
    }

    public final String getCategoryParent() {
        return categoryParent;
    }
    
    public final DateTime getCategoryModifiedDate() {
    	return categoryModifiedDate;
    }
    
    public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

	public void setCategoryCreateDate(DateTime categoryCreateDate) {
		this.categoryCreateDate = categoryCreateDate;
	}

	public void setCategoryCreator(String categoryCreator) {
		this.categoryCreator = categoryCreator;
	}

	public void setCategoryParent(String categoryParent) {
		this.categoryParent = categoryParent;
	}

	public void setCategoryModifiedDate(DateTime categoryModifiedDate) {
		this.categoryModifiedDate = categoryModifiedDate;
	}

	@Override
	public String toString() {
		return categoryName;
	}
}
