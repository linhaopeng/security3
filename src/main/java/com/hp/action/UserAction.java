package com.hp.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hp.base.BaseAction;
import com.hp.model.User;
import com.hp.service.UserService;

@ParentPackage("basePackage")
@Namespace("/user")
@Action(value = "userAction")
public class UserAction extends BaseAction<User>{
	
	private static final long serialVersionUID = -3131702037102065793L;

	@Autowired
	private UserService userService;
	
	/**
	 * 测试访问地址以及对应关系。
	 * 
	 * http://localhost:9999/security3/account/accountAction!login.action
	 * 这个访问地址对应到  /WEB-INF/content/account/list.jsp
	 * 在  struts有相应的配置
	 * @return
	 */
	public String test() {
		//测试注入model
		System.out.println(model.getLoginname());
		ServletActionContext.getRequest().setAttribute("list", "哈哈哈");
		return "list";
	}
	
	public void test2(){
		//测试返回json
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("a", "sadfadf");
		map.put("aa", "sadfadf");
		writeJson(map);
	}
	
	public void save(){
		User user = new User();
		user.setLoginname("haha"+new Random().nextInt(10000));
		user.setPwd("e10adc3949ba59abbe56e057f20f883e");
		userService.save(user);
	}
	
	public void update(){
		User user = new User();
		user.setLoginname("haha"+new Random().nextInt(10000));
		user.setPwd("e10adc3949ba59abbe56e057f20f883e");
		user.setId(16);
		userService.saveOrUpdate(user);
	}
	
	public void findAll(){
		User user = new User();
		user.setLoginname("haha"+new Random().nextInt(10000));
		user.setPwd("e10adc3949ba59abbe56e057f20f883e");
		user.setId(16);
		List<User> findAll = userService.findAll();
		for (User user2 : findAll) {
			System.out.println(user2.getLoginname());
		}
	}
	
}
