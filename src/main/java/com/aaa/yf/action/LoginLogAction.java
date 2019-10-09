package com.aaa.yf.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aaa.yf.entity.CmsLoginFailure;
import com.aaa.yf.entity.CmsLoginSuccess;
import com.aaa.yf.entity.CmsUser;
import com.aaa.yf.service.ICmsLoginFailureService;
import com.aaa.yf.service.ICmsLoginSuccessService;
import com.aaa.yf.util.toJsonUtil;
import com.et.mvc.util.Json;

@Controller
@Scope("prototype")
@Action
@ParentPackage(value="cms")
public class LoginLogAction extends BaseAction {

	@Autowired
	private ICmsLoginFailureService clfservice;
	@Autowired
	private ICmsLoginSuccessService clsservice;
	
	private CmsLoginSuccess cls = new CmsLoginSuccess();
	private CmsLoginFailure clf = new CmsLoginFailure();
	private String begin;
	private String end;
	private String ip;
	private Integer page;
	private Integer rows;
	private String ids;
	
	
	public String findLoginFailLog() throws Exception{
		Map condition = new HashMap();
		condition.put("obj.ip like ?", "%"+ip+"%");
		condition.put("obj.dateTime > ?", begin);
		condition.put("obj.dateTime < ?", end);
		toJsonUtil json = new toJsonUtil();
		json.setRows(clfservice.findLoginFailLog(condition, "obj.dateTime", "desc", page, rows));
		json.setTotal(clfservice.findCount(condition));
		this.printJSON(Json.toJson(json));
		return null;
		
	}
	
	public String deleteLoginFailLog() throws Exception{
		try {
			clfservice.doDeleteLoginLog(ids);
			this.printJSON("yes");
		} catch (Exception e) {
			this.printJSON("no");
			this.getLogger().info("删除登陆失败日志失败");
			e.printStackTrace();
		}
		
		return null;
	}
	public String deleteLoginSucLog() throws Exception{
		try {
			clsservice.doDeleteLoginLog(ids);
			this.printJSON("yes");
		} catch (Exception e) {
			this.printJSON("no");
			this.getLogger().info("删除登陆成功日志失败");
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public String findLoginSucLog() throws Exception{
		Map condition = new HashMap();
		if(cls.getUser() != null){
		condition.put("obj.user.userName like ?", "%"+cls.getUser().getUserName()+"%");
		}
		condition.put("obj.ip like ?", "%"+ip+"%");
		condition.put("obj.dateTime > ?", begin);
		condition.put("obj.dateTime < ?", end);
		
		toJsonUtil json = new toJsonUtil();
		json.setRows(clsservice.findLoginSucLog(condition, "obj.dateTime", "desc", page, rows));
		json.setTotal(clsservice.findCount(condition));
		this.printJSON(Json.toJson(json));
		return null;
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

	


	public CmsLoginSuccess getCls() {
		return cls;
	}


	public void setCls(CmsLoginSuccess cls) {
		this.cls = cls;
	}


	public CmsLoginFailure getClf() {
		return clf;
	}


	public void setClf(CmsLoginFailure clf) {
		this.clf = clf;
	}


	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
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


	public String getIds() {
		return ids;
	}


	public void setIds(String ids) {
		this.ids = ids;
	}
	
	
	
}
