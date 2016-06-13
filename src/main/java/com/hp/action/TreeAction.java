package com.hp.action;


import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.hp.base.BaseAction;
import com.hp.model.Resource;

@ParentPackage("basePackage")
@Namespace("/tree")
@Action(value = "treeAction")
public class TreeAction extends BaseAction<Resource>{

	private static final long serialVersionUID = -8298755088038382099L;
	
	
	public void getTreeByUser(){
	}
	
	public String index(){
		return "main";
	}
	
	public String north(){
		return "north";
	}
	
	public String south(){
		return "south";
	}
	
}
