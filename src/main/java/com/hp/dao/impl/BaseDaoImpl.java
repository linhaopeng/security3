package com.hp.dao.impl;


import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hp.dao.BaseDao;

@Transactional//注解可以被继承，即对子类也有效
@SuppressWarnings("unchecked")
@Repository("baseDao")
@Lazy(true) //或者将BaseDaoImpl 设置成abstract
public class BaseDaoImpl<T> implements BaseDao<T> {

	private Class<?> clazz = null; // clazz中存储了子类当前操作实体类型

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public BaseDaoImpl() {
		// 如果子类调用当前构造方法,this代表的是子类对象
//		System.out.println(this);
//		System.out.println("获取父类信息:" + this.getClass().getSuperclass());
//		System.out.println("获取父类信息包括泛型信息:" + this.getClass().getGenericSuperclass());
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class<?>) type.getActualTypeArguments()[0];
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	protected Session openSession() {
		return sessionFactory.openSession();
	}

	public Serializable save(T o) {
		return this.getSession().save(o);
	}

	/**
	 * 根据id获取对象
	 */
	@Override
	public T get(int id) {
		return (T) this.getSession().get(clazz, id);
	}
	
	/**
	 * 根据hql获取对象
	 */
	@Override
	public T get(String hql) {
		Query q = this.getSession().createQuery(hql);
		List<T> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	/**
	 * 根据hql,参数获取对象
	 * @param hql
	 * @param params
	 */
	@Override
	public T get(String hql, Map<String, Object> params) {
		Query q = this.getSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		List<T> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	/**
	 * 删除
	 */
	@Override
	public void delete(T o) {
		this.getSession().delete(o);
	}

	/**
	 * 更新
	 */
	@Override
	public void update(T o) {
		this.getSession().update(o);
	}

	/**
	 * 更新或删除
	 */
	@Override
	public void saveOrUpdate(T o) {
		this.getSession().saveOrUpdate(o);
	}

	/**
	 * 查找所有
	 */
	@Override
	public List<T> findAll() {
		String hql = "from " + clazz.getSimpleName();
		Query q = this.getSession().createQuery(hql);
		return q.list();
	}

	/**
	 * 根据hql查找
	 */
	@Override
	public List<T> find(String hql) {
		Query q = this.getSession().createQuery(hql);
		return q.list();
	}

	/**
	 * 根据hql和参数查找
	 */
	@Override
	public List<T> find(String hql, Map<String, Object> params) {
		Query q = this.getSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.list();
	}

	/**
	 * 根据hql和参数分页查找
	 */
	@Override
	public List<T> find(String hql, Map<String, Object> params, int page, int rows) {
		Query q = this.getSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	/**
	 * 分页查找
	 */
	@Override
	public List<T> find(String hql, int page, int rows) {
		Query q = this.getSession().createQuery(hql);
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	/**
	 * 查找总数
	 */
	@Override
	public Long count(String hql) {
		Query q = this.getSession().createQuery(hql);
		return (Long) q.uniqueResult();
	}

	/**
	 * 根据条件查找总数
	 */
	@Override
	public Long count(String hql, Map<String, Object> params) {
		Query q = this.getSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return (Long) q.uniqueResult();
	}
}
