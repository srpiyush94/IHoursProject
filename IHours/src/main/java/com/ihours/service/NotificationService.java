package com.ihours.service;

import java.util.List;

import com.ihours.model.Notification;

public interface NotificationService {
	
	public boolean addNotification(Notification notification);

	public List<Notification> getNotification(int userId,int viewed);
	
	public Notification updateNotification(int notificationId);

	public Notification getNotification(String notificationType,int notificationReferenceId);

	public boolean deleteNotification(Notification notification);

}
