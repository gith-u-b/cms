package com.aaa.yf.dao;

import com.aaa.yf.entity.CmsLoginSuccess;

public interface ICmsLoginSuccessDao extends IBaseDao<CmsLoginSuccess> {

	public void deleteLoginLog(String ids);
}
