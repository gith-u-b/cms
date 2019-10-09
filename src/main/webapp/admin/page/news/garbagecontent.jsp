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
  <script type="text/javascript" src="<%=path %>/admin/js/garbagecontent.js"></script>


</head>
<body>


<table id="newsTable" class="easyui-datagrid" data-options="url:'<%=path %>/content!findGarbageContentByPage.action',toolbar:'#toolbar',
	        pageList:[2,5,10,20],pageSize:10,
	        pagination:true,fitColumns:true,singleSelect:false,fit:true">
	   <thead>
        <tr> 
           <th data-options="width:100,checkbox:true"></th> 
            <th data-options="field:'contentId',width:100,sortable:true,align:'center'">ID</th>    
            <th data-options="field:'title',width:100,sortable:true">标题</th>
            <th data-options="field:'isDisplay',width:100,align:'center',formatter:function(value,row,index){
		            	if (row.status==1){
		            		return '<font color=blue>已审核</font>';
		            	}else{
		            		return '<font color=red>未审核</font>';
		            	}
		            }">新闻状态</th>  
            <th data-options="field:'cmsContentType.typeName',width:100,align:'center',formatter:function(value,row,index){
		            	return row.cmsContentType.typeName;
		            }">类别</th>
		    <th data-options="field:'cmsChannel',width:100,align:'center',formatter:format2">栏目</th>        
		          	<th data-options="field:'cmsUser.userName',width:100,align:'center',formatter:function(value,row,index){
		          		return row.cmsUser.userName;
		          	}">发布者</th>
		    <th data-options="field:'author',width:100,align:'center'">作者</th>
            <th data-options="field:'repleaseTime',width:100,align:'center',sortable:true">发布时间 </th>
        </tr>  
    </thead>  
</table> 
<div id="toolbar">
<div>
<a href="javascript:updateGarbage()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" >还原</a>|
<a href="javascript:deleteGarbage()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" >删除</a>|
<a href="javascript:reloadNews()" class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" >刷新</a>|
<a href="javascript:openSetNewsChannelWin()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" >设置栏目</a>|
审核状态:
<input type="radio" value="1" name="checkstatus" onclick="checkStatusRadio(this.value)"/>已审核
			<input type="radio"  value="0" name="checkstatus" onclick="checkStatusRadio(this.value)"/>未审核
			<input type="radio"  value="-1" checked="checked" name="checkstatus" onclick="checkStatusRadio(this.value)"/>全部|

标题:<input type="text" id="title" style="width:100px;"/>
作者:<input type="text" id="userName" style="width:100px;"/>|

<a href="javaScript:searchContent()" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">查询</a>|
<a href="javaScript:clearCondition()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true">清空</a>
</div>
</div>

<div id="newsChannelWin" class="easyui-dialog" title="设置栏目" 
   data-options="iconCls:'icon-edit',modal:true,closed:true,maximizable:true,resizable:false,buttons:'#setBtn'" style="width: 300px;height: 200px;">
		<center>
		<div >
		<br/>
		<br/>
		<br/>
	    请选择栏目:
	    <select  id="contentchannel" class="easyui-combotree" style="width: 155px"  name="content.cmsChannel.channelId" data-options="url:'<%=path %>/channel!findAllChannel.action',
									required:true,editable:false,onSelect:noCheckParent">
							</select> 
	    </div>
	    </center>
	    <div id="setBtn">
	    	<a href="javascript:setNewsChannel()" data-options="iconCls:'icon-ok',plain:true" class="easyui-linkbutton" >保存</a>
	    </div>
</div>

</body>
</html>