package com.hp.service.impl;


import org.springframework.stereotype.Service;

import com.hp.model.Role;
import com.hp.service.RoleService;

// 使用注解注入
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

	@Override
	public void delete2(Role o) {
		Role sysRole = super.get(o.getId());
		super.delete(sysRole);
	}

}