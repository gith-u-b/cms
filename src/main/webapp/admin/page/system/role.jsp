<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String path = request.getContextPath();
    %>
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
  <script type="text/javascript" src="<%=path %>/admin/js/role.js"></script>

</head>
<body>


<div id="grantRoleWin" class="easyui-dialog" title="授权" style="height:300px;width: auto;"  
        	data-options="collapsible:true,iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:'#grantBtn'">  
    			<center>	
    			<br>
    			<br>
    			<br>
    		<ul class="easyui-tree" id="powertree"  data-options="checkbox:true,cascadeCheck:true,animate:true,onlyLeafCheck:false" ></ul>
			</form>  
    		 </center>
    		   <div id="grantBtn">
    				<a id="btn" href="javascript:submitGrantForm()" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true">提交</a>  
    				<a id="btn" href="javascript:resetPower()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true">重置</a>  
    			</div>
</div>  


<div id="addRoleWin" style="width: 300px; height: 150px;" class="easyui-dialog" title="添加角色" data-options="collapsible:true,iconCls:'icon-save',resizable:true,modal:true,closed:true,resizable:false,buttons:'#buttons1'">
															
		<div >
	    <form id="addRoleForm" method="post">
	    <center>
	    <br/>
	    
	    	<table>
	    		<tr>
	    			<td width="50">角色名称</td>
	    			<td><input class="easyui-validatebox" type="text" name="role.roleName" data-options="required:true"></input></td>
	    		</tr>
	    	</table>
	    	</center>
	    </form>
	    </div>
	    <div id="buttons1">
	    	<a href="javascript:submitAddRoleForm()" data-options="iconCls:'icon-ok',plain:true" class="easyui-linkbutton" >添加</a>
	    </div>
</div>

<div id="updateRoleWin" style="width: 300px; height: 150px;" class="easyui-dialog" title="编辑角色" data-options="collapsible:true,iconCls:'icon-save',resizable:true,modal:true,closed:true,resizable:false,buttons:'#buttons'">
															
		<div >
	    <form id="updateRoleForm" method="post">
	    <center>
	    <br/>
	    	<table>
	    		<tr>
	    			<td width="50">角色名称</td>
	    			<td>
	    			<input type="hidden" id="updaterid" name="role.roleId"/>
	    			<input class="easyui-validatebox" id="updatername" type="text" name="role.roleName" data-options="required:true"></input></td>
	    		</tr>
	    	</table>
	    	</center>
	    </form>
	    </div>
	    <div id="buttons">
	    	<a href="javascript:submitUpdateRoleForm()" data-options="iconCls:'icon-ok',plain:true" class="easyui-linkbutton" >更改</a>
	    </div>
</div>



<div id="toolbar">
<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="showAddRoleWin()">添加</a>|
<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="showUpdateRoleWin()">编辑</a>|
<a href="javascript:deleteRole()" class="easyui-linkbutton" data-options="iconCls:'icon-no',plain:true" >删除</a>|
<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="grantRole()">赋权</a>|
<a href="javascript:roleHelp()" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true" >帮助</a>
</div>



<table id="roleTable" class="easyui-datagrid"  
        data-options="toolbar:'#toolbar',fit:true,url:'<%=path %>/role!findAllRole.action',fitColumns:true,rownumbers:true">   
    <thead>   
        <tr> 
        	<th data-options="field:'ck',checkbox:true"></th>  
            <th data-options="field:'roleId',width:100,align:'center'">编号</th>   
            <th data-options="field:'roleName',width:100,align:'center'">名称</th> 
        </tr>   
    </thead>   
</table> 


</body>
</html>