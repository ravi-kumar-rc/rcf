package com.riskcare.forums.server.vo;

import java.io.Serializable;

import org.joda.time.DateTime;

public class CategoryVO implements Serializable, Comparable<CategoryVO> {

	private static final long serialVersionUID = 1L;
    
    private String categoryName;
    private String categoryDesc;    
    private DateTime categoryDate;
    private String categoryCreator;
    private String categoryParent; 
    
    public CategoryVO() {
    	
    }
    
    public CategoryVO(final String categoryName, final String categoryDesc, 
            final DateTime categoryDate, final String categoryCreator, final String categoryParent) {
        this.categoryName = categoryName;
        this.categoryDesc = categoryDesc;
        this.categoryDate = categoryDate;
        this.categoryCreator = categoryCreator;
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
    
    public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

	public void setCategoryDate(DateTime categoryDate) {
		this.categoryDate = categoryDate;
	}

	public void setCategoryCreator(String categoryCreator) {
		this.categoryCreator = categoryCreator;
	}

	public void setCategoryParent(String categoryParent) {
		this.categoryParent = categoryParent;
	}

	@Override
	public int compareTo(CategoryVO category) {
		return this.getCategoryDate().compareTo(category.getCategoryDate());
	}
    
	@Override
	public String toString() {
		return categoryName;
	}
}
