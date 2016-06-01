package com.hp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.GrantedAuthority;

/**
 * 角色实体类
 * 
 * @author baojulin
 *
 */
@Entity
@Table(name = "sys_role", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Role extends BaseModel implements GrantedAuthority, ConfigAttribute {

	/**
	 * 
	 */
	private static final long serialVersionUID = -638143376103147253L;
	private String name;
	private String detail;
	private Set<User> accounts = new HashSet<User>(0);

	private String authority;
	private String attribute;

	@Column(name = "NAME", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "DETAIL", nullable = false, length = 100)
	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "sys_user_role", schema = "", joinColumns = { @JoinColumn(name = "rid", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "aid", nullable = false, updatable = false) })
	public Set<User> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<User> accounts) {
		this.accounts = accounts;
	}

	/**
	 * 返回角色名称
	 */
	@Transient
	@Override
	public String getAuthority() {
		return name;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Transient
	@Override
	public String getAttribute() {
		return name;
	}

}
