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
  <script type="text/javascript" src="<%=path %>/admin/js/user.js"></script>

</head>
<body>


<div id="addUserWin" class="easyui-dialog" title="添加用户" style="width:300px;height:210px;"  
        	data-options="collapsible:true,iconCls:'icon-save',resizable:true,modal:true,closed:true,resizable:false,buttons:'#buttons1'">  
		<div >
	    <form id="addUserform" method="post">
	    	<center>
	    	<br/>
	    	<table style="text-align: right;">
	    		<tr>
	    			<td width="50">用户名:</td>
	    			<td><input class="easyui-validatebox" id="adduname" type="text" name="user.userName" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>邮箱:</td>
	    			<td><input class="easyui-validatebox" id="adduemail" type="text" name="user.userEmail" data-options="required:true,validType:'email'"></input></td>
	    		</tr>
	    		<tr>
	    			<td>角色:</td>
	    			<td>
	    				<input  class="easyui-combobox" id="addurole" name="user.cmsRole.roleId" data-options="required:true,valueField:'roleId',textField:'roleName',url:'<%=path %>/role!findAllRole.action', editable:false " />  
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>会员组:</td>
	    			<td>
	    				<input class="easyui-combobox" id="addugroup" name="user.cmsGroup.groupId" data-options="required:true,valueField:'groupId',textField:'groupName',url:'<%=path %>/group!findAllGroup.action', editable:false " />  
	    			</td>
	    		</tr>
	    	</table>
	    	</center>
	    </form>
	    </div>
	    
	    <div id="buttons1">
	    	<a href="javascript:submitAddUserForm()" class="easyui-linkbutton"  data-options="iconCls:'icon-ok',plain:true">添加</a>
	    	<a href="javascript:clearAddForm()" class="easyui-linkbutton"  data-options="iconCls:'icon-remove',plain:true">重置</a>
	    </div>
	    
	</div>
	
	
	<div id="updateUserWin" class="easyui-dialog" title="编辑用户" style="width:300px;height:210px;"  
        	data-options="collapsible:true,iconCls:'icon-save',resizable:true,modal:true,closed:true,resizable:false,buttons:'#buttons2'">  
		<div >
	    <form id="updateUserform" method="post">
	    	<center>
	    	<br/>
	    	<table style="text-align: right;">
	    		<tr>
	    			<td width="50">用户名:</td>
	    			<td>
	    				<input class="easyui-validatebox" id="updateuname" type="text" name="user.userName" data-options="required:true"></input>
						<input type="hidden" name="user.userId" id="updateuid"></input>	    				
	    			</td>
	    			
	    		</tr>
	    		<tr>
	    			<td>邮箱:</td>
	    			<td><input class="easyui-validatebox" id="updateuemail" type="text" name="user.userEmail" data-options="required:true,validType:'email'"></input></td>
	    		</tr>
	    		<tr>
	    			<td>角色:</td>
	    			<td>
	    				<input id="updateurole" class="easyui-combobox" name="user.cmsRole.roleId" data-options="valueField:'roleId',textField:'roleName',url:'<%=path %>/role!findAllRole.action', editable:false " />  
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>会员组:</td>
	    			<td>
	    				<input id="updateugroup" class="easyui-combobox" name="user.cmsGroup.groupId" data-options="valueField:'groupId',textField:'groupName',url:'<%=path %>/group!findAllGroup.action', editable:false " />  
	    			</td>
	    		</tr>
	    	</table>
	    	</center>
	    </form>
	    </div>
	    
	    <div id="buttons2">
	    	<a href="javascript:submitUpdateForm()" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true">更改</a>
	    	<a href="javascript:clearUpdateForm()" class="easyui-linkbutton"  data-options="iconCls:'icon-remove',plain:true">重置</a>
	    </div>
	    
	</div>






<div id="toolbar">
<!--暂不需要  <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="deleteUser()">删除</a>-->
<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="showAddUserWin()">添加</a>|
<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="showUpdateUserWin()">编辑</a>|
<a href="javascript:disableUser()" class="easyui-linkbutton" data-options="iconCls:'icon-no',plain:true" >禁用</a>|
<a href="javascript:usableUser()" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true">启用</a>|
<a href="javascript:userHelp()" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true" >帮助</a>|
用户名:<input id="conditionUName"  value="${user.userName }"/>|
创建时间:<input id="conditionBegin"  class="easyui-datebox" value="${begin }"  type="text" style="width: 120px" data-options="formatter:function(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	return y+'-'+m+'-'+d;
}"
></input>至<input id="conditionEnd" type="text" value="${end }"   class="easyui-datebox" style="width: 120px" data-options="formatter:function(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	return y+'-'+m+'-'+d;
}"></input>
<a id="btnSearch" href="javascript:findByCondition()" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" >查询</a>|
<a  href="javascript:clearCondition()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" >清空</a>
</div>

<table id="userTable" class="easyui-datagrid"  
        data-options="toolbar:'#toolbar',fit:true,url:'<%=path %>/user!findByPage.action',fitColumns:true,pagination:true,rownumbers:true,pageList:[5,10,15,20],pageSize:10">   
    <thead>   
        <tr> 
        	<th data-options="field:'ck',checkbox:true"></th>  
            <th data-options="field:'userId'">编号</th>   
            
           	<th data-options="field:'userName',width:100,align:'center',formatter:userName">用户名</th> 
            <th data-options="field:'userEmail',width:100,align:'center'">email</th>
            <th data-options="field:'registerTime',width:100,align:'center'">注册时间</th>
            <th data-options="field:'isDisabled',width:100,align:'center',formatter:isDisabled
            
            ">状态</th>
            
              <th data-options="field:'cmsRole',width:100,align:'center',formatter:function(value,row,index){
					return row.cmsRole.roleName;
				}
            ">角色</th>   
            
            
            
            
        </tr>   
    </thead>   
</table>


<script>



function isDisabled(value,row,index){
	if (row.isDisabled==1){
		return "<font color='blue'>启用</font>";
	} else {
		return "<font color='red'>禁用</font>";
	}
}

function userName(value,row,index){
	
   		if(row.userName == "${sessionScope.user.userName }"){
   			return  "<font color='blue'>"+row.userName+"(当前用户)</font>";
   		}else{
   			return row.userName;
   		}
	
}
</script>
</body>
</html>