package com.ihours.dao;

import com.ihours.model.BlogPost;
import com.ihours.model.BlogPostLikes;
import com.ihours.model.UsersDetails;

public interface BlogPostLikesDAO {
	
	//select * from blogpostlikes where blogpost_id=? and user_username=?
			//if user already liked the post, 1 object
			//if user has not yet liked the post, null object
	public 	BlogPostLikes userLikes(BlogPost blogPost,UsersDetails user);

		//increment / decrement the number of likes
		//insert into blogpostlikes / delete from blogpostlikes 
		BlogPost updateLikes(BlogPost blogPost, UsersDetails user);

}
