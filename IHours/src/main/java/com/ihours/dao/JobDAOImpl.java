package com.ihours.dao;

import java.util.List;

import org.hibernate.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ihours.model.Job;

@SuppressWarnings("deprecation")
@Repository
public class JobDAOImpl implements JobDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	

		public boolean saveOrUpdate(Job job) {
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		session.saveOrUpdate(job);
		tx.commit();
		return true;
	}

@SuppressWarnings({ "rawtypes", "unchecked" })
@Transactional
	public List<Job> list() {
	Query query=	sessionFactory.openSession().createQuery("from Job");
	List<Job>jobList=query.list();
		return jobList;
	}

	public boolean delete(Job job) {
		// TODO Auto-generated method stub
		return false;
	}

}
