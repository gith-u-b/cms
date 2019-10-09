package com.aaa.yf.service;

import java.util.List;
import java.util.Map;

import com.aaa.yf.entity.CmsLoginSuccess;

public interface ICmsLoginSuccessService {

	public void doAddLoginLog(CmsLoginSuccess cls);
	public List<CmsLoginSuccess> findLoginSucLog(Map condition, String sortName, String sortType,
			Integer page, Integer rows);
	public long findCount(Map condition);
	public void doDeleteLoginLog(String ids);
}
