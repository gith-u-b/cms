package com.aaa.yf.dao.impl;

import org.springframework.stereotype.Repository;

import com.aaa.yf.dao.ICmsOperateLogDao;
import com.aaa.yf.entity.CmsOperateLog;

@Repository("coldao")
public class CmsOperateLogDaoImpl extends BaseDaoImpl<CmsOperateLog> implements ICmsOperateLogDao {

	private String entityName = "com.aaa.yf.entity.CmsOperateLog";

	
	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public void deleteOperateLog(String ids) {
		this.executeHql("delete from CmsOperateLog c where c.logId in ("+ids+")");
	}
	
}
