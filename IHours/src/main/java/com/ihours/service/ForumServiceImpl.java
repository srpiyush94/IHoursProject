package com.ihours.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihours.dao.ForumDAO;
import com.ihours.model.Forum;

@Service
public class ForumServiceImpl implements ForumService {
	@Autowired
	private ForumDAO forumDAO;

	public boolean saveOrUpdate(Forum forum) {
		// TODO Auto-generated method stub
		return forumDAO.saveOrUpdate(forum);
	}

	public boolean delete(Forum forum) {
		// TODO Auto-generated method stub
		return forumDAO.delete(forum);
	}

	public List<Forum> list() {
		// TODO Auto-generated method stub
		return forumDAO.list();
	}

	public Forum getforum(int id) {
		// TODO Auto-generated method stub
		return forumDAO.getforum(id);
	}

	public List<Forum> userlist() {
		// TODO Auto-generated method stub
		return forumDAO.userlist()
				;
	}

	public Forum get(int id) {
		// TODO Auto-generated method stub
		return forumDAO.get(id);
	}

}
