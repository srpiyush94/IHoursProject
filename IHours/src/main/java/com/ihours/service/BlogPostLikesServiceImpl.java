package com.ihours.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihours.dao.BlogPostLikesDAO;
import com.ihours.model.BlogPost;
import com.ihours.model.BlogPostLikes;
import com.ihours.model.UsersDetails;

@Service
public class BlogPostLikesServiceImpl implements BlogPostLikesService {
	
	@Autowired
	
	private BlogPostLikesDAO blogPostLikesDAO;

	public BlogPostLikes userLikes(BlogPost blogPost, UsersDetails user) {
	
		return blogPostLikesDAO.userLikes(blogPost, user);
	}

	public BlogPost updateLikes(BlogPost blogPost, UsersDetails user) {
		
		return blogPostLikesDAO.updateLikes(blogPost, user);
	}

}
