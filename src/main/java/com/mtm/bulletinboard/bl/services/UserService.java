package com.mtm.bulletinboard.bl.services;

import java.util.List;

import com.mtm.bulletinboard.bl.dto.UserDto;
import com.mtm.bulletinboard.persistance.entity.User;

public interface UserService {
	
	public List<UserDto> getAllUsers();

	public UserDto getUser(Integer userId);

	public void storeUser(User user);

	public void updateUser(Integer userId, User user);

	public void destroyUser(Integer userId);
}
