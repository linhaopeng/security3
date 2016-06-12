package com.hp.action;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.hp.base.BaseAction;
import com.hp.model.Privilege;
import com.hp.model.User;
import com.hp.service.PrivilegeService;
import com.hp.utils.ConfigUtil;

@ParentPackage("basePackage")
@Namespace("/tree")
@Action(value = "treeAction")
public class TreeAction extends BaseAction<Privilege>{

	private static final long serialVersionUID = -8298755088038382099L;
	
	@Autowired
	private PrivilegeService privilegeService;
	
	public void getTreeByUser(){
//		User sysUser = (User) ServletActionContext.getRequest().getSession().getAttribute(ConfigUtil.getSessionInfoName());
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("id", 1);
//		params.put("userId", sysUser.getId());
//		List<Privilege> resources = privilegeService.find("select distinct s from SysResource s join s.roles role join role.users user where s.resourcetype.id=:id and user.id = :userId", params);
//		//"select distinct t from Syresource t join t.syroles role join role.syusers user"
//		List<Tree> tree = new ArrayList<Tree>();
//		for (SysResource resource : resources) {
//			Tree node = new Tree();
//			// 复制属性值 这里只是复制了id跟pid
//			BeanUtils.copyProperties(resource, node);
//
//			node.setText(resource.getName());
//			Map<String, String> attributes = new HashMap<String, String>();
//			attributes.put("url", resource.getUrl());
//			attributes.put("target", resource.getTarget());
//			node.setAttributes(attributes);
//			tree.add(node);
//		}
//		writeJson(tree);
	}
}
