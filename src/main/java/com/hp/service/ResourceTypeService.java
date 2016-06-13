package com.hp.service;

import java.util.List;

import com.hp.dao.BaseDao;
import com.hp.model.ResourceType;


public interface ResourceTypeService extends BaseDao<ResourceType>{
	public List<ResourceType> findResourcetype();
}
