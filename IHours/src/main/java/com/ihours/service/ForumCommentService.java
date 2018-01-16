package com.ihours.service;

import java.util.List;

import com.ihours.model.ForumComment;

public interface ForumCommentService {
	public boolean saveOrUpdate(ForumComment forumcomment);

	public boolean delete(ForumComment forumcomment);

	public List<ForumComment> list(int fid);

}
