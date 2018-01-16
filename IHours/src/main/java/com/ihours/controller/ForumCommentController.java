package com.ihours.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ihours.model.ForumComment;
import com.ihours.model.UsersDetails;
import com.ihours.service.ForumCommentService;

@RestController
public class ForumCommentController {
	
	@Autowired
	private ForumCommentService forumCommentService;
	
	@PostMapping(value="/commentforum/{fid}")
	public ResponseEntity<ForumComment> forumcomment(@RequestBody ForumComment forumcomment,HttpSession session,@PathVariable("fid") int fid){
		UsersDetails validUser = (UsersDetails) session.getAttribute("validUser");
		forumcomment.setForumid(fid);
		forumcomment.setUserid(validUser.getId());
		forumcomment.setCommenttime(new Date());
		forumCommentService.saveOrUpdate(forumcomment);
		return new ResponseEntity<ForumComment>(forumcomment,HttpStatus.OK);
		
	}
	
	@GetMapping(value="/getforumcomment/{fid}")
	public ResponseEntity<List<ForumComment>> getcomment(@PathVariable("fid") int fid){
		List<ForumComment> comments =forumCommentService.list(fid);
		return new ResponseEntity<List<ForumComment>>(comments,HttpStatus.OK);
	}
}


