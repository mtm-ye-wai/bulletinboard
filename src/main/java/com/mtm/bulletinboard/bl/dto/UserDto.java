package com.mtm.bulletinboard.bl.dto;

import java.time.LocalDate;

import com.mtm.bulletinboard.common.enums.Role;

public record UserDto(
		Integer id,
		String name,
		String email,
		String profile,
		Role type,
		String phone,
		String address,
		LocalDate dob,
		Integer createdUserId,
		Integer updatedUserId,
		Integer deletedUserId
		) {}
