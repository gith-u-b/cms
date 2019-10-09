function checkNewsNo(){
	
	var selectRows=$("#newsTable").datagrid("getSelections");
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
		
		$.post("${path}/content!cancelCheckNews.action?ids="+ids,function(data){
			
			if(data == "yes"){
				$.messager.show({
					title:'提示',
					msg:'撤销成功.',
					timeout:5000,
					showType:'slide'
				});
				
				//审核或者撤销审核成功后保持原来页面内容不变
				searchNoCheckContentByCondition();
				
			}else{
				$.messager.show({
					title:'提示',
					msg:'撤销失败.',
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

function checkData(data){
	if(data.rows.length == 0){
		$.messager.show({
			title:'提示',
			msg:'暂没有未被审核的新闻.',
			timeout:5000,
			showType:'slide'
		});
	}
}

function checkNewsPass(){
	   
	
	var selectRows=$("#newsTable").datagrid("getSelections");
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
		
		$.post("${path}/content!checkNews.action?ids="+ids,function(data){
			
			if(data == "yes"){
				$.messager.show({
					title:'提示',
					msg:'审核通过.',
					timeout:5000,
					showType:'slide'
				});
				
				//审核或者撤销审核成功后保持原来页面内容不变
				searchNoCheckContentByCondition();
						
			}else{
				$.messager.show({
					title:'提示',
					msg:'审核不通过.',
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
function format1(value,row,index){
	if (row.status==1){
		return "<font color='blue'>已审核</font>";
	}else{
		return "<font color='red'>未审核</font>";
	}
}
function format2(value,row,index){
	if (row.hasTitleImg==1){
		return "<font color='blue'>有图片</font>";
	}else{
		return "<font color='blue'>没有图片</font>";;
	}
}
function searchNoCheckContentByCondition(){
	var checkstatus ;
	var obj = document.getElementsByName("checkstatus");
    for(var i=0; i<obj.length; i ++){
        if(obj[i].checked){
            checkstatus = obj[i].value;
        }
    }
	
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
			'content.cmsContentType.typeId':$('#contenttypeId').combobox('getValue'),
			'content.status':checkstatus
	};
	$.post("${path}/content!findNoCheckContentByPage.action",params,function(data){
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

function checkStatusRadio(checkstatus){
	if($('#fNewsChannelId').combotree('getValue') != ""){
		var params = {
				page:1,
				rows:15,
				"content.cmsChannel.channelId":$('#fNewsChannelId').combotree('getValue'),
				yesno:"channel",
				'content.status':checkstatus
				
		};
		$.post("${path}/content!findNoCheckContentByPage.action",params,function(data){
			$('#newsTable').datagrid('loadData',data);
		},"json");
		
	}else{
		var params = {
				page:1,
				rows:15,
				'content.status':checkstatus
				
		};
		$.post("${path}/content!findNoCheckContentByPage.action",params,function(data){
			$('#newsTable').datagrid('loadData',data);
		},"json");
	}
	
	
	
}

function selectTree(record){
		var checkstatus ;
	var obj = document.getElementsByName("checkstatus");
    for(var i=0; i<obj.length; i ++){
        if(obj[i].checked){
            checkstatus = obj[i].value;
        }
    }

    /*	
$("#fntitle").val("");
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
			yesno:"channel",
			'content.status':checkstatus
	};
	$.post("${path}/content!findNoCheckContentByPage.action",params,function(data){
		$('#newsTable').datagrid('loadData',data);
	},"json");
	
	
}
function serachNewsInfo(){
	
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
		
		$('#updatecontenttype').combobox('setValue', selectRows[0].cmsContentType.typeName);
		$('#updatetemplate').combobox('setValue', selectRows[0].cmsTemplate.templateName);
		$('#ucontentchannel').combotree('setValue', selectRows[0].cmsChannel.channelName);

		
	}else{
		
		$.messager.show({
			title:'提示',
			msg:'请选择一行数据.',
			timeout:5000,
			showType:'slide'
		});
		
	}
}