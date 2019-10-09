package com.aaa.yf.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aaa.yf.entity.CmsContentType;
import com.aaa.yf.service.ICmsContentTypeService;
import com.et.mvc.util.Json;

@Controller
@Scope("prototype")
@Action
@ParentPackage(value="cms")
public class ContentTypeAction extends BaseAction{

	@Autowired
	private ICmsContentTypeService cnewtservice;
	private CmsContentType contentType;
	
	
	public String findAllContentType() throws Exception{
		this.printJSON(Json.toJson(cnewtservice.findAllContentType()));
		return null;
	}
	
	
	public ICmsContentTypeService getCnewtservice() {
		return cnewtservice;
	}


	public void setCnewtservice(ICmsContentTypeService cnewtservice) {
		this.cnewtservice = cnewtservice;
	}


	public CmsContentType getContentType() {
		return contentType;
	}
	public void setContentType(CmsContentType contentType) {
		this.contentType = contentType;
	}
	
	
}
