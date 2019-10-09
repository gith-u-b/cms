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
  <script type="text/javascript" src="<%=path %>/admin/js/channel.js"></script>

</head>
<body>


<div id="updateChannelWin" class="easyui-dialog" title="编辑栏目" data-options="collapsible:true,iconCls:'icon-edit',resizable:true,modal:true,closed:true,buttons:'#updateBtn'" style="padding:10px;width: 400px;">
		<div >
	    <form id="updateChannelForm" method="post">
	    <center>
	    <table>
	    			<tr>
	    				<td><input type="hidden" id="ucid" name="channel.channelId"><label >栏目名称:</label></td>
	    				<td><input class="easyui-validatebox" id="ucname" type="text" name="channel.channelName" data-options="required:true" /></td>
	    			</tr>
	    			<tr>
	    				<td><label >分页大小:</label></td>
	    				<td><input class="easyui-numberbox" id="ucpage"  type="text" name="channel.page" data-options="required:true,max:100,min:0" /></td>
	    			</tr>
	    			<tr>
	    				<td> <label>标题:</label></td>
	    				<td><input  class="easyui-validatebox" id="uctitle" type="text" name="channel.title" data-options="required:true" /></td>
	    			</tr>
	    			<tr>
	    				<td><label>关键字:</label></td>
	    				<td><input  class="easyui-validatebox" id="uckeywords"  type="text" name="channel.keywords" /></td>
	    			</tr>
	    			<tr>
	    				<td><label>描述:</label></td>
	    				<td><input  class="easyui-validatebox" id="ucdescription"  type="text" name="channel.description" /></td>
	    			</tr>
	    			<tr>
	    				<td><label>请选择模板:</label></td>
	    				<td>
	    						<input  class="easyui-combobox" id="uctemplate" name="channel.cmsTemplate.templateId"   
    								data-options="url:'<%=path %>/template!findTemplateByChannel.action',valueField:'templateId',textField:'templateName',required:true,editable:false" />
	    				</td>
	    			</tr>
	    	</table>
	    	</center>
	    </form>
	    </div>
	    <div id="updateBtn">
	    	<a href="javascript:submitUpdateChannelForm()" data-options="iconCls:'icon-ok',plain:true" class="easyui-linkbutton">更改</a>
	    	<a href="javascript:resetUpdateChannel()" data-options="iconCls:'icon-cancel',plain:true" class="easyui-linkbutton" >重置</a>
	    </div>
	</div>   



