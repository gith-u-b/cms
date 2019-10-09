package com.aaa.yf.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aaa.yf.entity.CmsTemplate;
import com.aaa.yf.entity.CmsTemplateType;
import com.aaa.yf.entity.CmsUser;
import com.aaa.yf.service.ICmsTemplateService;
import com.aaa.yf.util.Tree;
import com.et.mvc.util.Json;

@Controller
@Action
@Scope("prototype")
@ParentPackage(value="cms")
public class TemplateAction extends BaseAction {

	@Autowired
	private ICmsTemplateService ctservice;
	private CmsTemplate template;
	private String text; //模板内容
	
	private String mlName;//目录名称
	private File mb;
	private String mbFileName;
	
	/*
	 * 编辑模板内容
	 */
	public String doUpdateText() throws Exception{
		
		try {
			CmsTemplate c=ctservice.findTemplateById(template.getTemplateId());//获取修改的模板对象
			c.setTemplateName(template.getTemplateName());//修改名称
			String path=c.getPath().substring(0,c.getPath().lastIndexOf("/"))+"/"+template.getTemplateName();//修改后的路径
			File f=new File(this.getApplication().getRealPath(c.getPath()));//获取源文件对象
			c.setPath(path);
			ctservice.doUpdateTemplate(c);//修改模板
			File f2=new File(this.getApplication().getRealPath("/")+"/"+path);//获取新对象
			f.renameTo(f2);//重命名
			PrintWriter pr=new PrintWriter(f2);
			pr.write(text);
			pr.flush();
			pr.close();
			this.printJSON("true");
		} catch (Exception e) {
			// TODO: handle exception
			this.printJSON("false");
			this.getLogger().info("编辑模板内容失败");
			e.printStackTrace();
		}
		
		return null;
	}
	
