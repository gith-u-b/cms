package com.aaa.yf.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * CmsContentType entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class CmsContentType implements java.io.Serializable {

	// Fields

	private Integer typeId;
	private String typeName;
	private Integer imgWidth;
	private Integer imgHeight;
	private Integer hasImage;
	private Integer isDisabled;
	private Set<CmsContent> cmsContents = new HashSet<CmsContent>();

	// Constructors

	/** default constructor */
	public CmsContentType() {
	}

	/** full constructor */
	public CmsContentType(String typeName, Integer imgWidth, Integer imgHeight,
			Integer hasImage, Integer isDisabled, Set<CmsContent> cmsContents) {
		this.typeName = typeName;
		this.imgWidth = imgWidth;
		this.imgHeight = imgHeight;
		this.hasImage = hasImage;
		this.isDisabled = isDisabled;
		this.cmsContents = cmsContents;
	}

	// Property accessors

	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getImgWidth() {
		return this.imgWidth;
	}

	public void setImgWidth(Integer imgWidth) {
		this.imgWidth = imgWidth;
	}

	public Integer getImgHeight() {
		return this.imgHeight;
	}

	public void setImgHeight(Integer imgHeight) {
		this.imgHeight = imgHeight;
	}

	public Integer getHasImage() {
		return this.hasImage;
	}

	public void setHasImage(Integer hasImage) {
		this.hasImage = hasImage;
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

}