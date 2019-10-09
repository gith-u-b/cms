package com.aaa.yf.dao;

import com.aaa.yf.entity.CmsOperateLog;

public interface ICmsOperateLogDao extends IBaseDao<CmsOperateLog> {
	
	public void deleteOperateLog(String ids);

}
