package com.mtm.bulletinboard.bl.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mtm.bulletinboard.bl.dto.UserDto;
import com.mtm.bulletinboard.bl.dto.mapper.UserDtoMapper;
import com.mtm.bulletinboard.persistance.dao.UserDao;
import com.mtm.bulletinboard.persistance.entity.User;

@Service
public class UserServiceImpl implements UserService {

	private final UserDao userDao;
	private final UserDtoMapper userDtoMapper;

	public UserServiceImpl(UserDao userDao, UserDtoMapper userDtoMapper) {
		this.userDao = userDao;
		this.userDtoMapper = userDtoMapper;
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<UserDto> users = userDao.getAllUsers()
				.stream()
				.map(userDtoMapper)
				.collect(Collectors.toList());
		return users;
	}

	@Override
	public UserDto getUser(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateUser(Integer userId, User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroyUser(Integer userId) {
		// TODO Auto-generated method stub

	}

}
