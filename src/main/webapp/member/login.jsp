<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path=request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会员登录  - Powered by JEECMS</title>
<link href="<%=path %>/resource/news/cms/front.css" rel="stylesheet" type="text/css"/>
<script src="<%=path %>/resource/news/cms/jquery.js" type="text/javascript"></script>
<script src="<%=path %>/resource/news/cms/front.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="<%=path %>/resource/news/cms/www/red/css/regist.css" />
</head>

<body>
<div class="container">
<div class="toolbar box">
   <div class="toolbar-inner">
    <div class="fl " id="logincontent">
     	<c:if test="${empty u }">
         	游客您好，您还没有登录哦！ <a href="<%=path %>/member/register.jsp">免费注册</a>|<a href="<%=path %>/member/login.jsp">登录</a>|<a href="#">找回密码</a></div>
     	</c:if>
        <c:if test="${!empty u }">
        	 欢迎您: ${u.userName} &nbsp; ${u.cmsGroup.groupName} &nbsp; <a href="<%=path %>/member/logout.jsp">退出登录</a>
        </c:if>
	</div>
    <div class="fr">
	<a href="#" target="_blank">Tags</a>|<a href="#" target="_blank">高级搜索</a>|<a href="${base}/rss.jspx" target="_blank">RSS订阅</a>|<a href="#" onClick="this.style.behavior='url(#default#homepage)';this.setHomePage('http://www.jeecms.com');">设为主页</a>|
	<a onclick="javascript:window.external.AddFavorite('index.html', '${site.name}');" href="javascript:void(0)" class="login_user">加入收藏</a>
	</div>
  </div>
</div>
<div class="main">  
	<div class="header box">
        <div class="brand">
        </div>
    </div>
<form id="jvForm" action="<%=path %>/user_frontlogin.action" method="post">
    
		<table width="900" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="500" height="300" align="left"><img src="<%=path %>/resource/news/cms/www/red/img/member/llogo.gif" /></td>
            <td>
            <div class="login-bg">
<table width="96%" border="0" align="center" cellpadding="0" cellspacing="5">	
                                <tr>
                                  <td height="40" colspan="3"><strong style="font-size:14px;">会员登录</strong></td>
                                </tr>	  
                                <tr>
                                    <td width="67" height="30" align="right">用户名：</td>
                                  <td colspan="2"><input value="${user.userName} " type="text" id="username" name="user.userName" class="input required"/></td>
                  </tr>
                                <tr>
                                  <td height="30" align="right">密　码：</td>
                                  <td colspan="2"><input type="password" id="password" name="user.userPassword" class="input required"/></td>
                                </tr>
                                <tr>
                                  <td height="30" align="right">验证码：</td>
                                  <td ><input type="text" id="captcha" name="validateCode" class="input required"/></td>
                                </tr>	
                                 <tr>
                                  <td height="30" align="right">&nbsp;</td>
                                  <td colspan="2"><img src="<%=path%>/image.jsp" onclick="this.src='<%=path%>/image.jsp?d='+new Date()*1" width="100" height="35"/></td>
                </tr>	
                                 <tr>
                                  <td height="40" colspan="3" align="center"><input type="submit" value=" 登 录 " class="login-button"/>&nbsp;&nbsp;&nbsp;<a href="" target="_blank" class="forgot-password">忘记密码？</a></td>
                                </tr>
                                 <tr>
                                  <td height="50" colspan="3" align="center" style="font-size:12px; color:#404040;">我还没有JEECMS帐号？<a href="<%=path %>/member/register.jsp" target="_blank" style="color:#1647a6;">马上注册</a></td>
                  </tr>
              </table>
			 </div>
			</td>
          </tr>
          
    </table>
	
</form>	
</div>

<div class="footer">
    <div class="clearfix footer-inner">
        <p class="extralink">
            <a target="_blank" href="http://www.jeecms.com/news/743.htm">关于我们</a> - 
			<a target="_blank" href="http://www.jeecms.com/yjdt/826.htm">联系我们</a> - 
			<a target="_blank" href="http://www.jeecms.com">诚聘英才</a> -
			<a target="_blank" href="http://www.jeecms.com">友情链接</a> - 
			<a target="_blank" href="http://www.jeecms.com/download/index.htm">程序下载</a> - 
			<a target="_blank" href="http://www.jeecms.com">合作服务</a> - 
			<a target="_blank" href="http://www.jeecms.com/yjdt/825.htm">许可协议</a> - 
			<a onClick="this.style.behavior='url(#default#homepage)';this.setHomePage(location.href)" href="javascript:void(0);">设为首页</a> - 
			<a onClick="window.external.addFavorite('http://www.jeecms.com','jeecms-国内专业、开源、免费的JAVA (JSP)版网站管理系统')" href="javascript:void(0);">加入收藏</a> - 
			<a target="_blank" href="${base}/jeeadmin/jeecms/index.do">管理登录</a>
        </p>
        <p class="copyright">
Powered by <a target="_blank" href="http://www.jeecms.com">JEECMS</a> Copyright &copy; 2007-2010 www.jeecms.com, All Rights Reserved 
        </p>
        <p class="extrainfo">
          电话：0791-6538070、13576281815　传真：0791-6538070  [@process_time/]<br />
        </p>
    </div>
</div>
</div>

<%
	if(request.getAttribute("msg") != null){
		%>
		<script>
			alert("${msg}");
		</script>
		<%
	}
	
%>
</body>
</html>