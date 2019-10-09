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
  <script type="text/javascript" src="<%=path %>/admin/js/template.js"></script>


</head>
<body>

	 <div id="toolbar">
<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="doDelete()">删除</a>
<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="openWindowForAdd()">添加</a>
<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="openWindow()">编辑/重命名</a>
<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="openWindowForXJML()">新建目录</a>
<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true" onclick="openWindowForUpload()">上传模板</a>

<link rel="stylesheet" href="<%=path%>/kindeditor/themes/default/default.css" />
<script charset="utf-8" src="<%=path%>/kindeditor/kindeditor-min.js"></script>
<script charset="utf-8" src="<%=path%>/kindeditor/zh_CN.js"></script>

</div>  
	<table id="table" class="easyui-treegrid"  
        data-options="fit:true,url:'<%=path %>/template!findAllTemplate.action',fitColumns:true,treeField:'text',idField:'id',rownumbers:true,onLoadSuccess:zk,animate:true,toolbar:'#toolbar',onBeforeExpand:validateNull">   
    <thead>   
        <tr> 
            <th data-options="field:'id'">编号</th>
				<th data-options="field:'text',width:20">名称</th>
				<th data-options="field:'attributes',width:50,formatter:format1">路径</th>
				<th data-options="field:'createtime',formatter:format2">创建时间</th>
				<th data-options="field:'creator',formatter:format3">上传者</th>
            
        </tr>   
    </thead> 
    </table>

<div id="windowCMM" style="height: 200px;width: 300px;" class="easyui-dialog" title="重命名" data-options="modal:true,closed:true,iconCls:'icon-save',minimizable:false,maximizable:false,resizable:false,buttons:'#btn3'" >
		<div >
		<center>
		<br/>
		<br/>
	    	<table>
	    		<tr>
	    			<td>文件夹名:</td>
	    			<td><input  name="mlName" id="cmmName" type="text"></td>
	    		</tr>
	    	</table>
	    	</center>
	    </div>
	    <div id="btn3">
	    	<a href="javascript:void(0)" data-options="iconCls:'icon-ok',plain:true"  class="easyui-linkbutton" onclick="doReName()">提交</a>
	    </div>
	</div>
	
	<div id="windowBJ" class="easyui-dialog" title="编辑" data-options="modal:true,closed:true,iconCls:'icon-save',minimizable:false,maximizable:false,resizable:false,buttons:'#btn4'" style="padding:10px;height: 400px;width: 760px">
		<div >
			<form id="BjForm">
			<input type="hidden" id="bjId" name="id">
			文件名:<input type="text" name="mlName" id="bjName"/><br/>
			<textarea id="markItUp"  name="content" style="width:710px;height:360px;visibility:hidden;display: block;"></textarea>
			</form>
	    	
	    </div>
	    <div id="btn4">
	    	<a href="javascript:void(0)" data-options="iconCls:'icon-ok',plain:true"  class="easyui-linkbutton" onclick="doEdit()">提交</a>
	    </div>
	</div>
	


	<div id="windowXJML" style="width: 300px;height: 200px;" class="easyui-dialog" title="新建目录" data-options="modal:true,closed:true,iconCls:'icon-save',minimizable:false,maximizable:false,resizable:false,buttons:'#btn2'" >
		<div >
		<center>
	    	<table>
	    	<br/>
	    	<br/>
	    		<tr>
	    			<td>请选择父级目录:</td>
	    			<td><input id="c1" name="" data-options="required:true"> </td>
	    		</tr>
	    		<tr>
	    			<td>文件夹名称:</td>
	    			<td><input class="easyui-validatebox" type="text" id="mlName" data-options="required:true" /> </td>
	    		</tr>
	    	</table>
	    	</center>
	    </div>
	    <div id="btn2">
	    	<a href="javascript:void(0)" data-options="iconCls:'icon-ok',plain:true" class="easyui-linkbutton" onclick="doXjml()">创建</a>
	    </div>
	</div>
	
	<div id="windowTJ" class="easyui-dialog" style="height: 400px;width: 800px;" title="添加" data-options="modal:true,closed:true,iconCls:'icon-save',minimizable:false,maximizable:false,resizable:false,buttons:'#btn2'" style="padding:10px;height: 400px;width: 760px">
		<div >
			<form id="tjForm">
			<center>
			<table>
			<tr>
	    	<td>请选择模板类型:</td>
	    	<td><input id="cc2" class="easyui-combobox" name="type" data-options="valueField:'typeId',textField:'typeName',url:'<%=path %>/template-type!findAllTemplateType.action'" /></td>
	    	</tr>
			<tr>
			<td>文件名:</td>
			<td><input type="text"  id="tjName"/>(例如：xxx.ftl或者xxx.html)</td>
			</tr>
			<tr>
			<td colspan="2"><textarea id="markItUp"  name="content2" style="width:710px;height:360px;visibility:hidden;display: block;"></textarea></td>
			</tr>
			</table>
			</center>
			</form>
	    	
	    </div>
	    <div id="btn2">
	    	<a href="javascript:void(0)" data-options="iconCls:'icon-ok',plain:true" class="easyui-linkbutton" onclick="doAdd()">提交</a>
	    </div>
	</div>
	
	
	
	<div id="windowSCMB" class="easyui-dialog" style="width: 400px;height: 200px;" title="上传模板" data-options="buttons:'#btn1',modal:true,closed:true,iconCls:'icon-save',minimizable:false,maximizable:false,resizable:false" >
		<div >
	    <form id="scmbForm" method="post" enctype="multipart/form-data">
	    	<center>
	    	<br/>
	    	<br/>
	    	<table>
	    		<tr>
	    			<td>请选择模板类型:</td>
	    			<td><input id="cc1" class="easyui-combobox" name="template.cmsTemplateType.typeId" data-options="valueField:'typeId',textField:'typeName',url:'<%=path %>/template-type!findAllTemplateType.action'" /> </td>
	    		</tr>
	    		<tr>
	    			<td>请选择模板:</td>
	    			<td><input  name="mb" id="mb" type="file"></td>
	    		</tr>
	    	</table>
	    	</center>
	    </form>
	    </div>
	    <div id="btn1">
	    	<a href="javascript:void(0)" data-options="iconCls:'icon-ok',plain:true"  class="easyui-linkbutton" onclick="doUpload()">上传</a>
	    </div>
	</div>
	
<script type="text/javascript">
	
//在线编辑空件
var editor;
var editor2;
KindEditor.ready(function(K) {
	editor = K.create('textarea[name="content"]', {
		readonlyMode : false
	});
	editor2 = K.create('textarea[name="content2"]', {
		readonlyMode : false
	});

});

</script>
</body>
</html>