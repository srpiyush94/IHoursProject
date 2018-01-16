package com.ihours.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihours.dao.BlogPostDAO;
import com.ihours.model.BlogComment;
import com.ihours.model.BlogPost;


@Service
public class BlogPostServiceImpl implements BlogPostService {

	@Autowired
	private BlogPostDAO blogPostDAO;

	public boolean createBlogPost(BlogPost post) {
	
		return blogPostDAO.createBlogPost(post);
	}

	public List<BlogPost> list(int approved) {
	
		return blogPostDAO.list(approved);
	}

	public List<BlogPost> findLatest5Post() {
	
		return null;
	}

	public BlogPost findPostById(int id) {
	
		return blogPostDAO.findPostById(id);
	}

	public void updateBlogPost(BlogPost blogPost){
	
	 blogPostDAO.updateBlogPost(blogPost);
	}

	public void deleteById(int id) {
		blogPostDAO.deleteById(id);
		
	}

	public List<BlogComment> getBlogComments(int blogId) {
	
		return blogPostDAO.getBlogComments(blogId);
	}

	public boolean addBlogComment(BlogComment blogComment) {
	
	return	blogPostDAO.addBlogComment(blogComment);
	}
	
	
	
	
}