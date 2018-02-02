package com.ihours.service;

import java.util.List;

import com.ihours.model.Friend;
import com.ihours.model.UsersDetails;

public interface FriendService {

	List<UsersDetails> suggestedUsersList(String username);
	public void addFriendRequest(Friend friend);
	List<Friend> pendingRequests(String username);
	public void updatePendingRequest(Friend friend);
	List<UsersDetails> listofFriends(String username);
	List<UsersDetails> listofMutualFriends(String loginId,String suggestedUsername);

}