package com.hp.action;


import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.hp.base.BaseAction;
import com.hp.model.Resource;
import com.hp.model.Role;
import com.hp.pageModel.DataGrid;
import com.hp.pageModel.ReturnJson;
import com.hp.service.ResourceService;
import com.hp.service.RoleService;
import com.hp.utils.HqlHelper;

@ParentPackage("basePackage")
@Namespace("/role")
@Action(value = "roleAction")
// 访问路径 /role/roleAction!方法.action
public class RoleAction extends BaseAction<Role> {
	
	@Autowired
	private RoleService roleService;
	@Autowired
	private ResourceService resourceService;
	
	public void getList() {
		HqlHelper hqlHelper = new HqlHelper(Role.class, "r")//
				.addCondition(model.getName() != null, "r.name like", "name", "%" + model.getName() + "%")//
				.addOrder(sort, order);
		List<Role> roles = roleService.find(hqlHelper.getQueryListHql(), hqlHelper.getMap(), page, rows);
		Long count = roleService.count(hqlHelper.getQueryCountHql(), hqlHelper.getMap());
		DataGrid<Role> grid = new DataGrid<Role>();
		grid.setRows(roles);
		grid.setTotal(count);
		writeJson(grid);
	}
	
	public String test(){
		return "test";
	}

	public void save() {
		ReturnJson json = new ReturnJson("添加失败");
		try {
			roleService.save(model);
			json.setMsg("添加成功");
			json.setSuccess(true);
		} catch (Exception e) {
		}
		writeJson(json);
	}

	public void getById() {
		Role sysRole = roleService.get(model.getId());
		writeJson(sysRole);
	}

	public void delete() {
		ReturnJson json = new ReturnJson("删除失败");
		try {
			//SysRole sysRole = roleService.get(model.getId());
			roleService.delete2(model);
			json.setMsg("删除成功");
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		writeJson(json);
	}

	/**
	 * 根据角色id获取角色已有权限
	 */
	public void getPrivilegeByRoleId() {
		String hql = "select distinct res from Role r join r.resources res where r.id=:id";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", model.getId());
		List<Role> resource = roleService.find(hql, map);
		writeJson(resource);
	}

	/**
	 * 修改角色权限
	 */
	public void doGrantPrivilege() {
		ReturnJson json = new ReturnJson("修改失败");
		try {
			Role role = roleService.get(model.getId());
			// 除去原有权限
			role.setResources(new HashSet<Resource>());
			for (String pid : ids.split(",")) {
				if (!StringUtils.isBlank(pid)) {
					// 查询出前台已经勾选的权限
					int intpid = Integer.parseInt(pid);
					Resource resource = resourceService.get(intpid);
					if (resource != null) {
						role.getResources().add(resource);
					}
				}
			}
			// 修改角色
			roleService.saveOrUpdate(role);
			json.setMsg("修改成功");
			json.setSuccess(true);
		} catch (Exception e) {
		}
		writeJson(json);
	}

	public void update() {
		ReturnJson json = new ReturnJson("删除失败");
		try {
			Role role = roleService.get(model.getId());
			BeanUtils.copyProperties(model, role, new String[] { "createdatetime" });
			roleService.update(role);
			json.setMsg("修改成功");
			json.setSuccess(true);
		} catch (Exception e) {
		}
		writeJson(json);
	}

	public String grantPrivilege(){
		return "grantPrivilege";
	}
}
