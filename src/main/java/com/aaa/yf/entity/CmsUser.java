package com.aaa.yf.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * CmsUser entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class CmsUser implements java.io.Serializable {
	private Integer userId;
	private CmsRole cmsRole;
	private CmsGroup cmsGroup;
	private String userName;
	private String userPassword;
	private String userEmail;
	private String registerTime;
	private String registerIp;
	private String lastLoginTime;
	private String lastLoginIp;
	private Integer loginCount;
	private Integer rank;
	private Integer uploadTotal;
	private Integer uploadSize;
	private String uploadTime;
	private Integer isAdmin;
	private Integer isDisabled;
	private Set<CmsContent> cmsContents = new HashSet<CmsContent>();
	private Set<CmsTemplate> cmsTemplates = new HashSet<CmsTemplate>();
	// Constructors

	/** default constructor */
	public CmsUser() {
	}


	// Property accessors
	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public CmsRole getCmsRole() {
		return this.cmsRole;
	}

	public void setCmsRole(CmsRole cmsRole) {
		this.cmsRole = cmsRole;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	

	public String getRegisterTime() {
		return registerTime;
	}


	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}


	public String getRegisterIp() {
		return this.registerIp;
	}

	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}

	public String getLastLoginTime() {
		return this.lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIp() {
		return this.lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	public Integer getRank() {
		return this.rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Integer getUploadTotal() {
		return this.uploadTotal;
	}

	public void setUploadTotal(Integer uploadTotal) {
		this.uploadTotal = uploadTotal;
	}

	public Integer getUploadSize() {
		return this.uploadSize;
	}

	public void setUploadSize(Integer uploadSize) {
		this.uploadSize = uploadSize;
	}

	public String getUploadTime() {
		return this.uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

	public Integer getIsAdmin() {
		return this.isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Integer getIsDisabled() {
		return this.isDisabled;
	}

	public void setIsDisabled(Integer isDisabled) {
		this.isDisabled = isDisabled;
	}

	public Set<CmsContent> getCmsContents() {
		return this.cmsContents;
	}

	public void setCmsContents(Set<CmsContent> cmsContents) {
		this.cmsContents = cmsContents;
	}


	public Set<CmsTemplate> getCmsTemplates() {
		return this.cmsTemplates;
	}

	public void setCmsTemplates(Set<CmsTemplate> cmsTemplates) {
		this.cmsTemplates = cmsTemplates;
	}

	public CmsGroup getCmsGroup() {
		return cmsGroup;
	}

	public void setCmsGroup(CmsGroup cmsGroup) {
		this.cmsGroup = cmsGroup;
	}





	
}