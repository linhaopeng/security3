package com.hp.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.hp.model.User;
import com.hp.utils.ConfigUtil;

public class UserHttpSessionAttributeListener implements HttpSessionAttributeListener {

	protected final Log logger = LogFactory.getLog(UserHttpSessionAttributeListener.class);
	private SecurityContext context = null;

	private final String springSecurityContext = "SPRING_SECURITY_CONTEXT";

	/**
	 * session添加
	 */
	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		// logger.info("attributeAdded---->" + event.getName() + event.getValue());
		// 当前session中有新的属性时触发 SPRING_SECURITY_CONTEXT 是spring存放的key
		if (event.getName().equals(springSecurityContext)) {
			context = SecurityContextHolder.getContext();
			User account = (User) context.getAuthentication().getPrincipal();
			event.getSession().setAttribute(ConfigUtil.getSessionInfoName(), account);
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		// 在注销的时候,security已经销毁的整个session
	}

	/**
	 * session替换
	 */
	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		if (event.getName().equals(springSecurityContext)) {
			// System.out.println("----session中更新登陆信息------");
			context = SecurityContextHolder.getContext();
			User account = (User) context.getAuthentication().getPrincipal();
			event.getSession().setAttribute(ConfigUtil.getSessionInfoName(), account);
		}
	}

}
