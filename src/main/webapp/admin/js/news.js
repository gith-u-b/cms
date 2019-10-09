//重置添加win
function resetAddContent(){
	document.getElementById("form1").reset();
	addContext.html("");
}
//重置更改win
function resetUpdateContent(){
	
		var selectRows=$("#newsTable").datagrid("getSelections");
		$("#updatenewswindow").window("open");
		$("#updatecontentId").val(selectRows[0].contentId);
		$("#updatesubTitle").val(selectRows[0].shortTitle);
		$("#updatetitleColor").val(selectRows[0].titleColor);
		$("#updatetitle").val(selectRows[0].title);
		$("#updateauthor").val(selectRows[0].author);
		$("#updatesummary").val(selectRows[0].summary);
		$("#updatesource").val(selectRows[0].origin);
		$("#updateurl").val(selectRows[0].originUrl);
		updateContext.html(selectRows[0].content);
		
		$('#updatecontenttype').combobox('setValue', selectRows[0].cmsContentType.typeId);
		$('#updatetemplate').combobox('setValue', selectRows[0].cmsTemplate.templateId);
		$('#ucontentchannel').combotree('setValue', selectRows[0].cmsChannel.channelId);
	
}
//删除静态页面
function deleteStaticHtml(){
	var selectRows = $("#newsTable").datagrid("getSelections");
	if(selectRows.length >= 1){
			var ids;
			if(selectRows.length == 1){
				ids = selectRows[0].contentId;
			}else{
				var idarr = [];
				for(var i in selectRows){
					idarr.push(selectRows[i].contentId);
				}
				ids = idarr.join(",");
			}
			
			$.post("${path}/create-html!deleteNewsHtml.action?ids="+ids,function(data){
				
				if(data == "yes"){
					$.messager.show({
						title:'提示',
						msg:'删除成功.',
						timeout:5000,
						showType:'slide'
					});
					$('#newsTable').datagrid('load');
				}else{
					$.messager.show({
						title:'提示',
						msg:'删除失败.',
						timeout:5000,
						showType:'slide'
					});
				}
			});
	}else{
		$.messager.show({
			title:'提示',
			msg:'请至少选中一条数据.',
			timeout:5000,
			showType:'slide'
		});
	}
	
	
}


//创建新闻静态页面
function createNewsHtml(){
	
	var selectRows = $("#newsTable").datagrid("getSelections");
	if(selectRows.length >= 1){
			var ids;
			if(selectRows.length == 1){
				ids = selectRows[0].contentId;
			}else{
				var idarr = [];
				for(var i in selectRows){
					idarr.push(selectRows[i].contentId);
				}
				ids = idarr.join(",");
			}
			
			$.post("${path}/create-html!createNewsHtml.action?ids="+ids,function(data){
				
				if(data == "yes"){
					$.messager.show({
						title:'提示',
						msg:'生成成功.',
						timeout:5000,
						showType:'slide'
					});
					$('#newsTable').datagrid('load');
				}else{
					$.messager.show({
						title:'提示',
						msg:'生成失败.',
						timeout:5000,
						showType:'slide'
					});
				}
				
			});
		
	}else{
		$.messager.show({
			title:'提示',
			msg:'请至少选中一条数据.',
			timeout:5000,
			showType:'slide'
		});
	}
	
	
	
}

function noStatic(){
	var selectRows = $("#newsTable").datagrid("getSelections");
	if(selectRows.length >= 1){
		var ids;
		if(selectRows.length == 1){
			ids = selectRows[0].contentId;
		}else{
			var idarr = [];
			for(var i in selectRows){
				idarr.push(selectRows[i].contentId);
			}
			ids = idarr.join(",");
		}
		
		$.post("${path}/content!noStatic.action?ids="+ids,function(data){
			
			if(data == "yes"){
				$.messager.show({
					title:'提示',
					msg:'静态成功.',
					timeout:5000,
					showType:'slide'
				});
				$('#newsTable').datagrid('load');
			}else{
				$.messager.show({
					title:'提示',
					msg:'静态失败.',
					timeout:5000,
					showType:'slide'
				});
			}
			
		});
		
		
	}else{
		$.messager.show({
			title:'提示',
			msg:'请至少选中一条数据.',
			timeout:5000,
			showType:'slide'
		});
	}
	
}



