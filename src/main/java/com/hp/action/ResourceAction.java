package com.hp.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hp.model.Resource;
import com.hp.model.User;
import com.hp.pageModel.ReturnJson;
import com.hp.pageModel.Tree;
import com.hp.service.ResourceService;
import com.hp.utils.ConfigUtil;

@Controller
@RequestMapping("/resource")
public class ResourceAction extends BaseAction {

	@Autowired
	private ResourceService resourceService;

	@RequestMapping("getTreeByUser")
	@ResponseBody
	public List<Tree> getTreeByUser(HttpServletRequest request) {
		User sysUser = (User) request.getSession().getAttribute(ConfigUtil.getSessionInfoName());
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
		return tree;
	}

	@RequestMapping("getTree")
	@ResponseBody
	public List<Tree> getTree() {
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
		return tree;
	}

	/**
	 * 获取资源树
	 */
	@RequestMapping("getResourceTree")
	@ResponseBody
	public List<Resource> getResourceTree() {
//		return resourceService.find("from Resource s left join fetch s.resourcetype");
		return  resourceService.getResourceTree();
	}
	
	/**
	 * 添加资源
	 */
	@RequestMapping("save")
	@ResponseBody
	public ReturnJson save(Resource resource) {
		ReturnJson json = new ReturnJson("添加失败");
		try {
			resourceService.save(resource);
			json.setMsg("添加成功");
			json.setSuccess(true);
		} catch (Exception e) {
		}
		return json;
	}

	/**
	 * 根据id获取资源
	 */
	@RequestMapping("/get")
	@ResponseBody
	public Resource get(Resource resource) {
		return resourceService.get(resource.getId());
	}

	/**
	 * 根据id获取资源
	 */
	@RequestMapping("/update")
	@ResponseBody
	public ReturnJson update(Resource resource) {
		// Resource Resource = resourceService.get(resource.getId());
		// writeJson(Resource);
		ReturnJson json = new ReturnJson("修改失败");
		if (resource.getId() != 0) {
			if (resource.getResource() != null && resource.getId() == resource.getResource().getId()) {
				json.setMsg("父资源不可以是自己！");
			} else {
				updateResource(resource);
				json.setMsg("修改成功");
				json.setSuccess(true);
			}
		}
		return json;
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
	
	@RequestMapping("/delete")
	@ResponseBody
	public ReturnJson delete(Resource resource){
		resourceService.delete(resourceService.get(resource.getId()));
		ReturnJson result = new ReturnJson();
		result.setMsg("删除成功");
		result.setSuccess(true);
		return result;
	}
	
	@RequestMapping("/north")
	public String north(){
		return "/resource/north";
	}
	
	@RequestMapping("/main")
	public String main(){
		return "/resource/main";
	}
	
	@RequestMapping("/south")
	public String south(){
		return "/resource/south";
	}
	
	@RequestMapping("/editUI")
	public String editUI(){
		return "/resource/editUI";
	}
	
	@RequestMapping("/list")
	public String list(){
		return "/resource/list";
	}
	
	
}
