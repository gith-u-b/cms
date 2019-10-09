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
  <script type="text/javascript" src="<%=path %>/admin/js/news.js"></script>
  
  <link rel="stylesheet" href="<%=path%>/kindeditor/themes/default/default.css" />
  <script charset="utf-8" src="<%=path%>/kindeditor/kindeditor-min.js"></script>
  <script charset="utf-8" src="<%=path%>/kindeditor/zh_CN.js"></script>

</head>
<body>
	<div id="toolbar" >
		<div>
			<a href="javascript:addNewsWindow()" class="easyui-linkbutton"  data-options="iconCls:'icon-edit',plain:true" onclick="">添加</a>|
			<a href="javascript:deleteContent()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="">删除</a>|
			<a href="javascript:updateContentWin()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="">修改</a>|
			<a href="javascript:reloadNews()" class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" >刷新</a>|
			<a href="javascript:createNewsHtml()" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true" onclick="">生成新闻页面</a>|
			<a href="javascript:yesStatic()" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true" onclick="">静态</a>|
			<a href="javascript:noStatic()" class="easyui-linkbutton" data-options="iconCls:'icon-no',plain:true" onclick="updateStatic(0)">取消静态</a>|
			<a href="javascript:deleteStaticHtml()" class="easyui-linkbutton" data-options="iconCls:'icon-no',plain:true" >删除静态页面</a>
			
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
			<a href="javaScript:searchContent()" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">查询</a>
			<a href="javaScript:clearCondition()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true">清空</a>
			
				
			
			
		</div>
	</div>
	
	
	<table id="newsTable" class="easyui-datagrid" data-options="url:'<%=path %>/content!findContentByPage.action',toolbar:'#toolbar',
	        pageList:[15,20,30,40],pageSize:15,
	        pagination:true,fitColumns:true,singleSelect:false,fit:true">      
		    <thead>  
		        <tr>  
		          <th data-options="width:100,checkbox:true"></th>  
		          <th data-options="field:'contentId',width:50">编号</th>
		            <th data-options="field:'title',width:200,formatter:title">标题</th>  
		            <th data-options="field:'cmsContentType.typeName',width:100,align:'center',formatter:function(value,row,index){
		            	return row.cmsContentType.typeName;
		            }">类别</th>
		          	<th data-options="field:'cmsUser.userName',width:100,align:'center',formatter:function(value,row,index){
		          		return row.cmsUser.userName;
		          	}">发布者</th>
		            <th data-options="field:'author',width:100,align:'center'">作者</th>
		            <th data-options="field:'cmsChannel',width:100,align:'center',formatter:channel">栏目</th>
		            <th data-options="field:'isStatic',width:100,align:'center',formatter:yesnostatic">能否静态</th>
		            <th data-options="field:'isCreated',width:150,align:'center',formatter:staticPage">静态页是否创建</th>  
		            <th data-options="field:'repleaseTime',width:100,align:'center'">发布时间</th>
		            <th data-options="field:'status',width:100,formatter:status,align:'center'">审核状态</th>
		            <th data-options="field:'contentImg',width:100,align:'center',formatter:function(value,row,index){
		            	if (row.contentImg){
		            		return '有图片';
		            	}else{
		            		return '没有图片';
		            	}
		            }">是否有图片</th>
		            
		            <th data-options="field:'caoz',width:100,align:'center',formatter:preview">前台预览</th>
		        </tr>
		    </thead>  
		</table>
	

