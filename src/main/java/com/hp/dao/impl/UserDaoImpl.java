package com.hp.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.hp.dao.UserDao;
import com.hp.model.User;

/**
 * @author baojulin
 *
 */
@Component("userDao")
public class UserDaoImpl implements UserDao {

	@Resource
	private SessionFactory sessionFactory;

	/**
	 * 查询用户名和角色信息
	 */
	@Override
	public User getJoinRole(String login) {
		Session session = sessionFactory.getCurrentSession();
		return (User) session.createQuery("FROM User a LEFT JOIN FETCH a.roles WHERE a.login=:login").setString("login", login).uniqueResult();
	}

	public static void main(String[] args) {
		// 运行的时候，需要将getCurrentSession改为openSession();
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-*.xml");
		System.out.println(context);
		UserDao accountDao = (UserDao) context.getBean("accountDao");
		System.out.println(accountDao);
		User account = accountDao.getJoinRole("admin");
		System.out.println(account.getRoles().size());
	}
}
