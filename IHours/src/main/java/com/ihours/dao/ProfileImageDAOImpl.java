package com.ihours.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ihours.model.ProfileImage;

@Repository
public class ProfileImageDAOImpl implements ProfileImageDAO {

	@Autowired
	SessionFactory sessionFactory;

	public ProfileImageDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public ProfileImage getProfilePicture(String username) {
		Session session = sessionFactory.openSession();
		ProfileImage profileImage = (ProfileImage) session.get(ProfileImage.class, username);
		session.close();
		return profileImage;
	}

	public void saveProfilePicture(ProfileImage profileImage) {
		 Session session=sessionFactory.openSession();
		 Transaction tx=session.beginTransaction();
		 session.saveOrUpdate(profileImage);
		 tx.commit();
		 
		 return; 
		
	}

}
