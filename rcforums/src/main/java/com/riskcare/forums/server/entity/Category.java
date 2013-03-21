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
@Table(name="CATEGORY", uniqueConstraints={@UniqueConstraint(name="CATEGORY_NAME_DATE_UN", columnNames={"NAME","DATE"})})
public class Category {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="CATEGORY_ID", nullable=false)
    private Long id;
    
    @Column(name="NAME", nullable=false)
    private String categoryName;
    
    @Column(name="DESCRIPTION", nullable=false)
    private String categoryDesc;    
    
    @Columns(columns = {@Column(name="DATE", nullable=false),
                        @Column(name="TIMEZONE", nullable=false)})
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTimeWithZone")
    private DateTime categoryDate;
    
    @Column(name="CREATOR", nullable=false)
    private String categoryCreator;
    
    @Column(name="PARENT")
    private String categoryParent;

    private Category() {
        
    }
    
    public Category(String categoryName, String categoryDesc, DateTime categoryDate, String categoryCreator, String categoryParent) {
        this.categoryName = categoryName;
        this.categoryDesc = categoryDesc;
        this.categoryDate = categoryDate;
        this.categoryCreator = categoryCreator;
        this.categoryParent = categoryParent;
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

    public DateTime getCategoryDate() {
        return categoryDate;
    }

    public void setCategoryDate(DateTime categoryDate) {
        this.categoryDate = categoryDate;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
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

    public void setCategoryModerator(String categoryParent) {
        this.categoryParent = categoryParent;
    }

}
