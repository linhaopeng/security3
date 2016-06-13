package com.hp.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 用于辅助拼接生成HQL的工具类
 * 
 * @author tyg
 * 
 */
public class HqlHelper {
	private static final Logger logger = Logger.getLogger(HqlHelper.class);

	private String fromClause; // From子句，必须
	private String whereClause = ""; // Where子句，可选
	private String orderByClause = ""; // OrderBy子句，可选

	// private List<Object> parameters = new ArrayList<Object>(); // 参数列表
	private Map<String, Object> map = new HashMap<String, Object>();// 参数列表

	/**
	 * 生成From子句，默认的别名为'o'
	 * 
	 * @param clazz
	 */
	public HqlHelper(Class clazz) {
		this.fromClause = "FROM " + clazz.getSimpleName() + " o";
	}

	/**
	 * 生成From子句，使用指定的别。'
	 * 
	 * @param clazz
	 * @param alias
	 *            别名
	 */
	public HqlHelper(Class clazz, String alias) {
		this.fromClause = "FROM " + clazz.getSimpleName() + " " + alias;
	}

	/**
	 * 拼接Where子句
	 * 
	 * @param condition
	 *            条件跟连接符 比如： name=
	 * @param fieldName
	 *            字段的名称 比如 :name
	 * @param fieldValue
	 *            字段的值
	 * @return
	 */
	public HqlHelper addCondition(String condition, String fieldName, String fieldValue) {
		// 拼接
		if (whereClause.length() == 0) {
			whereClause = " WHERE " + condition + " :" + fieldName;
		} else {
			whereClause += " AND " + condition + " :" + fieldName;
		}

		if (null != fieldValue) {
			map.put(fieldName, fieldValue);
		}

		return this;
	}

	/**
	 * 如果第1个参数为true，则拼接Where子句
	 * 
	 * @param append
	 *            是否需要添加条件
	 * @param condition
	 *            条件跟连接符 比如： name=
	 * @param fieldName
	 *            字段的名称 比如 :name
	 * @param fieldValue
	 *            字段的值
	 */
	public HqlHelper addCondition(boolean append, String condition, String fieldName, String fieldValue) {
		if (append) {
			addCondition(condition, fieldName, fieldValue);
		}
		return this;
	}

	/**
	 * 拼接OrderBy子句
	 * 
	 * @param propertyName
	 *            属性名
	 * @param order
	 *            "ASC"表示升序，"DESC"表示降序
	 */
	public HqlHelper addOrder(String propertyName, String order) {
		if (orderByClause.length() == 0) {
			orderByClause = " ORDER BY " + propertyName + " " + order;
		} else {
			orderByClause += ", " + propertyName + " " + order;
		}
		return this;
	}

	/**
	 * 如果第1个参数为true，则拼接OrderBy子句
	 * 
	 * @param append
	 * @param propertyName
	 *            属性名
	 * @param order
	 *            "ASC"表示升序，"DESC"表示降序
	 */
	public HqlHelper addOrder(boolean append, String propertyName, String order) {
		if (append) {
			addOrder(propertyName, order);
		}
		return this;
	}

	/**
	 * 获取生成的查询数据列表的HQL语句
	 * 
	 * @return
	 */
	public String getQueryListHql() {
		logger.info("-------->" + fromClause + whereClause + orderByClause);
		return fromClause + whereClause + orderByClause;
	}

	/**
	 * 获取生成的查询总记录数的HQL语句（没有OrderBy子句）
	 * 
	 * @return
	 */
	public String getQueryCountHql() {
		logger.info("SELECT COUNT(*) " + fromClause + whereClause);
		return "SELECT COUNT(*) " + fromClause + whereClause;
	}

	/**
	 * 获取参数列表，与HQL过滤条件中的'?'一一对应
	 * 
	 * @return
	 */
	// public List<Object> getParameters() {
	// return parameters;
	// }
	/**
	 * 获取参数列表
	 * 
	 * @return
	 */
	public Map<String, Object> getMap() {
		return map;
	}

}
