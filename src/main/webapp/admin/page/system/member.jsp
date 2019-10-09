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
  <script type="text/javascript" src="<%=path %>/admin/js/member.js"></script>

</head>
<body>

<table id="table" class="easyui-datagrid"  
        data-options="fit:true,url:'<%=path %>/user!findMemberByPage.action',fitColumns:true,toolbar:'#toolbar',pagination:true,rownumbers:true,pageList:[5,6,8],pageSize:5">   
    <thead>   
        <tr> 
        	<th data-options="field:'ck',checkbox:true"></th>  
            <th data-options="field:'userId',width:100">编号</th>   
            <th data-options="field:'userName',width:100">用户名</th> 
            <th data-options="field:'userEmail',width:100">email</th>
            <th data-options="field:'registerTime',width:100">注册时间</th>  
             <th data-options="field:'isDisabled',width:100,formatter: function(value,row,index){
				if (row.isDisabled == 1){
					return '<font color=blue>正常</font>';
				} else {
					return '<font color=red>禁用</font>';
				}
			}">会员状态</th>
            <th data-options="field:'cmsGroup',width:100,formatter: function(value,row,index){
				if (row.cmsGroup){
					return row.cmsGroup.groupName;
				} else {
					return value;
				}
			}
            ">会员级别</th>   
        </tr>    
    </thead>   
</table>
<div id="toolbar">
<a href="javascript:deleteUser()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
<a href="javascript:Usable()" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true">解禁</a>
<a href="javascript:Disable()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true">禁用</a>
<a href="javascript:setGroupWin()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" >设置级别</a>
会员名:<input id="tjName" value=""/>
创建时间:<input id="sdate"  class="easyui-datebox" type="text" style="width: 120px" data-options="formatter:function(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	return y+'-'+m+'-'+d;
}"
></input>--<input id="edate" type="text"  class="easyui-datebox" style="width: 120px" data-options="formatter:function(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	return y+'-'+m+'-'+d;
}"></input>
<a id="" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchMember()">查询</a>
<a id="" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="clearCondition()">清空</a>|
<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true" onclick="userHelp()">帮助</a>
</div> 
<div id="windowform" class="easyui-dialog" title="设置会员组" data-options="modal:true,closed:true,iconCls:'icon-save',minimizable:false,maximizable:false,resizable:false,buttons:'#btn1'" style="padding:10px;width: 300px;height: 200px;">
		<div>
		<center>
		<br/>
	    	<table>
	    		<tr>
	    			<td>会员组</td>
	    			<td>
	    			<input id="mygroup" class="easyui-combobox" name="dept"  
    				data-options="valueField:'groupId',textField:'groupName',url:'<%=path %>/group!findAllGroup.action'" />  
	    			</td>
	    		</tr>
	    	</table>
	    	</center>
	    </div>
	    <div id="btn1">
	    	<a href="javascript:void(0)" data-options="iconCls:'icon-ok',plain:true" class="easyui-linkbutton" onclick="setGroup()">确定</a>
	    </div>
	</div>
</body>
</html>