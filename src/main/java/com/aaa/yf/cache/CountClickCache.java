package com.aaa.yf.cache;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import org.apache.log4j.Logger;

import com.aaa.yf.entity.CmsClick;
import com.aaa.yf.entity.CmsContent;
import com.aaa.yf.util.ConfigUtil;


public class CountClickCache {

	private static Logger logger = Logger.getLogger("SYSTEM");
	private static LinkedList<CmsClick> Cache = new LinkedList<CmsClick>();
	public static void add(String cid){
		CmsContent content = new CmsContent();
		content.setContentId(Integer.parseInt(cid));
		CmsClick c = new CmsClick(content, new Date());
		Cache.add(c);
	}
	public static void clear(){
		
		Cache.clear();
	}
	public static void output(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		BufferedOutputStream fos = null;
		try {
			fos = new BufferedOutputStream(new FileOutputStream(ConfigUtil.click));
			
			StringBuffer sb = new StringBuffer();
			for(CmsClick c : Cache){
				sb.append(c.getCmsContent().getContentId()+"\t"+sdf.format(c.getClickTime())+"\r\n");
			}
			fos.write(sb.toString().getBytes());   //把点击记录写入的本地txt
		} catch (FileNotFoundException e) {
			logger.info("记录点击失败 找不到文件");
		} catch (IOException e) {
			logger.info("记录点击失败 io异常");
		}
		finally{
			
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
}
