package com.aaa.yf.dao;

import com.aaa.yf.entity.CmsLoginFailure;

public interface ICmsLoginFailureDao extends IBaseDao<CmsLoginFailure> {
	
	public void deletLoginLog(String ids);
	
}
