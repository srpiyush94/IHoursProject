package com.ihours.dao;

import java.util.List;

import com.ihours.model.Friend;
import com.ihours.model.UsersDetails;

public interface FriendDAO {
	
	List<UsersDetails> listOfSuggestedUsers(String Username);
	void friendRequest(String fromUsername, String toUsername);
	List<Friend> listOfPendingRequest(String loggedInUsername);
	void updatePendingRequest(String fromId,String toId,char status);
	List<Friend> listOfFriends(String Username);

}
