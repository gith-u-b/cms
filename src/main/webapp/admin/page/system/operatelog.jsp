<?xml version="1.0" encoding="UTF-8" ?>
<%
    String path=request.getContextPath();
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
 <script type="text/javascript" src="<%=path %>/admin/js/operatelog.js"></script>
</head>
<body>

<table class="easyui-datagrid" id="tabs" data-options="url:'<%=path %>/log!findOperateLog.action',fitColumns:true,fit:true,pagination:true,pageList:[15,20,30],pageSize:15,showFooter:true,
remoteSort:false,fit:true,toolbar:'#toolbar'">
<thead>  
        <tr>  
        	<th data-options="field:'ck',checkbox:true"></th> 
            <th data-options="field:'logId',width:100,sortable:true">日志编号</th>    
            <th data-options="field:'cmsUser.userName',width:100,sortable:true,formatter:function(value,row,index){
            	return row.cmsUser.userName;
            }">用户名称</th>  
            <th data-options="field:'logTime',width:100,sortable:true">日志时间</th>
            <th data-options="field:'logUrl',width:100,sortable:true">路径 </th>
            <th data-options="field:'logMethode',width:100,sortable:true">方法</th>
            <th data-options="field:'logMsg',width:100,sortable:true">日志消息 </th>
        </tr>  
    </thead>  
</table> 
<div id="toolbar">
用户名:<input id="userName" value=""/>
日志路径:<input type="text" id="logUrl" value="">
相关方法:<input type="text" id="logMethode" value="">
创建时间:<input id="startDate"  class="easyui-datebox" type="text" style="width: 120px" data-options="formatter:function(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	return y+'-'+m+'-'+d;
}"
></input>--<input  id="endDate" type="text"  class="easyui-datebox" style="width: 120px" data-options="formatter:function(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	return y+'-'+m+'-'+d;
}"></input>
<a id="search" href="javascript:searchLog()" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" >查询</a>
<a id="clear" href="javascript:clearCondition()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" >清空</a>|
<a id="delete" href="javascript:deleteLog()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" >删除</a>


</div>


</body>
</html>