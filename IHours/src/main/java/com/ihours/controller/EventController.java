package com.ihours.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ihours.model.Event;
import com.ihours.model.UsersDetails;
import com.ihours.service.EventService;

@RestController
public class EventController {

	@Autowired
	private EventService eventService;

	@PostMapping("/createevent")
	public ResponseEntity<Event> createevent(@RequestBody Event event,HttpSession session){
		System.out.println("event");
		UsersDetails validUser = (UsersDetails) session.getAttribute("validUser");
		event.setUserid(validUser.getId());
		eventService.saveOrUpdate(event);
		return new ResponseEntity<Event>(event ,HttpStatus.OK);
	}
	
	@DeleteMapping(value="/deleteevent/{eventid}")
	public ResponseEntity<Event> deleteevent(Event event,@PathVariable("eventid") int eventid){
		Event event1=eventService.get(eventid);
		eventService.delete(event1);
		return new ResponseEntity<Event>(event,HttpStatus.OK);
	}
	@GetMapping(value="/listevent")
	public ResponseEntity<List<Event>> listevent(){
		System.out.println("list of events");
		List<Event> event=eventService.list();
		return new ResponseEntity<List<Event>>(event,HttpStatus.OK);
	}
}