function yesStatic(){
	var selectRows = $("#newsTable").datagrid("getSelections");
	if(selectRows.length >= 1){
		var ids;
		if(selectRows.length == 1){
			ids = selectRows[0].contentId;
		}else{
			var idarr = [];
			for(var i in selectRows){
				idarr.push(selectRows[i].contentId);
			}
			ids = idarr.join(",");
		}
		
		$.post("${path}/content!yesStatic.action?ids="+ids,function(data){
			
			if(data == "yes"){
				$.messager.show({
					title:'提示',
					msg:'静态成功.',
					timeout:5000,
					showType:'slide'
				});
				$('#newsTable').datagrid('load');
			}else{
				$.messager.show({
					title:'提示',
					msg:'静态失败.',
					timeout:5000,
					showType:'slide'
				});
			}
			
		});
		
		
	}else{
		$.messager.show({
			title:'提示',
			msg:'请至少选中一条数据.',
			timeout:5000,
			showType:'slide'
		});
	}
	
}
function deleteContent(){
	var selectRows=$("#newsTable").datagrid("getSelections");
	
	if(selectRows.length > 0){
		var ids;
		if(selectRows.length == 1){
			ids = selectRows[0].contentId;
		}else{
			var idarr = [];
			for(var i in selectRows){
				idarr.push(selectRows[i].contentId);
			}
			ids = idarr.join(",");
		}
		
		$.post("${path}/content!deleteContent.action?ids="+ids,function(data){
			if(data == "yes"){
				$.messager.show({
					title:'提示',
					msg:'删除成功（请到回收站进行彻底删除）.',
					timeout:5000,
					showType:'slide'
				});
				$('#newsTable').datagrid('load');
				
			}else{
				$.messager.show({
					title:'提示',
					msg:'删除失败.',
					timeout:5000,
					showType:'slide'
				});
			}
		});
	}else{
		$.messager.show({
			title:'提示',
			msg:'请至少选择一行数据.',
			timeout:5000,
			showType:'slide'
		});
	}
	
}

function submitUpdateContentForm(){
	
	$("#ucontent").val(updateContext.html());
	if($.trim($("#ucontent").val())!=""){
		$.messager.progress();	// 显示一个进度条 
		$('#form2').form('submit', {
			url: "${path}/content!updateContent.action",
			onSubmit: function(){
				var isValid = $(this).form('validate');
				if (!isValid){
					$.messager.progress('close');	// 当form不合法的时候隐藏工具条
				}
				return isValid;	// 返回false将停止form提交 
			},
			success: function(data){
				if(data == "yes"){
					$.messager.show({
						title:'提示',
						msg:'修改成功.',
						timeout:5000,
						showType:'slide'
					});
					$('#updatenewswindow').dialog('close'); 
					$('#newsTable').datagrid('load');
					
				}else{
					$.messager.show({
						title:'提示',
						msg:'修改失败.',
						timeout:5000,
						showType:'slide'
					});
				}
				$.messager.progress('close');	// 当成功提交之后隐藏进度条
			}
		});

	}else{
		$.messager.show({
			title:'提示',
			msg:'新闻不能为空.',
			timeout:5000,
			showType:'slide'
		});
	}
	
}


function submitAddContentForm(){
	
	$("#acontent").val(addContext.html());
	if($.trim($("#acontent").val())!=""){
		$.messager.progress();	// 显示一个进度条 
		$('#form1').form('submit', {
			url: "${path}/content!addContent.action",
			onSubmit: function(){
				var isValid = $(this).form('validate');
				if (!isValid){
					$.messager.progress('close');	// 当form不合法的时候隐藏工具条
				}
				return isValid;	// 返回false将停止form提交 
			},
			success: function(data){
				if(data == "yes"){
					$.messager.show({
						title:'提示',
						msg:'添加成功.',
						timeout:5000,
						showType:'slide'
					});
					$('#addnewswindow').dialog('close'); 
					$('#newsTable').datagrid('load');
					
				}else{
					$.messager.show({
						title:'提示',
						msg:'添加失败.',
						timeout:5000,
						showType:'slide'
					});
				}
				$.messager.progress('close');	// 当成功提交之后隐藏进度条
			}
		});

	}else{
		$.messager.show({
			title:'提示',
			msg:'新闻不能为空.',
			timeout:5000,
			showType:'slide'
		});
	}
}
//打开添加新闻页面
	function addNewsWindow(){
		$('#acontentchannel').combotree('reload');  
		
		document.getElementById("form1").reset();
		addContext.html("");
		$('#addnewswindow').dialog('open'); 
	}
	
	//打开编辑新闻窗口
	function updateContentWin(){
		$('#ucontentchannel').combotree('reload');
		var selectRows=$("#newsTable").datagrid("getSelections");
		if(selectRows.length == 1){
			$("#updatenewswindow").window("open");
			$("#updatecontentId").val(selectRows[0].contentId);
			$("#updatesubTitle").val(selectRows[0].shortTitle);
			$("#updatetitleColor").val(selectRows[0].titleColor);
			$("#updatetitle").val(selectRows[0].title);
			$("#updateauthor").val(selectRows[0].author);
			$("#updatesummary").val(selectRows[0].summary);
			$("#updatesource").val(selectRows[0].origin);
			$("#updateurl").val(selectRows[0].originUrl);
			updateContext.html(selectRows[0].content);
			
			$('#updatecontenttype').combobox('setValue', selectRows[0].cmsContentType.typeId);
			$('#updatetemplate').combobox('setValue', selectRows[0].cmsTemplate.templateId);
			$('#ucontentchannel').combotree('setValue', selectRows[0].cmsChannel.channelId);

			
		}else{
			
			$.messager.show({
				title:'提示',
				msg:'请选择一行数据.',
				timeout:5000,
				showType:'slide'
			});
			
		}
		
		
	}	
	
