package com.aaa.yf.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aaa.yf.dao.ICmsChannelDao;
import com.aaa.yf.dao.ICmsContentDao;
import com.aaa.yf.entity.CmsChannel;
import com.aaa.yf.service.ICmsChannelService;

@Service("ccservice")
public class CmsChannelServiceImpl implements ICmsChannelService {

	@Autowired
	private ICmsChannelDao ccdao;
	@Autowired
	private ICmsContentDao cnewdao;
	
	public List<CmsChannel> findAllChannel(Map condition, String sortName, String sortType,
			Integer page, Integer rows) {
		
		List<CmsChannel> clist =  ccdao.findInfo(condition, sortName, sortType, page, rows);
		for (CmsChannel cmsChannel : clist) {
			cmsChannel.getCmsTemplate().getTemplateName();
		}
		return clist;
	}
	
	public ICmsChannelDao getCcdao() {
		return ccdao;
	}

	public void setCcdao(ICmsChannelDao ccdao) {
		this.ccdao = ccdao;
	}

	public long findCount() {
		return ccdao.findCount(null);
	}

	public void doAddChannel(CmsChannel channel) {
		ccdao.add(channel);
	}

	public CmsChannel findById(String state,Integer id) {
		CmsChannel channel = ccdao.findById(id);
		channel.getCmsTemplate().getPath();
		if(state != null){
			
			channel.setIsState(state);    //添加的时候，需要把父板块的state改为closed
		}
		return channel;
	}

	public void doDeleteChannel(Integer id) {
		cnewdao.deleteContent(id);  //先删除本栏目下的新闻，在删除本栏目（暂时放入回收站）
		ccdao.doDeleteChannel(id);
	}

	public void doUpdateChannel(CmsChannel channel) {
		ccdao.update(channel);
	}

	public ICmsContentDao getCnewdao() {
		return cnewdao;
	}

	public void setCnewdao(ICmsContentDao cnewdao) {
		this.cnewdao = cnewdao;
	}

	//给一个板块的Id 通过递归 取出本版块以及本版块下的子版块的id
	public String getIds(Integer id) {  //查看新闻时，点击父栏目即可查看父栏目下的所有新闻
		List<CmsChannel> clist = ccdao.findInfo(null, null, null, null, null);
		StringBuffer ids = new StringBuffer(id+",");
		return this.getIds(id, clist,ids);
	}
	public String getIds(Integer id,List<CmsChannel> clist,StringBuffer ids){
		
		for (CmsChannel cmsChannel : clist) {
			
			if(cmsChannel.getParentId().equals(id)){
				ids.append(cmsChannel.getChannelId()+",");
				getIds(cmsChannel.getChannelId(),clist,ids);
			}
		}
		
		return ids.substring(0,ids.length()-1).toString();
	}

	
	public boolean yesNoParent(Integer id) {
		if(ccdao.findById(id).getIsState().equals("closed")){
			return false;
		}
		return true;
	}
	/**
	 * 获得本版块以及本版块的父板块的ID
	 */
	public String getParentIds(Integer id,Integer pid) {
		List<CmsChannel> clist = ccdao.findInfo(null, null, null, null, null);
		StringBuffer ids = new StringBuffer(id+",");
		return this.getParentIds(pid, clist,ids);
		
		
		
	}

	public String getParentIds(Integer pid,List<CmsChannel> clist,StringBuffer ids){
		
		for (CmsChannel cmsChannel : clist) {
			
			if(cmsChannel.getChannelId().equals(pid)){
				ids.append(cmsChannel.getChannelId()+",");
				getParentIds(cmsChannel.getParentId(),clist,ids);
			}
		}
		return ids.substring(0,ids.length()-1).toString();
	}

	public void doUpdateGarbageChannel(String yn, String ids) {
		ccdao.updateGarbageChannel(yn, ids);
	}
}
