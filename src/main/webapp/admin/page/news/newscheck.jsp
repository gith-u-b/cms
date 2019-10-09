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
  <script type="text/javascript" src="<%=path %>/admin/js/newscheck.js"></script>

<link rel="stylesheet" href="<%=path%>/kindeditor/themes/default/default.css" />
  <script charset="utf-8" src="<%=path%>/kindeditor/kindeditor-min.js"></script>
  <script charset="utf-8" src="<%=path%>/kindeditor/zh_CN.js"></script>

</head>
<body>

<div id="toolbar" >
		<div>
			<a href="javaScript:serachNewsInfo()" class="easyui-linkbutton"  data-options="iconCls:'icon-search',plain:true">查看新闻详情</a>
			<a href="javaScript:checkNewsPass()" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true">审核通过</a>
			<a href="javaScript:checkNewsNo()" class="easyui-linkbutton" data-options="iconCls:'icon-no',plain:true">撤消审核</a>
			<input type="radio" value="1" name="checkstatus" onclick="checkStatusRadio(this.value)"/>已审核
			<input type="radio"  value="0" name="checkstatus" onclick="checkStatusRadio(this.value)"/>未审核
			<input type="radio"  value="-1" checked="checked" name="checkstatus" onclick="checkStatusRadio(this.value)"/>全部
			
			按栏目查看：<select  id="fNewsChannelId" class="easyui-combotree" style="width: 155px"  name="channel.parentId" data-options="url:'<%=path %>/channel!findAllChannel.action',
									editable:false,onSelect:selectTree">
			</select> 
			
		</div>
		<div>
			标题:<input type="text" id="fntitle" style="width:100px;"/>
			作者:<input type="text" id="fnauthor" style="width:100px;"/>
			发布者:<input type="text" id="fnusername" style="width:100px;"/>
			发布时间:<input id="fnsdate"  class="easyui-datebox" type="text" style="width: 100px" data-options="editable:false,formatter:function(date){
				var y = date.getFullYear();
				var m = date.getMonth()+1;
				var d = date.getDate();
				return y+'-'+m+'-'+d;
			}"
			></input>--<input id="fnedate" type="text"  class="easyui-datebox" style="width: 100px" data-options="editable:false,formatter:function(date){
				var y = date.getFullYear();
				var m = date.getMonth()+1;
				var d = date.getDate();
				return y+'-'+m+'-'+d;
			}"></input>
			新闻类型：<select id="contenttypeId" class="easyui-combobox" style="width:90px;" 
			    		data-options="valueField:'typeId',textField:'typeName',url:'<%=path %>/content-type!findAllContentType.action',editable:false">
			</select>
			<a href="javaScript:searchNoCheckContentByCondition()" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">查询</a>
			<a href="javaScript:clearCondition()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true">清空</a>
			
				
			
			
		</div>
	</div>
	
	
	<table id="newsTable" class="easyui-datagrid" data-options="url:'<%=path %>/content!findNoCheckContentByPage.action',toolbar:'#toolbar',
	        pageList:[15,20,30,40],pageSize:15,
	        pagination:true,fitColumns:true,singleSelect:false,fit:true">      
		    <thead>  
		       
		        <tr>  
		          <th data-options="width:100,checkbox:true"></th>  
		          <th data-options="field:'contentId',width:50">编号</th>
		           <th data-options="field:'title',width:150">标题</th>  
		            <th data-options="field:'cmsContentType.typeName',width:100,formatter:function(value,row,index){
		            	return row.cmsContentType.typeName;
		            }">类别</th>
		          	<th data-options="field:'cmsUser.userName',width:100,formatter:function(value,row,index){
		          		return row.cmsUser.userName;
		          	}">发布者</th>
		            <th data-options="field:'author',width:100">作者</th>  
		            <th data-options="field:'repleaseTime',width:100">发布时间</th>
		            <th data-options="field:'status',width:100,formatter:format1">审核状态</th>
		            <th data-options="field:'hasTitleImg',width:100,formatter:format2">是否有图片</th>
		            <th data-options="field:'originUrl',width:100">新闻来源</th>
		        </tr>
		       
		    </thead>  
		</table>


 <div id="updatenewswindow" class="easyui-dialog" title="新闻信息" 
  		 data-options="iconCls:'icon-save',modal:true,closed:true,maximizable:true,resizable:false,buttons:'#updateBtn'" style="width: 850px;height: 360px;" style="width: 850px;height: 360px;">
     	  <!-- 定义表单 --> 	
	  <div id="updateBtn">
	    	
	    	<a href="javascript:closeWin()" data-options="iconCls:'icon-cancel',plain:true" class="easyui-linkbutton">关闭</a>
	    	<script type="text/javascript">
	    		function closeWin(){
	    			$("#updatenewswindow").dialog("close");
	    		}
	    	</script>
	    </div>
	  
	   <form id="form2" method="post">
	   		<center>
	   		<input type="hidden" id="updatecontentId" name="content.contentId"/>
	    	<table style="margin:10px;">
	    		<tr>
			    	<td width="100px"> 请选择栏目：</td>
			    	<td>
			    		
			    		<select  id="ucontentchannel" class="easyui-combotree" style="width: 155px"  name="content.cmsChannel.channelId" data-options="
									disabled:true">
							</select> 
			    		
			    		
			    	</td>
			    	<td width="80px" align="right">新闻标题：</td>
					<td ><input class="easyui-validatebox" readonly="readonly" data-options="" id="updatetitle" name="content.title" style="width:150px;"/></td>
			    </tr>
	    		<tr>
	    			<td width="100px" align="right">简短名称：</td>
	                <td ><input class="easyui-validatebox" readonly="readonly" data-options="" name="content.shortTitle" id="updatesubTitle" style="width:150px;"/></td>
	               	<td width="80px" align="right">标题颜色：</td>
	               	<td><input type="text" id="updatetitleColor" readonly="readonly" name="content.titleColor" value="" /></td>
	    		</tr>
	    		<tr>
	                <td width="100px" align="right">新闻作者：</td>
	                <td ><input  class="easyui-validatebox" readonly="readonly" data-options="" id="updateauthor"  name="content.author" style="width:150px;"/></td>
	                 <td width="80px" align="right">新闻摘要：</td>
	                <td ><input class="easyui-validatebox" readonly="readonly" data-options=""  id="updatesummary"  name="content.summary" style="width:150px;"/></td>	             
	            </tr>
	            <tr>
	            	<td width="100px" align="right">新闻来源：</td>
	            	<td><input class="easyui-validatebox" readonly="readonly" data-options="" id="updatesource" name="content.origin" style="width: 150px;"/></td>
	            	<td width="80px" align="right">新闻地址：</td>
	            	<td><input class="easyui-validatebox" readonly="readonly" data-options="" id="updateurl" name="content.originUrl" style="width: 150px;"/></td>
	            </tr>
	            <tr>	             
	                <td width="100px" align="right">指定模板：</td>
	                <td >
	                   <select id="updatetemplate" name="content.cmsTemplate.templateId" class="easyui-combobox" style="width:155px;" 
			    		data-options="disabled:true"></select>   
	                </td>
	                <td width="80px" align="right">新闻类型：</td>
	                <td>
	                	<select id="updatecontenttype" name="content.cmsContentType.typeId" class="easyui-combobox" style="width:155px;" 
			    		data-options="disabled:true"></select>
	                </td>
	            </tr>
		    	<tr>
		    		<td width="100px" align="right">新闻内容：</td>
		    		<td colspan="3">
		    			<input type="hidden" name="content.content" id="ucontent"/>
						<textarea   id="updatecontent" style="width:400px;height:360px;visibility:hidden;display: none;"></textarea>
		    		</td>
		    	</tr>
	    	</table>
	    	</center>
	   </form>
	  </div>



<script type="text/javascript">


var updateContext;
KindEditor.ready(function(K) {
	
    
	
	//在线编辑器
	updateContext = K.create('textarea[id="updatecontent"]', {
			cssPath : 'kindeditor/plugins/code/prettify.css',
			uploadJson : '${path}/uploadPictureServlet',
			readonlyMode : true,
			afterCreate : function() {
				var self = this;
				K.ctrl(document, 13, function() {
					self.sync();
					document.forms['example'].submit();
				});
				K.ctrl(self.edit.doc, 13, function() {
					self.sync();
					document.forms['example'].submit();
				});
			}
		});
		
		prettyPrint();
	});


	


</script>



</body>
</html>