<?xml version="1.0" encoding="UTF-8" ?>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	application.setAttribute("path", path);
%>
	
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>home</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/admin/css/admin_style.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/admin/css/skins/tpphp.css" />
<script src="<%=path %>/admin/js/jquery-1.4.4.min.js"></script>
<script src="<%=path %>/admin/js/main.js"></script>
<script src="<%=path %>/admin/js/artDialog.js"></script>

  <link rel="stylesheet" href="<%=path %>/easyui/themes/default/easyui.css" type="text/css"></link>
  <link rel="stylesheet" href="<%=path %>/easyui/themes/icon.css" type="text/css"></link>
  <script type="text/javascript" src="<%=path %>/easyui/jquery.min.js"></script>
  <script type="text/javascript" src="<%=path %>/easyui/jquery.easyui.min.js"></script>
  <script type="text/javascript" src="<%=path %>/easyui/locale/easyui-lang-zh_CN.js"></script>
  <script src="<%=path %>/admin/js/accordion.js"></script>
  <script src="<%=path %>/admin/js/center.js"></script>

<script>
function openMsgWindow(user) {
	$.messager.alert('你好！',"   <font color='red'>"+user+'</font>  欢迎光临！'); 
}
function addTabed1(){
		if($("#tab").tabs("exists","系统设置")){
			
			$("#tab").tabs("select","系统设置");
			
		}else{
			$('#tab').tabs('add',{   
			    title:"系统设置",   
			    content:"<iframe src='admin/page/system/systemset.jsp' width='99%' height='99%' scrolling='no' frameborder='0' ></iframe>",   
			    closable:true,
			    selected:true
			       
			});  
		}
}
//系统管理
function addTabed2(){
	if($("#tab").tabs("exists","数据备份")){
		
		$("#tab").tabs("select","数据备份");
		
	}else{
		$('#tab').tabs('add',{   
		    title:"数据备份",   
		    content:"<iframe src='admin/page/system/databackup.jsp' width='99%' height='99%' scrolling='no' frameborder='0' ></iframe>",   
		    closable:true,
		    selected:true
		       
		});  
	}
}
//用户管理
function addTabed3(){
	if($("#tab").tabs("exists","用户管理")){
		
		$("#tab").tabs("select","用户管理");
		
	}else{
		$('#tab').tabs('add',{   
		    title:"用户管理",   
		    content:"<iframe src='admin/page/system/user.jsp' width='99%' height='99%' scrolling='no' frameborder='0' ></iframe>",   
		    closable:true,
		    selected:true
		       
		});  
	}
}
//用户管理
function addTabed4(){
	if($("#tab").tabs("exists","新闻管理")){
		
		$("#tab").tabs("select","新闻管理");
		
	}else{
		$('#tab').tabs('add',{   
		    title:"新闻管理",   
		    content:"<iframe src='admin/page/news/news.jsp' width='99%' height='99%' scrolling='no' frameborder='0' ></iframe>",   
		    closable:true,
		    selected:true
		       
		});  
	}
}
//用户管理
function addTabed5(){
	if($("#tab").tabs("exists","评论管理")){
		
		$("#tab").tabs("select","评论管理");
		
	}else{
		$('#tab').tabs('add',{   
		    title:"评论管理",   
		    content:"<iframe src='admin/page/system/comment.jsp' width='99%' height='99%' scrolling='no' frameborder='0' ></iframe>",   
		    closable:true,
		    selected:true
		       
		});  
	}
}
//用户管理
function addTabed6(){
	if($("#tab").tabs("exists","操作日志")){
		
		$("#tab").tabs("select","操作日志");
		
	}else{
		$('#tab').tabs('add',{   
		    title:"操作日志",   
		    content:"<iframe src='admin/page/system/operatelog.jsp' width='99%' height='99%' scrolling='no' frameborder='0' ></iframe>",   
		    closable:true,
		    selected:true
		       
		});  
	}
}
//用户管理
function addTabed7(){
	if($("#tab").tabs("exists","注册统计")){
		
		$("#tab").tabs("select","注册统计");
		
	}else{
		$('#tab').tabs('add',{   
		    title:"注册统计",   
		    content:"<iframe src='admin/page/system/registercountchart.jsp' width='99%' height='99%' scrolling='no' frameborder='0' ></iframe>",   
		    closable:true,
		    selected:true
		       
		});  
	}
}
//个人资料设置
function addTabed8(){
	if($("#tab").tabs("exists","密码修改")){
		
		$("#tab").tabs("select","密码修改");
		
	}else{
		$('#tab').tabs('add',{   
		    title:"密码修改",   
		    content:"<iframe src='admin/page/system/personinfo.jsp' width='99%' height='99%' scrolling='no' frameborder='0' ></iframe>",   
		    closable:true,
		    selected:true
		       
		});  
	}
}
</script>
</head>
<body style="overflow:hidden;">

