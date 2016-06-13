package com.hp.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "SYSRESOURCE", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Resource extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7287109326955757963L;

	private int pid;// 虚拟属性，用于获得当前资源的父资源ID

	private Date createdatetime;
	private Date updatedatetime;
	private String name;
	private String url;
	private String description;
	private String iconCls;
	private Integer seq;
	private String target;
	private ResourceType resourcetype;
	private Resource resource;
	private Set<Role> roles = new HashSet<Role>(0);
	private Set<Resource> resources = new HashSet<Resource>(0);

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SYRESOURCETYPE_ID")
	public ResourceType getResourcetype() {
		return resourcetype;
	}

	public void setResourcetype(ResourceType resourcetype) {
		this.resourcetype = resourcetype;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SYSRESOURCE_ID")
	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
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

	@Column(name = "URL", length = 200)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	@Column(name = "TARGET", length = 100)
	public String getTarget() {
		return this.target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "SYSROLE_SYSRESOURCE", schema = "", joinColumns = { @JoinColumn(name = "SYSRESOURCE_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "SYSROLE_ID", nullable = false, updatable = false) })
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	/**
	 * mappedBy 设置主表 对应resource private SysResource resource;
	 * 
	 * @return
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "resource", cascade = CascadeType.ALL)
	public Set<Resource> getResources() {
		return resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

	@Transient
	public int getPid() {
		if (resource != null) {
			return resource.getId();
		}
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

}