	/*
	 *得到模板内容
	 */
	public String getTemplateText() throws Exception{
		
		CmsTemplate c=ctservice.findTemplateById(template.getTemplateId());//得到要编辑的对象
		File f=new File(this.getApplication().getRealPath("/"+c.getPath()));//获取模板对象
		StringBuffer content=new StringBuffer();//模板内容
		if(f.exists()){
			HttpServletResponse response=this.getResponse();
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out=response.getWriter();
			BufferedReader br = new BufferedReader(new InputStreamReader(    //IO流待理解I
					new FileInputStream(f), "utf-8"));
			String s = null;
			while ((s = br.readLine()) != null) {
				out.println(s);
			}
			out.flush();
			out.close();
			br.close();

		}
		
		
		return null;
	}
	
	
	/*
	 * 重命名
	 */
	public String updateTemplateName() throws Exception{
		CmsTemplate c=ctservice.findTemplateById(template.getTemplateId());//获取要重命名的文件夹对象
		String path=c.getPath().substring(0,c.getPath().lastIndexOf("/"))+"/"+mlName;//获取修改后的路径
		System.out.println(path);
		try {
			File f=new File(this.getApplication().getRealPath("/")+c.getPath());//源文件对象
			if(f.exists()){
				File f2=new File(this.getApplication().getRealPath("/")+path);//重名后的文件对象
				f.renameTo(f2);//重命名
				//获取该模板的所有子孙模板
				Map condition = new HashMap();
				condition.put("obj.path like ?", "%"+c.getPath()+"%");
				List<CmsTemplate>cs= ctservice.findTemplateByCondition(condition, null, null, null, null);
				
				if(cs.size()>=1){
					for (CmsTemplate cm : cs) {
						String cpath=cm.getPath();
						cpath=cpath.replace(c.getPath(),path);//修改路径
						cm.setPath(cpath);
						ctservice.doUpdateTemplate(cm);//更新
					}
				}
				c.setTemplateName(mlName);
				c.setPath(path);
				ctservice.doUpdateTemplate(c);//更新
			}
			this.printJSON("true");
		} catch (Exception e) {
			this.printJSON("false");
			this.getLogger().info("重命名模板失败");
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 添加模板
	 * @throws Exception 
	 */
	
	
	public void addTemplate() throws Exception{
		try {
			
			
			CmsTemplate c=ctservice.findTemplateById(template.getTemplateId());//获取目录对象
			String path=c.getPath()+"/"+template.getTemplateName();//获取添加的路径
		
			CmsTemplate c2=new CmsTemplate();
			c2.setTemplateName(template.getTemplateName());
			c2.setCreatetime(new Date().toLocaleString());
			c2.setPath(path);
			c2.setPid(template.getTemplateId());
			c2.setHasContent(0);
			c2.setCmsTemplateType(template.getCmsTemplateType());
			c2.setCmsUser((CmsUser)this.getSession().getAttribute("user"));
			ctservice.doAddTemplate(c2); //执行添加
			
			File f=new File(this.getApplication().getRealPath("/")+"/"+path);
			if(!f.exists()){
				f.createNewFile();
			}
			PrintWriter pr=new PrintWriter(f);//写入文件
			pr.write(text);
			pr.flush();
			pr.close();
		
			this.printJSON("true");
		} catch (Exception e) {
			this.printJSON("false");
			this.getLogger().info("添加模板失败");
			e.printStackTrace();
		}
	}
	
	
	
	/*
	 * 删除选中template
	 */
	public String deleteTemplate() throws Exception{
		
		CmsTemplate c=ctservice.findTemplateById(template.getTemplateId());//获取要删除的模板
		if(c.getCmsChannels().size()>0||c.getCmsContents().size()>0){//如果被引用 禁止删除
			this.printJSON("error");
			return null;
		}
		
		File f=new File(this.getApplication().getRealPath("/"+c.getPath()));//用模板路径生成文件对象
		
		
		if(!f.exists()){  //判断是否存在
			Map condition = new HashMap();
			condition.put("obj.path like ?", "%"+c.getPath()+"%");
			
			List<CmsTemplate>cs= ctservice.findTemplateByCondition(condition, null, null, null, null);//获取该模板的所有子孙模板
			if(cs.size()>0){
				for (CmsTemplate cm : cs) {
					ctservice.doDeleteTemplate(cm);//执行删除
				}
			}else{
				ctservice.doDeleteTemplate(c);//执行删除
			}
			this.printJSON("true");
		}else{
			try {
				if(f.isDirectory()){ //是否是文件夹
					recursionFile(f);//递归删除文件
					
					Map condition1 = new HashMap();
					condition1.put("obj.path like ?", "%"+c.getPath()+"%");
					List<CmsTemplate>cs= ctservice.findTemplateByCondition(condition1, null, null, null, null);//获取该模板的所有子孙模板
					
					if(cs.size()>0){
						for (CmsTemplate cm : cs) {
							ctservice.doDeleteTemplate(cm);//执行删除
						}
					}else{
						ctservice.doDeleteTemplate(c);//执行删除
					}
				}else{
					f.delete();
					ctservice.doDeleteTemplate(c);//执行删除
				}
				this.printJSON("true");
			} catch (Exception e) {
				// TODO: handle exception
				this.printJSON("false");
				this.getLogger().info("删除模板失败");
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 递归删除文件及文件夹
	 */
	public void recursionFile(File parent){
		if(parent.isDirectory()){
			File children []= parent.listFiles();
			for (File file : children) {
				if(file.isDirectory()){
					recursionFile(file);
					file.delete();
				}else{
					file.delete();
				}
				
			}
			parent.delete();
		}
	}
	
	
	/*
	 * 新建目录
	 */
	public String createTemplateDir() throws Exception{
		
		try {
			CmsTemplate c=ctservice.findTemplateById(template.getTemplateId());
			String pPath=c.getPath();
			File f=new File(this.getApplication().getRealPath("/"+pPath+"/"+mlName));
			if(!f.exists()){
				f.mkdirs();
			}
			
			CmsTemplate c2=new CmsTemplate();
			c2.setTemplateName(mlName);
			c2.setCreatetime(new Date().toLocaleString());
			c2.setPath(pPath+"/"+mlName);
			c2.setPid(template.getTemplateId());
			c2.setHasContent(1);
			c2.setCmsUser((CmsUser)this.getSession().getAttribute("user"));
			ctservice.doAddTemplate(c2);
		
		this.printJSON("true");
	} catch (Exception e) {
		this.printJSON("false");
		this.getLogger().info("新建目录失败");
		e.printStackTrace();
	}
		
		return null;
	}
	/*
	 * 上传模板文件
	 */
	public String uploadTemplate() throws Exception{
		String ppath=ctservice.findTemplateById(template.getTemplateId()).getPath();//获取父模板路径
		String savePath=this.getApplication().getRealPath("/")+ppath+"/"+mbFileName;//获取要保存的路径
		
		try {
			FileInputStream fis = new FileInputStream(mb);
			FileOutputStream fos = new FileOutputStream(savePath);
			byte[] b = new byte[1024];
			int i = 0;
			while((i= fis.read(b))!=-1){
				
				fos.write(b,0,i);
			}
			fis.close();
			fos.close();
			
			CmsTemplate c=new CmsTemplate();
			c.setTemplateName(mbFileName);
			c.setCreatetime(new Date().toLocaleString());
			c.setPath(ppath+"/"+mbFileName);
			c.setPid(template.getTemplateId());
			c.setHasContent(0);
			c.setIsDisabled(0);
			CmsUser user=(CmsUser) this.getSession().getAttribute("user");
			c.setCmsUser(user);
			c.setTitleImgHeight(100);
			c.setTitleImgWidth(100);
			if(template.getCmsTemplateType().getTypeId() != null){
				c.setCmsTemplateType(template.getCmsTemplateType());
			}else{
				CmsTemplateType ctt = new CmsTemplateType();
				ctt.setTypeId(1);
				c.setCmsTemplateType(ctt);
			}
			ctservice.doAddTemplate(c); //执行添加
			this.printJSON("true");
		} catch (Exception e) {
			// TODO: handle exception
			this.printJSON("false");
			this.getLogger().info("上传模板文件失败");
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
	public String findAllTemplate() throws Exception{  //查询所有模板
	
		List<CmsTemplate>cs=ctservice.findTemplateByCondition(null, null, null, null, null);
		List<Tree>ts=new ArrayList<Tree>();
		for (CmsTemplate c : cs) {
			Tree t= new Tree();
			t.setId(c.getTemplateId());
			t.setText(c.getTemplateName());
			t.getAttributes().put("path", c.getPath());
			t.getAttributes().put("createtime", c.getCreatetime());
			t.getAttributes().put("pid", c.getPid());
			if(c.getCmsUser()!=null){
				t.getAttributes().put("creator", c.getCmsUser().getUserName());
			}
			if(c.getHasContent()==0){
				t.setState("open");
			}else{
				t.setState("closed");
			}
			ts.add(t);
		}
		ts=new Tree().findTree(ts);
		this.printJSON(Json.toJson(ts));
		return null;
	}

	
	
	
	
	public String findTemplateByNavigation() throws Exception{
		Map condition = new HashMap();
		condition.put("obj.cmsTemplateType.typeId = ?", 2);
		this.printJSON(Json.toJson(ctservice.findTemplateByCondition(condition, null, null, null, null)));
		return null;
	}
	
	
	public String findTemplateByIndex() throws Exception{
		Map condition = new HashMap();
		condition.put("obj.cmsTemplateType.typeId = ?", 1);
		this.printJSON(Json.toJson(ctservice.findTemplateByCondition(condition, null, null, null, null)));
		return null;
	}

	public String findTemplateByChannel() throws Exception{
		Map condition = new HashMap();
		condition.put("obj.cmsTemplateType.typeId = ?", 3);
		this.printJSON(Json.toJson(ctservice.findTemplateByCondition(condition, null, null, null, null)));
		return null;
	}
	public String findTemplateByContent() throws Exception{
		Map condition = new HashMap();
		condition.put("obj.cmsTemplateType.typeId = ?", 4);
		this.printJSON(Json.toJson(ctservice.findTemplateByCondition(condition, null, null, null, null)));
		return null;
	}
	
	
	public ICmsTemplateService getCtservice() {
		return ctservice;
	}
	public void setCtservice(ICmsTemplateService ctservice) {
		this.ctservice = ctservice;
	}
	public CmsTemplate getTemplate() {
		return template;
	}
	public void setTemplate(CmsTemplate template) {
		this.template = template;
	}


	public String getMlName() {
		return mlName;
	}


	public void setMlName(String mlName) {
		this.mlName = mlName;
	}


	public File getMb() {
		return mb;
	}


	public void setMb(File mb) {
		this.mb = mb;
	}


	public String getMbFileName() {
		return mbFileName;
	}


	public void setMbFileName(String mbFileName) {
		this.mbFileName = mbFileName;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
}
