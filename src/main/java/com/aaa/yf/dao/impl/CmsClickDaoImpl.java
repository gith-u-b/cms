package com.aaa.yf.dao.impl;

import org.springframework.stereotype.Repository;

import com.aaa.yf.dao.ICmsClickDao;
import com.aaa.yf.entity.CmsClick;
import com.aaa.yf.util.ConfigUtil;

@Repository("cclickdao")
public class CmsClickDaoImpl extends BaseDaoImpl<CmsClick> implements ICmsClickDao {
	private String entityName = "com.aaa.yf.entity.CmsClick";

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	//把大量点击记录数据插入的数据库
	public void batchSave() {
		this.executeSql("LOAD DATA INFILE '"+ConfigUtil.click+"' INTO TABLE cms_click(content_id,click_time)");
		
	}

	//清空超时数据库的点击记录
	public void clearData() {
		this.executeSql("delete  from cms_click  where DATEDIFF(SYSDATE(),click_time)>7");
	}

	//清空各种日志记录
	public void clearLog() {

		this.executeSql("delete from cms_login_success");
		this.executeSql("delete from cms_login_failure");
		this.executeSql("delete from cms_operate_log");
		this.executeSql("delete from cms_exception_log");
		
	}
}
