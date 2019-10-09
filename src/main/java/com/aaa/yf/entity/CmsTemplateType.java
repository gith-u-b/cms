package com.aaa.yf.entity;

/**
 * CmsTemplateType entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class CmsTemplateType implements java.io.Serializable {

	// Fields

	private Integer typeId;
	private String typeName;

	// Constructors

	/** default constructor */
	public CmsTemplateType() {
	}

	/** full constructor */
	public CmsTemplateType(String typeName) {
		this.typeName = typeName;
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

}