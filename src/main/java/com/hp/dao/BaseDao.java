package com.hp.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 基础数据库操作类
 * 
 * 其他DAO继承此类获取常用的数据库操作方法，基本上你能用到的方法这里都有了，不需要自己建立DAO了。
 * 
 * @param <T>
 *            模型
 */
public interface BaseDao<T> {

	public Serializable save(T o);

	public void delete(T o);

	public void update(T o);

	public void saveOrUpdate(T o);

	public T get(int id);

	public T get(String hql);

	public T get(String hql, Map<String, Object> params);

	public List<T> findAll();

	public List<T> find(String hql);

	public List<T> find(String hql, Map<String, Object> params);

	public List<T> find(String hql, int page, int rows);

	public List<T> find(String hql, Map<String, Object> params, int page, int rows);

	public Long count(String hql);

	public Long count(String hql, Map<String, Object> params);
}
