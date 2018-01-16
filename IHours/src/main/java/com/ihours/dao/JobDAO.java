package com.ihours.dao;

import java.util.List;

import com.ihours.model.Job;

public interface JobDAO {

		public boolean saveOrUpdate(Job job);
		public List<Job> list();
		public boolean delete(Job job);

		
	}


