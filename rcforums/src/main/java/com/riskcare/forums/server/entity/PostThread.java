package com.riskcare.forums.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Columns;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity()
@Table(name="THREAD")
public class PostThread {
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="THREAD_ID", nullable=false)
    private Long id;
    
    @Lob
    @Column(name="DESCRIPTION", nullable=false)
    private String threadDesc;    
    
    @Column(name="CREATOR", nullable=false)
    private String threadCreator;

    @Columns(columns = {@Column(name="CREATED_DATE", nullable=false),
                        @Column(name="CREATED_TIMEZONE", nullable=false)})
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTimeWithZone")
    private DateTime threadCreatedDate;
    
    @Column(name="LAST_MODIFIED_BY")
    private String lastModifiedBy;

    @Columns(columns = {@Column(name="MODIFIED_DATE", nullable=false),
			@Column(name="MODIFIED_TIMEZONE", nullable=false)})
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTimeWithZone")
    private DateTime threadModifiedDate;
    
    @ManyToOne()
    @ForeignKey(name="PARENT_POST_FK")    
    @JoinColumn(name="PARENT_POST", nullable=false)
    private Post threadPost;
    
    @SuppressWarnings("unused")
    private PostThread() {
    	
    }
    
    public PostThread(String threadDesc, String threadCreator, DateTime threadCreatedDate, 
    		String lastModifiedBy, DateTime threadModifiedDate) {
        this.threadDesc = threadDesc;
        this.threadCreator = threadCreator;
        this.threadCreatedDate = threadCreatedDate;
        this.lastModifiedBy = lastModifiedBy;
        this.threadModifiedDate = threadModifiedDate;
    }


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getThreadDesc() {
		return threadDesc;
	}


	public void setThreadDesc(String threadDesc) {
		this.threadDesc = threadDesc;
	}


	public String getThreadCreator() {
		return threadCreator;
	}


	public void setThreadCreator(String threadCreator) {
		this.threadCreator = threadCreator;
	}


	public DateTime getThreadCreatedDate() {
		return threadCreatedDate;
	}


	public void setThreadCreatedDate(DateTime threadCreatedDate) {
		this.threadCreatedDate = threadCreatedDate;
	}


	public String getLastModifiedBy() {
		return lastModifiedBy;
	}


	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}


	public DateTime getThreadModifiedDate() {
		return threadModifiedDate;
	}


	public void setThreadModifiedDate(DateTime threadModifiedDate) {
		this.threadModifiedDate = threadModifiedDate;
	}


	public Post getThreadPost() {
		return threadPost;
	}


	public void setThreadPost(Post threadPost) {
		this.threadPost = threadPost;
	}
    
}
