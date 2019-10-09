package com.aaa.yf.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aaa.yf.entity.CmsChannel;
import com.aaa.yf.entity.CmsContent;
import com.aaa.yf.entity.CmsUser;
import com.aaa.yf.service.ICmsChannelService;
import com.aaa.yf.service.ICmsContentService;
import com.aaa.yf.util.toJsonUtil;
import com.et.mvc.util.Json;

@Controller
@Scope("prototype")
@Action
@ParentPackage(value="cms")
public class ContentAction extends BaseAction {

	@Autowired
	private ICmsContentService cnewservice;
	@Autowired
	private ICmsChannelService ccservice;
	private CmsContent content = new CmsContent() ; 
	private String beginTime;
	private String endTime;
	private Integer page;
	private Integer rows;
	private String yesno;  //判断按照什么条件查询
	private String ids;
	private CmsChannel channel;
	
	
	/**
	 * 设置新闻栏目
	 * @return
	 * @throws Exception 
	 */
	public String setContentChannel() throws Exception{
		
		try {
			cnewservice.doSetContentChannel(channel.getChannelId(), ids);
			this.printJSON("yes");
		} catch (Exception e) {
			this.printJSON("no");
			this.getLogger().info("设置新闻栏目失败");
			e.printStackTrace();
		}
		
		return null;
	}
	/*
	 * 彻底删除回收站的新闻
	 */
	public String deleteGarbage() throws Exception{
		try {
			String arrId[] = ids.split(",");
			for (String id : arrId) {
				CmsContent c = cnewservice.findContentById(Integer.parseInt(id));
				File f = new File(this.getApplication().getRealPath("/")+c.getCmsChannel().getChannelPath()+"/news"+c.getContentId()+".html");
				if(f.exists()){
					f.delete();
				}
			}
			cnewservice.doUpdateGarbage("yes", ids);
			this.printJSON("yes");
		} catch (Exception e) {
			this.printJSON("no");
			this.getLogger().info("物理删除新闻失败");
			e.printStackTrace();
		}
		return null;
	}
	/*
	 * 还原新闻 
	 */
	public String updateGarbage() throws Exception{
		try {
			cnewservice.doUpdateGarbage("no", ids);
			this.printJSON("yes");
		} catch (Exception e) {
			this.printJSON("no");
			this.getLogger().info("还原回收站的新闻失败");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取被删除的新闻
	 * @return
	 * @throws Exception 
	 */
	public String findGarbageContentByPage() throws Exception{
		Map condition = new HashMap();
		if(content.getStatus() != null){
			if(content.getStatus() != -1){
				condition.put("obj.status = ?", content.getStatus());
			}
		}
		if("condition".equals(yesno)){
			condition.put("obj.title like ?", "%"+content.getTitle()+"%");
			condition.put("obj.author like ?", "%"+content.getAuthor()+"%");
		}
		
		condition.put("obj.isDisplay = ?", 0);
		toJsonUtil json = new toJsonUtil(cnewservice.findAllContent(condition, "obj.repleaseTime", "desc", page, rows),cnewservice.findCount(condition));
		this.printJSON(Json.toJson(json));
		return null;
	}
	/*
	 * 取消审核
	 */
	public String cancelCheckNews() throws Exception{
		
		try {
			cnewservice.doCheckNews("no",ids);
			this.printJSON("yes");
			
		} catch (Exception e) {
			this.printJSON("no");
			this.getLogger().info("取消新闻审核失败");
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 审核新闻
	 * @return
	 * @throws Exception 
	 */
	public String checkNews() throws Exception{
		
		try {
			cnewservice.doCheckNews("yes",ids);
			this.printJSON("yes");
		} catch (Exception e) {
			this.printJSON("no");
			this.getLogger().info("审核新闻失败");
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	//查询出未被审核的新闻
	public String findNoCheckContentByPage() throws Exception{
		Map condition = new HashMap();
		condition.put("obj.isDisplay = ?", 1);
		if(content.getStatus() != null){
			if(content.getStatus() != -1){
			condition.put("obj.status = ?", content.getStatus());
			}
		}
		if("condition".equals(yesno)){
			condition.put("obj.title like ?", "%"+content.getTitle()+"%");
			condition.put("obj.author like ?", "%"+content.getAuthor()+"%");
			condition.put("obj.cmsUser.userName like ?", "%"+content.getCmsUser().getUserName()+"%");
			condition.put("obj.repleaseTime > ?", beginTime);
			condition.put("obj.repleaseTime < ?", endTime);
			condition.put("obj.cmsUser.userName like ?", "%"+content.getCmsUser().getUserName()+"%");
			if(content.getCmsContentType().getTypeId() != null){
				condition.put("obj.cmsContentType.typeId = ?", content.getCmsContentType().getTypeId());
			}
			if(content.getCmsChannel().getChannelId() != null){
				condition.put("obj.cmsChannel.channelId in  ", "("+ccservice.getIds(content.getCmsChannel().getChannelId())+")");
			}
			
		}
		
		if("channel".equals(yesno)){
			condition.put("obj.cmsChannel.channelId in  ", "("+ccservice.getIds(content.getCmsChannel().getChannelId())+")");
		}
		
		toJsonUtil tojson = new toJsonUtil(cnewservice.findAllContent(condition, "obj.repleaseTime", "desc", page, rows),cnewservice.findCount(condition));
		this.printJSON(Json.toJson(tojson));
		return null;
	}
	public String noStatic() throws Exception{
		try {
			cnewservice.doYesNoStatic("no",ids);
			this.printJSON("yes");
		} catch (Exception e) {
			this.printJSON("no");
			this.getLogger().info("新闻非静态失败");
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String yesStatic() throws Exception{
		try {
			cnewservice.doYesNoStatic("yes",ids);
			this.printJSON("yes");
		} catch (Exception e) {
			this.printJSON("no");
			this.getLogger().info("新闻静态失败");
			e.printStackTrace();
		}
		
		return null;
	}
	public String deleteContent() throws Exception{
		
		try {
			cnewservice.doDeleteContent(ids);
			this.printJSON("yes");
		} catch (Exception e) {
			this.printJSON("no");
			this.getLogger().info("逻辑删除新闻失败");
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String updateContent() throws Exception{
		try {
			CmsContent news = cnewservice.findContentById(content.getContentId());
			//如果选择是的图文或者是焦点新闻则取出对应的图片路径放入新闻内容中
			if (news.getCmsContentType().getTypeId()==2 || news.getCmsContentType().getTypeId()==3){
				if(this.getSession().getAttribute("uploadPath") != null){
					news.setContentImg(this.getSession().getAttribute("uploadPath").toString());
				}
			}
			news.setCmsChannel(content.getCmsChannel());
			news.setShortTitle(content.getShortTitle());
			news.setAuthor(content.getAuthor());
			news.setOrigin(content.getOrigin());
			if(content.getCmsTemplate() != null){
				news.setCmsTemplate(content.getCmsTemplate());
			}
			news.setTitle(content.getTitle());
			if(content.getTitleColor() != null){
			news.setTitleColor(content.getTitleColor());
			}
			
			news.setSummary(content.getSummary());
			news.setOriginUrl(content.getOriginUrl());
			news.setCmsContentType(content.getCmsContentType());
			news.setContent(content.getContent());
			cnewservice.doUpdateContent(news);
			this.printJSON("yes");
		} catch (Exception e) {
			this.printJSON("no");
			this.getLogger().info("编辑新闻失败");
			e.printStackTrace();
		}
		return null;
	}
	
	
	public String addContent() throws Exception{
		//如果选择是的图文或者是焦点新闻则取出对应的图片路径放入新闻内容中
		try {		
				if (content.getCmsContentType().getTypeId()==2 || content.getCmsContentType().getTypeId()==3){
					if(this.getSession().getAttribute("uploadPath") != null){
					content.setContentImg(this.getSession().getAttribute("uploadPath").toString());
					}
				}
				//从Session中获取当前登录用户
				CmsUser cmsUser = (CmsUser) this.getSession().getAttribute("user");
				content.setCmsUser(cmsUser);
				content.setRepleaseTime(new SimpleDateFormat("yyyy-MM-ss hh:mm:ss").format(new Date()));
				
				if (content.getCmsContentType().getTypeId()==2 || content.getCmsContentType().getTypeId()==3){
					content.setHasTitleImg(1);
				}else{
					content.setHasTitleImg(0);
				}
				if (content.getIsBold()==null){
					content.setIsBold(0);
				}else{
					content.setIsBold(1);
				}
				if (content.getIsCommend()==null){
					content.setIsCommend(0);
				}else{
					content.setIsCommend(1);
				}
				if (content.getIsRecommend()==null){
					content.setIsRecommend(0);
				}else{
					content.setIsRecommend(1);
				}
				if(content.getIsStatic()==null){
					content.setIsStatic(0);
				}else{
					content.setIsStatic(1);
				}
				content.setStatus(0);
				content.setIsDisplay(1);
				content.setIsCreated(0);
				cnewservice.doAddContent(content);
				if (content.getCmsContentType().getTypeId()==2 || content.getCmsContentType().getTypeId()==3){
					if(this.getSession().getAttribute("uploadPath") != null){
							this.getSession().removeAttribute("uploadPath");
					}
				}
				
					this.printJSON("yes");
				} catch (IOException e) {
					this.getLogger().info("添加新闻失败");
					this.printJSON("no");
				}
		
		
		
		return null;
	}
	public String findContentByPage() throws Exception{
		
		Map condition = new HashMap();
		condition.put("obj.isDisplay = ?", 1);
		if("condition".equals(yesno)){
			
			condition.put("obj.title like ?", "%"+content.getTitle()+"%");
			condition.put("obj.author like ?", "%"+content.getAuthor()+"%");
			condition.put("obj.cmsUser.userName like ?", "%"+content.getCmsUser().getUserName()+"%");
			condition.put("obj.repleaseTime > ?", beginTime);
			condition.put("obj.repleaseTime < ?", endTime);
			if(content.getCmsContentType().getTypeId() != null){
				condition.put("obj.cmsContentType.typeId = ?", content.getCmsContentType().getTypeId());
			}
			if(content.getCmsChannel().getChannelId() != null){
				condition.put("obj.cmsChannel.channelId in  ", "("+ccservice.getIds(content.getCmsChannel().getChannelId())+")");
			}
			
		}
		
		if("channel".equals(yesno)){
			condition.put("obj.cmsChannel.channelId in  ", "("+ccservice.getIds(content.getCmsChannel().getChannelId())+")");
		}
		
		toJsonUtil tojson = new toJsonUtil(cnewservice.findAllContent(condition, "obj.repleaseTime", "desc", page, rows),cnewservice.findCount(condition));
		this.printJSON(Json.toJson(tojson));
		return null;
	}
	
	public ICmsContentService getCnewservice() {
		return cnewservice;
	}
	public void setCnewservice(ICmsContentService cnewservice) {
		this.cnewservice = cnewservice;
	}
	public CmsContent getContent() {
		return content;
	}
	public void setContent(CmsContent content) {
		this.content = content;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
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

	public String getYesno() {
		return yesno;
	}

	public void setYesno(String yesno) {
		this.yesno = yesno;
	}

	public ICmsChannelService getCcservice() {
		return ccservice;
	}

	public void setCcservice(ICmsChannelService ccservice) {
		this.ccservice = ccservice;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	public CmsChannel getChannel() {
		return channel;
	}
	public void setChannel(CmsChannel channel) {
		this.channel = channel;
	}
	
	
	
	
	
}
