package com.ihours.service;

import java.util.List;

import com.ihours.model.Job;

public interface JobService {
	
	public boolean saveOrUpdate(Job job);
	public List<Job> list();
	public boolean delete(Job job);

}
