package com.aaa.yf.dao;

import com.aaa.yf.entity.CmsClick;

public interface ICmsClickDao extends IBaseDao<CmsClick>{

	public void batchSave();   //保存点击记录到数据库
	public void clearData();   //清空大于7天的点击记录
	public void clearLog();    //清空各种日志
}
