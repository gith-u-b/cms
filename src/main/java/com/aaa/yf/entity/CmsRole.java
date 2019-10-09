package com.aaa.yf.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * CmsRole entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class CmsRole implements java.io.Serializable {

	// Fields

	private Integer roleId;
	private String roleName;
	private String roleDescribe;
	private Set<CmsUser> cmsUsers = new HashSet<CmsUser>();
	private Set<CmsPower> cmsPowers = new HashSet<CmsPower>();

	// Constructors

	/** default constructor */
	public CmsRole() {
	}

	/** full constructor */
	public CmsRole(String roleName, String roleDescribe, Set<CmsUser> cmsUsers,
			Set<CmsPower> cmsPowers) {
		this.roleName = roleName;
		this.roleDescribe = roleDescribe;
		this.cmsUsers = cmsUsers;
		this.cmsPowers = cmsPowers;
	}

	// Property accessors

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescribe() {
		return this.roleDescribe;
	}

	public void setRoleDescribe(String roleDescribe) {
		this.roleDescribe = roleDescribe;
	}

	public Set<CmsUser> getCmsUsers() {
		return this.cmsUsers;
	}

	public void setCmsUsers(Set<CmsUser> cmsUsers) {
		this.cmsUsers = cmsUsers;
	}

	public Set<CmsPower> getCmsPowers() {
		return this.cmsPowers;
	}

	public void setCmsPowers(Set<CmsPower> cmsPowers) {
		this.cmsPowers = cmsPowers;
	}

}