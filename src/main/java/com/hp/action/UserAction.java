package com.hp.action;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.hp.base.BaseAction;
import com.hp.model.Role;
import com.hp.model.User;
import com.hp.pageModel.DataGrid;
import com.hp.pageModel.ReturnJson;
import com.hp.service.RoleService;
import com.hp.service.UserService;
import com.hp.utils.HqlHelper;

@ParentPackage("basePackage")
@Namespace("/user")
@Action(value = "userAction")
public class UserAction extends BaseAction<User> {

	private static final long serialVersionUID = -3131702037102065793L;

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	/**
	 * 测试访问地址以及对应关系。
	 * 
	 * http://localhost:9999/security3/account/accountAction!login.action 这个访问地址对应到 /WEB-INF/content/account/list.jsp 在 struts有相应的配置
	 * 
	 * @return
	 */
	public String test() {
		// 测试注入model
		System.out.println(model.getLoginname());
		ServletActionContext.getRequest().setAttribute("list", "哈哈哈");
		return "list";
	}

	/**
	 * 保存用户
	 */
	public void save() {
		ReturnJson json = new ReturnJson("添加失败");
		try {
			// TODO 判断登录名是否存在
			userService.save(model);
			json.setMsg("添加成功");
			json.setSuccess(true);
		} catch (Exception e) {
			deletePhoto(model);
		}
		writeJson(json);
	}

	/**
	 * 获取用户列表 ，分页+模糊查询
	 */
	public void list() {
		DataGrid<User> grid = userService.findByPage(model, sort, order, page, rows);
		writeJson(grid);
	}

	/**
	 * 更新用户
	 */
	public void update() {
		ReturnJson json = new ReturnJson("编辑失败");
		try {
			// TODO 判断登录名是否存在
			User user = userService.get(model.getId());
			deletePhoto(user); // 删除头像
			// 修改数据库数据
			BeanUtils.copyProperties(model, user, new String[] { "createdatetime" });
			userService.update(user);
			json.setMsg("编辑成功");
			json.setSuccess(true);
		} catch (Exception e) {
		}
		writeJson(json);
	}

	/**
	 * 删除用户
	 */
	public void delete() {
		ReturnJson json = new ReturnJson("删除失败");
		try {
			User user = userService.get(model.getId());
			deletePhoto(user);// 删除头像
			userService.delete(user);
			json.setMsg("删除成功");
			json.setSuccess(true);
		} catch (Exception e) {
		}
		writeJson(json);
	}

	/**
	 * 删除头像
	 * 
	 * @param user
	 */
	private void deletePhoto(User user) {
		String webParentPath = new File(getRequest().getSession().getServletContext().getRealPath("/")).getParent();// 当前WEB环境的上层目录
		if (null != user.getPhoto() && !"".equals(user.getPhoto())) {
			String realPath = webParentPath + user.getPhoto();// 文件上传到服务器的真实路径
			File f = new File(realPath);
			if (f.exists()) {
				f.delete();
			}
		}
	}

	/**
	 * 获取单个用户
	 */
	public void getById() {
		User user = userService.get(model.getId());
		writeJson(user);
	}

	/**
	 * 查找所有角色
	 */
	public void getAllRole() {
		List<Role> roles = roleService.find(new HqlHelper(Role.class, "r").getQueryListHql());
		writeJson(roles);
	}

	/**
	 * 根据用户id查找该用户角色
	 */
	public void getRoleByUserId() {
		String hql = "select distinct u.roles from User u join u.roles where u.id = :id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", model.getId());
		List<Role> roles = roleService.find(hql, params);
		writeJson(roles);
	}

	/**
	 * 修改用户所属角色
	 */
	public void grantRole() {
		ReturnJson json = new ReturnJson("修改失败");
		try {
			User user = userService.get(model.getId());
			if (user != null) {
				// 清空之前的角色
				user.setRoles(new HashSet<Role>());
				// 循环添加角色
				for (String roleId : ids.split(",")) {
					if (!StringUtils.isBlank(roleId)) {
						int rid = Integer.parseInt(roleId);
						Role role = roleService.get(rid);
						if (role != null) {
							user.getRoles().add(role);
						}
					}
				}
			}
			// 修改角色
			userService.saveOrUpdate(user);
			json.setMsg("修改成功");
			json.setSuccess(true);
		} catch (Exception e) {
		}
		writeJson(json);
	}

}
