package com.aaa.yf.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * 数据库备份工作类
 * 
 * @author Administrator
 * 
 */
@Component("databackupjob")
public class DataBackUpJob {
	/**
	 * 数据库备份
	 * 
	 * @throws Exception
	 */
	public void doDataBackUp() throws Exception {
		// 获取服务器上存放备份文件的路径
		String serverPath = DataBackUpJob.class.getClassLoader()
				.getResource("").getPath();    //最终获得/E:/Eclipse/workspace/MyCms/target/classes/
		
		
		
		
		//获取jdbc 配置文件的路径
		String jdbcPath=serverPath.substring(1,serverPath.length() - 16)+"install/config/jdbc.properties";
		
		serverPath = serverPath.substring(1, serverPath.length() - 8)
				+ "backupfile/zidong";
		File f=new File(serverPath);
		if(f.exists()){ //如果存在，则删除 后创建
			recursionFile(f);//删除
			f.mkdirs();//创建
		}else{
			f.mkdirs();//不存在，创建
		}
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		// 彷止文件重名利用当前时间作为文件名
		String backupName = sdf.format(date);
		Runtime rt = Runtime.getRuntime();
		//获取ip
		String host=ConfigUtil.readValue(jdbcPath, "jdbc.host");
		//获取用户名
		String userName=ConfigUtil.readValue(jdbcPath, "jdbc.userName");
		//获取密码
		String password=ConfigUtil.readValue(jdbcPath, "jdbc.password");
		//获取数据库名
		String dbName=ConfigUtil.readValue(jdbcPath, "jdbc.dbName");
		
		// 一定要加 -hlocalhost(或是服务器IP地址)
		String cmd = "mysqldump -h"+host+" -u"+userName+" -p"+password+" --default-character-set=utf8 "+dbName+" > "
				+ serverPath + "/" + backupName + ".sql";
		rt.exec("cmd /c " + cmd);

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

}
