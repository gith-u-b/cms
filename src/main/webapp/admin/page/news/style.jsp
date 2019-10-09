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
  <script type="text/javascript" src="<%=path %>/admin/js/style.js"></script>

</head>
<body>

	<table id="table" class="easyui-treegrid"  
        data-options="fit:true,url:'<%=path %>/resource!findAllFile.action',fitColumns:true,treeField:'text',idField:'text',rownumbers:true,animate:true,toolbar:'#toolbar',onLoadSuccess:openStyleTable">   
    <thead>   
        <tr> 
            <th data-options="field:'text',width:180">文件名</th>
			<th data-options="field:'length',width:60">大小</th>
			<th data-options="field:'path',width:180">路径</th>
			<th data-options="field:'updatetime',width:80">修改时间</th>
        </tr>   
    </thead> 
    </table>
    
    <div id="toolbar">
<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="deleteResource()">删除</a>
<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="openWindowForUpdate()">编辑</a>
<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true" onclick="resourceHelp()">帮助</a>
<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true" onclick="openWindowForUpload()">上传资源</a>
</div>  

	<div id="windowformforadd" class="easyui-dialog" style="width:400px;height: 200px;" title="上传资源" data-options="modal:true,closed:true,iconCls:'icon-save',minimizable:false,maximizable:false,resizable:false,buttons:'#btn2'" >
		<div >
	    <form id="form" method="post" enctype="multipart/form-data">
	    <center>
	    <br/>
	    <br/>
	    	<table>
	    		<tr>
	    			<td>请选择文件:</td>
	    			<td><input type="file" name="up" id="up" >(可压缩zip) </td>
	    		</tr>
	    	</table>
	    	</center>
	    </form>
	    </div>
	    <div id="btn2">
	    	<a href="javascript:void(0)" data-options="iconCls:'icon-ok',plain:true" class="easyui-linkbutton" onclick="doUpload()">上传</a>
	    </div>
	</div>
	
	
	<div id="windowformforupdate" style="width: 300px;height: 200px;" class="easyui-dialog" title="编辑" data-options="modal:true,closed:true,iconCls:'icon-save',minimizable:false,maximizable:false,resizable:false,buttons:'#btn1'" >
		<div >
	    <form id="updateForm" method="post">
	    <center>
	    <br/>
	    <br/>
	    	<table>
	    		<tr>
	    			<td width="70">文件名:</td>
	    			<td><input  name="resourceName" id="resourceName" ><input name="resourcePath" id="hiddenRes" type="hidden"></td>
	    		</tr>
	    	</table>
	    	</center>
	    </form>
	    </div>
	    <div id="btn1">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="doUpdate()">修改</a>
	    </div>
	</div>
	



</body>
</html>