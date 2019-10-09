package com.aaa.yf.entity;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
@SuppressWarnings("serial")
public class CmsGroup implements Serializable{
   private Integer groupId;
   private String groupName;
   private Set<CmsUser> cmsUsers = new HashSet<CmsUser>();
   public Set<CmsUser> getCmsUsers() {
	return cmsUsers;
}
public void setCmsUsers(Set<CmsUser> cmsUsers) {
	this.cmsUsers = cmsUsers;
}
public Integer getGroupId() {
	return groupId;
   }
public void setGroupId(Integer groupId) {
	this.groupId = groupId;
}
public String getGroupName() {
	return groupName;
}
public void setGroupName(String groupName) {
	this.groupName = groupName;
 }
}
