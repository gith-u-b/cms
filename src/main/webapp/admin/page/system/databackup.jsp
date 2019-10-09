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
  <script type="text/javascript" src="<%=path %>/admin/js/databackup.js"></script>
  

</head>
<body>

<div id="toolbar">

	<a href="javaScript:queryColum()" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">查看详情</a>
	<a href="javaScript:backup()" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true">备份</a>
	<a href="javaScript:allBackup()" class="easyui-linkbutton" data-options="iconCls:'icon-back',plain:true">一键备份</a>
</div>
<table id="table" class="easyui-datagrid"  
        data-options="fit:true,url:'${path}/data!findTable.action',fitColumns:true,rownumbers:true,toolbar:'#toolbar'">   
    <thead>   
        <tr> 
        	<th data-options="field:'ck',checkbox:true"></th>  
            <th data-options="field:'tableName',width:100">数据库表名</th>
        </tr>   
    </thead>   
</table>


</body>
</html>