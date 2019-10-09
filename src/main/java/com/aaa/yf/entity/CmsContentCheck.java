package com.aaa.yf.entity;

/**
 * CmsContentCheck entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class CmsContentCheck implements java.io.Serializable {

	// Fields

	private Integer contentCheckId;
	private CmsContent cmsContent;
	private String checkOption;
	private Integer isRejected;
	private String reviewer;
	private String checkDatetime;

	// Constructors

	/** default constructor */
	public CmsContentCheck() {
	}

	/** full constructor */
	public CmsContentCheck(CmsContent cmsContent, String checkOption,
			Integer isRejected, String reviewer, String checkDatetime) {
		this.cmsContent = cmsContent;
		this.checkOption = checkOption;
		this.isRejected = isRejected;
		this.reviewer = reviewer;
		this.checkDatetime = checkDatetime;
	}

	// Property accessors

	public Integer getContentCheckId() {
		return this.contentCheckId;
	}

	public void setContentCheckId(Integer contentCheckId) {
		this.contentCheckId = contentCheckId;
	}

	public CmsContent getCmsContent() {
		return this.cmsContent;
	}

	public void setCmsContent(CmsContent cmsContent) {
		this.cmsContent = cmsContent;
	}

	public String getCheckOption() {
		return this.checkOption;
	}

	public void setCheckOption(String checkOption) {
		this.checkOption = checkOption;
	}

	public Integer getIsRejected() {
		return this.isRejected;
	}

	public void setIsRejected(Integer isRejected) {
		this.isRejected = isRejected;
	}

	public String getReviewer() {
		return this.reviewer;
	}

	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}

	public String getCheckDatetime() {
		return this.checkDatetime;
	}

	public void setCheckDatetime(String checkDatetime) {
		this.checkDatetime = checkDatetime;
	}

}