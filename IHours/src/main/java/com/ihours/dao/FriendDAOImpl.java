package com.ihours.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ihours.model.Friend;
import com.ihours.model.UsersDetails;

@Repository
public class FriendDAOImpl implements FriendDAO {

	public List<UsersDetails> listOfSuggestedUsers(String Username) {
		// TODO Auto-generated method stub
		return null;
	}

	public void friendRequest(String fromUsername, String toUsername) {
		// TODO Auto-generated method stub
		
	}

	public List<Friend> listOfPendingRequest(String loggedInUsername) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updatePendingRequest(String fromId, String toId, char status) {
		// TODO Auto-generated method stub
		
	}

	public List<Friend> listOfFriends(String Username) {
		// TODO Auto-generated method stub
		return null;
	}

}
