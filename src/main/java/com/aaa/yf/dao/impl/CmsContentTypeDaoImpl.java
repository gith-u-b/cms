package com.aaa.yf.dao.impl;

import org.springframework.stereotype.Repository;

import com.aaa.yf.dao.ICmsContentTypeDao;
import com.aaa.yf.entity.CmsContentType;

@Repository("cnewtdao")
public class CmsContentTypeDaoImpl extends BaseDaoImpl<CmsContentType> implements ICmsContentTypeDao {

	private String entityName = "com.aaa.yf.entity.CmsContentType";

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	
	
}
