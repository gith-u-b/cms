package com.aaa.yf.dao.impl;


import org.springframework.stereotype.Repository;

import com.aaa.yf.dao.ICmsTemplateTypeDao;
import com.aaa.yf.entity.CmsTemplateType;

@Repository("cttdao")
public class CmsTemplateTypeDaoImpl extends BaseDaoImpl<CmsTemplateType> implements ICmsTemplateTypeDao {

	private String entityName = "com.aaa.yf.entity.CmsTemplateType";
	
	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}


	
	
}
