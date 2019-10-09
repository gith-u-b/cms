package com.aaa.yf.dao.impl;

import org.springframework.stereotype.Repository;

import com.aaa.yf.dao.ICmsLoginFailureDao;
import com.aaa.yf.entity.CmsLoginFailure;
@Repository("clfdao")
public class CmsLoginFailureDaoImpl extends BaseDaoImpl<CmsLoginFailure> implements ICmsLoginFailureDao {

	private String entityName = "com.aaa.yf.entity.CmsLoginFailure";

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public void deletLoginLog(String ids) {
		this.executeHql("delete from CmsLoginFailure c where c.id in ("+ids+")");
	}
	
}
