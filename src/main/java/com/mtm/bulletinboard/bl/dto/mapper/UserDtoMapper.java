package com.mtm.bulletinboard.bl.dto.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.mtm.bulletinboard.bl.dto.UserDto;
import com.mtm.bulletinboard.persistance.entity.User;

@Service
public class UserDtoMapper implements Function<User, UserDto>{

	@Override
	public UserDto apply(User user) {
		return new UserDto(
				user.getId(),
				user.getName(),
				user.getEmail(),
				user.getProfile(),
				user.getType(),
				user.getPhone(),
				user.getAddress(),
				user.getDob(),
				user.getCreatedUserId(),
				user.getUpdatedUserId(),
				user.getDeletedUserId()
				);
	}
}
