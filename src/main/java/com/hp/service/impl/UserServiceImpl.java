package com.hp.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hp.model.User;
import com.hp.service.UserService;

/**
 * 登录操作，需要实现UserDetailsService接口
 * 
 * @author baojulin
 *
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService, UserDetailsService {

	/**
	 * 登录的时候，将用户信息存储到user中
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String hql = "FROM User u LEFT JOIN FETCH u.roles WHERE u.loginname=:loginname";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("loginname", username);
		return get(hql, params);
		// return userDao.getJoinRole(username);
	}
}
