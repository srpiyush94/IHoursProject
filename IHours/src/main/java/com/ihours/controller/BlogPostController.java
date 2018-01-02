package com.ihours.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ihours.model.*;
import com.ihours.model.Error;
import com.ihours.service.BlogPostService;

@RestController
public class BlogPostController {

	@Autowired
	private BlogPostService blogpostService;

	@Autowired
	HttpSession session;

	@PostMapping("/createBlog")
	public ResponseEntity<?> createBlogs(@RequestBody BlogPost post) {

		UsersDetails validUser = (UsersDetails) session.getAttribute("validUser");
		if (validUser == null)

		{
			Error error = new Error(3,"Unauthorized user,so you can't create blogs");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
		}
		post.setPostedOn(new Date());
		post.setCreatedBy(validUser);
		boolean result = blogpostService.createBlogPost(post);
		if (result) {
			return new ResponseEntity<BlogPost>(post, HttpStatus.OK);
		} else {
			Error error = new Error(2,"unable to create blogs");
			return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@RequestMapping(value="/listofblogs/{approved}",method=RequestMethod.GET)
	public ResponseEntity<?> getAllBlogs(@PathVariable int approved,HttpSession session)
	{
		UsersDetails users=(UsersDetails)session.getAttribute("validUser");
		if(users==null)
		{
			Error error=new Error(3,"UnAutorized user");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		List<BlogPost> blogPosts=blogpostService.list(approved);
		System.out.println(blogPosts.size());
		return new ResponseEntity<List<BlogPost>>(blogPosts,HttpStatus.OK);
	}
	@RequestMapping(value="/getblogpost/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> getBlogPost(@PathVariable int id,HttpSession session){
		UsersDetails users=(UsersDetails)session.getAttribute("validUser");
		if(users==null)
		{
			Error error=new Error(3,"UnAutorized user");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
	     BlogPost blogPost=blogpostService.findPostById(id);
		return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
		
	}

	@RequestMapping(value="/updateApproval",method=RequestMethod.PUT) 
	public ResponseEntity<?> updateBlogPost(@RequestBody BlogPost blogPost,HttpSession session){
		UsersDetails users=(UsersDetails)session.getAttribute("validUser");

		if(users==null)
		{
			Error error=new Error(3,"UnAutorized user");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	}
		blogpostService.updateBlogPost(blogPost);
		return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);

	}

	@RequestMapping(value="/addblogcomment",method=RequestMethod.POST) 
	public ResponseEntity<?> addBlogComment(@RequestBody BlogComment blogComment,HttpSession session){
		UsersDetails users=(UsersDetails)session.getAttribute("validUser");

		if(users==null)
		{
			Error error=new Error(3,"UnAutorized user");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	}
		try
		{
			//blogComment.setCommentedBy(users);
			blogComment.setCommentedOn(new Date());
			blogpostService.addBlogComment(blogComment);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		catch(Exception e)
		{
			Error error=new Error(4,"Unable to add comment"+e.getMessage());
			
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    	@RequestMapping(value="/getblogcomments/{blogPostId}",method=RequestMethod.GET)
	    public ResponseEntity<?> blogComments(@PathVariable int blogPostId,HttpSession session){
		UsersDetails user = (UsersDetails)session.getAttribute("validUser");
		if(user == null){
	    	Error error = new Error(3,"Unauthorized user, please login");
	    	return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	    }
		List<BlogComment> blogComments = blogpostService.getBlogComments(blogPostId);
		System.out.println(blogComments);
		return new ResponseEntity<List<BlogComment>>(blogComments,HttpStatus.OK);
	}
	}


