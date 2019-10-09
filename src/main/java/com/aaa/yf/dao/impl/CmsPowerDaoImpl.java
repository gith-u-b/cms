package com.aaa.yf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aaa.yf.dao.ICmsPowerDao;
import com.aaa.yf.entity.CmsPower;

@Repository("cpdao")
public class CmsPowerDaoImpl extends BaseDaoImpl<CmsPower> implements ICmsPowerDao {

	private String entityName = "com.aaa.yf.entity.CmsPower";

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public List<CmsPower> findAllPower() {
		return this.findByHql("select p from CmsPower p");
	}

	public void doDeletePower(Integer id) {
		this.executeSql("delete from cms_power where power_id = "+id+" or power_pid = "+id+" ");
	}

	public void doDeleteRP(Integer id) {
		this.executeSql("delete from cms_role_power where power_id in (select power_id from cms_power where power_pid = "+id+" or power_id = "+id+")");
	} 
	
}
