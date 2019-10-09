package com.aaa.yf.dao.impl;

import org.springframework.stereotype.Repository;

import com.aaa.yf.dao.ICmsExceptionLogDao;
import com.aaa.yf.entity.CmsExceptionLog;

@Repository("celdao")
public class CmsExceptionLogDaoImpl extends BaseDaoImpl<CmsExceptionLog> implements ICmsExceptionLogDao {

	private String entityName = "com.aaa.yf.entity.CmsExceptionLog";
	
	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public void deleteExceptionLog(String ids) {
		this.executeHql("delete from CmsExceptionLog c where c.logId in ("+ids+")");
	}
	
	
}
