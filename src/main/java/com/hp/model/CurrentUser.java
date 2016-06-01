package com.hp.model;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import com.hp.utils.ConfigUtil;


public class CurrentUser implements Serializable{
	
	private static final long serialVersionUID = 3269279910422934309L;
	
	public  User getCurrentUser(HttpServletRequest request){
		return (User) request.getSession().getAttribute(ConfigUtil.getSessionInfoName());
	}
}
