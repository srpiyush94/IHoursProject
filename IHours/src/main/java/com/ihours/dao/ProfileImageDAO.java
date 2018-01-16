package com.ihours.dao;

import com.ihours.model.ProfileImage;

public interface ProfileImageDAO {
	
	public ProfileImage getProfilePicture(String username);
	public void saveProfilePicture(ProfileImage profileImage);

}
