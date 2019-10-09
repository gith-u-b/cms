package com.aaa.yf.entity;


/**
 * CmsComment entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class CmsComment implements java.io.Serializable {

	// Fields

	private Integer comId;
	private CmsContent cmsContent;
	private Integer replyUserId;
	private String createTime;
	private String replyTime;
	private Integer isRecommend;
	private Integer isChecked;
	private String content;
	private String replyContent;
	private String userName;
	public Integer getComId() {
		return comId;
	}
	public void setComId(Integer comId) {
		this.comId = comId;
	}
	
	public CmsContent getCmsContent() {
		return cmsContent;
	}
	public void setCmsContent(CmsContent cmsContent) {
		this.cmsContent = cmsContent;
	}
	public Integer getReplyUserId() {
		return replyUserId;
	}
	public void setReplyUserId(Integer replyUserId) {
		this.replyUserId = replyUserId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}
	public Integer getIsRecommend() {
		return isRecommend;
	}
	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}
	public Integer getIsChecked() {
		return isChecked;
	}
	public void setIsChecked(Integer isChecked) {
		this.isChecked = isChecked;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	

	
}