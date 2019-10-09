//彻底删除回收站新闻
function deleteGarbage(){
	
	
	var selectRows=$("#newsTable").datagrid("getSelections");
	if(selectRows.length >= 1){
		$.messager.confirm('提示','确定要彻底删除新闻吗?',function(r){   
		    if (r){   

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
				
				$.post("${path}/content!deleteGarbage.action?ids="+ids,function(data){
					
					if(data == "yes"){
						$.messager.show({
							title:'提示',
							msg:'删除成功.',
							timeout:5000,
							showType:'slide'
						});
						
						//操作成功后保持原来页面页数不变
						searchContent();
								
					}else{
						$.messager.show({
							title:'提示',
							msg:'删除失败.',
							timeout:5000,
							showType:'slide'
						});
					}
					
				});
		    
		    }   
		});  
		
	}else{
		
		$.messager.show({
			title:'提示',
			msg:'请至少一行数据.',
			timeout:5000,
			showType:'slide'
		});
		
	}
	

	
	
}
//还原回收站的新闻
function updateGarbage(){
	var selectRows=$("#newsTable").datagrid("getSelections");
	if(selectRows.length >= 1){

		
		
		var ids;
		if(selectRows.length == 1){
			
			if(selectRows[0].cmsChannel.isDisplay == 0){
				$.messager.show({
					title:'提示',
					msg:'请先还原新闻栏目“ <b>'+selectRows[0].cmsChannel.channelName+'</b> ”或者把新闻递交其他栏目再进行还原操作.',
					timeout:5000,
					showType:'slide'
				});
				
				return;
			}
			
			ids = selectRows[0].contentId;
			
		}else{
			var idarr = [];
			for(var i in selectRows){
				if(selectRows[i].cmsChannel.isDisplay == 0){
					$.messager.show({
						title:'提示',
						msg:'请先还原新闻栏目“ <b>'+selectRows[0].cmsChannel.channelName+'</b> ”或者把新闻递交其他栏目再进行还原操作.',
						timeout:5000,
						showType:'slide'
					});
					return;
				}
				idarr.push(selectRows[i].contentId);
			}
			ids = idarr.join(",");
		}
		
		$.post("${path}/content!updateGarbage.action?ids="+ids,function(data){
			
			if(data == "yes"){
				$.messager.show({
					title:'提示',
					msg:'还原成功.',
					timeout:5000,
					showType:'slide'
				});
				
				//操作成功后保持原来页面页数不变
				searchContent();
						
			}else{
				$.messager.show({
					title:'提示',
					msg:'还原失败.',
					timeout:5000,
					showType:'slide'
				});
			}
			
		});
	}else{
		
		$.messager.show({
			title:'提示',
			msg:'请至少一行数据.',
			timeout:5000,
			showType:'slide'
		});
		
	}
	
}
function clearCondition(){
	$("#title").val("");
	$("#userName").val("");
}
function searchContent(){
	var checkstatus ;
	var obj = document.getElementsByName("checkstatus");
    for(var i=0; i<obj.length; i ++){
        if(obj[i].checked){
            checkstatus = obj[i].value;
        }
    }
	
	var params = {
			"content.title":$("#title").val(),
			"content.author":$("#userName").val(),
			"content.status":checkstatus,
			"yesno":"condition"
	};
	$.post("${path}/content!findGarbageContentByPage.action",params,function(data){
		$('#newsTable').datagrid('loadData',data);
	},"json");
	
	
}

function checkStatusRadio(checkstatus){
	$.post("${path}/content!findGarbageContentByPage.action?content.status ="+checkstatus,function(data){
		$('#newsTable').datagrid('loadData',data);
	},"json");
	
}


//打开设置新闻栏目页面
function openSetNewsChannelWin(){
	var selectRows=$("#newsTable").datagrid("getSelections");
	
	if(selectRows.length >= 1){
		$("#newsChannelWin").dialog("open");
	}else{
		$.messager.show({
			title:'提示',
			msg:'请至少选择一行数据.',
			timeout:5000,
			showType:'slide'
		});
	}
	
}

function setNewsChannel(){
	
	if($('#contentchannel').combotree('getValue') == null || $('#contentchannel').combotree('getValue') == ""){
		$.messager.show({
			title:'提示',
			msg:'请选择栏目.',
			timeout:5000,
			showType:'slide'
		});
	}else{
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
					
					$.post("${path}/content!setContentChannel.action?ids="+ids+"&channel.channelId="+$('#contentchannel').combotree('getValue'),function(data){
						if(data == "yes"){
							$.messager.show({
								title:'提示',
								msg:'设置成功.',
								timeout:5000,
								showType:'slide'
							});
							$("#newsChannelWin").dialog("close");
							//操作成功后保持原来页面页数不变
							searchContent();
									
						}else{
							$.messager.show({
								title:'提示',
								msg:'设置失败.',
								timeout:5000,
								showType:'slide'
							});
						}
						
					});
			    
			
		}else{
			
			$.messager.show({
				title:'提示',
				msg:'请至少一行数据.',
				timeout:5000,
				showType:'slide'
			});
			
		}
		
	}
	
}
function noCheckParent(node){
	$.post("${path}/channel!yesNoParent.action?channel.channelId="+node.id,function(data){
		if(data == "no"){
			$('#contentchannel').combotree("clear");
		}
		
	});
}
function format2(value,row,index){
	if(row.cmsChannel){
		return "<font color='blue'>"+row.cmsChannel.channelName+"</font>";
	}else{
		return "<font color='red'>无</font>";
	}
}
function reloadNews(){
	
	$('#newsTable').datagrid('reload');
}