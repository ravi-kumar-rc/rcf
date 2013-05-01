package com.riskcare.forums.server.vo;

import java.io.Serializable;

import org.joda.time.DateTime;

import com.riskcare.forums.server.entity.Category;

public class PostVO implements Serializable {

	private static final long serialVersionUID = 1L;

    private String postTitle;
    private String postDesc;    
    private String postCreator;
    private DateTime postCreatedDate;
    private String postLabel;
    private String lastModifiedBy;
    private DateTime postModifiedDate;
    private Category postCategory;
    private DateTime timeToDie;
    
	public PostVO() {
		
	}
	
	public PostVO(String postTitle, String postDesc, String postCreator, DateTime postCreatedDate,
			String postLabel, String lastModifiedBy, DateTime postModifiedDate, Category postCategory) {
		this.postTitle = postTitle;
		this.postDesc = postDesc;
		this.postCreator = postCreator;
		this.postCreatedDate = postCreatedDate;
		this.postLabel = postLabel;
		this.lastModifiedBy = lastModifiedBy;
		this.postModifiedDate = postModifiedDate;
		this.postCategory = postCategory;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostDesc() {
		return postDesc;
	}

	public void setPostDesc(String postDesc) {
		this.postDesc = postDesc;
	}

	public String getPostCreator() {
		return postCreator;
	}

	public void setPostCreator(String postCreator) {
		this.postCreator = postCreator;
	}

	public DateTime getPostCreatedDate() {
		return postCreatedDate;
	}

	public void setPostCreatedDate(DateTime postCreatedDate) {
		this.postCreatedDate = postCreatedDate;
	}

	public String getPostLabel() {
		return postLabel;
	}

	public void setPostLabel(String postLabel) {
		this.postLabel = postLabel;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public DateTime getPostModifiedDate() {
		return postModifiedDate;
	}

	public void setPostModifiedDate(DateTime postModifiedDate) {
		this.postModifiedDate = postModifiedDate;
	}

	public Category getPostCategory() {
		return postCategory;
	}

	public void setPostCategory(Category postCategory) {
		this.postCategory = postCategory;
	}

    public DateTime getTimeToDie() {
		return timeToDie;
	}

	public void setTimeToDie(DateTime timeToDie) {
		this.timeToDie = timeToDie;
	}
}
