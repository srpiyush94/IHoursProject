package com.ihours.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ihours.model.*;
import com.ihours.model.Error;
import com.ihours.service.FriendService;

@RestController
public class FriendController {
	
	@Autowired
	private FriendService friendService;
	
	@SuppressWarnings("unused")
	@RequestMapping(value="/suggestedusers",method=RequestMethod.GET)
	public ResponseEntity<?> getSuggestedUsers(HttpSession session)
	{
		UsersDetails validUser=(UsersDetails)session.getAttribute("validUser");
		String username=validUser.getUsername();
		if (validUser == null)
		{
			Error error = new Error(5, "UnAuthorized Access");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
		}
		
		List<UsersDetails> suggestedUsers=friendService.suggestedUsersList(username);
		return new ResponseEntity<List<UsersDetails>>(suggestedUsers,HttpStatus.OK);
	}
	
	@RequestMapping(value="/addfriendrequest/{toId}",method=RequestMethod.POST)
	public ResponseEntity<?> addFriendRequest(@PathVariable String toId,HttpSession session)
	{
		UsersDetails validUser=(UsersDetails)session.getAttribute("validUser");
		if (validUser == null)
		{
			Error error = new Error(5, "UnAuthorized Access");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
		}
		Friend friend=new Friend();
		friend.setFromId(validUser.getUsername());
		friend.setToId(toId);
		friend.setStatus('P');
		friendService.addFriendRequest(friend);
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}
	
	@RequestMapping(value="/pendingrequests",method=RequestMethod.GET)
	public ResponseEntity<?> pendingRequests(HttpSession session)
	{
		UsersDetails validUser=(UsersDetails)session.getAttribute("validUser");
		if (validUser == null)
		{
			Error error = new Error(5, "UnAuthorized Access");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
		}
		
	
		List<Friend> pendingRequests=friendService.pendingRequests(validUser.getUsername());
		return new ResponseEntity<List<Friend>>(pendingRequests,HttpStatus.OK);		
	}
	
	@RequestMapping(value="/updatependingrequest",method=RequestMethod.PUT)
	public ResponseEntity<?> updatePendingRequest(@RequestBody Friend friend,HttpSession session)
	{
		UsersDetails validUser=(UsersDetails)session.getAttribute("validUser");
		if (validUser == null)
		{
			Error error = new Error(5, "UnAuthorized Access");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
		}
		friendService.updatePendingRequest(friend); //updated status[a/r]
		return new ResponseEntity<Void>(HttpStatus.OK);		
	}
	
	@RequestMapping(value="/getfriends",method=RequestMethod.GET)
	public ResponseEntity<?> getListofFriends(HttpSession session)
	{
		UsersDetails validUser=(UsersDetails)session.getAttribute("validUser");
		if (validUser == null)
		{
			Error error = new Error(5, "UnAuthorized Access");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
		}
		List<UsersDetails> friends=friendService.listofFriends(validUser.getUsername());
		return new ResponseEntity<List<UsersDetails>>(friends,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/mutualfriends/{toId}",method=RequestMethod.GET)
	public ResponseEntity<?> getListofMutualFriends(@PathVariable String toId ,HttpSession session)
	{
		UsersDetails validUser=(UsersDetails)session.getAttribute("validUser");
	
		List<UsersDetails> mutualfriends=friendService.listofMutualFriends(validUser.getUsername(), toId);
		return new ResponseEntity<List<UsersDetails>>(mutualfriends,HttpStatus.OK);
		
	}
}


