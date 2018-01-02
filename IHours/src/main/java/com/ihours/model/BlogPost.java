package com.ihours.model;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="C_BlogPost")
 public class BlogPost {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String title;
	@Lob
	private String description;
	@ManyToOne
	@JoinColumn(name="username")
	private UsersDetails createdBy;
	private Date postedOn;
	private boolean approved;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UsersDetails getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(UsersDetails createdBy) {
		this.createdBy = createdBy;
	}
	public Date getPostedOn() {
		return postedOn;
	}
	public void setPostedOn(Date postedOn) {
		this.postedOn = postedOn;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}		
}