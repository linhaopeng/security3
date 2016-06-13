package com.hp.security.manager;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import com.hp.model.Resource;
import com.hp.model.Role;
import com.hp.service.ResourceService;

@Service("securityMetadataSource")
public class SecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	@Autowired
	private ResourceService resourceService;
	
	/**
	 * 此方法就是通过url地址获取 角色信息的方法
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		// 获取当前的URL地址
		// System.out.println("object的类型为:" + object.getClass());
		FilterInvocation filterInvocation = (FilterInvocation) object;
		String url = filterInvocation.getRequestUrl();
		// System.out.println("访问的URL地址为(包括参数):" + url);
		url = filterInvocation.getRequest().getServletPath();
		Resource resource = resourceService.getRoleByUrl(url);
		if (resource != null ) {
			Set<Role> roles = resource.getRoles();
			Collection<ConfigAttribute> c = new HashSet<ConfigAttribute>();
			c.addAll(roles);// roles 实现了ConfigAttribute接口
			return c; // 将privilege中的roles改为Collection<ConfigAttribute> ，role需要实现ConfigAttribute接口
		} else {
			// 如果返回为null则说明此url地址不需要相应的角色就可以访问, 这样Security会放行
			return null;
		}
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// 需要返回true表示支持
		return true;
	}

}
