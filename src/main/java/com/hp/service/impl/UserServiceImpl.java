package com.hp.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hp.model.User;
import com.hp.pageModel.DataGrid;
import com.hp.service.UserService;
import com.hp.utils.HqlHelper;

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

	@Override
	public DataGrid<User> findByPage(User user, String sort, String order, Integer page, Integer rows) {
		DataGrid<User> grid = new DataGrid<User>();
		HqlHelper hqlHelper = new HqlHelper(User.class, "u")//
				.addCondition(user.getLoginname() != null, "u.loginname like", "loginname", "%" + user.getLoginname() + "%")// 登录名模糊查询
				.addCondition(user.getName() != null, "u.name like", "name", "%" + user.getName() + "%")// 姓名模糊查询
				.addCondition(user.getSex() != null, "u.sex =", "sex", user.getSex())// 过滤性别
				.addOrder("u." + sort, order);
		List<User> users = find(hqlHelper.getQueryListHql(), hqlHelper.getMap(), page, rows);
		Long count = count(hqlHelper.getQueryCountHql(), hqlHelper.getMap());
		// List<SysUser> users = userService.find("from SysUser u");
		grid.setRows(users);
		grid.setTotal(count);
		return grid;
	}
}
