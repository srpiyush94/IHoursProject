package com.ihours.service;

import java.util.List;

import com.ihours.model.Forum;

public interface ForumService {
	
	public boolean saveOrUpdate(Forum forum);

	public boolean delete(Forum forum);

	public List<Forum> list();

	public Forum getforum(int id);

	public List<Forum> userlist();

	public Forum get(int id);

}
