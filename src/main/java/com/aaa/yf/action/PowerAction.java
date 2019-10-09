package com.aaa.yf.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aaa.yf.entity.CmsPower;
import com.aaa.yf.entity.CmsRole;
import com.aaa.yf.entity.CmsUser;
import com.aaa.yf.service.ICmsPowerService;
import com.aaa.yf.util.toJsonUtil;
import com.et.mvc.util.Json;

@Controller
@Scope("prototype")
@Action
@ParentPackage(value="cms")
public class PowerAction extends BaseAction {

	@Autowired
	private ICmsPowerService cpservice;
	private CmsPower power;
	private CmsRole role;
	
	//更改权限
	public String updatePower() throws Exception{
		try {
			cpservice.doUpdatePower(power);
			this.printJSON("yes");
		} catch (Exception e) {
			this.printJSON("no");
			this.getLogger().info("更改权限失败");
			e.printStackTrace();
			
		}
		return null;
	}
	//添加功能
	public String addPower() throws Exception{
		try {
			cpservice.doAddPower(power);
			this.printJSON("yes");
		} catch (Exception e) {
			this.printJSON("no");
			this.getLogger().info("添加功能失败");
			e.printStackTrace();
		}
		
		return null;
	}
	public String deletePower() throws Exception{
		try {
			cpservice.doDeletePower(power.getId());
			this.printJSON("yes");
		} catch (Exception e) {
			this.printJSON("no");
			this.getLogger().info("删除功能失败");
			e.printStackTrace();
		}
		
		return null;
	}
	/*
	 * treegrid  
	 */
	public String findPowerByPage() throws Exception{
		toJsonUtil tojson = new toJsonUtil(cpservice.findPowerByPage(),cpservice.findCount());
		this.printJSON(Json.toJson(tojson));
		return null;
	}
	/*
	 * 得到所有权限
	 */
	public String findAllPowers() throws Exception{
			
		this.printJSON(Json.toJson(cpservice.findAllPower()));
		
		return null;
	}
	
	/*
	 * 得到所有权限，并标记某角色已有权限
	 */
	public String findAllPower() throws Exception{
		System.out.println(Json.toJson(cpservice.findAllPower(role.getRoleId())));
		this.printJSON(Json.toJson(cpservice.findAllPower(role.getRoleId())));
		return null;
	}
	
	public String findPowerByUser() throws Exception{
		CmsUser u = (CmsUser) this.getSession().getAttribute("user");
		this.printJSON(Json.toJson(u.getCmsRole().getCmsPowers().toArray()));
		return null;
	}
	
	public ICmsPowerService getCpservice() {
		return cpservice;
	}
	public void setCpservice(ICmsPowerService cpservice) {
		this.cpservice = cpservice;
	}
	public CmsPower getPower() {
		return power;
	}
	public void setPower(CmsPower power) {
		this.power = power;
	}
	public CmsRole getRole() {
		return role;
	}
	public void setRole(CmsRole role) {
		this.role = role;
	}

	
	
}
