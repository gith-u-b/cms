package com.aaa.yf.entity;

import java.io.Serializable;
/**
 * 登陆失败记录
 * @author Administrator
 *
 */
public class CmsLoginFailure implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String userName;
	private String password;
	private String dateTime;
	private String ip;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public CmsLoginFailure(String userName, String password, String dateTime,
			String ip) {
		super();
		this.userName = userName;
		this.password = password;
		this.dateTime = dateTime;
		this.ip = ip;
	}
	public CmsLoginFailure() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
