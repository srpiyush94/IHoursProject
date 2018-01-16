package com.ihours.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihours.dao.EventDAO;
import com.ihours.model.Event;

@Service
public class EventServiceImpl implements EventService {
	
	@Autowired
	private EventDAO eventDAO;

	public boolean saveOrUpdate(Event event) {
	
		return eventDAO.saveOrUpdate(event);
	}

	public boolean delete(Event event) {
		// TODO Auto-generated method stub
		return eventDAO.delete(event);
	}

	public Event get(int id) {
		// TODO Auto-generated method stub
		return eventDAO.get(id);
	}

	public List<Event> list() {
		// TODO Auto-generated method stub
		return eventDAO.list();
	}

}
