package com.aaa.yf.service;

import java.util.List;
import java.util.Map;

import com.aaa.yf.entity.CmsChannel;

public interface ICmsChannelService {

	public List<CmsChannel> findAllChannel(Map condition, String sortName, String sortType,
			Integer page, Integer rows);
	public long findCount();  //暂时没用
	public void doAddChannel(CmsChannel channel);
	public CmsChannel findById(String state,Integer id);
	public void doDeleteChannel(Integer id);
	public void doUpdateChannel(CmsChannel channel);
	public String getIds(Integer id);
	public boolean yesNoParent(Integer id);  //判断是不是父栏目
	public String getParentIds(Integer id,Integer pid);
	public void doUpdateGarbageChannel(String yn,String ids); //还原或者删除回收站的栏目
	
}
