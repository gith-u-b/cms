<?xml version="1.0" encoding="UTF-8" ?>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
 <link rel="stylesheet" href="<%=path %>/easyui/themes/default/easyui.css" type="text/css"></link>
  <link rel="stylesheet" href="<%=path %>/easyui/themes/icon.css" type="text/css"></link>
  <script type="text/javascript" src="<%=path %>/easyui/jquery.min.js"></script>
  <script type="text/javascript" src="<%=path %>/easyui/jquery.easyui.min.js"></script>
  <script type="text/javascript" src="<%=path %>/easyui/locale/easyui-lang-zh_CN.js"></script>
  <script type="text/javascript" src="<%=path %>/admin/js/comment.js"></script>
</head>
<body>

 <table id="table" class="easyui-datagrid"  
        data-options="toolbar:'#toolbar',fit:true,url:'<%=path %>/comment!findCommentByPage.action',fitColumns:true,pagination:true,rownumbers:true,pageList:[5,6,8],pageSize:5">   
    <thead>   
        <tr> 
        	<th data-options="field:'ck',checkbox:true"></th>  
            <th data-options="field:'comId'">编号</th>   
            <th data-options="field:'cmsContent',width:100,formatter:format1">评论标题</th>
            <th data-options="field:'content',width:100,formatter:format4">评论内容</th> 
            <th data-options="field:'replyContent',width:100">回复内容</th> 
            <th data-options="field:'isChecked',width:100,formatter:format2">审核状态</th>
            <th data-options="field:'cmsUser',formatter:format3">用户名称</th>
            <th data-options="field:'createTime',width:100">评论时间</th>  
        </tr>   
    </thead>   
</table> 

<div id="toolbar">
<input type="hidden"  id="temp"/>
<a href="javascript:replyCommentWin()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">回复/查看详情</a>
<a href="javascript:deleteComment()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" >删除</a>
<a href="javascript:recommend()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" >推荐</a>
<a href="javascript:checkComment()" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true" >审核通过</a>
</div>


<!-- 修改窗体 -->
<div id="windowReply" class="easyui-dialog" title="回复评论" data-options="modal:true,closed:true,iconCls:'icon-save',minimizable:false,maximizable:false,resizable:false,buttons:'#btn'" style="height: 400px;width: 450px">
		<form  method="post" id="updateForm">
			<table>
				<tr>
					<td>新闻标题:<label id="title" style="font-weight: bold;color: red"></label></td>
					<td>
						<input type="hidden" value="${user.userName} " id="userName"/>
						<input id="cid" name="comment.comId" type="hidden"/>
					</td>
				</tr>
				<tr>
					<td>会员:<label id="user" style="font-weight: bold;color: blue"></label></td>
					<td>评论时间:<label id="timeLabel" style="font-weight: bold;"></label></td>
				</tr>
				<tr>
					<td>推荐:<input type="radio" value="1" name="comment.isRecommend">是<input type="radio" value="0" name="comment.isRecommend">否</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>评论内容:</td>
					<td></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<textarea  rows="5" readonly="readonly" cols="45" name="comment.content" id="content"></textarea>
					</td>
				</tr>
				<tr>
					<td>回复内容:</td>
					<td></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<textarea  rows="5" cols="45" name="comment.replyContent" id="reply"></textarea>
					</td>
				</tr>
			</table>
		</form>
		<div id="btn" >
	    		<a href="javascript:replyComment()"  id="btnReply" data-options="iconCls:'icon-ok',plain:true" class="easyui-linkbutton" >回复</a>
	    </div>
</div>


<script>
	
function format1(value,row,index){
	if(row.cmsContent){
		if(row.cmsContent.isCreated==1){
			var path='<%=path %>'+row.cmsContent.cmsChannel.channelPath+'/news'+row.cmsContent.contentId+'.html';
			path=path+"";
			return "<a style='text-decoration: none;' target='_blank' href='"+path+"'>"+row.cmsContent.title+"</a>";
		}else{
			return "<a style='text-decoration: none;' target='_blank' href='<%=path %>/html!newsHtml.action?content.contentId="+row.cmsContent.contentId+" '>"+row.cmsContent.title+"</a>";
		}
		
	}
}

</script>

</body>
</html>