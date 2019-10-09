package com.aaa.yf.action;


import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aaa.yf.entity.CmsGroup;
import com.aaa.yf.entity.CmsLoginFailure;
import com.aaa.yf.entity.CmsLoginSuccess;
import com.aaa.yf.entity.CmsRole;
import com.aaa.yf.entity.CmsUser;
import com.aaa.yf.service.ICmsLoginFailureService;
import com.aaa.yf.service.ICmsLoginSuccessService;
import com.aaa.yf.service.ICmsUserService;
import com.aaa.yf.util.MD5Code;
import com.aaa.yf.util.toJsonUtil;
import com.et.mvc.util.Json;

@Controller
@Scope("prototype")
@Action
@ParentPackage(value="cms")
public class UserAction extends BaseAction {
	@Autowired
	private ICmsUserService cuservice;
	private CmsUser user = new CmsUser();
	private String validateCode;
	private Integer page;
	private Integer rows;
	private String begin;
	private String end;
	private String ids;
	
	@Autowired
	private ICmsLoginFailureService clfservice;
	@Autowired
	private ICmsLoginSuccessService clsservice;
	
	private String way; //报表的方式(按月或者年)
	private String year; //选中某一年进行报表
	private String password;
	private String newpwd1;
	private String newpwd2;
	
	public String updatePassword() throws Exception{
		
		try {
			MD5Code md5 = new MD5Code();
			if(!md5.getMD5ofStr(password).equalsIgnoreCase(user.getUserPassword())){
				this.printJSON("no1");
			}else if(!newpwd1.equals(newpwd2)){
				this.printJSON("no2");
			}else{
				CmsUser u = cuservice.findUserById(user.getUserId());
				u.setUploadSize(u.getUploadSize()+1);
				u.setUserPassword(md5.getMD5ofStr(newpwd1));
				cuservice.doUpdateUser(u);
				this.printJSON("yes");
			}
		} catch (Exception e) {
			this.printJSON("no3");
			this.getLogger().info("更改密码失败");
			e.printStackTrace();
		}
		
		return null;
	}
	
