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
  <script type="text/javascript" src="<%=path %>/admin/js/garbagechannel.js"></script>

</head>
<body>

<table id="channelTable" class="easyui-datagrid" data-options="url:'<%=path %>/channel!findGarbageChannel.action',toolbar:'#toolbar',
	       fitColumns:true,singleSelect:false,fit:true">
	   <thead>
        <tr> 
           <th data-options="width:100,checkbox:true"></th> 
           
           <th data-options="field:'channelId',width:100,align:'center'">编号</th>   
            <th data-options="field:'channelName',width:100,align:'center'">栏目名称</th> 
          
            <th data-options="field:'channelPath',width:100">栏目路径</th>
            <th data-options="field:'page',width:100,align:'center'">分页大小</th>
            <th data-options="field:'title',width:100,align:'center'">标题</th>
            <th data-options="field:'keywords',width:100,align:'center'">关键字</th>
            <th data-options="field:'description',width:100,align:'center'">描述</th>
           
        </tr>  
    </thead>  
</table> 


<div id="toolbar">
<div>
<a href="javascript:updateGarbage()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" >还原</a>|
<a href="javascript:deleteGarbage()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" >删除</a>|
<a href="javascript:reloadChannel()" class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" >刷新</a>|

名称:<input type="text" id="channelname" style="width:100px;"/>
标题:<input type="text" id="channeltitle" style="width:100px;"/>|

<a href="javaScript:searchChannel()" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">查询</a>|
<a href="javaScript:clearCondition()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true">清空</a>
</div>
</div>



</body>
</html>