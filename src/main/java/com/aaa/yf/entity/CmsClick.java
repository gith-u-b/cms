package com.aaa.yf.entity;

import java.util.Date;


/**
 * CmsClick entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class CmsClick implements java.io.Serializable {

	// Fields

	private Integer clickId;
	private CmsContent cmsContent;
	private Date clickTime;

	// Constructors

	/** default constructor */
	public CmsClick() {
	}

	/** full constructor */
	public CmsClick(CmsContent cmsContent, Date clickTime) {
		this.cmsContent = cmsContent;
		this.clickTime = clickTime;
	}

	// Property accessors

	public Integer getClickId() {
		return this.clickId;
	}

	public void setClickId(Integer clickId) {
		this.clickId = clickId;
	}

	public CmsContent getCmsContent() {
		return this.cmsContent;
	}

	public void setCmsContent(CmsContent cmsContent) {
		this.cmsContent = cmsContent;
	}

	public Date getClickTime() {
		return this.clickTime;
	}

	public void setClickTime(Date clickTime) {
		this.clickTime = clickTime;
	}

}