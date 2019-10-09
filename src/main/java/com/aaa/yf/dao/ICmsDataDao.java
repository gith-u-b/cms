package com.aaa.yf.dao;

import java.util.List;

import com.aaa.yf.util.CmsDatapool;

public interface ICmsDataDao extends IBaseDao<CmsDatapool> {

	public List<CmsDatapool> findAllTable();
	public List<CmsDatapool> findTableColumnByTableName(String tableName);
	public List<CmsDatapool> findAllDatabase();
}
