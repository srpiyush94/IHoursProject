package com.ihours.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihours.dao.ProfileImageDAO;
import com.ihours.model.ProfileImage;

@Service
public class ProfileImageServiceImpl implements ProfileImageService {
	
	@Autowired
	private ProfileImageDAO profileImageDAO;

	public ProfileImage getProfilePicture(String username) {
	
		return profileImageDAO.getProfilePicture(username);
	}

	public void saveProfilePicture(ProfileImage profileImage) {
		profileImageDAO.saveProfilePicture(profileImage);
		
	}

}
