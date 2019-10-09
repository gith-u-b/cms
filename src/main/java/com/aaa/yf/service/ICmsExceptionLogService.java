package com.aaa.yf.service;

import java.util.List;
import java.util.Map;

import com.aaa.yf.entity.CmsExceptionLog;

public interface ICmsExceptionLogService {

	public List<CmsExceptionLog> findExceptionLog(Map condition, String sortName, String sortType,
			Integer page, Integer rows);
	public long findCount(Map condition);
	public void doDeleteExceptionLog(String ids);
}
