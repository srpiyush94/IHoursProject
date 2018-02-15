package com.ihours.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihours.dao.NotificationDAO;
import com.ihours.model.Notification;


@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired 
	private NotificationDAO notificationDAO;
	
	public boolean addNotification(Notification notification) {
		// TODO Auto-generated method stub
		return notificationDAO.addNotification(notification);
	}

	public List<Notification> getNotification(int userId, int viewed) {
		// TODO Auto-generated method stub
		return notificationDAO.getNotification(userId, viewed);
	}

	public Notification updateNotification(int notificationId) {
		// TODO Auto-generated method stub
		return notificationDAO.updateNotification(notificationId);
	}

	public Notification getNotification(String notificationType, int notificationReferenceId) {
		// TODO Auto-generated method stub
		return notificationDAO.getNotification(notificationType, notificationReferenceId);
	}

	public boolean deleteNotification(Notification notification) {
		// TODO Auto-generated method stub
		return notificationDAO.deleteNotification(notification);
	}

}
