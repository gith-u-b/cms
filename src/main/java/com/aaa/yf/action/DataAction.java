package com.aaa.yf.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aaa.yf.entity.CmsDuplicate;
import com.aaa.yf.entity.CmsUser;
import com.aaa.yf.service.ICmsDataService;
import com.aaa.yf.service.ICmsDuplicateService;
import com.aaa.yf.util.CmsDatapool;
import com.aaa.yf.util.ConfigUtil;
import com.aaa.yf.util.JsonFile;
import com.aaa.yf.util.toJsonUtil;
import com.et.mvc.util.Json;

@Controller
@Action
@Scope("prototype")
@ParentPackage(value="cms")
public class DataAction extends BaseAction {

	@Autowired
	private ICmsDataService cdservice;
	@Autowired
	private ICmsDuplicateService cdupservice;
	private CmsDatapool table;
	private String tableNames;
	private File sqlfile;
	private String sqlfileContentType;
	private String sqlfileFileName;
	private String dbnames;

	/*
	 * 数据恢复（服务器中的备份文件）
	 */
	public String dataRecover1() throws Exception{
		try {
			String fileName =  this.getApplication().getRealPath("/WEB-INF/backupfile")+"/"+sqlfileFileName;
			String arrDbname[] = dbnames.split(","); 
		String jdbcPath = this.getApplication().getRealPath("/install/config/jdbc.properties");
		String host = ConfigUtil.readValue(jdbcPath, "jdbc.host");
		String user = ConfigUtil.readValue(jdbcPath, "jdbc.username");
		String pwd = ConfigUtil.readValue(jdbcPath, "jdbc.password");
			Runtime rt = Runtime.getRuntime();
			
			for (String dbname : arrDbname) {
//			String cmd = "mysql -h"+host+" -u"+user+" -p"+pwd+" --default-character-set=utf8 "
//					+ dataBaseName + "<" + filePathName;
//			rt.exec("cmd /c " + cmd);
				String cmd = "mysql -h"+host+" -u"+user+" -p"+pwd+" --default-character-set=utf8 "+dbname+"<" + fileName;
				rt.exec("cmd /c " + cmd);
			}
			this.printJSON("yes");
		} catch (Exception e) {
			this.printJSON("no");
			this.getLogger().info("数据回复失败（服务器）");
			e.printStackTrace();
		}
		
		return null;
	}
	/*
	 * 查询备份的文件
	 */
	public String findBackUpFile() throws Exception{
		
		File root = new File(this.getApplication().getRealPath(
				"/WEB-INF/backupfile"));// 获取备份文件夹的绝对路径
		List<JsonFile> list = new ArrayList<JsonFile>();
		JsonFile jf = new JsonFile(root.getName(), root.length(),
				root.getAbsolutePath(),
				new Date(root.lastModified()).toLocaleString());
		jf.setState("closed");
		list.add(jf);// 添加到集合中
		recursionFile(root, list);// 递归获取所有文件
		toJsonUtil t = new toJsonUtil();
		t.setTotal(list.size());
		t.setRows(list);
		this.printJSON(Json.toJson(t));
		return null;
	}
	/**
	 * 获取文件夹下的所有资源
	 * 
	 * @param parent
	 * @param list
	 */
	public void recursionFile(File parent, List<JsonFile> list) {

		File children[] = parent.listFiles();
		for (File file : children) {
			@SuppressWarnings("deprecation")
			JsonFile jfs = new JsonFile(file.getName(), file.length(),
					file.getAbsolutePath(), parent.getName(), new Date(
							parent.lastModified()).toLocaleString());
			list.add(jfs);
			if (file.isDirectory()) {
				jfs.setState("closed");
				recursionFile(file, list);
			}
		}
	}
	
