package com.aaa.yf.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aaa.yf.entity.CmsChannel;
import com.aaa.yf.entity.CmsContent;
import com.aaa.yf.entity.CmsTemplate;
import com.aaa.yf.entity.CmsTemplateType;
import com.aaa.yf.service.ICmsChannelService;
import com.aaa.yf.service.ICmsContentService;
import com.aaa.yf.service.ICmsTemplateService;
import com.aaa.yf.util.DivData;
import com.aaa.yf.util.FreemarkUtil;


@Controller
@Scope("prototype")
@Action
@ParentPackage(value="cms")
public class CreateHtmlAction extends BaseAction{
	
	@Autowired
	private ICmsChannelService ccservice;
	@Autowired
	private ICmsTemplateService ctservice;
	@Autowired
	private ICmsContentService cnewservice;
	private CmsContent content;
	private CmsTemplate template;
	private CmsChannel channel = new CmsChannel();
	private List<CmsContent> indexContents=new ArrayList<CmsContent>();//首页 所有新闻列表
	private String ids;
	private String yesnoall;  //是否全部生成页面
	
	

	//删除新闻静态页面
	public String deleteNewsHtml() throws Exception{
		
		try {
			String arrId[] = ids.split(",");
			
			for (String id : arrId) {
				CmsContent cnew = cnewservice.findContentById(Integer.parseInt(id));
				if (cnew.getIsDisplay() == 1  && cnew.getIsCreated()==1 && cnew.getStatus()==1) {
					String htmlPath = this.getApplication().getRealPath("/")+cnew.getCmsChannel().getChannelPath()+"/"+"news"+cnew.getContentId()+".html";
					File f = new File(htmlPath);
					if(f.exists()){
						f.delete();
					}
					cnew.setIsCreated(0);
					cnewservice.doUpdateContent(cnew);
				}else{
					this.printJSON("no");
					return null;
				}
				
			}
			this.printJSON("yes");
		} catch (Exception e) {
			this.printJSON("no");
			this.getLogger().info("删除静态页面失败");
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	//生成新闻页面(多选)
	public String createNewsHtml() throws Exception{
		try {
			String arrId[] = ids.split(",");
			for (String id : arrId) {
				CmsContent cnew = cnewservice.findContentById(Integer.parseInt(id)); 
				
					if (cnew.getIsDisplay() == 1 && cnew.getIsStatic()==1 && cnew.getIsCreated()==0 && cnew.getStatus()==1) {
						
						String	ftlPath = this.getApplication().getRealPath("/")
									+ cnew.getCmsTemplate().getPath();// 获取该新闻的模板
						
						String ftlName = ftlPath.substring(ftlPath
								.lastIndexOf("/") + 1);// 获取模板名称
						
						ftlPath = ftlPath.substring(0,
								ftlPath.lastIndexOf("/")); //该模板的目录
						
						//String tml=cmsTemplateBiz.findByPid(0).get(0).getPath();//获取模板根目录
						//获取模板根目录
						Map condition2 = new HashMap();
						condition2.put("obj.pid =  ?", 0);
						String tml = ctservice.findTemplateByCondition(condition2, null, null, null, null).get(0).getPath();
						
						File f1 = new File(this.getApplication().getRealPath("/")+tml
								+ "/nav.html");
						
						File f2 = new File(ftlPath + "/nav.html");
						
						FileUtils.copyFile(f1, f2);     //这一段不知道什么意思（待问老师）
						String path = this.getRequest().getContextPath();// 获取项目根目录
						
						String filePath = this.getApplication().getRealPath("/")
								+ cnew.getCmsChannel().getChannelPath();// 获取要保存的文件路径
						File f = new File(filePath);
						if (!f.exists()) { // 判断路径是否存在
							f.mkdirs(); // 创建
						}
						
						//生成的静态html名称
						String fileName = filePath + "/news"
								+ cnew.getContentId() + ".html";
						
						/**
						 * 获得本新闻的板块以及父板块
						 */
						Map condition4 = new HashMap();
						condition4.put("obj.channelId in ", "("+ccservice.getParentIds(cnew.getCmsChannel().getChannelId(),cnew.getCmsChannel().getParentId())+")");
						List<CmsChannel> channels = ccservice.findAllChannel(condition4, null, null, null, null);
						
						
						Map<String, Object> root = new HashMap<String, Object>();
						root.put("channelList", channels);
						root.put("content", cnew);
						root.put("path", path);
						FreemarkUtil.createFile(ftlPath, ftlName, fileName,
								root);
						//f2.delete();// 删除文件（咱不能删除，因为对于动态的访问会找不到nav.html）
						cnew.setIsCreated(1);
						cnewservice.doUpdateContent(cnew); //更新
					}else{
						this.printJSON("no");
						this.getLogger().info("生成静态页面失败");
						return null;
					}
			}
			this.printJSON("yes");
		} catch (Exception e) {
			this.printJSON("no");
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	//xxxStatic  生成静态新闻html（通过栏目，可以生成所有）
	public String newsStatic() throws Exception{
		
			try {
				Map condition1 = new HashMap();
				condition1.put("obj.channelId in ", "("+ccservice.getIds(channel.getChannelId())+")");
				
				List<CmsChannel> cs = ccservice.findAllChannel(condition1, null, null, null, null);
				
				for (CmsChannel cc : cs) {
					int cid = cc.getChannelId();// 获取栏目编号
					Map condition3 = new HashMap();
					condition3.put("obj.cmsChannel.channelId = ?", cid);
					List<CmsContent> contents = cnewservice.findAllContent(condition3, null, null, null, null); 
					if(contents != null){
						
//						if(contents.size() == 0){
//							this.printJSON("null");
//							return null;
//						}
						
						for (CmsContent cnew : contents) {
							// 条件 && cnew.getIsCreated()==0  去掉   如果进行了改变可以从新生成更新新内容：例如导航发生了改变
							if (cnew.getIsDisplay() == 1 && cnew.getIsStatic()==1  && cnew.getStatus()==1) {
								
								
								String	ftlPath = this.getApplication().getRealPath("/")
											+ cnew.getCmsTemplate().getPath();// 获取该新闻的模板
								
								String ftlName = ftlPath.substring(ftlPath
										.lastIndexOf("/") + 1);// 获取模板名称
								
								ftlPath = ftlPath.substring(0,
										ftlPath.lastIndexOf("/")); //该模板的目录
								
								//String tml=cmsTemplateBiz.findByPid(0).get(0).getPath();//获取模板根目录
								//获取模板根目录
								Map condition2 = new HashMap();
								condition2.put("obj.pid =  ?", 0);
								String tml = ctservice.findTemplateByCondition(condition2, null, null, null, null).get(0).getPath();
								
								
								
								File f1 = new File(this.getApplication().getRealPath("/")+tml
										+ "/nav.html");
								
								File f2 = new File(ftlPath + "/nav.html");
								
								FileUtils.copyFile(f1, f2);     //复制
								
								
								String path = this.getRequest().getContextPath();// 获取项目根目录
								
								String filePath = this.getApplication().getRealPath("/")
										+ cc.getChannelPath();// 获取要保存的文件路径
								File f = new File(filePath);
								if (!f.exists()) { // 判断路径是否存在
									f.mkdirs(); // 创建
								}
								String fileName = filePath + "/news"
										+ cnew.getContentId() + ".html";
								
								/**
								 * 获得本新闻的板块以及父板块
								 */
								Map condition4 = new HashMap();
								condition4.put("obj.channelId in ", "("+ccservice.getParentIds(cnew.getCmsChannel().getChannelId(),cnew.getCmsChannel().getParentId())+")");
								List<CmsChannel> channels = ccservice.findAllChannel(condition4, null, null, null, null);
								
								
								
	//						for (CmsChannel channel : channels) {
	//							System.out.println(channel.getChannelName());
	//						}
								
								Map<String, Object> root = new HashMap<String, Object>();
								root.put("channelList", channels);
								root.put("content", cnew);
								root.put("path", path);
								FreemarkUtil.createFile(ftlPath, ftlName, fileName,
										root);
								
								
								//f2.delete();// 删除文件
								cnew.setIsCreated(1);
								//content.setStatus(1);
								cnewservice.doUpdateContent(cnew); //更新
							}
						}
					}
				}
				this.printJSON("yes");
			} catch (Exception e) {
				this.printJSON("no");
				this.getLogger().info("生成静态新闻页面失败");
				e.printStackTrace();
			}
			
		
		
		return null;
	}
	
	//首页生成静态页面首页（并且生成所有新闻页面）
	public String indexStatic() throws Exception{
		
		try {
			if(yesnoall.equals("yes")){
				
				//生成所有新闻
				channel.setChannelId(1);
				String yy = this.newsStatic();
			}
			//获取首页模板
			Map condition1 = new HashMap();
			condition1.put("obj.templateId = ?", template.getTemplateId());
			CmsTemplate template= ctservice.findTemplateByCondition(condition1, null, null, null, null).get(0);//获取首页模板
			String path = this.getApplication().getContextPath();// 获取项目名
			Map condition2 = new HashMap();
			condition2.put("obj.parentId = ?", 1);
			condition2.put("obj.isDisplay = ?", 1);
			//获取所有根栏目
			List<CmsChannel> cs = ccservice.findAllChannel(condition2, null, null, null, null);
			//主页中每个栏目的数据，分别有置顶新闻 和普通新闻
			List<DivData> divs = new ArrayList<DivData>();
			for (CmsChannel cc : cs) {
				String ids="";
				//获取栏目下所有栏目id
				Map condition3 = new HashMap();
				condition3.put("obj.channelId in ", "("+ccservice.getIds(cc.getChannelId())+")");
				List<CmsChannel> channels1 =  ccservice.findAllChannel(condition3, null, null, null, null);
				for (CmsChannel c1 : channels1) {
					ids+=c1.getChannelId()+",";
				}
				ids=ids.substring(0,ids.length()-1);
				//System.out.println(ids+"****************");
				
				//查询出栏目下(包括子栏目)的分页内容
				Map condition4 = new HashMap();
				condition4.put("obj.cmsChannel.channelId in ", "("+ids+")");
				condition4.put("obj.cmsContentType.typeId = ?", 1);
				condition4.put("obj.isDisplay = ?", 1);
				condition4.put("obj.status = ?", 1);
				
				List<CmsContent> contents= cnewservice.findAllContent(condition4, "obj.repleaseTime,obj.isRecommend", "desc", 1, 5);  
				
			
					//indexContents.addAll(contents);
					indexContents.addAll(contents);
					Map condition5 = new HashMap();
					condition5.put("obj.cmsChannel.channelId in ", "("+ids+")");
					condition5.put("obj.cmsContentType.typeId = ?", 3);
					condition5.put("obj.isDisplay = ?", 1);
					condition5.put("obj.status = ?", 1);
					//获取图片新闻
					List<CmsContent> piccmt = cnewservice.findAllContent(condition5, "obj.repleaseTime,obj.isRecommend", "desc", 1, 1);
					CmsContent cmt = null;
					if(piccmt.size() > 0){
					 cmt= cnewservice.findAllContent(condition5, "obj.repleaseTime,obj.isRecommend", "desc", 1, 1).get(0);
					indexContents.add(cmt);
					}
					
					//首页中每个栏目中第一个图片新闻
					if(contents.size()!=0){
						divs.add(new DivData(cc, cmt, contents));  //cc 表示首页新闻各模块的名称 cmt表示每个模块的第一个图片新闻
					}
				
				
			}
			
			//开始查询焦点新闻（即图片轮转） 规则是焦点中按照发布时间逆序的头3个
			Map condition6 = new HashMap();
			condition6.put("obj.cmsContentType.typeId = ?", 2);
			condition6.put("obj.isDisplay = ?", 1);
			condition6.put("obj.status = ?", 1);
			List<CmsContent>jds=  cnewservice.findAllContent(condition6, "obj.repleaseTime,obj.isRecommend", "desc", 1, 3);
			indexContents.addAll(jds);
			
			String jdimgs="";   //滚动新闻的图片
			String jdlinks="";  //滚动新闻的地址
			String jdtexts="";  //滚动新闻的标题
			
			for (CmsContent ct : jds) {
				jdimgs+=path+"/"+ct.getContentImg()+"|";
				jdlinks+=path+ct.getCmsChannel().getChannelPath()+"/news"+ct.getContentId()+".html|";
				jdtexts+=ct.getTitle()+"|";
			}
			
				jdimgs=jdimgs.substring(0,jdimgs.length()-1);
				jdlinks=jdlinks.substring(0,jdlinks.length()-1);
				jdtexts=jdtexts.substring(0,jdtexts.length()-1);
			
			
				
				
				
			
			//开始查询头条新闻（即首页中的头条）。 规则是头条中按照发布时间逆序的头6个 ，而且头一个必须是被推荐并根据时间逆序
				Map condition7 = new HashMap();
				condition7.put("obj.cmsContentType.typeId = ?", 4);
				condition7.put("obj.isDisplay = ?", 1);
				condition7.put("obj.status = ?", 1);
			List<CmsContent>tts=cnewservice.findAllContent(condition7, "obj.repleaseTime,obj.isRecommend", "desc", 1, 6);
			indexContents.addAll(tts);
			CmsContent tj=tts.get(0);
			indexContents.add(tj);
			
			
			
			//开始查询点击排行
			
			List<CmsContent>hots=cnewservice.findHotContent();
			indexContents.addAll(hots);
			String ftlPath = this.getApplication().getRealPath("/")
					+ "/"+template.getPath();// 获取模板的绝对路径
			String ftlName=ftlPath.substring(ftlPath.lastIndexOf("/")+1);//获取模板名称
			ftlPath=ftlPath.substring(0,ftlPath.lastIndexOf("/"));//获取模板文件夹路径
			String fileName = this.getApplication().getRealPath("/")
					+ "/index.html";// 生成的网页全路径名
			
			
			Map condition8 = new HashMap();
			condition8.put("obj.pid =  ?", 0);
			
			String tml = ctservice.findTemplateByCondition(condition8, null, null, null, null).get(0).getPath();
			
			File f1 = new File(this.getApplication().getRealPath("/")+ tml+ "/nav.html");
			File f2 = new File(ftlPath + "/nav.html");
			FileUtils.copyFile(f1, f2);
			Map<String, Object> root = new HashMap<String, Object>();
			
			root.put("channels", cs);
			root.put("divs",divs);
			root.put("jdtexts",jdtexts);// 滚动图片的内容
			root.put("jdlinks",jdlinks);  //滚动图片的新闻  
			root.put("jdimgs",jdimgs);//滚动图片的图片
			root.put("hots", hots);
			root.put("tjContent", tj);
			root.put("ttContents", tts);
			root.put("path", path);
			
			FreemarkUtil.createFile(ftlPath, ftlName, fileName, root);
			//f2.delete();// 删除文件
			
			this.printJSON("yes");
		} catch (Exception e) {
			// TODO: handle exception
			this.printJSON("no");
			this.getLogger().info("生成静态首页失败");
			e.printStackTrace();
		}
		
		return null;
	}
	/**
	 * 生成栏目导航
	 * @return
	 * @throws Exception
	 */
	public String channelStatic() throws Exception{
		try {
		Map condition1 = new HashMap();
		condition1.put("obj.parentId = ?", 1);
		condition1.put("obj.isDisplay = ?", 1);
		//获取所有根栏目
		List<CmsChannel> cs = ccservice.findAllChannel(condition1, null, null, null, null);
		
		Map condition2 = new HashMap();
		condition2.put("obj.pid =  ?", 0);
		
		//String tml=cmsTemplateBiz.findByPid(0).get(0).getPath();//获取模板根目录
		String tml = ctservice.findTemplateByCondition(condition2, null, null, null, null).get(0).getPath();
		//获取模板
		Map condition3 = new HashMap();
		
		condition3.put("obj.templateId =  ?", template.getTemplateId());
		CmsTemplate ctemplate=ctservice.findTemplateByCondition(condition3, null, null, null, null).get(0);//获取模板
		
		int width = 980 / (cs.size() + 1);
		
		String ftlPath = this.getApplication().getRealPath("/") + "/"
				+ ctemplate.getPath();
		//模板
		String ftlName=ftlPath.substring(ftlPath.lastIndexOf("/")+1);
		//模板路径
		ftlPath = ftlPath.substring(0, ftlPath.lastIndexOf("/"));
		
		String fileName = this.getApplication().getRealPath("/"+tml) + "/nav.html";
		String path = this.getRequest().getContextPath();// 获取项目根目录
		
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("cs", cs);
		root.put("width", width);
		root.put("path", path);
		
		
		
			FreemarkUtil.createFile(ftlPath, ftlName, fileName, root);
			
			File f1 = new File(this.getApplication().getRealPath("/")+tml
					+ "/nav.html");
			
			List<CmsTemplate> ctlist = ctservice.findTemplateByType();  
			//生成导航时，需要把生成的导航复制的各个新闻模板的同目录下
			for (CmsTemplate ct : ctlist) {
				File f2 = new File(this.getApplication().getRealPath("/")+ct.getPath().substring(0,
						ct.getPath().lastIndexOf("/"))+ "/nav.html");
				FileUtils.copyFile(f1, f2);     //复制
			}
			
			
			this.printJSON("yes");
		} catch (Exception e) {
			this.printJSON("no");
			this.getLogger().info("生成静态导航失败");
			e.printStackTrace();
		}
		
		return null;
	}
	
	public CmsTemplate getTemplate() {
		return template;
	}

	public void setTemplate(CmsTemplate template) {
		this.template = template;
	}
	public ICmsChannelService getCcservice() {
		return ccservice;
	}
	public void setCcservice(ICmsChannelService ccservice) {
		this.ccservice = ccservice;
	}
	public ICmsTemplateService getCtservice() {
		return ctservice;
	}
	public void setCtservice(ICmsTemplateService ctservice) {
		this.ctservice = ctservice;
	}
	public CmsChannel getChannel() {
		return channel;
	}
	public void setChannel(CmsChannel channel) {
		this.channel = channel;
	}
	public CmsContent getContent() {
		return content;
	}
	public void setContent(CmsContent content) {
		this.content = content;
	}
	public ICmsContentService getCnewservice() {
		return cnewservice;
	}
	public void setCnewservice(ICmsContentService cnewservice) {
		this.cnewservice = cnewservice;
	}
	public List<CmsContent> getIndexContents() {
		return indexContents;
	}
	public void setIndexContents(List<CmsContent> indexContents) {
		this.indexContents = indexContents;
	}


	public String getIds() {
		return ids;
	}


	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getYesnoall() {
		return yesnoall;
	}

	public void setYesnoall(String yesnoall) {
		this.yesnoall = yesnoall;
	}


	
	
}
