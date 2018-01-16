package com.ihours.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihours.dao.JobDAO;
import com.ihours.model.Job;

@Service
public class JobServiceImpl implements JobService {
	
	@Autowired
	
	private JobDAO jobDAO;

	public boolean saveOrUpdate(Job job) {
	
		return jobDAO.saveOrUpdate(job);
	}

	public List<Job> list() {
		
		return jobDAO.list();
	}

	public boolean delete(Job job) {
	
		return jobDAO.delete(job);
	}

}
