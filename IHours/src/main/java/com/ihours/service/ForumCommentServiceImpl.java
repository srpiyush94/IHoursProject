package com.ihours.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ihours.dao.ForumCommentDAO;
import com.ihours.model.ForumComment;

@Repository
public class ForumCommentServiceImpl implements ForumCommentService {
	
	@Autowired
	
	private ForumCommentDAO commentDAO;

	public boolean saveOrUpdate(ForumComment forumcomment) {
		// TODO Auto-generated method stub
		return commentDAO.saveOrUpdate(forumcomment);
	}

	public boolean delete(ForumComment forumcomment) {
		// TODO Auto-generated method stub
		return commentDAO.delete(forumcomment);
	}

	public List<ForumComment> list(int fid) {
		// TODO Auto-generated method stub
		return commentDAO.list(fid);
	}

}
