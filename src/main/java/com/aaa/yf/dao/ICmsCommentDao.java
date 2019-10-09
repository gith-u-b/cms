package com.aaa.yf.dao;

import java.util.List;

import com.aaa.yf.entity.CmsComment;

public interface ICmsCommentDao extends IBaseDao<CmsComment> {

	public void deleteComment(String ids);
	public void checkComment(String ids);
	public void recommendComment(String ids);
	
	
	public List<Object[]> commentCountChart(String way,String year);  //评论统计
}
