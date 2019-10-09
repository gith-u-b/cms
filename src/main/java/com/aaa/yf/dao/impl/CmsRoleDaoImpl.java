package com.aaa.yf.dao.impl;

import org.springframework.stereotype.Repository;

import com.aaa.yf.dao.ICmsRoleDao;
import com.aaa.yf.entity.CmsRole;
@Repository("crdao")
public class CmsRoleDaoImpl extends BaseDaoImpl<CmsRole> implements ICmsRoleDao {

	private String entityName = "com.aaa.yf.entity.CmsRole";

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public void doDeleteRole(String ids) {
		
		this.executeHql("delete from CmsRole r where r.roleId in ("+ids+")");
	}

	public void doDeleteRP(String ids) {
		this.executeSql("delete from cms_role_power  where role_id in ("+ids+")");
	} 
	
}
