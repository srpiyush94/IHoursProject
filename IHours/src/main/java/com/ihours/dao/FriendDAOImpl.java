package com.ihours.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.ihours.model.Friend;
import com.ihours.model.UsersDetails;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

	
	@SuppressWarnings("deprecation")
	@Repository
	@Transactional
	public class FriendDAOImpl implements FriendDAO{
		@Autowired
		private SessionFactory sessionFactory;


		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public List<UsersDetails> suggestedUsersList(String username) {

			Session session=sessionFactory.openSession();
			SQLQuery sqlQuery=session.createSQLQuery("select * from C_USERS where username in " 
								 					+"(select username from C_USERS where username!=? "
													+"minus "
													+"(select fromId from C_FRIEND where toId=?"
													+"union "
													+"select toId from C_FRIEND where fromId=? ))");
			sqlQuery.setString(0, username);
			sqlQuery.setString(1, username);
			sqlQuery.setString(2, username);
			sqlQuery.addEntity(UsersDetails.class);
			List<UsersDetails> suggestedUsersList=sqlQuery.list();
			session.close();
			return suggestedUsersList;
		}

		
		public void addFriendRequest(Friend friend) {
			Session session=sessionFactory.getCurrentSession();
			session.save(friend); //insert into friend values(fromId,toId,status)
			
		}

		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public List<Friend> pendingRequests(String username) {
			Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Friend where toId=? and status=?");
		query.setString(0, username);
		query.setCharacter(1,'P');
		List<Friend> pendingRequests=query.list();
			return pendingRequests;
		}

		public void updatePendingRequest(Friend friend) {
		Session session=sessionFactory.getCurrentSession();
		if(friend.getStatus()=='A')
			session.update(friend); //update friend set status='A' where id=?
		else
			session.delete(friend);  //delete friend where id=?
			
			
		}

		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public List<UsersDetails> listofFriends(String username) {
		Session session=sessionFactory.getCurrentSession();
		SQLQuery query1=session.createSQLQuery("select * from  C_USERS where username in " + "(select toId from C_FRIEND where fromId=? and status='A')");
		SQLQuery query2=session.createSQLQuery("select * from C_USERS where username in (select fromId from C_FRIEND where toId=? and status='A') ")	;
		query1.setString(0, username);
		query2.setString(0, username);
		query1.addEntity(UsersDetails.class);
		query2.addEntity(UsersDetails.class);
		List<UsersDetails> list1=query1.list();
		List<UsersDetails> list2=query2.list();
		list1.addAll(list2);
		return list1;
		}

		
		public List<UsersDetails> listofMutualFriends(String loginId, String suggestedUsername) {
			List<UsersDetails> friendlist1=listofFriends(loginId);
			List<UsersDetails> friendlist2=listofFriends(suggestedUsername);
			List<UsersDetails> mutualFriends=new ArrayList<UsersDetails>();
			for(UsersDetails user1:friendlist1)
			{
				for(UsersDetails user2:friendlist2)
				{
					if(user1.getUsername().equals(user2.getUsername()))
						mutualFriends.add(user1);
				}
			}
			return mutualFriends;
		}

		
		
		
		
		
		
	}