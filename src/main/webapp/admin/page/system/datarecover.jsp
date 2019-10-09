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
  <script type="text/javascript" src="<%=path %>/admin/js/datarecover.js"></script>

</head>
<body>
<div id="toolbar">
<form id="uploadfile" method="post" enctype="multipart/form-data">
	<input type="file" title="本地文件" style="width: 150px;overflow: hidden;"  id="datafile" name="sqlfile" />
	<a href="javaScript:doRecover()" title="恢复本地文件" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true">恢复</a>&nbsp;|&nbsp;
	<input id="datafile1" readonly="readonly" title="服务器中的文件" name="sqlfile1" class="easyui-validatebox" />
	<a href="javaScript:showBackupWindow()" class="easyui-linkbutton" title="服务器中的文件"  data-options="iconCls:'icon-search',plain:true">选择文件</a>
	<a href="javaScript:doRecover1()" class="easyui-linkbutton" title="恢复服务器中的文件" data-options="iconCls:'icon-undo',plain:true">恢复</a>
	
</form>
</div>

<table id="table" class="easyui-datagrid"  
        data-options="fit:true,url:'<%=path %>/data!findAllDatabase.action',fitColumns:true,pagination:true,rownumbers:true,toolbar:'#toolbar'">   
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>  
            <th data-options="field:'dataBaseName',width:100">数据库名</th>
        </tr>   
    </thead>   
</table>

<div id="showbackupwindow" class="easyui-dialog" title="数据恢复" data-options="iconCls:'icon-save',closed:true,
	modal:true,draggable:true,resizable:false" style="width: 800px;height:368px;">
   <div id="toolbar1">
	<a href="javaScript:checkBackUpFile()" class="easyui-linkbutton"   data-options="iconCls:'icon-search',plain:true">确认</a>
	<a href="javaScript:cancelBackUpCheck()" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true">取消</a>
	</div>
   <table id="filetable" class="easyui-treegrid"  
        data-options="toolbar:'#toolbar1',fit:true,url:'<%=path %>/data!findBackUpFile.action',fitColumns:true,treeField:'text',idField:'text',rownumbers:true,animate:true">
    <thead>
        <tr>
            <th data-options="field:'text',width:150">文件名称</th>
			<th data-options="field:'length',width:80">文件大小(kb)</th>
			<th data-options="field:'path',width:615">文件路径</th>
			<th data-options="field:'updatetime',width:90">修改时间</th>
        </tr>   
    </thead>   
</table>
</div>



</body>
</html>