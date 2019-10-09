<?xml version="1.0" encoding="UTF-8" ?>
<%
	String path = request.getContextPath();
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<link rel="stylesheet" href="<%=path %>/easyui/themes/default/easyui.css" type="text/css"></link>
  <link rel="stylesheet" href="<%=path %>/easyui/themes/icon.css" type="text/css"></link>
  <script type="text/javascript" src="<%=path %>/easyui/jquery.min.js"></script>
  <script type="text/javascript" src="<%=path %>/easyui/jquery.easyui.min.js"></script>
  <script type="text/javascript" src="<%=path %>/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
<center>
	<br/>
	<br/>
	<br/>
	<form id="pwdform" method="post">
	<table>
		<tr>
			<td>原密码：</td>
			<td>
			<input name="password" type="password" class="easyui-validatebox" data-options="required:true" />
			<input type="hidden" value="${ user.userPassword}" name="user.userPassword"/>
			<input type="hidden" value="${ user.userId}" name="user.userId"/>
			</td>
		</tr>
		<tr>
			<td>新密码：</td><td><input type="password" name="newpwd1"  class="easyui-validatebox" data-options="required:true" /></td>
		</tr>
		<tr>
			<td>确认新密码：</td><td><input type="password" name="newpwd2" class="easyui-validatebox" data-options="required:true" /></td>
		</tr>
		<tr>
			<td colspan="2" align="right">
			<a  href="javascript:submitUpdatePwd()" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确定</a> 
			</td>
		</tr>
	</table>
	</form>
</center>


<script type="text/javascript">
	function submitUpdatePwd(){
		$.messager.progress();	// 显示一个进度条 
		$('#pwdform').form('submit', {
			url: "<%=path %>/user!updatePassword.action",
			onSubmit: function(){
				var isValid = $(this).form('validate');
				if (!isValid){
					$.messager.progress('close');	// 当form不合法的时候隐藏工具条
				}
				return isValid;	// 返回false将停止form提交 
			},
			success: function(data){
				if(data == "yes"){
					$.messager.show({
						title:'提示',
						msg:'更改成功,下次登陆生效.',
						timeout:5000,
						showType:'slide'
					});
					document.getElementById("pwdform").reset();
				}else if(data == "no1"){
					$.messager.show({
						title:'提示',
						msg:'原密码不正确.',
						timeout:5000,
						showType:'slide'
					});
				}else if(data == "no2"){
					$.messager.show({
						title:'提示',
						msg:'两次密码输入不一致.',
						timeout:5000,
						showType:'slide'
					});
				}else{
					$.messager.show({
						title:'提示',
						msg:'更改失败.',
						timeout:5000,
						showType:'slide'
					});
				}
				$.messager.progress('close');	// 当成功提交之后隐藏进度条
			}
		});

	}
</script>

</body>
</html>