package com.aaa.yf.dao.impl;

import org.springframework.stereotype.Repository;

import com.aaa.yf.dao.ICmsLoginSuccessDao;
import com.aaa.yf.entity.CmsLoginSuccess;
@Repository("clsdao")
public class CmsLoginSuccessDaoImpl extends BaseDaoImpl<CmsLoginSuccess> implements ICmsLoginSuccessDao {

	private String entityName = "com.aaa.yf.entity.CmsLoginSuccess";

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public void deleteLoginLog(String ids) {
		this.executeHql("delete from CmsLoginSuccess c where c.id in ("+ids+")");
	}
	
}
