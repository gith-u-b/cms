package com.aaa.yf.util;

import java.util.List;

import com.aaa.yf.entity.CmsChannel;
import com.aaa.yf.entity.CmsContent;



public class DivData {
	private CmsChannel channel;
	private CmsContent zd;
	private List<CmsContent> pt;
	public DivData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CmsChannel getChannel() {
		return channel;
	}
	public void setChannel(CmsChannel channel) {
		this.channel = channel;
	}
	public CmsContent getZd() {
		return zd;
	}
	public void setZd(CmsContent zd) {
		this.zd = zd;
	}
	public List<CmsContent> getPt() {
		return pt;
	}
	public void setPt(List<CmsContent> pt) {
		this.pt = pt;
	}
	public DivData(CmsChannel channel, CmsContent zd, List<CmsContent> pt) {
		super();
		this.channel = channel;
		this.zd = zd;
		this.pt = pt;
	}
	
	
	
}
