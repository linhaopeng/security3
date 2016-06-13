package com.hp.service.impl;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.hp.dao.impl.BaseDaoImpl;
import com.hp.model.ResourceType;
import com.hp.service.ResourceTypeService;

@Service("resourceTypeService")
public class ResourceTypeServiceImpl extends BaseDaoImpl<ResourceType> implements ResourceTypeService {
	/**
	 * 为列表添加了缓存，查询一次过后，只要不重启服务，缓存一直存在，不需要再查询数据库了，节省了一些资源
	 * 
	 * 在ehcache.xml里面需要有对应的value
	 * 
	 * <cache name="resourcetypeServiceCache"
	 * 
	 * key是自己设定的一个ID，用来标识缓存
	 */
	@Cacheable(value = "resourcetypeServiceCache", key = "'resourcetypeServiceCache'")
	public List<ResourceType> findResourcetype() {
		return findAll();
	}
}
