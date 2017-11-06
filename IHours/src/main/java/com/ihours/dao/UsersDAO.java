package com.ihours.dao;

import java.util.List;

import com.ihours.model.UsersDetails;

public interface UsersDAO {
	
	public boolean saveOrUpdate(UsersDetails users);
	public UsersDetails updateUser(UsersDetails users);
	public void delete(UsersDetails user);
	public UsersDetails getUser(String username);
	public UsersDetails viewUser(int userid);
	public List<UsersDetails> UserList();
	public UsersDetails login(UsersDetails user);
	public boolean isUsernameValid(String username);
	public boolean isEmailValid(String email);
}
