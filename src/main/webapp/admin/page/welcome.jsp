<%@page import="com.aaa.yf.entity.CmsUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  
 
  

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>欢迎首页</title>
</head>
<body>
	


	<%
     String path=request.getContextPath();
	CmsUser c=(CmsUser)request.getSession().getAttribute("user");
	String time=c.getLastLoginTime().toString();

	String s1 =System.getProperty("os.name");
String s2=System.getProperty("os.version");
String s3 =  System.getProperty("os.arch");

String s4 =System.getProperty("user.name");   //用户名    
String s5 =	  System.getProperty("user.home");                
String s6 =	  System.getProperty("user.dir"); //当前程序所在目录  
String s7 =	System.getProperty("java.version");
String s8 =System.getProperty("java.vm.version");
long i1 =Runtime.getRuntime().totalMemory()/(1024*1024);
long i2 =Runtime.getRuntime().freeMemory()/(1024*1024);

long i3 =Runtime.getRuntime().maxMemory()/(1024*1024);


%>


	<div>
		<div style="float: left;margin-left: 50px;"><img src="<%=path %>/admin/page/images/test.jpg"></div>
		<div style="text-align: left;font-size: 14px;float: left;margin-left: 50px">
		欢迎使用AAACMS内容管理系统！<br>
		AAACMS程序版本： aaacms-2014-sb2 <a href="#" style="color: red;text-decoration: none;" >【查看最新版本】</a><br>
		您上次登录的时间是：<%=time %><br>
		已用内存：<font color="blue"><%=i1-i2 %>MB</font>    剩余内存：<font color="red"><%=i2 %>MB</font>     最大内存：<font color="blue"><%=i1 %>MB</font><br>
	 	</div>
	</div>
	<div style="clear: both;padding-top: 10px;color: blue">
		<hr color="#E0ECFF"/>
			系统属性
		<hr color="#E0ECFF" />
	</div>
	
	 
	 
	 <div style="text-align: left;font-size: 14px;margin-top: 40px;clear: left;margin-left: 50px">
	操作系统版本： 	<%=s1 %> <%=s2 %><br/>
操作系统类型： 	<%=s3 %> 62位<br/>
用户： 	<%=s4 %><br/>
目录： 	<%=s5 %><br/>
临时目录： 	 <%=s6 %><br/>
JAVA运行环境： 	Java(TM) SE Runtime Environment <%=s7 %><br/>
JAVA虚拟机： 	Java HotSpot(TM) Client VM <%=s8 %><br/>

   	</div>
   	
   	<div style="clear: both;padding-top: 40px;color: blue">
		<hr color="#E0ECFF"/>
	</div>
   	
</body>
</html>