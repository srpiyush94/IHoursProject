package com.ihours.model;

import javax.persistence.*;

@Entity
@Table(name="c_blogpostlikes")
public class BlogPostLikes {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private int id;
@ManyToOne
private BlogPost blogPost;
@ManyToOne
private UsersDetails user;

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public BlogPost getBlogPost() {
	return blogPost;
}
public void setBlogPost(BlogPost blogPost) {
	this.blogPost = blogPost;
}
public UsersDetails getUser() {
	return user;
}
public void setUser(UsersDetails user) {
	this.user = user;
}
}