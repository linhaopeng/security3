package com.hp.action;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hp.model.Resource;
import com.hp.model.Role;
import com.hp.pageModel.DataGrid;
import com.hp.pageModel.ReturnJson;
import com.hp.service.ResourceService;
import com.hp.service.RoleService;
import com.hp.utils.HqlHelper;

@Controller
@RequestMapping("/role")
public class RoleAction extends BaseAction {

	@Autowired
	private RoleService roleService;
	@Autowired
	private ResourceService resourceService;

	@RequestMapping("/getList")
	@ResponseBody
	public DataGrid<Role> getList(Role role, String sort, String order, Integer page, Integer rows) {
		HqlHelper hqlHelper = new HqlHelper(Role.class, "r")//
				.addCondition(role.getName() != null, "r.name like", "name", "%" + role.getName() + "%")//
				.addOrder(sort, order);
		List<Role> roles = roleService.find(hqlHelper.getQueryListHql(), hqlHelper.getMap(), page, rows);
		Long count = roleService.count(hqlHelper.getQueryCountHql(), hqlHelper.getMap());
		DataGrid<Role> grid = new DataGrid<Role>();
		grid.setRows(roles);
		grid.setTotal(count);
		return grid;
	}

	@RequestMapping("save")
	@ResponseBody
	public ReturnJson save(Role role) {
		ReturnJson json = new ReturnJson("添加失败");
		try {
			roleService.save(role);
			json.setMsg("添加成功");
			json.setSuccess(true);
		} catch (Exception e) {
		}
		return json;
	}

	@RequestMapping("getById")
	@ResponseBody
	public Role getById(Role role) {
		return roleService.get(role.getId());
	}

	@RequestMapping("delete")
	@ResponseBody
	public ReturnJson delete(Role role) {
		ReturnJson json = new ReturnJson("删除失败");
		try {
			// SysRole sysRole = roleService.get(role.getId());
			roleService.delete2(role);
			json.setMsg("删除成功");
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 根据角色id获取角色已有权限
	 */
	@RequestMapping("getPrivilegeByRoleId")
	@ResponseBody
	public List<Role> getPrivilegeByRoleId(Role role) {
		String hql = "select distinct res from Role r join r.resources res where r.id=:id";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", role.getId());
		return roleService.find(hql, map);
	}

	/**
	 * 修改角色权限
	 */
	@RequestMapping("doGrantPrivilege")
	@ResponseBody
	public ReturnJson doGrantPrivilege(Role r, String ids) {
		ReturnJson json = new ReturnJson("修改失败");
		try {
			Role role = roleService.get(r.getId());
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
		return json;
	}

	@RequestMapping("update")
	@ResponseBody
	public ReturnJson update(Role r) {
		ReturnJson json = new ReturnJson("删除失败");
		try {
			Role role = roleService.get(r.getId());
			BeanUtils.copyProperties(role, role, new String[] { "createdatetime" });
			roleService.update(role);
			json.setMsg("修改成功");
			json.setSuccess(true);
		} catch (Exception e) {
		}
		return json;
	}

	@RequestMapping("/grantPrivilege")
	public String grantPrivilege() {
		return "/role/grantPrivilege";
	}
	
	@RequestMapping("/editUI")
	public String editUI(){
		return "/role/editUI";
	}
	
	@RequestMapping("/list")
	public String list(){
		return "/role/list";
	}
	
}
