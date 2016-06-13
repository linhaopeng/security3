package com.hp.model;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "SYSUSER", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class User extends BaseModel implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1654907104097381594L;

	private String ip;// 此属性不存数据库，虚拟属性

	private Date createdatetime;
	private Date updatedatetime;
	private String loginname; // 不允许为null
	private String pwd = "123456"; // 默认为123456
	private String name;
	private String sex;
	private Integer age;
	private String photo;
	private Set<Role> roles = new HashSet<Role>(0);

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDATETIME", length = 7)
	public Date getCreatedatetime() {
		if (this.createdatetime != null)
			return this.createdatetime;
		return new Date();
	}

	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATEDATETIME", length = 7)
	public Date getUpdatedatetime() {
		if (this.updatedatetime != null)
			return this.updatedatetime;
		return new Date();
	}

	public void setUpdatedatetime(Date updatedatetime) {
		this.updatedatetime = updatedatetime;
	}

	@Column(name = "LOGINNAME", nullable = false, length = 100)
	public String getLoginname() {
		return this.loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	@Column(name = "PWD", length = 100)
	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Column(name = "NAME", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "SEX", length = 1)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "AGE", precision = 8, scale = 0)
	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Column(name = "PHOTO", length = 200)
	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "SYSUSER_SYSROLE", schema = "", joinColumns = { @JoinColumn(name = "SYSUSER_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "SYSROLE_ID", nullable = false, updatable = false) })
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Transient
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	//------------------------------  userDetails
	private String password;
	private String username;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	
	@Override
	@Transient
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	/**
	 * 获取密码
	 */
	@Override
	@Transient
	public String getPassword() {
		return pwd;
	}

	/**
	 * 获取用户名
	 */
	@Override
	@Transient
	public String getUsername() {
		return loginname;
	}

	/**
	 * 账户是否没有过期
	 */
	@Override
	@Transient
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * 是否没有被锁
	 */
	@Override
	@Transient
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * 用户的凭证是否没有过期
	 */
	@Override
	@Transient
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * 是否激活
	 */
	@Override
	@Transient
	public boolean isEnabled() {
		return true;
	}

}
