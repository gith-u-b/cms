package com.aaa.yf.dao;

import com.aaa.yf.entity.CmsRole;

public interface ICmsRoleDao extends IBaseDao<CmsRole>{
	public void doDeleteRole(String ids);
	public void doDeleteRP(String ids);
	
}
