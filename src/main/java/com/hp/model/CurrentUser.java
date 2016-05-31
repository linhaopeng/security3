package com.hp.model;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import com.hp.utils.ConfigUtil;


public class CurrentUser implements Serializable{
	
	private static final long serialVersionUID = 3269279910422934309L;
	
	public  Account getCurrentUser(HttpServletRequest request){
		return (Account) request.getSession().getAttribute(ConfigUtil.getSessionInfoName());
	}
}
