package com.hp.action;


import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hp.base.BaseAction;
import com.hp.model.ResourceType;
import com.hp.service.ResourceTypeService;

@ParentPackage("basePackage")
@Namespace("/resourceType")
@Action(value = "resourceTypeAction")
public class ResourceTypeAction extends BaseAction<ResourceType> {
	
	private static final long serialVersionUID = 6509771230455244010L;
	
	@Autowired
	private ResourceTypeService resourceTypeService;
	
	public void getResourcetypeList(){
		List<ResourceType> findResourcetype = resourceTypeService.findResourcetype();
		writeJson(findResourcetype);
	}
}
