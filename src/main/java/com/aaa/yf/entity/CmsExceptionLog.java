package com.aaa.yf.entity;


/**
 * CmsExceptionLog entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class CmsExceptionLog implements java.io.Serializable {

	// Fields

	private Integer logId;
	private String logTime;
	private String logLevel;
	private String logSite;
	private String logMsg;

	// Constructors

	/** default constructor */
	public CmsExceptionLog() {
	}

	/** full constructor */
	public CmsExceptionLog(String logTime, String logLevel, String logSite,
			String logMsg) {
		this.logTime = logTime;
		this.logLevel = logLevel;
		this.logSite = logSite;
		this.logMsg = logMsg;
	}

	// Property accessors

	public Integer getLogId() {
		return this.logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public String getLogTime() {
		return this.logTime;
	}

	public void setLogTime(String logTime) {
		this.logTime = logTime;
	}

	public String getLogLevel() {
		return this.logLevel;
	}

	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}

	public String getLogSite() {
		return this.logSite;
	}

	public void setLogSite(String logSite) {
		this.logSite = logSite;
	}

	public String getLogMsg() {
		return this.logMsg;
	}

	public void setLogMsg(String logMsg) {
		this.logMsg = logMsg;
	}

}