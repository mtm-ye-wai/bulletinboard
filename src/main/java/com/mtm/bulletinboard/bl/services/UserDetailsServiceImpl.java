package com.mtm.bulletinboard.bl.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.mtm.bulletinboard.persistance.dao.UserDao;
import com.mtm.bulletinboard.persistance.entity.User;

//@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private UserDao userDao;

	public UserDetailsServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		User user = userDao.getUserByName(userName).orElseThrow(
				() -> new UsernameNotFoundException("Username not found"));

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		grantedAuthorities
				.add(new SimpleGrantedAuthority(user.getType().toString()));

		return new org.springframework.security.core.userdetails.User(
				user.getName(), user.getPassword(), grantedAuthorities);
	}
}
