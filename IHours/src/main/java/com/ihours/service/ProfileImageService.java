package com.ihours.service;

import com.ihours.model.ProfileImage;

public interface ProfileImageService {
	
	public ProfileImage getProfilePicture(String username);
	public void saveProfilePicture(ProfileImage profileImage);

}