<div id="addChannelWin" class="easyui-dialog" title="添加栏目" data-options="collapsible:true,iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:'#addBtn'" style="padding:10px;width: 500px;">
		<div >
	    <form id="addChannelForm" method="post">
	    <center>
	    	<table>
	    			<tr>
	    				<td><label >栏目名称:</label></td>
	    				<td><input class="easyui-validatebox" type="text" id="acname" name="channel.channelName" data-options="required:true" /></td>
	    			</tr>
	    			<tr>
	    				<td><label >分页大小:</label></td>
	    				<td><input class="easyui-numberbox"  type="text" id="acpage" name="channel.page" data-options="required:true,min:0,max:100" /></td>
	    			</tr>
	    			<tr>
	    				<td><label>栏目路径:</label></td>
	    				<td><input class="easyui-validatebox"  type="text" id="acpath" name="channel.channelPath" data-options="required:true"/>（例如：/yunfeng 或者 \\云风）</td>
	    			</tr>
	    			<tr>
	    				<td> <label >标题:</label></td>
	    				<td><input class="easyui-validatebox" type="text" id="actitle" name="channel.title" data-options="required:true" /></td>
	    			</tr>
	    			<tr>
	    				<td><label>关键字:</label></td>
	    				<td><input class="easyui-validatebox"  type="text" id="ackeywords" name="channel.keywords" /></td>
	    			</tr>
	    			<tr>
	    				<td><label>描述:</label></td>
	    				<td><input class="easyui-validatebox"  type="text" id="acdescription" name="channel.description" /></td>
	    			</tr>
	    			<tr>
	    				<td><label>请选择父级栏目:</label></td>
	    				<td>
	    						<select  id="acparent" class="easyui-combotree" style="width: 155px"  name="channel.parentId" data-options="url:'<%=path %>/channel!findAllChannel.action',
									required:true,editable:false"><!-- 这个地方required其实应该为false ，因为可能要添加父栏目 ，由于json数据，则不能添加父栏目 （可以添加，但是读取时则会乱掉）-->
								</select>  
	    				</td>
	    			</tr>
	    			<tr>
	    				<td><label>请选择模板:</label></td>
	    				<td>
	    						<input id="c2" class="easyui-combobox" name="channel.cmsTemplate.templateId"   
    								data-options="required:true,editable:false,valueField:'templateId',textField:'templateName',url:'<%=path %>/template!findTemplateByChannel.action'" />
	    				</td>
	    			</tr>
	    	</table>
	    	</center>
	    </form>
	    </div>
	    <div id="addBtn">
	    	<a href="javascript:submitAddChannelForm()" data-options="iconCls:'icon-ok',plain:true" class="easyui-linkbutton" >添加</a>
	    	<a href="javascript:resetAddChannel()" data-options="iconCls:'icon-cancel',plain:true" class="easyui-linkbutton">重置</a>
	    </div>
	</div> 

<div id="toolbar">
<a href="javascript:deleteChannel()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
<a href="javascript:addChannel()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" >添加</a>
<a href="javascript:updateChannel()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" >编辑</a>
<a href="javascript:channelHelp()" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true" >帮助</a>
<a href="javascript:reloadChannel()" class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" >刷新</a>
</div>

	<table id="channelTable" class="easyui-treegrid"  
        data-options="fit:true,url:'<%=path %>/channel!findAllChannel.action',fitColumns:true,singleSelect:true,treeField:'text',idField:'id',rownumbers:true,animate:true,toolbar:'#toolbar',onLoadSuccess:openChannelTable">   
     <thead>   
        <tr>
            <th data-options="field:'id',width:100,align:'center'">编号</th>   
            <th data-options="field:'text',width:100,align:'center'">栏目名称</th> 
           	 <th data-options="field:'pid',width:100,align:'center',formatter:format">父栏目</th>
            <th data-options="field:'channelPath',width:100,formatter:function(value,row){
            return row.attributes.channelPath}">栏目路径</th>
            <th data-options="field:'page',width:100,align:'center',formatter:function(value,row){
            return row.attributes.page}">分页大小</th>
            <th data-options="field:'isDisplay',width:100,align:'center',formatter:format2">是否显示</th>
            <th data-options="field:'muban',width:100,align:'center',formatter:format3">模板</th>
            <th data-options="field:'title',width:100,align:'center',formatter:function(value,row){
            return row.attributes.title}">标题</th>
            <th data-options="field:'keywords',width:100,align:'center',formatter:function(value,row){
            return row.attributes.keywords}">关键字</th>
            <th data-options="field:'description',width:100,align:'center',formatter:function(value,row){
            return row.attributes.description}">描述</th>
           
           
        </tr> 
    </thead> 
    </table>
<script>

function format(value,row,index){
	var n=$("#channelTable").treegrid("getParent",row.id);
	if(n){
		return "<font color='red'>"+n.text+"</font>";
	}
}
//是否显示
function format2(value,row,index){
if(row.attributes.isDisplay==1){
	return "<font color='blue'>是</font>";
}else{
	return "<font color='red'>否</font>";
}
}

//模板
function format3(value,row,index){
if(row.attributes.template){
	return "<font color='blue'>"+row.attributes.template.templateName+"</font>";
}else{
	return "<font color='red'>无</font>";
}
}


</script>

</body>
</html>