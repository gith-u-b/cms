package com.aaa.yf.action;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aaa.yf.entity.CmsChannel;
import com.aaa.yf.entity.CmsTemplate;
import com.aaa.yf.service.ICmsChannelService;
import com.aaa.yf.service.ICmsContentService;
import com.aaa.yf.util.Tree;
import com.et.mvc.util.Json;

@Controller
@Action
@Scope("prototype")
@ParentPackage(value="cms")
public class ChannelAction extends BaseAction {

	@Autowired
	private ICmsChannelService ccservice;
	@Autowired
	private ICmsContentService cnewservice;
	private CmsChannel channel = new CmsChannel();
	private String ids;
	/*
	 * 删除回收站里的栏目
	 */
	public String deleteGarbageChannel() throws Exception{
		
		try {
			String arrId[] = ids.split(",");
			for (String id : arrId) {
				CmsChannel c = ccservice.findById(null, Integer.parseInt(id));
				
				//删除栏目文件夹以及文件夹下的所有文件
				File f = new File(this.getApplication().getRealPath("/")+c.getChannelPath());
				this.deleteFile(f); //递归循环查找文件
				if(f.exists()){
					f.delete();
				}
				cnewservice.doDeleteContentByCid(ccservice.getIds(Integer.parseInt(id)));
				ccservice.doUpdateGarbageChannel("yes", ccservice.getIds(Integer.parseInt(id)));
				
			}
			this.printJSON("yes");
		} catch (Exception e) {
			this.printJSON("no");
			this.getLogger().info("删除回收站里的栏目失败");
			e.printStackTrace();
		}
		return null;
		
	}
	/*
	 * 递归查找文件
	 */
	public void deleteFile(File file){
		File[] fs = file.listFiles();
		if(fs != null){
		for (File f2 : fs) {
			if(f2.isDirectory()){
				deleteFile(f2);
				f2.delete();
			}else{
				f2.delete();
			}
		}
		}
	}
	
