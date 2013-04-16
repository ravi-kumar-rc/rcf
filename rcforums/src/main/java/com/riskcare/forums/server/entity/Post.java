package com.riskcare.forums.server.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Columns;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity()
@Table(name="POST")
public class Post {
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="POST_ID", nullable=false)
    private Long id;
    
    @Column(name="TITLE", nullable=false)
    private String postTitle;
    
    @Lob
    @Column(name="DESCRIPTION", nullable=false)
    private String postDesc;    
    
    @Column(name="CREATOR", nullable=false)
    private String postCreator;

    @Columns(columns = {@Column(name="CREATED_DATE", nullable=false),
                        @Column(name="CREATED_TIMEZONE", nullable=false)})
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTimeWithZone")
    private DateTime postCreatedDate;
    
    @Column(name="LABELS")
    private String postLabel;
    
    @Column(name="LAST_MODIFIED_BY")
    private String lastModifiedBy;

    @Columns(columns = {@Column(name="MODIFIED_DATE", nullable=false),
			@Column(name="MODIFIED_TIMEZONE", nullable=false)})
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTimeWithZone")
    private DateTime postModifiedDate;
    
    @ManyToOne()
    @ForeignKey(name="PARENT_CATEGORY_FK")    
    @JoinColumn(name="PARENT_CATEGORY", nullable=false)
    private Category postCategory;
    
    @OneToMany(targetEntity=PostThread.class, mappedBy="threadPost",
    		cascade=CascadeType.ALL)
    List<PostThread> threads;
    
    @SuppressWarnings("unused")
    private Post() {
    	
    }
    
	public Post(String postTitle, String postDesc, String postCreator, DateTime postCreatedDate, String postLabel, 
    		String lastModifiedBy, DateTime postModifiedDate, Category postCategory) {
        this.postTitle = postTitle;
        this.postDesc = postDesc;
        this.postCreator = postCreator;
        this.postCreatedDate = postCreatedDate;
        this.postLabel = postLabel;
        this.lastModifiedBy = lastModifiedBy;
        this.postModifiedDate = postModifiedDate;
        this.postCategory = postCategory;
    }


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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
    
    public List<PostThread> getThreads() {
		return threads;
	}

	public void setThreads(List<PostThread> threads) {
		this.threads = threads;
	}
}
