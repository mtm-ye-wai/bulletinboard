package com.mtm.bulletinboard.web.form.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.mtm.bulletinboard.persistance.entity.Post;
import com.mtm.bulletinboard.web.form.PostForm;

@Component
public class PostFormMapper implements Function<PostForm, Post>{

	@Override
	public Post apply(PostForm postForm) {
		Post post = new Post();
		post.setTitle(postForm.getTitle());
		post.setDescription(postForm.getDescription());
		post.setStatus(postForm.getStatus());
		return post;
	}

}
