package com.hp.service;

import com.hp.dao.BaseDao;
import com.hp.model.Role;


public interface RoleService extends BaseDao<Role>{
	public void delete2(Role o);
}