function title(value,row,index){  //标题
	var color=row.titleColor;
	color+="";
	if(row.isBold==1){
		return "<b><font color='"+ color +"'>"+value+"</font></b>";
	}else{
		return "<font color='"+ color +"'>"+value+"</font>";
	}
}
function channel(value,row,index){  //是否有栏目
	if(row.cmsChannel){
		return "<font color='blue'>"+row.cmsChannel.channelName+"</font>";
	}else{
		return "<font color='red'>还没栏目</font>";
	}
}
function yesnostatic(value,row,index){  //是否能创建静态页面
	if(value==1){
		return "<font color='blue'>能</font>";
	}else{
		return "<font color='red'>不能</font>";
	}
}
function staticPage(value,row,index){  //静态页面是否已创建
	if(value==1){
		return "<font color='blue'>已创建</font>";
	}else{
		return "<font color='red'>未创建</font>";
	}
}

function status(value,row,index){ //审核状态
		if(row.status==1){
			return "<font color='blue'>已审核</font>";
		}else{
			return "<font color='red'>未审核</font>";
		}
}

function reloadNews(){
	$('#newsTable').datagrid('load');
}
function selectTree(record){
	
	/*$("#fntitle").val("");
	$("#fnauthor").val("");
	$("#fnusername").val("");
	$('#contenttypeId').combobox('clear');
	$('#fnedate').datetimebox('clear');
	$('#fnsdate').datetimebox('clear');
	*/
	var params = {
			page:1,
			rows:15,
			"content.cmsChannel.channelId":record.id,
			yesno:"channel"
			
	};
	$.post("${path}/content!findContentByPage.action",params,function(data){
		$('#newsTable').datagrid('loadData',data);
	},"json");
	
	
}

function searchContent(){
	var params = {
			page:1,
			rows:15,
			"content.cmsChannel.channelId":$('#fNewsChannelId').combotree('getValue'),
			yesno:"condition",
			'content.title':$("#fntitle").val(),
			'content.author':$("#fnauthor").val(),
			'content.cmsUser.userName':$("#fnusername").val(),
			beginTime:$('#fnsdate').datetimebox('getValue'),
			endTime:$('#fnedate').datetimebox('getValue'),
			'content.cmsContentType.typeId':$('#contenttypeId').combobox('getValue')
	};
	$.post("${path}/content!findContentByPage.action",params,function(data){
		$('#newsTable').datagrid('loadData',data);
	},"json");
}
function clearCondition(){
	$("#fntitle").val("");
	$("#fnauthor").val("");
	$("#fnusername").val("");
	$('#contenttypeId').combobox('clear');
	$('#fnedate').datetimebox('clear');
	$('#fnsdate').datetimebox('clear');
}

function noCheckParent(node){
	$.post("${path}/channel!yesNoParent.action?channel.channelId="+node.id,function(data){
		if(data == "no"){
			$('#acontentchannel').combotree("clear");
		}
		
	});
}
function noCheckParent1(node){
	$.post("${path}/channel!yesNoParent.action?channel.channelId="+node.id,function(data){
		if(data == "no"){
			$('#ucontentchannel').combotree("clear");
		}
		
	});
}