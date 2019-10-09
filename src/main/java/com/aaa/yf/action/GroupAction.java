package com.aaa.yf.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aaa.yf.entity.CmsGroup;
import com.aaa.yf.entity.CmsUser;
import com.aaa.yf.service.ICmsGroupService;
import com.aaa.yf.service.ICmsUserService;
import com.et.mvc.util.Json;

@Controller
@Scope("prototype")
@Action
@ParentPackage(value="cms")
public class GroupAction extends BaseAction {

	@Autowired
	private ICmsGroupService cgservice;
	@Autowired
	private ICmsUserService cuservice;
	private CmsGroup group;
	
	
	public String deleteGroup() throws Exception{
		try {
			Map condition = new HashMap();
			condition.put("obj.cmsGroup.groupId = ?", group.getGroupId());
			List<CmsUser> list = cuservice.findByPage(condition, null, null, null, null);
			
				if(list.size() > 0){
					this.printJSON("yesno");
				}else{
					cgservice.doDeleteGroup(group);
					this.printJSON("yes");
				}
			
		} catch (Exception e) {
			this.printJSON("no");
			this.getLogger().info("删除会员组失败");
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String updateGroup() throws Exception{
		
		try {
			cgservice.doUpdateGroup(group);
			this.printJSON("yes");
		} catch (Exception e) {
			this.printJSON("no");
			this.getLogger().info("更改会员组失败");
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String addGroup() throws Exception{
		try {
			cgservice.doAddGroup(group);
			this.printJSON("yes");
		} catch (Exception e) {
			this.printJSON("no");
			this.getLogger().info("添加会员组失败");
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public String findAllGroup() throws Exception{
		this.printJSON(Json.toJson(cgservice.findAllGroup(null, null, null, null, null)));
		return null;
	}
	public ICmsGroupService getCgservice() {
		return cgservice;
	}
	public void setCgservice(ICmsGroupService cgservice) {
		this.cgservice = cgservice;
	}
	public CmsGroup getGroup() {
		return group;
	}
	public void setGroup(CmsGroup group) {
		this.group = group;
	}

	public ICmsUserService getCuservice() {
		return cuservice;
	}

	public void setCuservice(ICmsUserService cuservice) {
		this.cuservice = cuservice;
	}
	
	
	
}
