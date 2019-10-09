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
  <script type="text/javascript" src="<%=path %>/admin/js/power.js"></script>
  
</head>
<body>

<div id="toolbar">
<a href="javascript:deletePower()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
<a href="javascript:addPower()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
<a href="javascript:updatePower()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" >编辑</a>
<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true" onclick="powerHelp()">帮助</a>
</div>
	
	
	
	
	
	<div id="addPowerWin" class="easyui-dialog" title="添加功能" data-options="collapsible:true,iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:'#addBtn'" style="padding:10px;width: 400px;">
		<div>
		
	    <form id="addPowerForm" method="post">
	    	<center>
	    	<table>
	    		<tr>
	    			<td>功能名称:</td>
	    			<td><input class="easyui-validatebox" id="addpname" type="text" name="power.text" style="width:195px;" data-options="required:true" /></td>
	    		</tr>
	    		<tr>
	    			<td>链接地址:</td>
	    			<td><input class="easyui-validatebox" id="addpurl" type="text" name="power.url" style="width:195px;" data-options="" /></td>
	    		</tr>
	    		<tr>
	    			<td>功能状态:</td>
	    			<td>
		    			<select id="addpstate" class="easyui-combobox" name="power.state" style="width:200px;" data-options="editable:false">   
	    					<option value="closed">closed</option>   
	    					<option value="open">open</option>   
						</select>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>功能图标:</td>
	    			<td><input class="easyui-validatebox" id="addpicon" type="text" name="power.iconCls" style="width:195px;" data-options="" /></td>
	    		</tr>
	    		<tr>
	    			<td>上层节点:</td>
	    			<td>
	    				<select id="comboxtree" class="easyui-combotree" name="power._parentId" style="width:200px;"   
        				data-options="valueField:'id',textField:'text',editable:false"></select>
	    			</td>
	    		</tr>	    		    		
	    	</table>
	    	</center>
	    </form>
	    </div>
	    <div id="addBtn">
	    	<a href="javascript:submitAddPowerForm()" data-options="iconCls:'icon-ok',plain:true" class="easyui-linkbutton" >添加</a>
	    	<a href="javascript:void(0)" data-options="iconCls:'icon-cancel',plain:true" class="easyui-linkbutton" onclick="resetadd()">重置</a>
	    </div>
	</div>
	
	
	<div id="updatePowerWin" class="easyui-dialog" title="编辑功能" data-options="collapsible:true,iconCls:'icon-edit',resizable:true,modal:true,closed:true,buttons:'#updateBtn'" style="padding:10px;width: 400px;">
		<div>
	    <form id="updatePowerForm" method="post">
	    <center>
	    <table>
	    	<tr>
	    		<td><input type="hidden" id="upid" name="power.id"/><label >名称:</label></td>
	    		<td><input class="easyui-validatebox" id="upname" type="text" name="power.text" style="width:195px;" data-options="required:true" /></td>
	    	</tr>
	    	<tr>
	    		<td><label >链接地址:</label></td>
	    		<td><input class="easyui-validatebox" id="upurl" type="text" name="power.url" style="width:195px;" data-options="" /></td>
	    	</tr>
	    	<tr>
	    		<td><label>状态:</label></td>
	    		<td>
	    			<select id="upstate" class="easyui-combobox" name="power.state" style="width:200px;" data-options="editable:false">   
    					<option value="closed">closed</option>   
    					<option value="open">open</option>   
					</select>  
	    		</td>
	    	</tr>
	    	<tr>
	    		<td><label >图标:</label></td>
	    		<td><input class="easyui-validatebox" id="upicon" type="text" name="power.iconCls" style="width:195px;" data-options="" /></td>
	    	</tr>
	    	<tr>
	    		<td><label>上层节点:</label></td>
	    		<td><select id="comboxtree1" class="easyui-combotree" name="power._parentId" style="width:200px;"></select>
        		</td>
	    	</tr>
	    </table>
	    </center>
	    </form>
	    </div>
	    <div id="updateBtn">
	    	<a href="javascript:submitUpdatePowerForm()" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true" >修改</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="loadUpdateInfo()">重置</a>
	    </div>
	</div>
 
	
	 <table id="powerTable" class="easyui-treegrid"    
        data-options="toolbar:'#toolbar',url:'<%=path %>/power!findPowerByPage.action',idField:'id',treeField:'text', rownumbers: true,animate: true,collapsible: true,fitColumns: true,fit:true"><!-- 在这个treegrid 加onLoadSuccess 会报错 -->   
    <thead>   
        <tr>  
            <th data-options="field:'id',width:100">编号</th>   
            <th data-options="field:'text',width:100,align:'center'">名称</th> 
            <th data-options="field:'url',width:100,align:'center'">链接地址</th>
            <th data-options="field:'iconCls',width:100,align:'center',formatter:icons">icon</th>
            <th data-options="field:'_parentId',width:100,align:'center',formatter:parent">上级名称</th>
        </tr>   
    </thead>   
</table>  

<script >
function icons(value,row,index){
	if(value){
		var s=value.replace("icon-","");
		s=s.replace("-","_")+".png";
		return "<img src='<%=path%>/easyui/themes/icons/"+s+"'>";
	}
}
function parent(value,row,index){
	var p=$("#powerTable").treegrid("getParent",row.id);
	if(p){
		return "<font color='red'>"+p.text+"</font>";
	}else{
		return "";		
	}
}
</script>
</body>
</html>