	/*
	 * 还原数据
	 */
	public String dataRecover() throws Exception{
		
		try {
			
			//由于火狐得不到文件的绝对路径，所以先把文件上传至服务器，在执行还原
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String fileName = this.getApplication().getRealPath("/WEB-INF/backupfile")+"/"+sdf.format(date)+"."+this.getSqlfileFileName().substring(this.getSqlfileFileName().lastIndexOf(".")+1, this.getSqlfileFileName().length());
			
			//文件输出流
			FileOutputStream fos = new FileOutputStream(fileName);
			//文件上传流
			FileInputStream fis = new FileInputStream(sqlfile);
			byte[] b = new byte[1024];
			int len = 0;
			//循环把文件二进制数据写入到数据
			while((len=fis.read(b))>0){
				fos.write(b, 0, len);
			}
			String arrDbname[] = dbnames.split(","); 
			String jdbcPath = this.getApplication().getRealPath("/install/config/jdbc.properties");
			String host = ConfigUtil.readValue(jdbcPath, "jdbc.host");
			String user = ConfigUtil.readValue(jdbcPath, "jdbc.username");
			String pwd = ConfigUtil.readValue(jdbcPath, "jdbc.password");
			Runtime rt = Runtime.getRuntime();
			
			for (String dbname : arrDbname) {
//				String cmd = "mysql -h"+host+" -u"+user+" -p"+pwd+" --default-character-set=utf8 "
//						+ dataBaseName + "<" + filePathName;
//				rt.exec("cmd /c " + cmd);
				String cmd = "mysql -h"+host+" -u"+user+" -p"+pwd+" --default-character-set=utf8 "+dbname+"<" + fileName;
				rt.exec("cmd /c " + cmd);
			}
			File file = new File(fileName);  //最后把文件删除掉
			if(file.exists()){
				file.delete();
			}
			this.printJSON("yes");
		} catch (Exception e) {
			this.printJSON("no");
			this.getLogger().info("还原数据失败（本地）");
			e.printStackTrace();
		}
		
		return null;
	}
	/*
	 * 获取所有数据库
	 */
	public String findAllDatabase() throws Exception{
		List<CmsDatapool> list = cdservice.findAllDatabase();
		this.printJSON(Json.toJson(list));
		return null;
	}
	/*
	 *备份数据库表
	 */
	public String dataTableBackUp() throws Exception{
		
		// 获取jdbc信息
				String jdbcPath = this.getApplication().getRealPath("/install/config/jdbc.properties");
				String host = ConfigUtil.readValue(jdbcPath, "jdbc.host");
				String user = ConfigUtil.readValue(jdbcPath, "jdbc.username");
				String pwd = ConfigUtil.readValue(jdbcPath, "jdbc.password");
				String dbName = ConfigUtil.readValue(jdbcPath, "jdbc.dbName");

				// 获取服务器上存放备份文件的路径
				String serverPath = this.getApplication().getRealPath("/WEB-INF/backupfile");
				
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				// 彷止文件重名利用当前时间作为文件名
				String backupName = sdf.format(date);

				// 向数据备份记录表里添加信息
				this.doAddDuplicate(serverPath + "\\" + backupName + ".sql");
				try {
					Runtime rt = Runtime.getRuntime();
					
					String cmd;
//					if (tableNames == null) {
//						cmd = "mysqldump -h" + localhost + " -u" + user + " -p" + pwd
//								+ " --default-character-set=utf8 " + dbName + " > "
//								+ serverPath + "/" + backupName + ".sql";
//					} else {
//						System.out.println("111111");
//						cmd = "mysqldump -h" + host + " -u" + user + " -p" + pwd
//								+ " --default-character-set=utf8 " + dbName + " "
//								+ tableNames + " > " + serverPath + "/" + backupName
//								+ ".sql";
//					}
					if (tableNames == null) {
						cmd = "mysqldump -h"+host+" -u"+user+" -p"+pwd+" --default-character-set=utf8 "+dbName+" > "
								+ serverPath + "/" + backupName + ".sql";
					} else {
						
						cmd = "mysqldump -hlocalhost -uroot -padmin --default-character-set=utf8 "+dbName+" "
								+ tableNames + " > " + serverPath + "/" + backupName
								+ ".sql";
					}
					rt.exec("cmd /c " + cmd);
					this.printJSON("true");
				} catch (Exception e) {
					e.printStackTrace();
					this.getLogger().info("备份数据库表失败");
					this.printJSON("false");
				}
		return null;
	}
	
	/**
	 * 向数据备份记录表里添加记录信息
	 * 
	 * @param serverPath
	 *            备份的资源文件
	 */
	public void doAddDuplicate(String serverPath) {
		CmsUser user = (CmsUser) this.getSession().getAttribute("user");
		String tableString = "";
		if (tableNames != null) {
			String tableNameArray[] = tableNames.split(" ");
			for (int i = 0; i < tableNameArray.length; i++) {
				tableString += tableNameArray[i] + ",";
			}
		} else {
			tableString = "MyCms数据库";
		}
		// 向数据备份记录表里添加记录信息
		CmsDuplicate cmsDuplicate = new CmsDuplicate();
		cmsDuplicate.setDuplicateUrl(serverPath);
		cmsDuplicate.setDuplicateTable("备份的数据为" + tableString);
		cmsDuplicate.setDuplicateUserName(user.getUserName());
		cmsDuplicate.setDuplicateDatetime(new Date().toLocaleString());
		cdupservice.doAddDuplicate(cmsDuplicate);
	}
	
	/*
	 * 根据表明查询表的列信息
	 */
	public String findTableColumnByTableName() throws Exception{
		List<CmsDatapool> list = cdservice.findTableColumnByTableName(table.getTableName());
		this.printJSON(Json.toJson(list));
		return null;
	}
	
	public String findTable() throws Exception{
		List<CmsDatapool> list = cdservice.findAllTable();
		this.printJSON(Json.toJson(list));
		
		return null;
	}
	public ICmsDataService getCdservice() {
		return cdservice;
	}
	public void setCdservice(ICmsDataService cdservice) {
		this.cdservice = cdservice;
	}

	public CmsDatapool getTable() {
		return table;
	}

	public void setTable(CmsDatapool table) {
		this.table = table;
	}

	public String getTableNames() {
		return tableNames;
	}

	public void setTableNames(String tableNames) {
		this.tableNames = tableNames;
	}

	public ICmsDuplicateService getCdupservice() {
		return cdupservice;
	}

	public void setCdupservice(ICmsDuplicateService cdupservice) {
		this.cdupservice = cdupservice;
	}
	public File getSqlfile() {
		return sqlfile;
	}
	public void setSqlfile(File sqlfile) {
		this.sqlfile = sqlfile;
	}
	public String getSqlfileContentType() {
		return sqlfileContentType;
	}
	public void setSqlfileContentType(String sqlfileContentType) {
		this.sqlfileContentType = sqlfileContentType;
	}
	public String getSqlfileFileName() {
		return sqlfileFileName;
	}
	public void setSqlfileFileName(String sqlfileFileName) {
		this.sqlfileFileName = sqlfileFileName;
	}
	public String getDbnames() {
		return dbnames;
	}
	public void setDbnames(String dbnames) {
		this.dbnames = dbnames;
	}
	
	
	
}
