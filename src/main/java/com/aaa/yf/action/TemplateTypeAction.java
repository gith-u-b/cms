package com.aaa.yf.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.aaa.yf.entity.CmsTemplateType;
import com.aaa.yf.service.ICmsTemplateTypeService;
import com.et.mvc.util.Json;

@Controller
@Action
@ParentPackage(value="cms")
public class TemplateTypeAction extends BaseAction {
	@Autowired
	private ICmsTemplateTypeService cttservice;
	private CmsTemplateType ttype;
	
	public String findAllTemplateType() throws Exception{
		List<CmsTemplateType> list = cttservice.findTemplateTypeByCondition(null, null, null, null, null);
		this.printJSON(Json.toJson(list));
		return null;
	}
	
	public ICmsTemplateTypeService getCttservice() {
		return cttservice;
	}
	public void setCttservice(ICmsTemplateTypeService cttservice) {
		this.cttservice = cttservice;
	}
	public CmsTemplateType getTtype() {
		return ttype;
	}
	public void setTtype(CmsTemplateType ttype) {
		this.ttype = ttype;
	}
	
}
