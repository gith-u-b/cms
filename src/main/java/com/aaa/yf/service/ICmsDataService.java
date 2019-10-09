package com.aaa.yf.service;

import java.util.List;

import com.aaa.yf.util.CmsDatapool;

public interface ICmsDataService {

	public List<CmsDatapool> findAllTable();
	public List<CmsDatapool> findTableColumnByTableName(String tableName);
	public List<CmsDatapool> findAllDatabase();
	
}
