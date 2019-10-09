package com.aaa.yf.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.aaa.yf.entity.CmsUser;
import com.aaa.yf.service.ICmsUserService;

@Action
@Scope("prototype")
@ParentPackage(value="cms")
public class MemberAction extends BaseAction {

	@Autowired
	private ICmsUserService cuservice;
	private CmsUser user;
	
	/**
	 * 验证是否登录
	 * @throws Exception
	 */
	public String verifyLogin() throws Exception{
		if(this.getSession().getAttribute("u") == null){
			this.printJSON("false");
		}else{
			CmsUser c=(CmsUser)this.getSession().getAttribute("u") ;
			String s=c.getUserName()+","+c.getCmsGroup().getGroupName();
			this.printJSON(s);
		}
		return null;
	}
	
	
	
	
	public ICmsUserService getCuservice() {
		return cuservice;
	}
	public void setCuservice(ICmsUserService cuservice) {
		this.cuservice = cuservice;
	}
	public CmsUser getUser() {
		return user;
	}
	public void setUser(CmsUser user) {
		this.user = user;
	}
	
	
	
}
