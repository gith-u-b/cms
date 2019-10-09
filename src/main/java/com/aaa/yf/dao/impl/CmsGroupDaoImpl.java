package com.aaa.yf.dao.impl;

import org.springframework.stereotype.Repository;

import com.aaa.yf.dao.ICmsGroupDao;
import com.aaa.yf.entity.CmsGroup;

@Repository("cgdao")
public class CmsGroupDaoImpl extends BaseDaoImpl<CmsGroup> implements ICmsGroupDao {

	private String entityName = "com.aaa.yf.entity.CmsGroup";

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	
	
}
