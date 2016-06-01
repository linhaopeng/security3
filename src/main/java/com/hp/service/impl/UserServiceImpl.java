package com.hp.service.impl;

import javax.annotation.Resource;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hp.dao.UserDao;
import com.hp.service.UserService;

/**
 * 登录操作，需要实现UserDetailsService接口
 * 
 * @author baojulin
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {
	@Resource
	private UserDao userDao;

	/**
	 * 登录的时候，将用户信息存储到Account中
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userDao.getJoinRole(username);
	}
}
