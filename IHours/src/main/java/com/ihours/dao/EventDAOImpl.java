package com.ihours.dao;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ihours.model.Event;



@SuppressWarnings("deprecation")
@Repository
public class EventDAOImpl implements EventDAO{
	
	private static Logger log = LoggerFactory.getLogger(EventDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public EventDAOImpl(SessionFactory sessionFactory) {
		log.debug("Event Session");
		this.sessionFactory=sessionFactory;
	}


	public boolean saveOrUpdate(Event event) {
	
			
			Session session=sessionFactory.openSession();
		
			Transaction tx=session.beginTransaction();
			session.saveOrUpdate(event);
			tx.commit();
			session.clear();
			session.close();	
			return true;
		}


	@Transactional
	public boolean delete(Event event) {
		log.debug("Starting of the method deleteEvent");
		try {
			sessionFactory.getCurrentSession().delete(event);
			log.debug("Ending of the method deleteEvent");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception occurred while deleting event");
			log.error(e.getMessage());
			return false;
		}
	}

	@Transactional
	public Event get(int id) {
		log.debug("Starting of the method getEvent");
		String hql = "from Event where id='"+ id+"'" ;
		@SuppressWarnings({ "rawtypes" })
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings({ "unchecked" })
		List<Event>list= query.list();
		
		if(list==null)
		{
			log.debug("getEvent is null");
			return null;
		}
		else
		{
			log.debug("List of the event");
			return list.get(0);
		}
	}

	@Transactional
	public List<Event> list() {
		log.debug("Starting of the method listEvent");
		Criteria c=sessionFactory.getCurrentSession().createCriteria(Event.class);
		@SuppressWarnings("unchecked")
		List<Event> list=c.list();
		log.debug("Ending of the method listEvent");
		return list;
	}

}