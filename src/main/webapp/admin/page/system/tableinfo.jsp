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

<title>Insert title here</title>
</head>
<body>

<div id="toolbar">
	<a href="databackup.jsp" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true">返回</a>
</div>
<table id="table" class="easyui-datagrid"  
        data-options="fit:true,url:'<%=path %>/data!findTableColumnByTableName.action?table.tableName=${param.tableName}',fitColumns:true,toolbar:'#toolbar'">   
    <thead>   
        <tr>  
            <th data-options="field:'columName',width:100">字段名</th>
            <th data-options="field:'columType',width:100">字段类型</th>
            <th data-options="field:'columAttr',width:100">字段属性</th>
            <th data-options="field:'columDefaultValue',width:100">默认值</th>
            <th data-options="field:'characterSetName',width:100">字段字符类型</th>
        </tr>
    </thead>   
</table>


</body>
</html>