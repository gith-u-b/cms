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
<link rel="stylesheet" href="<%=path %>/easyui/themes/default/easyui.css" type="text/css"></link>
  <link rel="stylesheet" href="<%=path %>/easyui/themes/icon.css" type="text/css"></link>
  <script type="text/javascript" src="<%=path %>/easyui/jquery.min.js"></script>
  <script type="text/javascript" src="<%=path %>/easyui/jquery.easyui.min.js"></script>
  <script type="text/javascript" src="<%=path %>/easyui/locale/easyui-lang-zh_CN.js"></script>
 <script type="text/javascript" src="<%=path %>/admin/js/loginfailure.js"></script>

<title>Insert title here</title>
</head>
<body>

 <table id="tabs" class="easyui-datagrid"  
        data-options="fit:true,url:'<%=path %>/login-log!findLoginFailLog.action',fitColumns:true,pagination:true,rownumbers:true,pageList:[10,15,20],pageSize:10,toolbar:'#toolbar'">   
    <thead>   
        <tr> 
        	<th data-options="field:'ck',checkbox:true"></th>  
            <th data-options="field:'id'">编号</th>   
            <th data-options="field:'userIdaa',width:100,formatter:format,align:'center'">信息</th>
             <th data-options="field:'userName',width:100,align:'center'">用户名</th>
              <th data-options="field:'password',width:100,align:'center'">密码</th>
            <th data-options="field:'ip',width:100,align:'center'">登陆ip</th> 
            <th data-options="field:'dateTime',width:100,align:'center'">登陆时间</th>
        </tr>   
    </thead>   
</table> 

<div id="toolbar">
登陆ip:<input class="easyui-validatebox" id="ip"/>
登陆时间:<input id="sdate" type="text" class="easyui-datebox" style="width: 120px"></input>--<input id="edate" type="text" class="easyui-datebox" style="width: 120px" ></input>

<a id="btnSearch" href="javascript:findLoginLog()" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" >查询</a>
<a id="" href="javascript:cleanCondition()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" >清空</a>|
<a href="javascript:deleteLoginLog()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" >删除</a>
</div>



</body>
</html>