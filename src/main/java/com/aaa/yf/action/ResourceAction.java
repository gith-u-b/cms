package com.aaa.yf.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;

import com.aaa.yf.util.JsonFile;
import com.aaa.yf.util.UnzipFile;
import com.aaa.yf.util.toJsonUtil;
import com.et.mvc.util.Json;


@Action
@Scope("prototype")
@ParentPackage(value="cms")
public class ResourceAction extends BaseAction {

	
	private File up;
	private String upContentType;
	private String ppath;  
	private String upFileName;
	
	
	private String resourcePath; //原来的资源名字
	
	
	
	
	/**
	 * 删除文件
	 * @throws Exception 
	 */
	
	public void deleteStyle() throws Exception{
		try {
			File f=new File(ppath);
			if(f.isDirectory()){
				recursionFile(f);
			}
				f.delete();
			
			this.printJSON("true");
		} catch (Exception e) {
			// TODO: handle exception
			this.getLogger().info("删除样式失败");
			this.printJSON("false");
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 递归删除文件以及文件夹
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
	 * 更改资源名字
	 */
	public void updateStyle() throws Exception{
		try {
			File f1=new File(ppath);
			File f2=new File(resourcePath);
			if(f2.renameTo(f1)){   //f2的名字更改为f1的名字
				this.printJSON("true");
			}else{
				this.printJSON("false");
			}
		} catch (Exception e) {
			// TODO: handle exception
			this.printJSON("false");
			this.getLogger().info("更改样式失败");
			e.printStackTrace();
		}
	}
	
	/*
	 * 资源上传
	 */
	public String uploadStyle() throws Exception{
		
		String type=upFileName.substring(upFileName.lastIndexOf(".")+1);//获取上传文件的类型
		try {
			if("zip".equals(type)){//是否为压缩文件
				UnzipFile.unZip(up, ppath); //解压并保存
			}else{
				FileInputStream fis = new FileInputStream(up);
				FileOutputStream fos = new FileOutputStream(ppath+upFileName);
				byte[] b = new byte[1024];
				
				int i = 0;
				while((i= fis.read(b))!=-1){
					
					fos.write(b,0,i);
				}
				fis.close();
				fos.close();
			}
			//CmsUser cu=(CmsUser) this.getSession().getAttribute("cmsUser1");//获取当前用户
			//CmsFile c=new CmsFile(cu, null, upFileName, ppath+upFileName, new Date(), 1);//生成cmsfile对象
			//iCmsFileBiz.add(c);//添加
			this.printJSON("yes");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			this.getLogger().info("资源上传失败");
			this.printJSON("no");
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	/**
	 * 获取所有资源文件
	 * @throws Exception
	 */
	public void findAllFile() throws Exception{
		File root = new File( this.getApplication().getRealPath("/resource/news")  );//获取资源文件夹的绝对路径
		List<JsonFile> list = new  ArrayList<JsonFile>();
		JsonFile jf = new JsonFile(root.getName(), root.length(), root.getAbsolutePath(),new Date(root.lastModified()).toLocaleString());
		jf.setState("closed");
		list.add(jf);//添加到集合中
	
		recursionFile(root,list);//递归获取所有文件
		toJsonUtil t = new toJsonUtil();
		t.setRows(list);
		t.setTotal(list.size());
		this.printJSON(Json.toJson(t));
	}
	/**
	 * 递归获取文件夹下的所有资源
	 * @param parent
	 * @param list
	 */
	public void recursionFile(File parent,List<JsonFile> list){
		
		File children []= parent.listFiles();
		for (File file : children) {
			JsonFile jfs = new JsonFile(file.getName(), file.length(), file.getAbsolutePath(),parent.getName(),new Date(parent.lastModified()).toLocaleString());
			list.add(jfs);
			if(file.isDirectory()){
				jfs.setState("closed");
				recursionFile(file,list);
			}
		}
	}
	public File getUp() {
		return up;
	}
	public void setUp(File up) {
		this.up = up;
	}
	public String getUpContentType() {
		return upContentType;
	}
	public void setUpContentType(String upContentType) {
		this.upContentType = upContentType;
	}
	public String getPpath() {
		return ppath;
	}
	public void setPpath(String ppath) {
		this.ppath = ppath;
	}
	public String getUpFileName() {
		return upFileName;
	}
	public void setUpFileName(String upFileName) {
		this.upFileName = upFileName;
	}

	public String getResourcePath() {
		return resourcePath;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}
	
}
