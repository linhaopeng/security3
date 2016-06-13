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
import com.hp.model.Resource;
import com.hp.model.User;
import com.hp.pageModel.ReturnJson;
import com.hp.pageModel.Tree;
import com.hp.service.ResourceService;
import com.hp.utils.ConfigUtil;

@ParentPackage("basePackage")
@Namespace("/resource")
@Action(value = "resourceAction")
public class ResourceAction extends BaseAction<Resource> {

	private static final long serialVersionUID = -8298755088038382099L;

	@Autowired
	private ResourceService resourceService;

	public void getTreeByUser() {
		User sysUser = (User) ServletActionContext.getRequest().getSession().getAttribute(ConfigUtil.getSessionInfoName());
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", 1);
		params.put("userId", sysUser.getId());
		List<Resource> resources = resourceService.find("select distinct s from Resource s join s.roles role join role.users user where s.resourcetype.id=:id and user.id = :userId", params);
		// "select distinct t from Syresource t join t.syroles role join role.syusers user"
		List<Tree> tree = new ArrayList<Tree>();
		for (Resource resource : resources) {
			Tree node = new Tree();
			// 复制属性值 这里只是复制了id跟pid
			BeanUtils.copyProperties(resource, node);

			node.setText(resource.getName());
			Map<String, String> attributes = new HashMap<String, String>();
			attributes.put("url", resource.getUrl());
			attributes.put("target", resource.getTarget());
			node.setAttributes(attributes);
			tree.add(node);
		}
		writeJson(tree);
	}

	public void getTree() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", 1);
		List<Resource> resources = resourceService.find("from Resource s where s.resourcetype.id=:id", params);
		List<Tree> tree = new ArrayList<Tree>();
		for (Resource resource : resources) {
			Tree node = new Tree();
			// 复制属性值 这里只是复制了id跟pid
			BeanUtils.copyProperties(resource, node);

			node.setText(resource.getName());
			Map<String, String> attributes = new HashMap<String, String>();
			attributes.put("url", resource.getUrl());
			attributes.put("target", resource.getTarget());
			node.setAttributes(attributes);
			tree.add(node);
		}
		writeJson(tree);
	}

	/**
	 * 获取资源树
	 */
	public void getResourceTree() {
		List<Resource> resources = resourceService.find("from Resource s left join fetch s.resourcetype");
		writeJson(resources);
	}

	/**
	 * 添加资源
	 */
	public void save() {
		ReturnJson json = new ReturnJson("添加失败");
		try {
			resourceService.save(model);
			json.setMsg("添加成功");
			json.setSuccess(true);
		} catch (Exception e) {
		}
		writeJson(json);
	}

	/**
	 * 根据id获取资源
	 */
	public void get() {
		Resource Resource = resourceService.get(model.getId());
		writeJson(Resource);
	}

	/**
	 * 根据id获取资源
	 */
	public void update() {
		// Resource Resource = resourceService.get(model.getId());
		// writeJson(Resource);
		ReturnJson json = new ReturnJson("修改失败");
		if (model.getId() != 0) {
			if (model.getResource() != null && model.getId() == model.getResource().getId()) {
				json.setMsg("父资源不可以是自己！");
			} else {
				updateResource(model);
				json.setMsg("修改成功");
				json.setSuccess(true);
			}
		}
		writeJson(json);
	}

	private void updateResource(Resource resource) {
		if (resource.getId() != 0) {
			Resource t = resourceService.get(resource.getId());
			BeanUtils.copyProperties(resource, t, new String[] { "createdatetime" });
			if (resource.getResource() != null) {// 说明前台选中上级节点。需要修改
				Resource pt = resourceService.get(resource.getResource().getId());// 上级节点
				t.setResource(pt);
			} else {
				t.setResource(null);
			}
			resourceService.update(t);
		}
	}
	
	public void delete(){
		resourceService.delete(resourceService.get(model.getId()));
		ReturnJson result = new ReturnJson();
		result.setMsg("删除成功");
		result.setSuccess(true);
		writeJson(result);
	}
	
	//----------------------跳转----------------
	
	public String main(){
		return "main";
	}
	public String south(){
		return "south";
	}
	public String north(){
		return "north";
	}
	

}
