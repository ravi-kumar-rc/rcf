package com.riskcare.forums.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;


@Entity()
@Table(name="CATEGORY", uniqueConstraints={@UniqueConstraint(name="CATEGORY_NAME_UN", columnNames={"NAME"})})
public class Category {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="CATEGORY_ID", nullable=false)
    private Long id;
    
    @Column(name="NAME", nullable=false)
    private String categoryName;
    
    @Column(name="DESCRIPTION")
    private String categoryDesc;    
    
    @Columns(columns = {@Column(name="CREATED_DATE", nullable=false),
                        @Column(name="CREATED_TIMEZONE", nullable=false)})
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTimeWithZone")
    private DateTime categoryCreatedDate;
    
    @Column(name="CREATOR", nullable=false)
    private String categoryCreator;
    
    @Column(name="PARENT")
    private String categoryParent;
    
    @Columns(columns = {@Column(name="MODIFIED_DATE", nullable=false),
    					@Column(name="MODIFIED_TIMEZONE", nullable=false)})
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTimeWithZone")
    private DateTime categoryModifiedDate;

    private Category() {
    	
    }
    
    public Category(String categoryName, String categoryDesc, DateTime categoryDate, String categoryCreator, String categoryParent, DateTime categoryModifiedDate) {
        this.categoryName = categoryName;
        this.categoryDesc = categoryDesc;
        this.categoryCreatedDate = categoryDate;
        this.categoryCreator = categoryCreator;
        this.categoryParent = categoryParent;
        this.categoryModifiedDate = categoryModifiedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }

    public DateTime getCategoryCreateDate() {
        return categoryCreatedDate;
    }

    public void setCategoryCreateDate(DateTime categoryCreatedDate) {
        this.categoryCreatedDate = categoryCreatedDate;
    }

    public String getCategoryCreator() {
        return categoryCreator;
    }

    public void setCategoryCreator(String categoryCreator) {
        this.categoryCreator = categoryCreator;
    }
    
    public String getCategoryParent() {
        return categoryParent;
    }

    public void setCategoryParent(String categoryParent) {
        this.categoryParent = categoryParent;
    }
    
    public DateTime getCategoryModifiedDate() {
    	return categoryModifiedDate;
    }
    
    public void setCategoryModifiedDate(DateTime categoryModifiedDate) {
    	this.categoryModifiedDate = categoryModifiedDate;
    }
}
