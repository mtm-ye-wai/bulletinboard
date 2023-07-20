package com.mtm.bulletinboard.persistance.dao;

import java.util.List;
import java.util.Optional;

import com.mtm.bulletinboard.persistance.entity.Post;

public interface PostDao {
	public List<Post> getAllPosts();

	public Post getPost(Integer PostId);

	public void storePost(Post post);

	public void updatePost(Integer postId, Post post);

	public void destroyPost(Integer postId);
}
