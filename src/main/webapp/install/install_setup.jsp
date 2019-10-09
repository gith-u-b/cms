<%@page import="com.aaa.yf.util.Install"%>
<%@page contentType="text/html; charset=gbk" language="java" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>��װ���--JEECMS��װ��</title>
<link href="img/style.css" rel="stylesheet" type="text/css" />
</head>
<body>

<%
	String dbHost = request.getParameter("dbHost");
	String dbPort = request.getParameter("dbPort");
	String dbName = request.getParameter("dbName");
	String dbUser = request.getParameter("dbUser");
	String dbPassword = request.getParameter("dbPassword");

	String isCreateDb = request.getParameter("isCreateDb");
	String isCreateTable = request.getParameter("isCreateTable");
	String isInitData = request.getParameter("isInitData");
	String domain = request.getParameter("domain");
	String cxtPath = request.getParameter("cxtPath");
	String port = request.getParameter("port");

	
	
	
	
	
	String dbFileName = "/install/db/zzzycms-db-1.1-final.sql";
	String initFileName = "/install/db/zzzycms-init-1.1-final.sql";
	//String dbXmlFileName = "/WEB-INF/classes/jdbc.properties";
	String webXmlFrom = "/install/config/web.xml";
	String webXmlTo = "/WEB-INF/web.xml";
	 //�������ݿ�
	if ("true".equals(isCreateDb)) {
		Install.createDb(dbHost, dbPort, dbName, dbUser, dbPassword);
	}
	
	//������
	if ("true".equals(isCreateTable)) {
		String sqlPath = application.getRealPath(dbFileName);
		List<String> sqlList = Install.readSql(sqlPath);
		Install.createTable(dbHost, dbPort, dbName, dbUser, dbPassword,
				sqlList);
	}
	//��ʼ������
	if ("true".equals(isInitData)) {
		String initPath = application.getRealPath(initFileName);
		List<String> initList = Install.readSql(initPath);
		Install.createTable(dbHost, dbPort, dbName, dbUser, dbPassword,
				initList);
	}
	     
	//����web.xml
	String webXmlFromPath = application.getRealPath(webXmlFrom);
	String webXmlToPath = application.getRealPath(webXmlTo);
	Install.webXml(webXmlFromPath, webXmlToPath);
	
	//�޸�jdbc.properties;
	String jdbcpath=application.getRealPath("/install/config/jdbc.properties");
	Install.dbXml(jdbcpath,dbHost,dbPort,dbName,dbUser,dbPassword); 
	
	//�޸�log4j.properties
	String log4jPath=application.getRealPath("/install/config/log4j.properties");
	Install.dbXml2(log4jPath,dbHost,dbPort,dbName,dbUser,dbPassword); 
	String log4jToPath=application.getRealPath("/WEB-INF/classes/log4j.properties");
	Install.webXml(log4jPath, log4jToPath);
	
%>

<table width="600" align="center"
	style="border: #106DBA 1px solid; margin-top: 8%;">
	<tr>
		<td bgcolor="#D1E9FA">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="27%" height="60" rowspan="2" align="center"><img
					src="img/logo.gif" border="0" /></td>
				<td width="73%" height="30" class="f14b">3��ϵͳ��װ���</td>
			</tr>
			<tr>
				<td height="20" valign="top">&nbsp;&nbsp;&nbsp;&nbsp;��װϵͳ��װ��ɣ�������TOMCAT����</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td height="280" align="left" bgcolor="#F0F8FD"
			style="padding: 10px; line-height: 1.7em;">��ϲ����ϵͳ�Ѿ���װ�ɹ���<br />
		������TOMCAT����ֻ������TOMCAT����֮�󣬰�װ������Ч��<br />
		��̨��¼��ַ����վ��·��/login.jsp��<br />
		��̨����Աadmin������admin��</td>
	</tr>
</table>

</body>
</html>
