package com.hp.model;

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
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "SYSROLE", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Role extends BaseModel implements GrantedAuthority,ConfigAttribute{

	/**
	 * 
	 */
	private static final long serialVersionUID = 819803310961788818L;
	private Date createdatetime;
	private Date updatedatetime;
	private String name;
	private String description;
	private String iconCls;
	private Integer seq;
	private Set<User> users = new HashSet<User>(0);
	private Set<Resource> resources = new HashSet<Resource>(0);

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

	@Column(name = "NAME", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "DESCRIPTION", length = 200)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "ICONCLS", length = 100)
	public String getIconCls() {
		return this.iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	@Column(name = "SEQ", precision = 8, scale = 0)
	public Integer getSeq() {
		return this.seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "SYSUSER_SYSROLE", schema = "", joinColumns = { @JoinColumn(name = "SYSROLE_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "SYSUSER_ID", nullable = false, updatable = false) })
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "SYSROLE_SYSRESOURCE", schema = "", joinColumns = { @JoinColumn(name = "SYSROLE_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "SYSRESOURCE_ID", nullable = false, updatable = false) })
	public Set<Resource> getResources() {
		return resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

	
	//-------------------GrantedAuthority
	private String authority;
	/**
	 * 返回角色名称 
	 */
	@Override
	@Transient
	public String getAuthority() {
		return name;
	}
	
	//-------------------ConfigAttribute
	private String attribute;

	@Override
	@Transient
	public String getAttribute() {
		return name;
	}

}
