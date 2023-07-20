package com.mtm.bulletinboard.persistance.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mtm.bulletinboard.persistance.entity.Post;
import com.mtm.bulletinboard.persistance.entity.User;

@Transactional(readOnly = true)
@Repository
public class UserDaoImpl implements UserDao {

	private final SessionFactory sessionFactory;

	public UserDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<User> getAllUsers() {

		Session session = sessionFactory.openSession();
//		try {
//			session.beginTransaction();
//			 Query<User> user = session.createQuery("FROM User", User.class);
//			session.getTransaction().commit();
//			System.out.println(user.list());
//			session.close();
//			return user.list();
//		} catch (Exception e) {
//			session.getTransaction().rollback();
//			throw e;
//		}
		 Query<User> user = session.createQuery("FROM User", User.class);
		 System.out.println(user.list());
		 return user.list();
	}

	@Override
	public User getUser(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Optional<User> getUserByName(String userName) {
		try (Session session = sessionFactory.openSession()) {
			 String hql = "FROM User WHERE name = :username";
	            Query<User> query = session.createQuery(hql, User.class);
	            query.setParameter("username", userName);
	            User user = query.uniqueResult();
	            return Optional.ofNullable(user);
		}
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
