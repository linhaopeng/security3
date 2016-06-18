package com.hp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.hp.model.Resource;
import com.hp.model.ResourceType;
import com.hp.service.ResourceService;

@Service("resourceService")
public class ResourceServiceImpl extends BaseServiceImpl<Resource> implements ResourceService {

	@Override
	public Resource getRoleByUrl(String url) {
		String hql = "FROM Resource r JOIN FETCH r.roles WHERE r.url=:url";
		Session session = getSession();// 无法使用currentSession
		return (Resource) session.createQuery(hql).setString("url", url).uniqueResult();
	}

	@Override
	public List<Resource> getResourceTree() {
		List<Resource> resources = find("from Resource s left join fetch s.resourcetype");
		List<Resource> resList = new ArrayList<Resource>();
		for (Resource resource : resources) {
			Resource res = new Resource();
			BeanUtils.copyProperties(resource, res, new String[]{"resource","roles","resources","resourcetype"});
			ResourceType resourceType = new ResourceType();
			BeanUtils.copyProperties(resource.getResourcetype(), resourceType, new String[]{"resources"});
			res.setResourcetype(resourceType);
			resList.add(res);
		}
		return resList;
	}

}
