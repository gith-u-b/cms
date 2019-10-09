package com.aaa.yf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aaa.yf.dao.ICmsTemplateDao;
import com.aaa.yf.entity.CmsTemplate;
@Repository("ctdao")
public class CmsTemplateDaoImpl extends BaseDaoImpl<CmsTemplate> implements ICmsTemplateDao {

	private String entityName = "com.aaa.yf.entity.CmsTemplate";

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public List<CmsTemplate> findTemplateByType() {
		return this.findByHql("select ct from CmsTemplate ct inner join ct.cmsTemplateType ctt ");
	}
	
}