<!-- 与禁用浏览器有关，无需理会 -->
<!-- 服务器端脚本，强制浏览器重新访问服务器下载页面，而不从缓存读取，让它从新访问 -->
	<% 

        response.setHeader("Cache-Control", "no-cache");
       
        response.setHeader("Cache-Control", "no-store");

        response.setDateHeader("Expires", 0);

        response.setHeader("Pragma", "no-cache");

	%>
	
	
	<!-- 当我们注销时，王session域放入history ，登陆成功时移除history  ， 
	
	当我们注销掉，点击后退按钮则会返回登录页面
	 -->
	<c:if test="${history != null}">      <!-- 与禁用浏览器后退键有关，无需理会 -->
		<script>
			window.location="${path}/login.jsp";
		</script>
	</c:if>


	<div class="top">
	<div class="top_about">	
		<a href="javascript:openMsgWindow('${user.userName }')" class="help1" id="btn2">使用帮助</a>
		<a href="javascript:openMsgWindow('${user.userName }')" class="help2">关于</a>
	</div>
	<div class="admin_logo">
		<img src="<%=path %>/admin/images/admin_logo.jpg">
	</div>
	<div class="top_nav">
			<ul>
				<li><a href="<%=path %>/admin/page/main.jsp" >后台首页</a></li>
				<li><a href="index.html" target="_blank" class="selected" >网站前台首页</a></li>
				<li><a href="javascript:addTabed7()">注册统计</a></li>
				<li><a href="javascript:addTabed6()">日志记录</a></li>
				<li><a href="javascript:addTabed5()">评论管理</a></li>
				<li><a href="javascript:addTabed4()">新闻管理</a></li>
				<li><a href="javascript:addTabed2()">数据备份</a></li>
				<li><a href="javascript:addTabed3()">用户管理</a></li>
				<li><a href="javascript:addTabed1()">系统设置</a></li>
			</ul>
	</div>
	
	
	
	<div class="top_member">
	欢迎您，<font color="blue">${sessionScope.user.userName }</font> | <a href="javascript:addTabed8()">密码修改</a> 
	</div>
</div>

<div class="left" style="height: 550px">
	<div  class="member_info"><div class="member_ico">
		<img src="<%=path %>/admin/images/admin_logo1.jpg" width="43" height="43"></div><a class="system_a" href="javascript:addTabed1()">系统设置</a><a href="<%=path %>/user!logout.action" class="system_log">注销</a> <a href="<%=path %>/user!logout.action" class="system_logout">退出</a>
	</div>
	<div class="left_title">功能操作</div>
	<!-- <ul class="side">
		<li><a href="#">网站栏目管理</a></li>
		<li><a href="#" class="selected">档案列表</a></li>
		<li><a href="#">等待审核的文档(23)</a></li>
		<li><a href="#">我发布的文档</a></li>
		<li><a href="#">评论管理</a></li>
		<li><a href="#">内容回收站</a></li>
	</ul>
	 -->
	 
	 <div id="mainacc" class="easyui-accordion" style="width:197px;height: 350px;">  
	</div>  
	 
	 
	 
	<ul class="side catsub">
				<li class="feed"><a href="#">网站订阅查看</a></li>
				<li class="side_about"><a href="#">版权声明</a></li>
	</ul>
</div>
<div class="right"  style="background-color:#F2F2F2;  ">


   <!-- <IFRAME style="OVERFLOW: visible" id="main" name="main" src="center.jsp" frameBorder=0 width="100%" scrolling="yes" height="100%"></IFRAME>-->
	  
	   <div id="tab"  class="easyui-tabs" data-options="fit:false" style=" height: 530px;margin-top: 20px; margin-left: 205px;" >
			     <div  title="首页"  data-options="iconCls:'icon-reload',closable:false" >  
			           <iframe src="<%=path %>/admin/page/welcome.jsp" width="100%" height="100%" scrolling="no" frameborder="0" ></iframe>
			    </div>
			      
	   </div>
	  
	 
</div>




















</body>
</html>