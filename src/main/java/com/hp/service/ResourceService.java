package com.hp.service;

import com.hp.dao.BaseDao;
import com.hp.model.Resource;

public interface ResourceService extends BaseDao<Resource>{
	public Resource getRoleByUrl(String url);
}
