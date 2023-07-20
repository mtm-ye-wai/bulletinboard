package com.mtm.bulletinboard.bl.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mtm.bulletinboard.bl.dto.PostDto;
import com.mtm.bulletinboard.bl.dto.mapper.PostDtoMapper;
import com.mtm.bulletinboard.persistance.dao.PostDao;
import com.mtm.bulletinboard.persistance.entity.Post;

@Service
public class PostServiceImpl implements PostService {

	private final PostDao postDao;
	private final PostDtoMapper postDtoMapper;

	public PostServiceImpl(PostDao postDao, PostDtoMapper postDtoMapper) {
		this.postDao = postDao;
		this.postDtoMapper = postDtoMapper;
	}

	@Override
	public List<PostDto> getAllPosts() {
		List<PostDto> posts = postDao.getAllPosts().stream().map(postDtoMapper)
				.collect(Collectors.toList());
		return posts;
	}

	@Override
	public PostDto getPost(Integer PostId) {
		PostDto post = postDtoMapper.apply(postDao.getPost(PostId));
		return post;
	}

	@Override
	public void storePost(Post post) {
		postDao.storePost(post);
	}

	@Override
	public void updatePost(Integer postId, Post post) {
		postDao.updatePost(postId, post);
	}

	@Override
	public void destroyPost(Integer postId) {
		postDao.destroyPost(postId);
	}

}
