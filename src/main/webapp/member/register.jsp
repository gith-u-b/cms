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
<title>注册新帐号 - ${site.name} - Powered by JEECMS</title>
        <meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
       <link href="<%=path %>/resource/news/cms/front.css" rel="stylesheet" type="text/css"/>
<script src="<%=path %>/resource/news/cms/jquery.js" type="text/javascript"></script>
<script src="<%=path %>/resource/news/cms/front.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="<%=path %>/resource/news/cms/www/red/css/regist.css" />
    
    <script type="text/javascript">
    	
    function validateReg(){
    	
    	var name = document.getElementById("username").value;
    	var email = document.getElementById("email").value;
    	var password = document.getElementById("password").value;
    	var rpassword = document.getElementById("rpassword").value;
    	
    	if(name.trim().length < 3){
    		alert("请输入格式正确用户名！");
    		return false;
    	}
    	if(email.trim().length < 6){
    		alert("请输入格式正确电子邮箱！");
    		return false;
    	}
    	if(password.trim().length < 5 ){
    		alert("请输入格式正确的密码！");
    		return false;
    	}
    	if(password.trim() != rpassword.trim()){
    		alert("两次密码输入不一致！");
    		return false;
    	}
    	return true;
    	
    }
    
    </script>
    <body>
        <div class="container">
        <div class="toolbar box" style="margin-bottom: 0px">
        <div class="toolbar-inner">
        <div class="fl " id="logincontent">
		<c:if test="${empty u}">
         	游客您好，您还没有登录哦！ <a href="<%=path %>/member/register.jsp">免费注册</a>|<a href="<%=path %>/member/login.jsp">登录</a>|<a href="#">找回密码</a></div>
     	</c:if>
        <c:if test="${!empty u}">
        	欢迎您: ${u.userName} &nbsp; ${u.group.groupName} &nbsp; <a href="<%=path %>/member/logout.jsp">退出登录</a>
        </c:if>
        <div class="fr"><a target="_blank" href="${base}/tag.jspx">Tags</a>|<a target="_blank" href="${base}/search.jspx">高级搜索</a>|<a target="_blank" href="${base}/rss.jspx">RSS订阅</a>|<a onClick="this.style.behavior='url(#default#homepage)';this.setHomePage('http://www.jeecms.com');" href="#">设为主页</a>| <a class="login_user" onclick="javascript:window.external.AddFavorite('index.html', '');" href="javascript:void(0)">加入收藏</a></div>
        </div>
        </div>
        <div class="regist-header box">
        <div class="rgheader box">
        <div class="brand fl">
        </div>
        </div>
        </div>
        <div class="main">
        <table class="rg-tbg" cellspacing="0" cellpadding="0" width="971" align="center" border="0">
            <tbody>
                <tr>
                    <td width="231" height="66">
                    <h2>注册新会员</h2>
                    </td>
                    <td align="right" width="740" style="font-size: 12px">您的位置：JEECMS官网&nbsp;&gt;&nbsp;<span style="color: #016dd0">注册会员</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="2">
                    <form id="jvForm" action="<%=path %>/user_register.action" onsubmit="return validateReg()" method="post" style="padding-top: 20px">
                        <table cellspacing="5" cellpadding="0" width="850" align="center" border="0">
                            <tbody>
                                <tr>
                                    <td align="right" width="187" height="30">用户名：</td>
                                    <td width="648"><input class="input2" id="username" value="${user.userName} "  name="user.userName" type="text" /></td>
                                </tr>
                                <tr>
                                    <td align="right" width="187" height="30">&nbsp;</td>
                                    <td width="648">用户名由3到14位的中文字符、英文字母、数字组成。</td>
                                </tr>
                                <tr>
                                    <td align="right" width="187" height="30">电子邮箱：</td>
                                    <td><input class="input2 required email" value="${user.userEmail} " id="email" maxlength="30"  name="user.userEmail" type="text" /></td>
                                </tr>
                                <tr>
                                    <td align="right" width="187" height="30">&nbsp;</td>
                                    <td width="648">请准确填入您的邮箱，在忘记密码，或者您使用邮件通知功能时，会发送邮件到该邮箱。</td>
                                </tr>
                                <tr>
                                    <td align="right" width="187" height="30">密 码：</td>
                                    <td><input class="input2 required" id="password" type="password" name="user.userPassword" /></td>
                                </tr>
                                <tr>
                                    <td align="right" width="187" height="30">&nbsp;</td>
                                    <td width="648">密码由5到14位的中文字符、英文字母、数字组成。</td>
                                </tr>
                                <tr>
                                    <td align="right" width="187" height="30">确认密码：</td>
                                    <td><input class="input2" id="rpassword" type="password" equalto="#repassword" /></td>
                                </tr>
                                
                                <tr>
                                    <td align="center" colspan="2" height="40"><input class="regist-submit" type="submit" name="register" value="" /></td>
                                </tr>
                            </tbody>
                        </table>
                    </form>
                    </td>
                </tr>
            </tbody>
            
        </table>
        </div>
        <div class="footer">
        <div class="clearfix footer-inner">
        <p class="extralink"><a target="_blank" href="http://www.jeecms.com/news/743.htm">关于我们</a> - <a target="_blank" href="http://www.jeecms.com/yjdt/826.htm">联系我们</a> - <a target="_blank" href="http://www.jeecms.com">诚聘英才</a> - <a target="_blank" href="http://www.jeecms.com">友情链接</a> - <a target="_blank" href="http://www.jeecms.com/download/index.htm">程序下载</a> - <a target="_blank" href="http://www.jeecms.com">合作服务</a> - <a target="_blank" href="http://www.jeecms.com/yjdt/825.htm">许可协议</a> - <a onClick="this.style.behavior='url(#default#homepage)';this.setHomePage(location.href)" href="javascript:void(0);">设为首页</a> - <a onClick="window.external.addFavorite('http://www.jeecms.com','jeecms-国内专业、开源、免费的JAVA (JSP)版网站管理系统')" href="javascript:void(0);">加入收藏</a> - <a target="_blank" href="${base}/jeeadmin/jeecms/index.do">管理登录</a></p>
        <p class="copyright">Powered by <a target="_blank" href="http://www.jeecms.com">JEECMS</a> Copyright &copy; 2007-2010 www.jeecms.com, All Rights Reserved</p>
        <p class="extrainfo">电话：0791-6538070、13576281815　传真：0791-6538070 [@process_time/]<br />
        &nbsp;</p>
        </div>
        </div>
        </div>
        
        
        <%
       
        if(request.getAttribute("msg") != null){
        	
        	%>
        	<script type="text/javascript">
        		alert("${msg}");
        	</script>
        	<%
        	
        	if(request.getAttribute("msg").equals("注册成功")){
        		%>
            	<script type="text/javascript">
            		window.location = "<%=path %>/member/login.jsp";
            	</script>
            	<%	
        		
        	}
        
        }
        %>
        
    </body>
</html>