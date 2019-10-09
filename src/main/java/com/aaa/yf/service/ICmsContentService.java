package com.aaa.yf.service;

import java.util.List;
import java.util.Map;

import com.aaa.yf.entity.CmsContent;

public interface ICmsContentService {

	public List<CmsContent> findAllContent(Map condition, String sortName, String sortType,
			Integer page, Integer rows);
	public long findCount(Map condition);
	public void doAddContent(CmsContent content);
	public void doDeleteContent(String ids);
	public void doUpdateContent(CmsContent content);
	public List<CmsContent> findHotContent();  //点击量排行新闻
	public CmsContent findContentById(Integer id); 
	public void doYesNoStatic(String yn,String ids);  //静态新闻
	public void doCheckNews(String yn,String ids); //审核新闻/撤销新闻
	public void doUpdateGarbage(String yn,String ids); //彻底删除新闻或者恢复新闻
	public void doSetContentChannel(Integer id,String ids); //设置新闻栏目
	public void doDeleteContentByCid(String ids); //通过栏目删除新闻
}
