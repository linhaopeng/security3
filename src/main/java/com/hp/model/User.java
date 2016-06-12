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
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 用户实体类
 * 
 * @author baojulin
 *
 */
@Entity
@Table(name = "sys_user", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class User extends BaseModel implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4414050582422802953L;
	private String login; // 不允许为null
	private String pass;
	private String name;
	private Date createdatetime;
	private Date updatedatetime;
	private String sex;
	private Integer age;
	private String photo;

	private Set<Role> roles = new HashSet<Role>(0);

	private Collection<? extends GrantedAuthority> authorities;
	private String password;
	private String username;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;

	@Column(name = "LOGIN", nullable = false, length = 100)
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Column(name = "PASS", length = 100)
	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Column(name = "NAME", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "sys_user_role", schema = "", joinColumns = { @JoinColumn(name = "aid", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "rid", nullable = false, updatable = false) })
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	/**
	 * 返回角色集合
	 */
	@Transient
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	/**
	 * 返回密码
	 */
	@Transient
	@Override
	public String getPassword() {
		return pass;
	}

	/**
	 * 返回用户名
	 */
	@Transient
	@Override
	public String getUsername() {
		return name;
	}

	/**
	 * 账户是否没有过期
	 */
	@Transient
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * 是否没有被锁
	 */
	@Transient
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * 用户的凭证是否没有过期
	 */
	@Transient
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * 是否激活
	 */
	@Transient
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Column(name = "createdatetime", length = 100)
	public Date getCreatedatetime() {
		return createdatetime;
	}

	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}

	@Column(name = "updatedatetime", length = 100)
	public Date getUpdatedatetime() {
		return updatedatetime;
	}

	public void setUpdatedatetime(Date updatedatetime) {
		this.updatedatetime = updatedatetime;
	}

	@Column(name = "sex", length = 2)
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "age", precision = 8, scale = 0)
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Column(name = "photo", length = 100)
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
}
