package com.ihours.dao;

import java.util.List;

import com.ihours.model.Forum;

public interface ForumDAO {
	public boolean saveOrUpdate(Forum forum);

	public boolean delete(Forum forum);

	public List<Forum> list();

	public Forum getforum(int id);

	public List<Forum> userlist();

	public Forum get(int id);
}