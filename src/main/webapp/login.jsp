<?xml version="1.0" encoding="UTF-8" ?>
<%
	String path = request.getContextPath();
	application.setAttribute("path", path);
%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>login</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/admin/css/style.css" />
<script src="<%=path %>/admin/js/jquery-1.4.4.min.js"></script>
<script>

	
	$(function(){
	var _select=$('.select');
		_select.click(function(){
			$(this).find('ul').show();
		})

		$('.select li').click(function(){
			var eid=$(this).attr('eid');
			var eid_input='<input type="hidden" value="'+eid+'" name="eid" id="eid" \/>';
			var _eidhtml=$(this).html();
			$('.eid_value').html(_eidhtml);
			if($('#eid').attr('value')){
				$('#eid').attr('value',eid);	
			}
			
		})
		$('.select ul').hover(function(){
			
		},function(){
			$(this).hide();
		})
	})
</script>

<style>
	.field{
	position: relative;
	}
	#code{
	position: absolute;
	left: 165px;
	top: 2px;
	}
</style>
</head>
<body>


	<div class="login">
		<div class="login_form">
			<form action="<%=path %>/user!login.action" name="loginform" method="post">
			
			<div class="form_info">
				<div class="field">
					<label>用户名：</label>
					<input type="text" value="${user.userName}" name="user.userName" class="text" size="20">
				</div>
				<div class="field">
					<label>密　码：</label>
					<input type="password"  name="user.userPassword" class="text" size="20">
				</div>
				<div class="field">
					<label>验证码：</label>
					<input type="text"   name="validateCode" class="text" size="10"/>
                   <img id="code" onclick="this.src='<%=path%>/image.jsp?d='+new Date()*1" src="image.jsp"/>
				</div>
				<div class="field">
					<label></label>
					<button class="button" style="margin-left:50px;_margin-left:48px" ></button>
				</div>
			</div>
			</form>
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