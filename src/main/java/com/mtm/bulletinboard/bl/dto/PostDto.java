package com.mtm.bulletinboard.bl.dto;

import com.mtm.bulletinboard.common.enums.Status;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record PostDto(
		Integer id,
		String title,
		String description,
		Status status,
		String createdUserName,
		String updatedUserName,
		String createdAt,
		String updatedAt
		) {}
