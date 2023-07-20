package com.mtm.bulletinboard.bl.services;

import java.util.List;
import java.util.Optional;

import com.mtm.bulletinboard.bl.dto.PostDto;
import com.mtm.bulletinboard.persistance.entity.Post;

public interface PostService {
	public List<PostDto> getAllPosts();

	public PostDto getPost(Integer PostId);

	public void storePost(Post post);

	public void updatePost(Integer postId, Post post);

	public void destroyPost(Integer postId);
}
