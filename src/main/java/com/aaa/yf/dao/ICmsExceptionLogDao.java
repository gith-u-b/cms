package com.aaa.yf.dao;

import com.aaa.yf.entity.CmsExceptionLog;

public interface ICmsExceptionLogDao extends IBaseDao<CmsExceptionLog> {

	public void deleteExceptionLog(String ids);
	
}
