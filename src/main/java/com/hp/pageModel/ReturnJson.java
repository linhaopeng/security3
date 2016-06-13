package com.hp.pageModel;

/**
 * 
 * JSON模型
 * 
 * 用户后台向前台返回的JSON对象
 * 
 */
public class ReturnJson implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 383378212673823217L;

	private boolean success = false;

	private String msg = "";

	private Object obj = null;
	
	public ReturnJson() {
		super();
	}

	public ReturnJson(String msg) {
		super();
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

}
