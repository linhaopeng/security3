package com.hp.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.hp.base.BaseAction;
import com.hp.model.Account;

@ParentPackage("basePackage")
@Namespace("/account")
@Action(value = "accountAction")
public class AccountAction extends BaseAction<Account>{
	
	private static final long serialVersionUID = -3131702037102065793L;

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
		System.out.println(model.getLogin());
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
}
