package com.aaa.yf.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * CmsTemplate entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class CmsTemplate implements java.io.Serializable {

	// Fields

	private Integer templateId;
	private CmsTemplateType cmsTemplateType;
	private CmsUser cmsUser;
	private String templateName;
	private String createtime;
	private Integer titleImgWidth;
	private Integer titleImgHeight;
	private Integer priority;
	private Integer hasContent;
	private Integer isDisabled;
	private String path;
	private Integer pid;
	private Set<CmsChannel> cmsChannels = new HashSet<CmsChannel>();
	private Set<CmsContent> cmsContents = new HashSet<CmsContent>(0);
	// Constructors

	/** default constructor */
	public CmsTemplate() {
	}
	public Integer getTemplateId() {
		return this.templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public CmsTemplateType getCmsTemplateType() {
		return cmsTemplateType;
	}

	public void setCmsTemplateType(CmsTemplateType cmsTemplateType) {
		this.cmsTemplateType = cmsTemplateType;
	}

	public CmsUser getCmsUser() {
		return this.cmsUser;
	}

	public void setCmsUser(CmsUser cmsUser) {
		this.cmsUser = cmsUser;
	}

	public String getTemplateName() {
		return this.templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public Integer getTitleImgWidth() {
		return this.titleImgWidth;
	}

	public void setTitleImgWidth(Integer titleImgWidth) {
		this.titleImgWidth = titleImgWidth;
	}

	public Integer getTitleImgHeight() {
		return this.titleImgHeight;
	}

	public void setTitleImgHeight(Integer titleImgHeight) {
		this.titleImgHeight = titleImgHeight;
	}

	public Integer getPriority() {
		return this.priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getHasContent() {
		return this.hasContent;
	}

	public void setHasContent(Integer hasContent) {
		this.hasContent = hasContent;
	}

	public Integer getIsDisabled() {
		return this.isDisabled;
	}

	public void setIsDisabled(Integer isDisabled) {
		this.isDisabled = isDisabled;
	}

	public Set<CmsChannel> getCmsChannels() {
		return this.cmsChannels;
	}

	public void setCmsChannels(Set<CmsChannel> cmsChannels) {
		this.cmsChannels = cmsChannels;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Set<CmsContent> getCmsContents() {
		return cmsContents;
	}
	public void setCmsContents(Set<CmsContent> cmsContents) {
		this.cmsContents = cmsContents;
	}
}