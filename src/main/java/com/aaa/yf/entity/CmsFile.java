package com.aaa.yf.entity;


/**
 * CmsFile entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class CmsFile implements java.io.Serializable {
	private Integer fileId;
	private CmsUser cmsUser;
	private CmsContent cmsContent;
	private String fileName;
	private String fileUrl;
	private String fileUploadTime;
	private Integer fileIsDisable;
	
	/** default constructor */
	public CmsFile() {
	}

	/** full constructor */
	public CmsFile(CmsUser cmsUser, CmsContent cmsContent, String fileName,
			String fileUrl, String fileUploadTime, Integer fileIsDisable) {
		this.cmsUser = cmsUser;
		this.cmsContent = cmsContent;
		this.fileName = fileName;
		this.fileUrl = fileUrl;
		this.fileUploadTime = fileUploadTime;
		this.fileIsDisable = fileIsDisable;
	}

	// Property accessors

	public Integer getFileId() {
		return this.fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public CmsUser getCmsUser() {
		return this.cmsUser;
	}

	public void setCmsUser(CmsUser cmsUser) {
		this.cmsUser = cmsUser;
	}

	public CmsContent getCmsContent() {
		return this.cmsContent;
	}

	public void setCmsContent(CmsContent cmsContent) {
		this.cmsContent = cmsContent;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileUrl() {
		return this.fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getFileUploadTime() {
		return this.fileUploadTime;
	}

	public void setFileUploadTime(String fileUploadTime) {
		this.fileUploadTime = fileUploadTime;
	}

	public Integer getFileIsDisable() {
		return this.fileIsDisable;
	}

	public void setFileIsDisable(Integer fileIsDisable) {
		this.fileIsDisable = fileIsDisable;
	}

}