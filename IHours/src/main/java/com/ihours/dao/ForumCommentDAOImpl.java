package com.ihours.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ihours.model.ForumComment;



@Repository
public class ForumCommentDAOImpl implements ForumCommentDAO{
	
	private static Logger log = LoggerFactory.getLogger(ForumCommentDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public ForumCommentDAOImpl(SessionFactory sessionFactory) {
		log.debug("ForumComment Session");
		this.sessionFactory=sessionFactory;
	}

	public boolean saveOrUpdate(ForumComment forumcomment) {
		Session session=sessionFactory.openSession();
		
		Transaction tx=session.beginTransaction();
		session.saveOrUpdate(forumcomment);
		tx.commit();
		session.clear();
		session.close();	
		return true;
		
	}

	@Transactional
	public boolean delete(ForumComment forumcomment) {
		log.debug("Starting of the method delete ForumComment");
		try {
			sessionFactory.getCurrentSession().delete(forumcomment);
			log.debug("Ending of the method delete ForumComment");
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			log.error("Exception occurred while deleting ForumComment");
			log.error(e.getMessage());
			return false;
		}
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Transactional
	public List<ForumComment> list(int fid) {
		log.debug("Starting of the method list ForumComment");
		Criteria c=sessionFactory.getCurrentSession().createCriteria(ForumComment.class);
		c.add(Restrictions.eq("forumid", fid));
		List<ForumComment> list=c.list();
		log.debug("Ending of the method list ForumComment");
		return list;
	}
}