	/*
	 * 注册量报表
	 */
	public String registerCountChart() throws Exception{
		StringBuffer json= new StringBuffer("{\"color\":\"#61B161\",\"label\":\"注册数量\",\"data\":[");
		try {
			List<Object[]> list1 = cuservice.registerCountChart(way,year);
			if(list1.size() > 0){
				for (Object[] obj : list1) {
					json.append("["+obj[0]+","+obj[1]+"],");
				}
				StringBuffer data = new StringBuffer(json.substring(0, json.length()-1));
				data.append("]}");
				this.printJSON(data.toString());
			}else{
				this.printJSON("{\"color\":\"#61B161\",\"label\":\"注册数量\",\"data\":[["+year+",0]]}");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/*
	 * 后台读取评论时得到评论者的名称(具体操作无需理会)
	 */
	public String findUserById() throws Exception{
		CmsUser u = cuservice.findUserById(user.getUserId());
		this.printJSON(u.getUserName());
		return null;
	}
	/*
	 * 给会员设置会员级别
	 */
	public String setUserGroup() throws Exception{
		
		try {
			String arrid[] = ids.split(",");
			for (String id : arrid) {
				CmsUser u = cuservice.findUserById(Integer.parseInt(id));
				u.setCmsGroup(user.getCmsGroup());
				cuservice.doUpdateUser(u);
			}
			this.printJSON("yes");
		} catch (NumberFormatException e) {
			this.printJSON("no");
			this.getLogger().info("设置会员组失败");
			e.printStackTrace();
		}
		
		return null;
	}
	
	/*
	 * 删除会员
	 */
	public String deleteUser() throws Exception{
		try {
			cuservice.doDeleteUser(ids);
			this.printJSON("yes");
		} catch (Exception e) {
			this.printJSON("no");
			this.getLogger().info("删除会员失败");
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * 获得所有会员
	 */
	public String findMemberByPage() throws Exception{
		
		Map condition = new HashMap();
		condition.put("obj.userName like ?", "%"+user.getUserName()+"%");
		condition.put("obj.registerTime > ?", begin);
		condition.put("obj.registerTime < ?", end);
		condition.put("obj.isAdmin = ?", 0);
		
		toJsonUtil json = new toJsonUtil();
		
		try {
			json.setRows(cuservice.findByPage(condition, null, null, page, rows));
			json.setTotal(cuservice.findCount(condition));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.printJSON(Json.toJson(json));
		
		return null;
	}
	
	
	/*
	 * 前台登陆
	 */
	public String frontlogin(){
		
		
		if(user.getUserName() == null || "".equals(user.getUserName())){
			this.getRequest().setAttribute("msg", "请输入帐号！");
			return "frontloginfail";
		}
		if(user.getUserPassword() == null || "".equals(user.getUserPassword())){
			this.getRequest().setAttribute("msg", "请输入密码！");
			return "frontloginfail";
		}
		
		
		if(this.getSession().getAttribute("rand")==null){   //有的时候注册码还没有加载出来，用户就开始登录
			this.getRequest().setAttribute("msg", "验证码错误！");
			return "frontloginfail";
		}
		String rand = (String) this.getSession().getAttribute("rand");
		MD5Code md5 = new MD5Code();
		if(validateCode != null){
			if(rand.equalsIgnoreCase(validateCode.trim())){
					user.setUserPassword(md5.getMD5ofStr(user.getUserPassword()));
					List<CmsUser> ulist = cuservice.frontLogin(user);
					if(ulist != null){
							CmsUser u = ulist.get(0);
							this.getSession().setAttribute("u", u);
							return "frontloginsuc";
					}
				this.getRequest().setAttribute("msg", "用户名或者密码错误！");
				return "frontloginfail";
			}
			
		}
			this.getRequest().setAttribute("msg", "验证码错误！");
		
		
		return "frontloginfail";
		
		
	}
	
	
	/*
	 * 前台注册
	 */
	public String register() throws Exception{
		MD5Code md5 = new MD5Code();
		
			
			try {
				if(cuservice.findUserByName(user.getUserName())){
					CmsGroup group = new CmsGroup();
					group.setGroupId(1);
					user.setCmsGroup(group);
					user.setUserPassword(md5.getMD5ofStr(user.getUserPassword()));
					user.setIsDisabled(1);
					user.setRegisterIp(InetAddress.getLocalHost().getHostAddress());
					user.setLoginCount(0);
					user.setIsAdmin(0);
					user.setUploadSize(0);
					user.setUploadTotal(1);
					user.setRegisterTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
					cuservice.doAddUser(user);
					this.getRequest().setAttribute("msg", "注册成功");
				}else{
					this.getRequest().setAttribute("msg", "此用户名已被占用");
				}
				
			} catch (Exception e) {
				this.getRequest().setAttribute("msg", "服务器异常，请联系管理员！");
				e.printStackTrace();
			}
		
		return "register";
	}
	
	
	/*
	 * 启用
	 */
	public String doUsableUser() throws Exception{
		try {
			cuservice.doUsableUser(ids);
			this.printJSON("yes");
		} catch (Exception e) {
			this.printJSON("no");
			this.getLogger().info("启用用户失败");
			e.printStackTrace();
			
		}
		
		return null;
	}
	/*
	 * 禁用用户
	 */
	public String disableUser() throws Exception{
		
		try {
			cuservice.doDisableUser(ids);
			this.printJSON("yes");
		} catch (Exception e) {
			this.printJSON("no");
			this.getLogger().info("禁用用户失败");
			e.printStackTrace();
		}
		
		return null;
	}
	/*
	 * 编辑用户
	 */
	public String updateUser() throws Exception{
		try {
				CmsUser u = cuservice.findUserById(user.getUserId());
				CmsRole role = new CmsRole();
				role.setRoleId(user.getCmsRole().getRoleId());
				CmsGroup group = new CmsGroup();
				group.setGroupId(user.getCmsGroup().getGroupId());
				u.setCmsGroup(group);
				u.setCmsRole(role);
				u.setUserName(user.getUserName());
				u.setUserEmail(user.getUserEmail());
				u.setUploadSize(u.getUploadSize()+1);
				u.setUploadTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
				cuservice.doUpdateUser(u);
				this.printJSON("yes");
		} catch (Exception e) {
			e.printStackTrace();
			this.getLogger().info("编辑用户失败");
			this.printJSON("no");
		}
	
		return null;
	}
	/*
	 * 添加用户
	 */
	public String addUser() throws Exception{
		MD5Code md5 = new MD5Code();
		
		try {
			if(cuservice.findUserByName(user.getUserName())){
				user.setUserPassword(md5.getMD5ofStr("123456789"));
				user.setIsDisabled(1);
				user.setRegisterIp(InetAddress.getLocalHost().getHostAddress());
				user.setLoginCount(0);
				user.setIsAdmin(1);
				user.setUploadSize(0);
				user.setUploadTotal(1);
				user.setRegisterTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
				cuservice.doAddUser(user);
				this.printJSON("yes");
			}else{
				this.printJSON("noyes");  //用户名已被占用
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.getLogger().info("添加用户失败");
			this.printJSON("no");
		}
		
		return null;
	}
	/*
	 * 注销
	 */
	public String logout(){
		this.getSession().removeAttribute("user");
		this.getSession().setAttribute("history", "history");
		return "logout";
	}
	/*
	 * 登录
	 */
	public String login() throws Exception{
		if(user.getUserName() == null || "".equals(user.getUserName())){
			this.getRequest().setAttribute("msg", "请输入帐号！");
			return "loginfail";
		}
		if(user.getUserPassword() == null || "".equals(user.getUserPassword())){
			this.getRequest().setAttribute("msg", "请输入密码！");
			return "loginfail";
		}
		
		if(this.getSession().getAttribute("rand")==null){   //有的时候注册码还没有加载出来，用户就开始登录
			this.getRequest().setAttribute("msg", "验证码错误！");
			return "loginfail";
		}
		String rand = (String) this.getSession().getAttribute("rand");
		MD5Code md5 = new MD5Code();
		if(validateCode != null){
			if(rand.equalsIgnoreCase(validateCode.trim())){
					String pwd = user.getUserPassword();
					user.setUserPassword(md5.getMD5ofStr(user.getUserPassword()));
					List<CmsUser> ulist = cuservice.login(user);
					if(ulist != null){
							CmsUser u = ulist.get(0);
							this.getSession().removeAttribute("history");
							this.getSession().setAttribute("user", u);
							CmsLoginSuccess cls = new CmsLoginSuccess();
							cls.setUser(u);
							cls.setIp(InetAddress.getLocalHost().getHostAddress());
							cls.setDateTime(new Date().toLocaleString());
							clsservice.doAddLoginLog(cls);
							
							
							return "loginsuc";
					}
					
				this.getRequest().setAttribute("msg", "用户名或者密码错误！");
				
				CmsLoginFailure cf = new CmsLoginFailure();
				cf.setDateTime(new Date().toLocaleString());
				cf.setIp(InetAddress.getLocalHost().getHostAddress());
				cf.setUserName(user.getUserName());
				cf.setPassword(pwd);
				clfservice.doAddLoginLog(cf);
				this.getLogger().info("登陆失败");
				return "loginfail";
			}
			
		}
		this.getRequest().setAttribute("msg", "验证码错误！");
		
		return "loginfail";
	}
	/*
	 * 分页查询
	 */
	public String findByPage() throws Exception{
		Map condition = new HashMap();
		condition.put("obj.isAdmin = ?", 1);
		if(user.getUserName() != null && !"".equals(user.getUserName())){
			condition.put("obj.userName like ?", "%"+user.getUserName()+"%");
		}
		if(end != null && !"".equals(end)){
			condition.put("obj.registerTime <= ?", end);
		}
		if(begin != null && !"".equals(begin)){
			condition.put("obj.registerTime >= ?", begin);
		}
		if(condition.size() == 0){
			condition = null;
		}
		toJsonUtil tojson = new toJsonUtil(cuservice.findByPage(condition, null, null, page, rows),cuservice.findCount(condition));
		
		this.printJSON(Json.toJson(tojson));
		
		return null;
		
	}
	public CmsUser getUser() {
		return user;
	}

	public void setUser(CmsUser user) {
		this.user = user;
	}

	public ICmsUserService getCuservice() {
		return cuservice;
	}

	public void setCuservice(ICmsUserService cuservice) {
		this.cuservice = cuservice;
	}
	public String getValidateCode() {
		return validateCode;
	}
	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public String getBegin() {
		return begin;
	}
	public void setBegin(String begin) {
		this.begin = begin;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}


	public ICmsLoginFailureService getClfservice() {
		return clfservice;
	}


	public void setClfservice(ICmsLoginFailureService clfservice) {
		this.clfservice = clfservice;
	}


	public ICmsLoginSuccessService getClsservice() {
		return clsservice;
	}


	public void setClsservice(ICmsLoginSuccessService clsservice) {
		this.clsservice = clsservice;
	}


	public String getWay() {
		return way;
	}


	public void setWay(String way) {
		this.way = way;
	}


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewpwd1() {
		return newpwd1;
	}

	public void setNewpwd1(String newpwd1) {
		this.newpwd1 = newpwd1;
	}

	public String getNewpwd2() {
		return newpwd2;
	}

	public void setNewpwd2(String newpwd2) {
		this.newpwd2 = newpwd2;
	}
	
	
}
