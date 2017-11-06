package com.ihours.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ihours.model.UsersDetails;
import com.ihours.service.UsersService;

@RestController
public class UsersController {

	@Autowired
	private UsersService usersService;

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody UsersDetails user) {
	
	
		if(!usersService.isUsernameValid(user.getUserName()))
				{
		

			Error error = new Error(user.getUserName()+"..username already exists,, please enter different username");
			return new ResponseEntity<Error>(error, HttpStatus.NOT_ACCEPTABLE);
		}
		
	 if (!usersService.isEmailValid(user.getEmail())) {
			Error error = new Error(user.getEmail()+"...Email address already exists,, please enter different email");
			return new ResponseEntity<Error>(error, HttpStatus.NOT_ACCEPTABLE);
		}

		boolean result = usersService.saveOrUpdate(user);
		if (result) {
			return new ResponseEntity<UsersDetails>(user, HttpStatus.OK);
		} else {
			Error error = new Error("unable to register user details");
			return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	/************************Login***********************************/
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody UsersDetails users,HttpSession session)
	{ 
	    System.out.println("Is Session New For" + users.getUserName() + session.isNew());
	    UsersDetails validUser=usersService.login(users);
	    if(validUser==null)

	    {
		    Error error=new Error("Invalid username and password.. please enter valid credentials");
		    return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}	   
	    else	
	    {
	        validUser.setIsonline(true);
		    validUser=usersService.updateUser(validUser);
		    session.setAttribute("user", validUser);
		    return new ResponseEntity<UsersDetails>(validUser,HttpStatus.OK);    
		}
	}
	/************************Logout ***********************************/
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public ResponseEntity<?> logout(HttpSession session)
	{ 
	  
	    UsersDetails validUser=(UsersDetails) session.getAttribute("user");
	    if(validUser==null)

	    {
	    	Error error=new Error("Unauthorized user");
		    return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}	   
	    else	
	    {
	        validUser.setIsonline(false);
	        usersService.updateUser(validUser);
		    session.removeAttribute("user");
		    session.invalidate();
		    return new ResponseEntity<UsersDetails>(validUser,HttpStatus.OK);    
		}
	}
	
	
	
	
}