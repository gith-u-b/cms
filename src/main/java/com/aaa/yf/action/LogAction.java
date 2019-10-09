package com.aaa.yf.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aaa.yf.entity.CmsExceptionLog;
import com.aaa.yf.entity.CmsOperateLog;
import com.aaa.yf.service.ICmsExceptionLogService;
import com.aaa.yf.service.ICmsOperateLogService;
import com.aaa.yf.util.toJsonUtil;
import com.et.mvc.util.Json;

@Controller
@Scope("prototype")
@Action
@ParentPackage(value="cms")
public class LogAction extends BaseAction {

	@Autowired
	private ICmsExceptionLogService celservice;
	@Autowired
	private ICmsOperateLogService colservice;
	private CmsExceptionLog cel = new CmsExceptionLog();
	private CmsOperateLog col = new CmsOperateLog();
	private String begin;
	private String end;
	private Integer page;
	private Integer rows;
	private String ids;
	
	public String deleteOperateLog() throws Exception{
		try {
			colservice.doDeleteOperateLog(ids);
			this.printJSON("yes");
		} catch (Exception e) {
			this.printJSON("no");
			this.getLogger().info("删除操作日志失败");
			e.printStackTrace();
		}
		return null;
	}
	
	public String findOperateLog() throws Exception{
		Map condition = new HashMap();
		condition.put("obj.logTime >= ?", begin);
		condition.put("obj.logTime <= ?", end);
		condition.put("obj.logUrl like ?", "%"+col.getLogUrl()+"%");
		condition.put("obj.logMethode like ?", "%"+col.getLogMethode()+"%");
		if(col.getCmsUser() != null){
			condition.put("obj.cmsUser.userName like ?", "%"+col.getCmsUser().getUserName()+"%");
		}
		toJsonUtil json = new toJsonUtil();
		json.setRows(colservice.findOperateLog(condition, "obj.logTime", "desc", page, rows));
		json.setTotal(colservice.findCount(condition));
		this.printJSON(Json.toJson(json));
		
		return null;
	}
	
	public String deleteExceptionLog() throws Exception{
		
		try {
			celservice.doDeleteExceptionLog(ids);
			this.printJSON("yes");
		} catch (Exception e) {
			this.printJSON("no");
			this.getLogger().info("删除异常日志失败");
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String findExceptionLog() throws Exception{
		Map condition = new HashMap();
		condition.put("obj.logTime >= ?", begin);
		condition.put("obj.logTime <= ?", end);
		condition.put("obj.logSite like ?", "%"+cel.getLogSite()+"%");
		condition.put("obj.logMsg like ?", "%"+cel.getLogMsg()+"%");
		
		toJsonUtil json = new toJsonUtil();
		json.setRows(celservice.findExceptionLog(condition, "obj.logTime", "desc", page, rows));
		json.setTotal(celservice.findCount(condition));
		this.printJSON(Json.toJson(json));
		
		return null;
	}
	
	public ICmsExceptionLogService getCelservice() {
		return celservice;
	}
	public void setCelservice(ICmsExceptionLogService celservice) {
		this.celservice = celservice;
	}
	public ICmsOperateLogService getColservice() {
		return colservice;
	}
	public void setColservice(ICmsOperateLogService colservice) {
		this.colservice = colservice;
	}
	public CmsExceptionLog getCel() {
		return cel;
	}
	public void setCel(CmsExceptionLog cel) {
		this.cel = cel;
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

	public CmsOperateLog getCol() {
		return col;
	}

	public void setCol(CmsOperateLog col) {
		this.col = col;
	}
	
	
}