<div id="addnewswindow" class="easyui-dialog" title="新闻添加" 
   data-options="iconCls:'icon-save',modal:true,closed:true,maximizable:true,resizable:false,buttons:'#addBtn'" style="width: 850px;height: 360px;">
	  
	   <!-- 定义表单 --> 	
	   <form id="form1" method="post">
	   		<center>
	    	<table style="margin:10px;">
	    		<tr>
			    	<td width="100px"> 请选择栏目：</td>
			    	<td>
			    			<select  id="acontentchannel" class="easyui-combotree" style="width: 155px"  name="content.cmsChannel.channelId" data-options="url:'<%=path %>/channel!findAllChannel.action',
									required:true,editable:false,onSelect:noCheckParent">
							</select>  
			    	</td>
			    	<td width="80px" align="right">新闻标题：</td>
					<td ><input class="easyui-validatebox" data-options="required:true" id="title" name="content.title" style="width:150px;"/></td>
			    </tr>
	    		<tr>
	    			<td width="100px" align="right">简短名称：</td>
	                <td ><input class="easyui-validatebox" data-options="required:true" name="content.shortTitle" id="subTitle" style="width:150px;"/></td>
	               	<td width="80px" align="right">标题颜色：</td>
	               	<td>
	               	<input type="text" id="titleColor" readonly="readonly" name="content.titleColor" value="" /> <input type="button" id="colorpicker" value="打开取色器" />
	    		</tr>
	    		<tr>
	                <td width="100px" align="right">新闻作者：</td>
	                <td ><input  class="easyui-validatebox" data-options="required:true" id="author"  name="content.author" style="width:150px;"/></td>
	                 <td width="80px" align="right">新闻摘要：</td>
	                <td ><input class="easyui-validatebox" data-options="required:true"  id="summary"  name="content.summary" style="width:150px;"/></td>	             
	            </tr>
	            <tr>
	            	<td width="100px" align="right">新闻来源：</td>
	            	<td><input class="easyui-validatebox" data-options="required:true" id="source" name="content.origin" style="width: 150px;"/></td>
	            	<td width="80px" align="right">新闻地址：</td>
	            	<td><input class="easyui-validatebox" data-options="required:true" id="url" name="content.originUrl" style="width: 150px;"/></td>
	            </tr>
	            <tr>	             
	                <td width="100px" align="right">指定模板：</td>
	                <td >
	                    <select id="template" name="content.cmsTemplate.templateId" class="easyui-combobox" style="width:155px;" 
			    		data-options="required:true,valueField:'templateId',textField:'templateName',url:'<%=path %>/template!findTemplateByContent.action',editable:false"></select>
			    		 
	                </td>
	                <td width="80px" align="right">新闻类型：</td>
	                <td>
	                	 <select id="contenttype" name="content.cmsContentType.typeId" class="easyui-combobox" style="width:155px;" 
			    		data-options="valueField:'typeId',textField:'typeName',url:'<%=path %>/content-type!findAllContentType.action',editable:false,required:true"></select>
			    		 
	                </td>
	            </tr>
	            <tr>
	            	<td colspan="2" style="padding-left:40px">标题是否加粗：<input type="checkbox" id="titleBold" value="1" name="content.isBold"/></td>
	            	<td colspan="2" style="padding-left:20px">内容可否评论：<input type="checkbox" id="commend" value="1" name="content.isCommend"/></td>
	            	
	            </tr>
	            <tr>
	            	<td colspan="2" style="padding-left:40px">内容可否回复：<input type="checkbox" id="recommend" value="1" name="content.isRecommend"/></td>
	            	<td colspan="2" style="padding-left:20px">内容是否静态：<input type="checkbox" id="static" value="1" name="content.isStatic"/></td>
	            </tr>
		    	<tr>
		    		<td width="100px" align="right">新闻内容：</td>
		    		<td colspan="3">
		    		<input type="hidden" name="content.content" id="acontent"/>
						 <textarea  id="addcontent" style="width:400px;height:360px;visibility:hidden;display: none;"></textarea> 
		    		</td>
		    	</tr>
	    	</table>
	    	</center>
	   </form>
	   	 <div id="addBtn">
	    	<a href="javascript:submitAddContentForm()" data-options="iconCls:'icon-ok',plain:true" class="easyui-linkbutton" >添加</a>
	    	<a href="javascript:resetAddContent()" data-options="iconCls:'icon-cancel',plain:true" class="easyui-linkbutton">重置</a>
	    </div>
	   	
	  </div>
	
	

	
	 <div id="updatenewswindow" class="easyui-dialog" title="新闻修改" 
  		 data-options="iconCls:'icon-save',modal:true,closed:true,maximizable:true,resizable:false,buttons:'#updateBtn'" style="width: 850px;height: 360px;" style="width: 850px;height: 360px;">
     	  <!-- 定义表单 --> 	
	  <div id="updateBtn">
	    	<a href="javascript:submitUpdateContentForm()" data-options="iconCls:'icon-ok',plain:true" class="easyui-linkbutton" >更改</a>
	    	<a href="javascript:resetUpdateContent()" data-options="iconCls:'icon-cancel',plain:true" class="easyui-linkbutton">重置</a>
	    </div>
	  
	   <form id="form2" method="post">
	   		<center>
	   		<input type="hidden" id="updatecontentId" name="content.contentId"/>
	    	<table style="margin:10px;">
	    		<tr>
			    	<td width="100px"> 请选择栏目：</td>
			    	<td>
			    		
			    		<select  id="ucontentchannel" class="easyui-combotree" style="width: 155px"  name="content.cmsChannel.channelId" data-options="url:'<%=path %>/channel!findAllChannel.action',
									required:true,editable:false,onSelect:noCheckParent1"><!-- 这个地方required其实应该为false ，因为可能要添加父栏目 ，由于json数据，则不能添加父栏目 （可以添加，但是读取时则会乱掉）-->
							</select> 
			    		
			    		
			    	</td>
			    	<td width="80px" align="right">新闻标题：</td>
					<td ><input class="easyui-validatebox" data-options="required:true" id="updatetitle" name="content.title" style="width:150px;"/></td>
			    </tr>
	    		<tr>
	    			<td width="100px" align="right">简短名称：</td>
	                <td ><input class="easyui-validatebox" data-options="required:true" name="content.shortTitle" id="updatesubTitle" style="width:150px;"/></td>
	               	<td width="80px" align="right">标题颜色：</td>
	               	<td><input type="text" id="updatetitleColor" readonly="readonly" name="content.titleColor" value="" /> <input type="button" id="colorpicker2" value="打开取色器" /></td>
	    		</tr>
	    		<tr>
	                <td width="100px" align="right">新闻作者：</td>
	                <td ><input  class="easyui-validatebox" data-options="required:true" id="updateauthor"  name="content.author" style="width:150px;"/></td>
	                 <td width="80px" align="right">新闻摘要：</td>
	                <td ><input class="easyui-validatebox" data-options="required:true"  id="updatesummary"  name="content.summary" style="width:150px;"/></td>	             
	            </tr>
	            <tr>
	            	<td width="100px" align="right">新闻来源：</td>
	            	<td><input class="easyui-validatebox" data-options="required:true" id="updatesource" name="content.origin" style="width: 150px;"/></td>
	            	<td width="80px" align="right">新闻地址：</td>
	            	<td><input class="easyui-validatebox" data-options="required:true" id="updateurl" name="content.originUrl" style="width: 150px;"/></td>
	            </tr>
	            <tr>	             
	                <td width="100px" align="right">指定模板：</td>
	                <td >
	                   <select id="updatetemplate" name="content.cmsTemplate.templateId" class="easyui-combobox" style="width:155px;" 
			    		data-options="valueField:'templateId',textField:'templateName',required:true,url:'<%=path %>/template!findTemplateByContent.action',editable:false,required:true"></select>   
	                </td>
	                <td width="80px" align="right">新闻类型：</td>
	                <td>
	                	<select id="updatecontenttype" name="content.cmsContentType.typeId" class="easyui-combobox" style="width:155px;" 
			    		data-options="valueField:'typeId',textField:'typeName',url:'<%=path %>/content-type!findAllContentType.action',editable:false,required:true"></select>
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
	<%
	%>
	 
	
	<script type="text/javascript">
	
	//在线编辑空件，色板
	var updateContext;
	var addContext;
	KindEditor.ready(function(K) {
		//色板
		var colorpicker;
		K('#colorpicker').bind('click', function(e) {
			e.stopPropagation();
			if (colorpicker) {
				colorpicker.remove();
				colorpicker = null;
				return;
			}
			var colorpickerPos = K('#colorpicker').pos();
			colorpicker = K.colorpicker({
				x : colorpickerPos.x,
				y : colorpickerPos.y + K('#colorpicker').height(),
				z : 19811214,
				selectedColor : 'default',
				noColor : '无颜色',
				click : function(color) {
					K('#titleColor').val(color);
					colorpicker.remove();
					colorpicker = null;
				}
			});
		});
		K('#colorpicker2').bind('click', function(e) {
			e.stopPropagation();
			if (colorpicker) {
				colorpicker.remove();
				colorpicker = null;
				return;
			}
			var colorpickerPos = K('#colorpicker2').pos();
			colorpicker = K.colorpicker({
				x : colorpickerPos.x,
				y : colorpickerPos.y + K('#colorpicker2').height(),
				z : 19811214,
				selectedColor : 'default',
				noColor : '无颜色',
				click : function(color) {
					K('#updatetitleColor').val(color);
					colorpicker.remove();
					colorpicker = null;
				}
			});
		});
		K(document).click(function() {
			if (colorpicker) {
				colorpicker.remove();
				colorpicker = null;
			}
		});
		
		
		
		//在线编辑器
		updateContext = K.create('textarea[id="updatecontent"]', {
				cssPath : 'kindeditor/plugins/code/prettify.css',
				uploadJson : '${path}/uploadPictureServlet',
				readonlyMode : false,
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
			addContext = K.create('textarea[id="addcontent"]', {
				cssPath : 'kindeditor/plugins/code/prettify.css',
				uploadJson : '${path}/uploadPictureServlet',
				readonlyMode : false,
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
	
	
	
	
	
	function preview(value,row,index){  //前台预览
		var channel=row.cmsChannel;
		
		if(row.isCreated==1){
			
			return "<a target='_blank' href='<%=path %>"+channel.channelPath+"/news"+row.contentId+".html'>前台预览</a>";	
		}else{
			return "<a target='_blank' href='<%=path %>/news/html_newsHtml.action?content.contentId="+row.contentId+"'>前台预览</a>";	
		}
		
	}
	
	
	</script>
	
	
</body>
</html>