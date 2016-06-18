package com.hp.action;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hp.model.ResourceType;
import com.hp.service.ResourceTypeService;

@Controller
@RequestMapping("/resourceType")
public class ResourceTypeAction extends BaseAction {
	
	@Autowired
	private ResourceTypeService resourceTypeService;
	
	@RequestMapping("/getResourcetypeList")
	@ResponseBody
	public List<ResourceType> getResourcetypeList(){
		List<ResourceType> findResourcetype = resourceTypeService.findResourcetype();
		return findResourcetype;
	}
}
