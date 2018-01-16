package com.ihours.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihours.dao.FriendDAO;
import com.ihours.model.Friend;
import com.ihours.model.UsersDetails;

@Service
public class FriendServiceImpl implements FriendService {
	
	@Autowired
	private FriendDAO friendDAO;

	public List<UsersDetails> suggestedUsersList(String username) {
		// TODO Auto-generated method stub
		return friendDAO.suggestedUsersList(username);
	}

	public void addFriendRequest(Friend friend) {
		friendDAO.addFriendRequest(friend);
		
	}

	public List<Friend> pendingRequests(String username) {
		// TODO Auto-generated method stub
		return friendDAO.pendingRequests(username);
	}

	public void updatePendingRequest(Friend friend) {
		friendDAO.updatePendingRequest(friend);
		
	}

	public List<UsersDetails> listofFriends(String username) {
		// TODO Auto-generated method stub
		return friendDAO.listofFriends(username);
	}

	public List<UsersDetails> listofMutualFriends(String loginId, String suggestedUsername) {
		// TODO Auto-generated method stub
		return friendDAO.listofMutualFriends(loginId, suggestedUsername);
	}

}
