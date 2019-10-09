package com.aaa.yf.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.aaa.yf.dao.ICmsCommentDao;
import com.aaa.yf.entity.CmsComment;

@Repository("ccommentdao")
public class CmsCommentDaoImpl extends BaseDaoImpl<CmsComment> implements ICmsCommentDao {

	private String entityName = "com.aaa.yf.entity.CmsComment";

	
	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public void deleteComment(String ids) {
		this.executeHql("delete from CmsComment c where c.comId in ("+ids+")");
	}

	public void checkComment(String ids) {

		this.executeHql("update from CmsComment c set c.isChecked = 1 where c.comId in ("+ids+")");
	}

	public void recommendComment(String ids) {
		this.executeHql("update from CmsComment c set c.isRecommend = 1 where c.comId in ("+ids+")");
	}

	public List<Object[]> commentCountChart(String way,String year) {
		List<Object[]> list = new ArrayList<Object[]>();
		if(year.equals("1")){	  //则是按年查找
			list = this.findObjectByHql("select YEAR(u.createTime) ,count(u)   from CmsComment u group by YEAR(u.createTime)");
		}
		if(!year.equals("1") && way.equals("m")){
			list = this.findObjectByHql("select MONTH(u.createTime) ,count(u)   from CmsComment u where YEAR(u.createTime) = '"+year+"' group by YEAR(u.createTime) ");
		}
		if(!year.equals("1") && way.equals("w")){
			list = this.findObjectByHql("select WEEKDAY(u.createTime) ,count(u)   from CmsComment u where YEAR(u.createTime) = '"+year+"' group by YEAR(u.createTime) ");
		}
		if(!year.equals("1") && way.equals("d")){
			list = this.findObjectByHql("select DAYOFMONTH(u.createTime) ,count(u)   from CmsComment u where YEAR(u.createTime) = '"+year+"' group by YEAR(u.createTime) ");
		}
		
		return list;
	}
}
