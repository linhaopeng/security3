package com.hp.model;


import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 权限实体类
 * 
 * @author baojulin
 *
 */
@Entity
@Table(name = "sys_privilege", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Privilege extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1574165994680444408L;
	private String name;
	private Date createdatetime;
	private Date updatedatetime;
	private String description;
	private String iconCls;
	private Integer seq;
	private String target;

	private Set<Role> roles;

	/** default constructor */
	public Privilege() {
	}

	@ManyToMany
	@JoinTable(name = "sys_role_privilege", schema = "", joinColumns = { @JoinColumn(name = "pid", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "rid", nullable = false, updatable = false) })
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	/** full constructor */
	public Privilege(String name) {
		this.name = name;
	}

	@Column(name = "NAME", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Column(name = "description", length = 100)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "iconCls", length = 100)
	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	@Column(name = "SEQ", precision = 8, scale = 0)
	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	@Column(name = "target", length = 100)
	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

}
