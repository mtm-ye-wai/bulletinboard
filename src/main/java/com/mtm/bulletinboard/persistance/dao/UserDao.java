package com.mtm.bulletinboard.persistance.dao;

import java.util.List;
import java.util.Optional;

import com.mtm.bulletinboard.persistance.entity.User;

public interface UserDao {
	public List<User> getAllUsers();

	public User getUser(Integer userId);
	
	public Optional<User> getUserByName(String userName);

	public void storeUser(User user);

	public void updateUser(Integer userId, User user);

	public void destroyUser(Integer userId);
}
