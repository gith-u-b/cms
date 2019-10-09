package com.aaa.yf.dao;

import java.util.List;

import com.aaa.yf.entity.CmsContent;

public interface ICmsContentDao extends IBaseDao<CmsContent> {

	public void deleteContent(Integer cid);  //通过删除栏目要删除本栏目下的新闻（暂时放入回收站）
	public void deleteContent(String ids); // 删除指定新闻
	public List<CmsContent> findHotContent();  //点击量排行新闻
	public void yesNoStatic(String yn,String ids);//新闻静态化或者新闻非静态化
	public void checkNews(String yn,String ids);  //审核新闻/撤销新闻
	public void updateGarbage(String yn,String ids); //彻底删除新闻或者还原新闻
	public void setContentChannel(Integer id,String ids); //给新闻设置栏目
	public void deleteContentByCid(String ids);  //通过栏目删除新闻
}
