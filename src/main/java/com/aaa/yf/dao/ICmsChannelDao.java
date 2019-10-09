package com.aaa.yf.dao;


import com.aaa.yf.entity.CmsChannel;

public interface ICmsChannelDao extends IBaseDao<CmsChannel> {

	public void doDeleteChannel(Integer id);
	public void updateGarbageChannel(String yn,String ids);//还原或者删除回收站的栏目
	
}
