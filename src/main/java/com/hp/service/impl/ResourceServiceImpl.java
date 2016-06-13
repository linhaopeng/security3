package com.hp.service.impl;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import com.hp.model.Resource;
import com.hp.service.ResourceService;

@Service("resourceService")
public class ResourceServiceImpl extends BaseServiceImpl<Resource> implements ResourceService {

	@Override
	public Resource getRoleByUrl(String url) {
		String hql = "FROM Resource r JOIN FETCH r.roles WHERE r.url=:url";
		Session session = getSession();// 无法使用currentSession
		return (Resource) session.createQuery(hql).setString("url", url).uniqueResult();
	}

}
