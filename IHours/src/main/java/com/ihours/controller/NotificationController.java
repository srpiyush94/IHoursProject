package com.ihours.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ihours.model.*;
import com.ihours.model.Error;
import com.ihours.service.NotificationService;


@Controller
public class NotificationController {
	
	
	@Autowired
	NotificationService notificationService;
	
	
	@RequestMapping(value="/getnotification/{viewed}")
	public ResponseEntity<?> getNotification(@PathVariable("viewed")int viewed,HttpSession session){

		Integer userId = (Integer) session.getAttribute("userId");
		
		if(userId == null){
			  
			    return new ResponseEntity<Error>(new Error(7,"User session details not found"),HttpStatus.UNAUTHORIZED);
		}	   
		else {
			List<Notification> notificationList = notificationService.getNotification(userId, viewed);
			
			if(notificationList == null){
			
				return new ResponseEntity<Error>(new Error(51,"Notification details not found"),HttpStatus.INTERNAL_SERVER_ERROR);
			}else{
				
				return new ResponseEntity<List<Notification>>(notificationList,HttpStatus.OK);
			}
		}
	}
	
	
	@RequestMapping(value="/updatenotification/{notificationId}")
	public ResponseEntity<?> updateNotification(@PathVariable("notificationId")int notificationId,HttpSession session){

		Integer userId = (Integer) session.getAttribute("userId");
		
		if(userId == null){
			  
			    return new ResponseEntity<Error>(new Error(7,"User session details not found"),HttpStatus.UNAUTHORIZED);
		}	   
		else {
			Notification notification = notificationService.updateNotification(notificationId);
			
			if(notification == null){
			
				return new ResponseEntity<Error>(new Error(51,"Notification details not found"),HttpStatus.INTERNAL_SERVER_ERROR);
			}else{
				
				return new ResponseEntity<Notification>(notification,HttpStatus.OK);
			}
		}
	}
	
	
}