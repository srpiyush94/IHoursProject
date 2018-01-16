package com.ihours.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

@Entity
@Table(name="C_BlogComment")
 public class BlogComment {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@ManyToOne	
	@JsonIgnore
	private BlogPost blogPost;
	@ManyToOne
	private UsersDetails commentedBy;
	private Date commentedOn;
	@Column(nullable=false)
	private String body;

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public UsersDetails getCommentedBy() {
	return commentedBy;
}
public void setCommentedBy(UsersDetails commentedBy) {
	this.commentedBy = commentedBy;
}
public Date getCommentedOn() {
	return commentedOn;
}
public void setCommentedOn(Date commentedOn) {
	this.commentedOn = commentedOn;
}
public BlogPost getBlogPost() {
	return blogPost;
}
public void setBlogPost(BlogPost blogPost) {
	this.blogPost = blogPost;
}
public String getBody() {
	return body;
}
public void setBody(String body) {
	this.body = body;
}


}