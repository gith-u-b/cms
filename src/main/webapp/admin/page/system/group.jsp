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
  <script type="text/javascript" src="<%=path %>/admin/js/group.js"></script>

</head>
<body>


	<table id="table" class="easyui-datagrid"  
        data-options="fit:true,url:'<%=path %>/group!findAllGroup.action',toolbar:'#toolbar',fitColumns:true,singleSelect:true,rownumbers:true">   
    <thead>   
        <tr> 
            <th data-options="field:'groupId',width:100">编号</th>   
            <th data-options="field:'groupName',width:100">会员名称</th> 
        </tr>   
    </thead>   
</table> 
<div id="toolbar">
<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="deleteGroup()">删除</a>
<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addGroupWin()">添加</a>
<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="updateGroupWin()">编辑</a>
<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true" onclick="groupHelp()">帮助</a>
</div>
<div id="windowadd" class="easyui-dialog" title="添加会员级别" data-options="modal:true,closed:true,iconCls:'icon-save',minimizable:false,maximizable:false,resizable:false,buttons:'#btn1'" style="padding:10px;width: 300px;height: 150px;">
		<div >
	    <form id="form1" method="post">
	    <center>
	    <br/>
	    	<table>
	    		<tr>
	    			<td>会员组名称:</td>
	    			<td><input class="easyui-validatebox" type="text" name="group.groupName" data-options="required:true"></input></td>
	    		</tr>
	    	</table>
	    </center>
	    </form>
	    </div>
	    <div id="btn1">
	    	<a href="javascript:void(0)" data-options="iconCls:'icon-ok',plain:true" class="easyui-linkbutton" onclick="submitAdd()">添加</a>
	    </div>
	</div>
	<div id="windowupdate" class="easyui-dialog" title="编辑会员级别" data-options="modal:true,closed:true,iconCls:'icon-save',minimizable:false,maximizable:false,resizable:false,buttons:'#btn2'" style="padding:10px;width: 300px;height: 150px;">
		<div>
	    <form id="form2" method="post">
	    	<center>
	    	<br/>
	    	<table>
	    		<tr>
	    			<td width="50">会员名称</td>
	    			<td><input type="hidden" id="groupId" name="group.groupId">
	    			<input id="groupName" class="easyui-validatebox" type="text" name="group.groupName" data-options="required:true"></input></td>
	    		</tr>
	    	</table>
	    	</center>
	    </form>
	    </div>
	    <div id="btn2">
	    	<a href="javascript:void(0)" data-options="iconCls:'icon-ok',plain:true" class="easyui-linkbutton" onclick="submitUpdate()">修改</a>
	    </div>
	</div>

</body>
</html>