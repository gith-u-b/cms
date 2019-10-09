package com.aaa.yf.dao.impl;

import org.springframework.stereotype.Repository;

import com.aaa.yf.dao.ICmsDuplicateDao;
import com.aaa.yf.entity.CmsDuplicate;

@Repository("cdupdao")
public class CmsDuplicateDaoImpl extends BaseDaoImpl<CmsDuplicate> implements ICmsDuplicateDao{
	
	private String entityName = "com.aaa.yf.entity.CmsDuplicate";

	
	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	
}
