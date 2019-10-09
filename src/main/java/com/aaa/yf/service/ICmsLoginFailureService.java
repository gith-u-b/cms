package com.aaa.yf.service;

import java.util.List;
import java.util.Map;

import com.aaa.yf.entity.CmsLoginFailure;

public interface ICmsLoginFailureService {

	public void doAddLoginLog(CmsLoginFailure clf);
	public List<CmsLoginFailure> findLoginFailLog(Map condition, String sortName, String sortType,
			Integer page, Integer rows);
	public long findCount(Map condition);
	public void doDeleteLoginLog(String ids);
}
