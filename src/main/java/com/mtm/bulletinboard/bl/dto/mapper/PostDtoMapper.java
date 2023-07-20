package com.mtm.bulletinboard.bl.dto.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.mtm.bulletinboard.bl.dto.PostDto;
import com.mtm.bulletinboard.persistance.entity.Post;
import com.mtm.bulletinboard.utils.DateUtils;

@Service
public class PostDtoMapper implements Function<Post, PostDto> {

	@Override
	public PostDto apply(Post post) {
		return new PostDto(
				post.getId(),
				post.getTitle(),
				post.getDescription(),
				post.getStatus(),
				post.getCreatedUser().getName(),
				post.getUpdatedUser().getName(),
				DateUtils.formatDateTime(post.getCreatedAt()),
				DateUtils.formatDateTime(post.getUpdatedAt())
				);
	}
}
