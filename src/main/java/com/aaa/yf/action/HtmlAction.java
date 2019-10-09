package com.aaa.yf.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aaa.yf.entity.CmsChannel;
import com.aaa.yf.entity.CmsContent;
import com.aaa.yf.service.ICmsChannelService;
import com.aaa.yf.service.ICmsContentService;
import com.aaa.yf.service.ICmsTemplateService;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
@Action
@ParentPackage(value="cms")
@Namespace(value="news")
public class HtmlAction extends BaseAction {
	
	@Autowired
	private ICmsContentService cnewservice;
	@Autowired
	private ICmsChannelService ccservice;
	@Autowired
	private ICmsTemplateService ctservice;
	private CmsContent content;
	private List<CmsChannel> channelList;
	private String path;
	private String ftlPath;   //最总得到模板的相对路径（传道struts.xml中result中），作为视图来访问
	private CmsChannel channel;
	private Integer page;
	private Integer rows;
	
	/*
	 * 前台首页，点击导航进入某个栏目下列表
	 */
	public String findByChannel() throws Exception{
		
		
		CmsChannel c = ccservice.findById(null, channel.getChannelId());
		// 获取要查询的栏目
		ftlPath = "/"+c.getCmsTemplate().getPath();
		Map condition = new HashMap();
		condition.put("obj.channelId in ", "("+ccservice.getParentIds(c.getChannelId(), c.getParentId())+")");
		List<CmsChannel> channels = ccservice.findAllChannel(condition, null, null, null, null);// 获得栏目下的所有父级栏目
		
		Map condition1 = new HashMap();
		condition1.put("obj.channelId in ", "("+ccservice.getIds(c.getChannelId())+")");
		List<CmsChannel> channels2 = ccservice.findAllChannel(condition1, null, null, null, null);// 获取所有子孙栏目
		
		channels.remove(channels.size() - 1);
		String cids = "";// 获取该栏目下所有子孙栏目的id
		for (CmsChannel cc : channels2) {
			cids += cc.getChannelId() + ",";
		}
		cids = cids.substring(0, cids.length() - 1);
		Map condition2 = new HashMap();
		condition2.put("obj.status= ?", 1);
		condition2.put("obj.cmsChannel.channelId in ", "("+cids+")");
		
		List<CmsContent> cs = cnewservice.findAllContent(condition2, "obj.repleaseTime", "desc", page, rows);
		long count = cnewservice.findCount(condition2);

		String path = this.getRequest().getContextPath();// 获取项目根路径
		long pages = (count+rows-1)/rows;// 获取总页数  
		
		ActionContext.getContext().getSession().put("pages", pages);
		ActionContext.getContext().getSession().put("page", page);// 当前页
		ActionContext.getContext().getSession().put("contents", cs);
		ActionContext.getContext().getSession().put("channels", channels);
		ActionContext.getContext().getSession().put("cmschannel", c);
		ActionContext.getContext().getSession().put("count", count);
		ActionContext.getContext().getSession().put("path", path);
		
		ftlPath = c.getCmsTemplate().getPath();// 模板路径
		String fpath = this.getApplication().getRealPath("/") + "/"
				+ ftlPath.substring(0, ftlPath.lastIndexOf("/"));
		
		
		//获取根目录
		Map condition3 = new HashMap();
		condition2.put("obj.pid =  ?", 0);
		String tml = ctservice.findTemplateByCondition(condition3, null, null, null, null).get(0).getPath();
		
		
		
		//一下两部可以不写，因为在生成导航时已经完成了下面的工作
		//根目录下的nav.html
		File f1 = new File(this.getApplication().getRealPath("/")+tml+ "/nav.html");  
		
		File f2 = new File(fpath + "/nav.html");
		
		FileUtils.copyFile(f1, f2); //把根目录中的复制到栏目首页下的nav.html
		
		return "channellist";
		
		
		
		
	}
	
	
	//静态的还没有生成，预览访问新闻模板
		public String newsHtml(){
			
			content = cnewservice.findContentById(content.getContentId());
				/**
				 * 获得本新闻的板块以及父板块
				 */
				Map condition4 = new HashMap();
				condition4.put("obj.channelId in ", "("+ccservice.getParentIds(content.getCmsChannel().getChannelId(),content.getCmsChannel().getParentId())+")");
				channelList = ccservice.findAllChannel(condition4, null, null, null, null);
				path = this.getRequest().getContextPath();
				//传入到struts.xml配置文件中的result中
				ftlPath = content.getCmsTemplate().getPath();
			
			return "newshtml";
		}

		public ICmsContentService getCnewservice() {
			return cnewservice;
		}

		public void setCnewservice(ICmsContentService cnewservice) {
			this.cnewservice = cnewservice;
		}

		public ICmsChannelService getCcservice() {
			return ccservice;
		}

		public void setCcservice(ICmsChannelService ccservice) {
			this.ccservice = ccservice;
		}

		public CmsContent getContent() {
			return content;
		}

		public void setContent(CmsContent content) {
			this.content = content;
		}

		public List<CmsChannel> getChannelList() {
			return channelList;
		}

		public void setChannelList(List<CmsChannel> channelList) {
			this.channelList = channelList;
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public String getFtlPath() {
			return ftlPath;
		}

		public void setFtlPath(String ftlPath) {
			this.ftlPath = ftlPath;
		}

		public CmsChannel getChannel() {
			return channel;
		}

		public void setChannel(CmsChannel channel) {
			this.channel = channel;
		}


		public Integer getPage() {
			return page;
		}


		public void setPage(Integer page) {
			this.page = page;
		}


		public Integer getRows() {
			return rows;
		}


		public void setRows(Integer rows) {
			this.rows = rows;
		}


		public ICmsTemplateService getCtservice() {
			return ctservice;
		}


		public void setCtservice(ICmsTemplateService ctservice) {
			this.ctservice = ctservice;
		}
		
		
}
