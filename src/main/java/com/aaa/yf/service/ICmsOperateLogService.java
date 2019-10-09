package com.aaa.yf.service;

import java.util.List;
import java.util.Map;

import com.aaa.yf.entity.CmsExceptionLog;
import com.aaa.yf.entity.CmsOperateLog;

public interface ICmsOperateLogService {

	public List<CmsOperateLog> findOperateLog(Map condition, String sortName, String sortType,
			Integer page, Integer rows);
	public long findCount(Map condition);
	public void doDeleteOperateLog(String ids);
}
