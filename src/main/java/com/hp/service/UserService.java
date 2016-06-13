package com.hp.service;


import com.hp.dao.BaseDao;
import com.hp.model.User;
import com.hp.pageModel.DataGrid;

public interface UserService extends BaseDao<User> {
	public DataGrid<User> findByPage(User user, String sort, String order, Integer page, Integer rows);
}
