package com.aaa.yf.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.aaa.yf.dao.ICmsDataDao;
import com.aaa.yf.util.CmsDatapool;
@Repository("cddao")
public class CmsDataDaoImpl extends BaseDaoImpl<CmsDatapool> implements ICmsDataDao {

	private String entityName = "com.aaa.yf.util.CmsDatapool";

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public List<CmsDatapool> findAllTable() {
		List<String> list = this.findBySql("select table_name from information_schema.tables t where t.table_schema='mycms'");
		List<CmsDatapool> cmsDatapools = new ArrayList<CmsDatapool>();
		for (String string : list) {
			CmsDatapool cmsDatapool = new CmsDatapool();  //把查询出的表封装到一个工具类中
			cmsDatapool.setTableName(string);
			cmsDatapools.add(cmsDatapool);
		}
		
		return cmsDatapools;
	}

	public List<CmsDatapool> findTableColumnByTableName(String tableName) {
		List<Object[]> list = this.findBySql("select COLUMN_NAME,COLUMN_TYPE,COLUMN_KEY,COLUMN_DEFAULT,CHARACTER_SET_NAME from information_schema.columns where table_name='"+tableName+"'");
		List<CmsDatapool> cmsDatapools = new ArrayList<CmsDatapool>();
		for (Object[] object:list) {
			CmsDatapool cmsDatapool = new CmsDatapool();
			cmsDatapool.setColumName(object[0]+"");
			cmsDatapool.setColumType(object[1]+"");
			cmsDatapool.setColumAttr(object[2]+"");
			cmsDatapool.setColumDefaultValue(object[3]+"");
			cmsDatapool.setCharacterSetName(object[4]+"");
			cmsDatapools.add(cmsDatapool);
		}
		return cmsDatapools;
	}

	public List<CmsDatapool> findAllDatabase() {
		
		List<String> list = this.findBySql("select DISTINCT TABLE_SCHEMA from information_schema.tables");
		List<CmsDatapool> cmsDatapools = new ArrayList<CmsDatapool>();
		for (String string : list) {
			CmsDatapool cmsDatapool = new CmsDatapool();
			cmsDatapool.setDataBaseName(string);
			cmsDatapools.add(cmsDatapool);
		}
		return cmsDatapools;
	}

	
}
