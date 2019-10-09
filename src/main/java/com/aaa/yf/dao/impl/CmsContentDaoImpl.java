package com.aaa.yf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aaa.yf.dao.ICmsContentDao;
import com.aaa.yf.entity.CmsContent;

@Repository("cnewdao")
public class CmsContentDaoImpl extends BaseDaoImpl<CmsContent> implements ICmsContentDao { 

	private String entityName = "com.aaa.yf.entity.CmsContent";

	
	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public void deleteContent(Integer cid) {
		this.executeHql("update CmsContent c set c.isDisplay = 0 where c.cmsChannel.channelId = ?",cid);
	}

	public void deleteContent(String ids) {
		this.executeHql("update CmsContent c set c.isDisplay = 0 where c.contentId in ("+ids+")");
	}

	public List<CmsContent> findHotContent() {
		return this.findByPage("select cn  from CmsClick cc INNER JOIN cc.cmsContent cn where cn.status=1 and cn.isDisplay=1  GROUP BY cc.cmsContent.contentId ORDER BY COUNT(cn) DESC  ", 1, 9);
	}

	public void yesNoStatic(String yn,String ids) {
		if(yn.equals("yes")){
			this.executeHql("update CmsContent c set c.isStatic = 1 where c.contentId in ("+ids+")");
		}else{
			this.executeHql("update CmsContent c set c.isStatic = 0 where c.contentId in ("+ids+")");
		}
	}

	public void checkNews(String yn,String ids) {
		if(yn.equals("yes")){
			this.executeHql("update CmsContent c set c.status = 1 where c.contentId in ("+ids+")");
		}else{
			this.executeHql("update CmsContent c set c.status = 0 where c.contentId in ("+ids+")");
		}
	}

	public void updateGarbage(String yn, String ids) {
		if(yn.equals("yes")){
			this.executeHql("delete from CmsContent c where c.contentId in ("+ids+")");
		}else{
			this.executeHql("update from CmsContent c set c.isDisplay = 1 where c.contentId in ("+ids+")");
		}
	}

	public void setContentChannel(Integer id, String ids) {
		this.executeHql("update from CmsContent c set c.cmsChannel.channelId = "+id+" where c.contentId in ("+ids+")");
	}

	public void deleteContentByCid(String ids) {
		this.executeHql("delete from CmsContent c where c.cmsChannel.channelId in ("+ids+")");
	}
	
	
}
