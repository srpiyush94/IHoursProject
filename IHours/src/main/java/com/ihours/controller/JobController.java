package com.ihours.controller;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ihours.model.Error;
import com.ihours.model.Job;
import com.ihours.model.UsersDetails;
import com.ihours.service.JobService;

@RestController
public class JobController {
	
	@Autowired
	private JobService jobService;
	
	@PostMapping(value="/createjob")
	public ResponseEntity<?> createjob(@RequestBody Job job,HttpSession session){
		
		UsersDetails validUser = (UsersDetails) session.getAttribute("validUser");
		if (validUser == null)

		{
			Error error = new Error(8,"Unauthorized user,so you can't create blogs");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
		}
	
			boolean result = false;
			if(validUser.getRole().equals("admin"))
			{
			job.setUserid(validUser.getId());
			job.setDoc(new Date());
			jobService.saveOrUpdate(job);
			result	 = jobService.saveOrUpdate(job);
		}
		if (result) {
			return new ResponseEntity<Job>(job, HttpStatus.OK);
		} else {
			Error error = new Error(2,"unable to create blogs");
			return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value="/getjobs")
		public ResponseEntity<List<Job>> getjobs(){
	
		List<Job> jobs =jobService.list();
		return new ResponseEntity<List<Job>>(jobs,HttpStatus.OK);
	}
	
	@GetMapping(value="/jobslist")
	public ResponseEntity<List<Job>> jobslist(){
	
		List<Job> jobs =jobService.list();
		return new ResponseEntity<List<Job>>(jobs,HttpStatus.OK);
	}

}