	/*
	 * 还原栏目
	 */
	public String updateGarbageChannel() throws Exception{
		try {
			String arrId[] = ids.split(",");
			for (String id : arrId) {
				
				CmsChannel c = ccservice.findById(null, Integer.parseInt(id));
				ccservice.findById("closed", c.getParentId());  //还原栏目时，先把父栏目isState更改为closed
				ccservice.doUpdateGarbageChannel("no", ccservice.getParentIds(c.getChannelId(), c.getParentId()));
			}
			this.printJSON("yes");
		} catch (Exception e) {
			this.printJSON("no");
			this.getLogger().info("还原还原回收站里的栏目失败");
			e.printStackTrace();
		}
		return null;
	}
	/*
	 * 获取被回收的栏目
	 */
	public String findGarbageChannel() throws Exception{
		
		try {
			Map condition = new HashMap();
			condition.put("obj.isDisplay = ?", 0);
			condition.put("obj.title like ?", "%"+channel.getTitle()+"%");
			condition.put("obj.channelName like ?", "%"+channel.getChannelName()+"%");
			condition.put("obj.isState = ?", "open");
			List<CmsChannel> list = ccservice.findAllChannel(condition, null, null, null, null);
			this.printJSON(Json.toJson(list));
		} catch (Exception e) {
			this.getLogger().info("获取逻辑删除的栏目失败");
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String yesNoParent() throws Exception{
		if(ccservice.yesNoParent(channel.getChannelId())){
			this.printJSON("yes");
		}else{
			this.printJSON("no");
		}
		return null;
	}
	public String updateChannel() throws Exception{
		
		try {
			CmsChannel c = ccservice.findById(null,channel.getChannelId());  
			c.setChannelName(channel.getChannelName());
			c.setCmsTemplate(channel.getCmsTemplate());
			c.setPage(channel.getPage());
			c.setTitle(channel.getTitle());
			c.setDescription(channel.getDescription());
			c.setKeywords(channel.getKeywords());
			ccservice.doUpdateChannel(c);
			this.printJSON("yes");
		} catch (Exception e) {
			this.printJSON("no");
			this.getLogger().info("编辑栏目失败");
			e.printStackTrace();
		}
		return null;
	}
	/*
	 * 栏目暂时放入回收站
	 */
	public String deleteChannel() throws Exception{
		
		try {
			ccservice.doDeleteChannel(channel.getChannelId());
			CmsChannel cc = ccservice.findById(null, channel.getChannelId());
			//当副栏目下没有子栏目时更改其isState为open 
			Map condition = new HashMap();
			condition.put("obj.parentId = ?", cc.getParentId());
			condition.put("obj.isDisplay = ?", 1);
			List<CmsChannel> clist = ccservice.findAllChannel(condition, null, null, null, null);
			if(clist != null){
				if(clist.size() == 0){
					ccservice.findById("open", cc.getParentId());
				}
			}else{
				ccservice.findById("open", cc.getParentId());
			}
			this.printJSON("yes");
		} catch (Exception e) {
			this.printJSON("no");
			this.getLogger().info("逻辑删除栏目失败");
			e.printStackTrace();
		}
		
		return null;
	}
	//添加栏目
	public String addChannel() throws Exception{
		
		try {
				CmsChannel c=ccservice.findById("closed",channel.getParentId()); //如果选择某个栏目为父栏目，则更改某个栏目state 为closed
					//判断路径是否书写正确，如果错误进行纠正
				if(!channel.getChannelPath().substring(0, 1).equals("/") &&  !channel.getChannelPath().substring(0, 1).equals("\\")){
					channel.setChannelPath(c.getChannelPath()+"/"+channel.getChannelPath());
				}else{
					channel.setChannelPath(c.getChannelPath()+channel.getChannelPath());
				}
				channel.setContentImgHeight(100);
				channel.setContentImgWidth(100);
				channel.setHasContent(1);
				channel.setPriority(1);
				channel.setIsDisplay(1);
				channel.setIsState("open");
				channel.setIsStatic(0);
				CmsTemplate t = new CmsTemplate();
				t.setTemplateId(9);
				channel.setCmsTemplate(t);
			
			String path=this.getApplication().getRealPath(channel.getChannelPath());//获取栏目绝对路径
			File f=new File(path);
			if(!f.exists()){
				f.mkdirs();//创建目录
			}
			ccservice.doAddChannel(channel);
			this.printJSON("yes");
		} catch (Exception e) {
			this.printJSON("no");
			this.getLogger().info("添加栏目失败");
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	public String findAllChannel() throws Exception{
		try {
			Map condition = new HashMap();
			condition.put("obj.isDisplay = ?", 1);
			List<CmsChannel> clist = ccservice.findAllChannel(condition, null, null, null, null);
			List<Tree> tlist = new ArrayList<Tree>();
			for (CmsChannel c : clist) {
				Tree t=new Tree();
				t.setId(c.getChannelId());
				t.setText(c.getChannelName());
				t.setState(c.getIsState());
				Map<String, Object> map = t.getAttributes();
				map.put("pid", c.getParentId());
				map.put("channelPath", c.getChannelPath());
				map.put("page",c.getPage());
				map.put("hasContent",c.getHasContent());
				map.put("isDisplay",c.getIsDisplay());
				map.put("title", c.getTitle());
				map.put("keywords",c.getKeywords());
				map.put("description",c.getDescription());
				map.put("template", c.getCmsTemplate());
				tlist.add(t);
			}
				tlist=new Tree().findTree(tlist);//组装成树
				this.printJSON(Json.toJson(tlist));
		} catch (Exception e) {
			this.getLogger().info("查询栏目失败");
			e.printStackTrace();
		}
		return null;
	}
	
	public ICmsChannelService getCcservice() {
		return ccservice;
	}
	public void setCcservice(ICmsChannelService ccservice) {
		this.ccservice = ccservice;
	}
	public CmsChannel getChannel() {
		return channel;
	}
	public void setChannel(CmsChannel channel) {
		this.channel = channel;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public ICmsContentService getCnewservice() {
		return cnewservice;
	}
	public void setCnewservice(ICmsContentService cnewservice) {
		this.cnewservice = cnewservice;
	}
	
	
}
