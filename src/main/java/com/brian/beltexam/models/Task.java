package com.brian.beltexam.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;



@Entity
@Table(name="tasks")
public class Task {
	  @Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private Long id;
	  
	  @NotEmpty
	  @Size(min=1)
	  private String content;
	  
	  @NotEmpty
	  private String assignee;
	  
	  @NotEmpty
	  private String priority;
	  
	  @Column(updatable=false)
	  private Date createdAt;
	  private Date updatedAt;
	  
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name="user_id")
	 private User user;
	  
	  public Task() {
		   }
	  
	  @PrePersist
	    protected void onCreate(){
	        this.createdAt = new Date();
	    }
	    @PreUpdate
	    protected void onUpdate(){
	        this.updatedAt = new Date();
	    }

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}
		
		  
		  public String getAssignee() {
			return assignee;
		}

		public void setAssignee(String assignee) {
			this.assignee = assignee;
		}

		public String getPriority() {
			return priority;
		}

		public void setPriority(String priority) {
			this.priority = priority;
		}

		public Date getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
		}

		public Date getUpdatedAt() {
			return updatedAt;
		}

		public void setUpdatedAt(Date updatedAt) {
			this.updatedAt = updatedAt;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}
	  
	  
}
