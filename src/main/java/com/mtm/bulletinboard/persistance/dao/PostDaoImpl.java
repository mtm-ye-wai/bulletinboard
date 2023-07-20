package com.mtm.bulletinboard.persistance.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mtm.bulletinboard.persistance.entity.Post;

@Repository
public class PostDaoImpl implements PostDao {

	private final SessionFactory sessionFactory;

	public PostDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	@Override
	public List<Post> getAllPosts() {
		try (Session session = sessionFactory.openSession()) {
			String hql = "FROM Post WHERE deletedAt IS NULL";
			Query<Post> post = session.createQuery(hql, Post.class);
			System.out.println(post.list());
			return post.list();
		}
	}

	@Override
	public Post getPost(Integer postId) {
		try (Session session = sessionFactory.openSession()) {
			Post post = session.get(Post.class, postId);
			return post;
		}
	}

	@Transactional
	@Override
	public void storePost(Post post) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			try {
				post.setCreatedUserId(1); // TODO auth userId
				post.setUpdatedUserId(1); // TODO auth userId
				post.setCreatedAt(LocalDateTime.now());
				post.setUpdatedAt(LocalDateTime.now());
				session.persist(post);
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
			}
		}
	}

	@Override
	public void updatePost(Integer postId, Post post) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			try {
				Post existingPost = session.get(Post.class, postId);
				if (existingPost != null) {
					existingPost.setTitle(post.getTitle());
					existingPost.setDescription(post.getDescription());
					existingPost.setStatus(post.getStatus());
					existingPost.setUpdatedUserId(1); // TODO auth userId
					existingPost.setUpdatedAt(LocalDateTime.now());
					Post updatedPost = session.merge(existingPost);
					System.out.println(updatedPost);
					transaction.commit();
				}
			} catch (Exception e) {
				transaction.rollback();
			}
		}
	}

	@Override
	public void destroyPost(Integer postId) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			try {
				Post post = session.get(Post.class, postId);
				if (post != null) {
					post.setDeletedAt(LocalDateTime.now());
					session.merge(post);
					transaction.commit();
				}				
				
			} catch (Exception e) {
				transaction.rollback();
			}
		}

	}

}
