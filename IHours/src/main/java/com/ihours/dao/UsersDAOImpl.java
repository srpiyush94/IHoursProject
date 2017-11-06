package com.ihours.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ihours.model.UsersDetails;


@Repository
public class UsersDAOImpl implements UsersDAO {
	
	
	Logger Logger=LoggerFactory.getLogger(UsersDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public boolean saveOrUpdate(UsersDetails users) {
		
		Logger.info("save Operation started", users.getUser_id());
		Session session=sessionFactory.openSession();

		Transaction tx=session.beginTransaction();
		users.setEnable(true);
		users.setIsonline(false);
		session.saveOrUpdate(users);
		tx.commit();
		Logger.info("User object has been saved successfually", users.getUser_id());
	
		return true;
	}
	@Transactional
	public void delete(UsersDetails user) {
		sessionFactory.getCurrentSession().delete(user);
		
	}
		@SuppressWarnings("deprecation")
		@Transactional
		public UsersDetails getUser(String username) {
		Criteria c=sessionFactory.getCurrentSession().createCriteria(UsersDetails.class);
		c.add(Restrictions.eq("username", username));
		UsersDetails user=(UsersDetails)c.uniqueResult();
		return user;
	}
	
@SuppressWarnings("deprecation")
@Transactional
	public UsersDetails viewUser(int userid) {
		Criteria c=sessionFactory.getCurrentSession().createCriteria(UsersDetails.class);
		c.add(Restrictions.eq("userid", userid));
		UsersDetails user=(UsersDetails) c.uniqueResult();
		return user;
		
	}
	
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Transactional
	public List<UsersDetails> UserList() {
		Criteria c=sessionFactory.openSession().createCriteria(UsersDetails.class);
		List<UsersDetails> l = c.list();
		return l;
	}

	@SuppressWarnings({ "rawtypes", "deprecation" })
	@Transactional
		public UsersDetails login(UsersDetails user) {
		
		Session session=sessionFactory.openSession();

		Query query=session.createQuery("from UsersDetails where userName=? and password=? and enable=?");
	
		query.setString(0, user.getUserName()); //for assigning the values to parameter username
		query.setString(1, user.getPassword());
		query.setBoolean(2, true);
		UsersDetails validUsers=(UsersDetails)query.uniqueResult();
		session.close();
		System.out.println("Dao completed");
		return validUsers;
	}
	
	
		@Transactional
		public boolean isUsernameValid(String username) {
		List<UsersDetails> list = UserList();

		for (UsersDetails usersDetail : list) {
			if (usersDetail.getUserName().equals(username)) {
				return false;// invalid user
			}
		}
		return true;// valid user
	}
	

	@Transactional
		public boolean isEmailValid(String email) {
		List<UsersDetails> list = UserList();

		for (UsersDetails usersDetail : list) {
			if (usersDetail.getEmail().equals(email)){
				return false;// invalid user
			}
		}
		return true;// valid user
	}
	
	public UsersDetails updateUser(UsersDetails validUser)
	{
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		session.update(validUser);
		tx.commit();
		
		return validUser;
	}
	}

	
