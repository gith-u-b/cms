package com.aaa.yf.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * CmsContent entity
 */
@SuppressWarnings("serial")
public class CmsContent implements java.io.Serializable {
	private Integer contentId;
	private CmsContentType cmsContentType;
	private CmsUser cmsUser;
	private	CmsTemplate cmsTemplate;
	private CmsChannel cmsChannel;
	private String title;
	private String shortTitle;
	private String summary;
	private Integer hasTitleImg;
	private Integer isRecommend;
	private Integer isStatic;
	private Integer isCommend;
	private Integer isDisplay;
	private Integer status;
	private String author;
	private String origin;
	private String originUrl;
	private String content;
	private String contentImg;
	private String repleaseTime;
	private String titleColor;
	private Integer isBold;
	private String titleImg;
	private String link;
	private Integer isCreated;
	private Set<CmsClick> cmsClicks = new HashSet<CmsClick>();
	private Set<CmsComment> cmsComments = new HashSet<CmsComment>();
	private Set<CmsFile> cmsFiles = new HashSet<CmsFile>();
	/** default constructor */
	public CmsContent() {
	}
	public Integer getContentId() {
		return this.contentId;
	}
	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}

	public CmsContentType getCmsContentType() {
		return this.cmsContentType;
	}

	public void setCmsContentType(CmsContentType cmsContentType) {
		this.cmsContentType = cmsContentType;
	}

	public CmsUser getCmsUser() {
		return this.cmsUser;
	}

	public void setCmsUser(CmsUser cmsUser) {
		this.cmsUser = cmsUser;
	}

	public CmsTemplate getCmsTemplate() {
		return cmsTemplate;
	}

	public void setCmsTemplate(CmsTemplate cmsTemplate) {
		this.cmsTemplate = cmsTemplate;
	}

	public CmsChannel getCmsChannel() {
		return this.cmsChannel;
	}

	public void setCmsChannel(CmsChannel cmsChannel) {
		this.cmsChannel = cmsChannel;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShortTitle() {
		return this.shortTitle;
	}

	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Integer getHasTitleImg() {
		return this.hasTitleImg;
	}

	public void setHasTitleImg(Integer hasTitleImg) {
		this.hasTitleImg = hasTitleImg;
	}

	public Integer getIsRecommend() {
		return this.isRecommend;
	}

	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}
	public Integer getIsStatic() {
		return isStatic;
	}
	public void setIsStatic(Integer isStatic) {
		this.isStatic = isStatic;
	}
	public Integer getIsCommend() {
		return this.isCommend;
	}

	public void setIsCommend(Integer isCommend) {
		this.isCommend = isCommend;
	}
	public Integer getIsDisplay() {
		return isDisplay;
	}
	public void setIsDisplay(Integer isDisplay) {
		this.isDisplay = isDisplay;
	}
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getOrigin() {
		return this.origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getOriginUrl() {
		return this.originUrl;
	}

	public void setOriginUrl(String originUrl) {
		this.originUrl = originUrl;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContentImg() {
		return contentImg;
	}

	public void setContentImg(String contentImg) {
		this.contentImg = contentImg;
	}

	public String getRepleaseTime() {
		return this.repleaseTime;
	}

	public void setRepleaseTime(String repleaseTime) {
		this.repleaseTime = repleaseTime;
	}

	public String getTitleColor() {
		return this.titleColor;
	}

	public void setTitleColor(String titleColor) {
		this.titleColor = titleColor;
	}

	public Integer getIsBold() {
		return this.isBold;
	}

	public void setIsBold(Integer isBold) {
		this.isBold = isBold;
	}

	public String getTitleImg() {
		return this.titleImg;
	}

	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Set<CmsClick> getCmsClicks() {
		return this.cmsClicks;
	}

	public void setCmsClicks(Set<CmsClick> cmsClicks) {
		this.cmsClicks = cmsClicks;
	}

	public Set<CmsComment> getCmsComments() {
		return this.cmsComments;
	}

	public void setCmsComments(Set<CmsComment> cmsComments) {
		this.cmsComments = cmsComments;
	}

	public Set<CmsFile> getCmsFiles() {
		return this.cmsFiles;
	}

	public void setCmsFiles(Set<CmsFile> cmsFiles) {
		this.cmsFiles = cmsFiles;
	}
	public Integer getIsCreated() {
		return isCreated;
	}
	public void setIsCreated(Integer isCreated) {
		this.isCreated = isCreated;
	}
	
	

}