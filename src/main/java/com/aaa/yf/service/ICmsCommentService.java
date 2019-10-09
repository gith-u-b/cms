package com.aaa.yf.service;

import java.util.List;
import java.util.Map;

import com.aaa.yf.entity.CmsComment;

public interface ICmsCommentService {

	public void doComment(CmsComment comment); //评论新闻
	public List<CmsComment> findCommentByPage(Map condition, String sortName, String sortType,
			Integer page, Integer rows); 
	public long findCount(Map condition);
	
	public void doCheckComment(String ids);
	public void doDeleteComment(String ids);
	
	public void doRecommendComment(String ids);  //推荐
	public CmsComment findCommentById(Integer id);
	
	public void doReplyComment(CmsComment c);   //更改或者回复
	
	public List<Object[]> commentCountChart(String way,String year);  //统计回复   
	
}
