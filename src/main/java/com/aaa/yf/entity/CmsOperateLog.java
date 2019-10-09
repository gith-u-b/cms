package com.aaa.yf.entity;


/**
 * CmsOperateLog entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class CmsOperateLog implements java.io.Serializable {

	// Fields

	private Integer logId;
	private CmsUser cmsUser;
	private String logTime;
	private String logUrl;
	private String logMethode;
	private String logMsg;

	// Constructors

	/** default constructor */
	public CmsOperateLog() {
	}
	public Integer getLogId() {
		return this.logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public CmsUser getCmsUser() {
		return cmsUser;
	}
	public void setCmsUser(CmsUser cmsUser) {
		this.cmsUser = cmsUser;
	}
	public String getLogTime() {
		return this.logTime;
	}

	public void setLogTime(String logTime) {
		this.logTime = logTime;
	}

	public String getLogUrl() {
		return this.logUrl;
	}

	public void setLogUrl(String logUrl) {
		this.logUrl = logUrl;
	}

	public String getLogMethode() {
		return this.logMethode;
	}

	public void setLogMethode(String logMethode) {
		this.logMethode = logMethode;
	}

	public String getLogMsg() {
		return this.logMsg;
	}

	public void setLogMsg(String logMsg) {
		this.logMsg = logMsg;
	}

}