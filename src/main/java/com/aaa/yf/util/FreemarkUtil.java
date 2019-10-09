package com.aaa.yf.util;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreemarkUtil {
	public static Configuration cfg = new Configuration();
	@SuppressWarnings("rawtypes")
	public static  void createFile(String ftlpath,String ftlname,String filename,Map root){
		BufferedWriter out = null;
		try { 
	        //设定去哪里读取相应的ftl模板文件
	 //       cfg.setClassForTemplateLoading(this.getClass(),"/ftl");
	          cfg.setDirectoryForTemplateLoading(new File(ftlpath));
	          cfg.setDefaultEncoding(ConfigUtil.encoding); 
	      
	        //在模板文件目录中找到名称为name的文件
				Template temp = cfg.getTemplate(ftlname);
				temp.setEncoding(ConfigUtil.encoding);
      
				        //通过一个文件输出流，就可以写到相应的文件中
				//这里必须再设置一次字符集  否则 还是乱码    
				out = new  BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), ConfigUtil.encoding));
				temp.process(root, out);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TemplateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	      
	           
	        
	}
}
