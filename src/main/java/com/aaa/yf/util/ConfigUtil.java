package com.aaa.yf.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
/**
 * 参数配置帮助类
 * @author Administrator
 *
 */
public class ConfigUtil {
	public static  String encoding;//字符编码格式
	public static  String ftlPath; //ftl文件所放的路径
	public static  String indexTemplate;//初始化模板
	public static  String saveDir; //保存路径
	public static  String waterMarkImg;//水印图片名称
	public static  String click;
	public static  String clickCycle;
	public static  String clickClear;
	public static  String logClear;
	public static  String dataBackUp;
	
	static{
		try {
			Properties prop = new Properties();
			InputStream inputStream = ConfigUtil.class.getResourceAsStream("/cms-config.properties");
			prop.load(inputStream);
			encoding = prop.getProperty("encoding");
			saveDir= prop.getProperty("saveDir");
			waterMarkImg= prop.getProperty("waterMarkImg");
			click= prop.getProperty("click");
			if(click!=null && !"".equals(click)){
				
				
				String path = click.substring(0, click.lastIndexOf("/"));
				
				File f1=new File(path);
				if(!f1.exists()){
					f1.mkdirs();
				}
				File f2 = new File(click);
				if(!f2.exists()){
					f2.createNewFile();
				}
			}
			clickCycle=prop.getProperty("clickCycle");
			clickClear=prop.getProperty("clickClear");
			logClear=prop.getProperty("logClear");
			dataBackUp=prop.getProperty("dataBackUp");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	//根据key 得到value
	public static String readValue(String filePath,String key) {
		  Properties props = new Properties();
		        try {
		         InputStream in = new BufferedInputStream (new FileInputStream(filePath));
		         props.load(in);
		         String value = props.getProperty (key);
		            return value;
		        } catch (Exception e) {
		         e.printStackTrace();
		         return null;
		        }
		 }
	
	//修改或设置
	public static void writeProperties(String filePath,String parameterName,String parameterValue) {
		 Properties prop = new Properties();
	     try {
	      InputStream fis = new FileInputStream(filePath);
	            //从输入流中读取属性列表（键和元素对）
	            prop.load(fis);
	            //调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。
	            //强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
	            OutputStream fos = new FileOutputStream(filePath);
	            prop.setProperty(parameterName, parameterValue);
	            //以适合使用 load 方法加载到 Properties 表中的格式，
	            //将此 Properties 表中的属性列表（键和元素对）写入输出流
	            prop.store(fos, "Update '" + parameterName + "'value"+parameterValue);
	        } catch (IOException e) {
	         System.err.println("Visit "+filePath+" for updating "+parameterName+" value error");
	        }
	}
	
	
}
