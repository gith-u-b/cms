package com.aaa.yf.dao.impl;


import org.springframework.stereotype.Repository;

import com.aaa.yf.dao.ICmsChannelDao;
import com.aaa.yf.entity.CmsChannel;

@Repository("ccdao")
public class CmsChannelDaoImpl extends BaseDaoImpl<CmsChannel> implements ICmsChannelDao {

	private String entityName = "com.aaa.yf.entity.CmsChannel";

	
	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public void doDeleteChannel(Integer id) {
		this.executeHql("update CmsChannel c set c.isDisplay = 0  where c.channelId = "+id+" or c.parentId = "+id+"");
	}

	public void updateGarbageChannel(String yn, String ids) {
		if(yn.equals("yes")){
			this.executeHql("delete from CmsChannel c where c.channelId in ("+ids+")");
		}else{
			this.executeHql("update from CmsChannel c set c.isDisplay = 1 where c.channelId in ("+ids+")");
		}
	}

	
	
}
