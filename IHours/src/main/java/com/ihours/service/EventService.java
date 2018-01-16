package com.ihours.service;

import java.util.List;

import com.ihours.model.Event;

public interface EventService {

	public boolean saveOrUpdate(Event event);

	public boolean delete(Event event);

	public Event get(int id);

	public List<Event> list();

}


