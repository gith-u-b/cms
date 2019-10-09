package com.aaa.yf.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aaa.yf.entity.CmsRole;
import com.aaa.yf.service.ICmsRoleService;
import com.et.mvc.util.Json;

@Controller
@Scope("prototype")
@Action
@ParentPackage(value="cms")
public class RoleAction extends BaseAction {

	@Autowired
	private ICmsRoleService crservice;
	private CmsRole role = new CmsRole();
	private String ids;
	
	public String grantRole() throws Exception{
		
		try {
			crservice.doGrantRole(ids, role.getRoleId());
			this.printJSON("yes");
		} catch (Exception e) {
			this.printJSON("no");
			this.getLogger().info("赋权失败");
			e.printStackTrace();
		}
		
		return null;
	}
	public String deleteRole() throws Exception{
		
		try {
			if(crservice.doDeleteRole(ids)){
				this.printJSON("yes");
			}else{
				this.printJSON("noyes");  //表示有关联的user，不能删除
			}
		} catch (Exception e) {
			this.printJSON("no");
			this.getLogger().info("删除角色失败");
			e.printStackTrace();
		}
	
		return null;
	}
	/*
	 * 编辑角色
	 */
	public String updateRole() throws Exception{
		try {
			CmsRole r = crservice.findRoleById(role.getRoleId());
			r.setRoleName(role.getRoleName());
			crservice.doUpdateRole(r);
			this.printJSON("yes");
		} catch (Exception e) {
			this.printJSON("no");
			this.getLogger().info("更改角色失败");
			e.printStackTrace();
		}
		return null;
	}
	/*
	 * 添加角色
	 */
	public String addRole() throws Exception{
		
		try {
			crservice.doAddRole(role);
			this.printJSON("yes");
		} catch (Exception e) {
			this.printJSON("no");
			this.getLogger().info("添加角色失败");
			e.printStackTrace();
		}
		
		return null;
	}
	/*
	 * 查询所有角色
	 */
	public String findAllRole() throws Exception{
		this.printJSON(Json.toJson(crservice.findAllRole(null, null, null, null, null)));
		return null;
	}
	
	public ICmsRoleService getCrservice() {
		return crservice;
	}
	public void setCrservice(ICmsRoleService crservice) {
		this.crservice = crservice;
	}
	public CmsRole getRole() {
		return role;
	}
	public void setRole(CmsRole role) {
		this.role = role;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	
}
