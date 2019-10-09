package com.aaa.yf.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * CmsChannel entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class CmsChannel implements java.io.Serializable {

	// Fields

	private Integer channelId;
	private CmsTemplate cmsTemplate;
	private String channelName;
	private Integer parentId;
	private String channelPath;
	private Integer priority;
	private Integer hasContent;
	private Integer isDisplay;
	private Integer isStatic;
	private String isState;
	private Integer page;
	private String url;
	private String titleImg;
	private String contentImg;
	private Integer titleImgHeight;
	private Integer titleImgWidth;
	private Integer contentImgWidth;
	private Integer contentImgHeight;
	private String title;
	private String keywords;
	private String description;
	private Set<CmsContent> cmsContents = new HashSet<CmsContent>();

	// Constructors

	/** default constructor */
	public CmsChannel() {
	}

	/** full constructor */
	public CmsChannel(CmsTemplate cmsTemplate, String channelName,
			Integer parentId, String channelPath, Integer priority,
			Integer hasContent, Integer isDisplay, Integer isStatic,
			String isState, Integer page, String url, String titleImg,
			String contentImg, Integer titleImgHeight, Integer titleImgWidth,
			Integer contentImgWidth, Integer contentImgHeight, String title,
			String keywords, String description, Set<CmsContent> cmsContents) {
		this.cmsTemplate = cmsTemplate;
		this.channelName = channelName;
		this.parentId = parentId;
		this.channelPath = channelPath;
		this.priority = priority;
		this.hasContent = hasContent;
		this.isDisplay = isDisplay;
		this.isStatic = isStatic;
		this.isState = isState;
		this.page = page;
		this.url = url;
		this.titleImg = titleImg;
		this.contentImg = contentImg;
		this.titleImgHeight = titleImgHeight;
		this.titleImgWidth = titleImgWidth;
		this.contentImgWidth = contentImgWidth;
		this.contentImgHeight = contentImgHeight;
		this.title = title;
		this.keywords = keywords;
		this.description = description;
		this.cmsContents = cmsContents;
	}

	// Property accessors

	public Integer getChannelId() {
		return this.channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public CmsTemplate getCmsTemplate() {
		return this.cmsTemplate;
	}

	public void setCmsTemplate(CmsTemplate cmsTemplate) {
		this.cmsTemplate = cmsTemplate;
	}

	public String getChannelName() {
		return this.channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getChannelPath() {
		return this.channelPath;
	}

	public void setChannelPath(String channelPath) {
		this.channelPath = channelPath;
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

	public Integer getIsDisplay() {
		return this.isDisplay;
	}

	public void setIsDisplay(Integer isDisplay) {
		this.isDisplay = isDisplay;
	}

	public Integer getIsStatic() {
		return this.isStatic;
	}

	public void setIsStatic(Integer isStatic) {
		this.isStatic = isStatic;
	}

	public String getIsState() {
		return this.isState;
	}

	public void setIsState(String isState) {
		this.isState = isState;
	}

	public Integer getPage() {
		return this.page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitleImg() {
		return this.titleImg;
	}

	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}

	public String getContentImg() {
		return this.contentImg;
	}

	public void setContentImg(String contentImg) {
		this.contentImg = contentImg;
	}

	public Integer getTitleImgHeight() {
		return this.titleImgHeight;
	}

	public void setTitleImgHeight(Integer titleImgHeight) {
		this.titleImgHeight = titleImgHeight;
	}

	public Integer getTitleImgWidth() {
		return this.titleImgWidth;
	}

	public void setTitleImgWidth(Integer titleImgWidth) {
		this.titleImgWidth = titleImgWidth;
	}

	public Integer getContentImgWidth() {
		return this.contentImgWidth;
	}

	public void setContentImgWidth(Integer contentImgWidth) {
		this.contentImgWidth = contentImgWidth;
	}

	public Integer getContentImgHeight() {
		return this.contentImgHeight;
	}

	public void setContentImgHeight(Integer contentImgHeight) {
		this.contentImgHeight = contentImgHeight;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKeywords() {
		return this.keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<CmsContent> getCmsContents() {
		return this.cmsContents;
	}

	public void setCmsContents(Set<CmsContent> cmsContents) {
		this.cmsContents = cmsContents;
	}

}