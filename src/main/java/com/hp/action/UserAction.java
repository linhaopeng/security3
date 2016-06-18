package com.hp.action;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hp.model.Role;
import com.hp.model.User;
import com.hp.pageModel.DataGrid;
import com.hp.pageModel.ReturnJson;
import com.hp.service.RoleService;
import com.hp.service.UserService;
import com.hp.utils.HqlHelper;
import com.hp.utils.MD5Util;

@Controller
@RequestMapping("/user")
public class UserAction extends BaseAction {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	/**
	 * 保存用户
	 */
	@RequestMapping("save")
	@ResponseBody
	public ReturnJson save(HttpServletRequest request,User user) {
		ReturnJson json = new ReturnJson("添加失败");
		try {
			// TODO 判断登录名是否存在
			user.setPwd(MD5Util.md5(user.getPwd()));
			userService.save(user);
			json.setMsg("添加成功");
			json.setSuccess(true);
		} catch (Exception e) {
			deletePhoto(request,user);
		}
		return json;
	}

	/**
	 * 获取用户列表 ，分页+模糊查询
	 */
	@RequestMapping("getList")
	@ResponseBody
	public DataGrid<User> getList(User user,String sort,String order,Integer page,Integer rows) {
		return userService.findByPage(user, sort, order, page, rows);
	}

	/**
	 * 更新用户
	 */
	@RequestMapping("update")
	@ResponseBody
	public ReturnJson update(HttpServletRequest request,User u) {
		ReturnJson json = new ReturnJson("编辑失败");
		try {
			// TODO 判断登录名是否存在
			User user = userService.get(u.getId());
			deletePhoto(request,user); // 删除头像
			// 修改数据库数据
			user.setPwd(MD5Util.md5(user.getPwd()));
			BeanUtils.copyProperties(user, user, new String[] { "createdatetime","roles" });
			userService.update(user);
			json.setMsg("编辑成功");
			json.setSuccess(true);
		} catch (Exception e) {
		}
		return json;
	}

	/**
	 * 删除用户
	 */
	@RequestMapping("delete")
	@ResponseBody
	public ReturnJson delete(HttpServletRequest request,User u) {
		ReturnJson json = new ReturnJson("删除失败");
		try {
			User user = userService.get(u.getId());
			deletePhoto(request,user);// 删除头像
			userService.delete(user);
			json.setMsg("删除成功");
			json.setSuccess(true);
		} catch (Exception e) {
		}
		return json;
	}

	/**
	 * 删除头像
	 * 
	 * @param user
	 */
	
	private void deletePhoto(HttpServletRequest request,User user) {
		String webParentPath = new File(request.getSession().getServletContext().getRealPath("/")).getParent();// 当前WEB环境的上层目录
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
	@RequestMapping("getById")
	@ResponseBody
	public User getById(User user) {
		return userService.get(user.getId());
	}

	/**
	 * 查找所有角色
	 */
	@RequestMapping("getAllRole")
	@ResponseBody
	public List<Role> getAllRole() {
		return roleService.find(new HqlHelper(Role.class, "r").getQueryListHql());
	}

	/**
	 * 根据用户id查找该用户角色
	 */
	@RequestMapping("getRoleByUserId")
	@ResponseBody
	public List<Role> getRoleByUserId(User user) {
		String hql = "select distinct u.roles from User u join u.roles where u.id = :id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", user.getId());
		return roleService.find(hql, params);
	}

	/**
	 * 修改用户所属角色
	 */
	@RequestMapping("doGrantRole")
	@ResponseBody
	public ReturnJson doGrantRole(User u,String ids) {
		ReturnJson json = new ReturnJson("修改失败");
		try {
			User user = userService.get(u.getId());
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
		return json;
	}
	
	@RequestMapping("grantRole")
	public String grantRole(){
		return "/user/grantRole";
	}

	@RequestMapping("/editUI")
	public String editUI(){
		return "/user/editUI";
	}
	
	@RequestMapping("/list")
	public String list(){
		return "/user/list";
	}
}
