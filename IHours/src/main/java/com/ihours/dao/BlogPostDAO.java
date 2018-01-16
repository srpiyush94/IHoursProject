package com.ihours.dao;

import java.util.List;

import com.ihours.model.BlogComment;
import com.ihours.model.BlogPost;

public interface BlogPostDAO {
	
	public boolean createBlogPost(BlogPost post);
	List<BlogPost> list(int approved);
	public List<BlogPost> findLatest5Post();
	public BlogPost findPostById(int id);
	void updateBlogPost(BlogPost blogPost);
	public void deleteById(int id);
	List<BlogComment> getBlogComments(int blogId);
	public boolean  addBlogComment(BlogComment blogComment);
	

}
