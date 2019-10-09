function deleteGarbage(){
	var selectRows=$("#channelTable").datagrid("getSelections");
	if(selectRows.length >= 1){
		$.messager.confirm('提示','彻底删除栏目不仅要还删除本栏目还要删除本栏目的子栏目以及栏目下的新闻，确定要删除栏目吗?',function(r){
		if(r){
			var ids;
			if(selectRows.length == 1){
				ids = selectRows[0].channelId;
			}else{
				var idarr = [];
				for(var i in selectRows){
					idarr.push(selectRows[i].channelId);
				}
				ids = idarr.join(",");
			}
			
			$.post("${path}/channel!deleteGarbageChannel.action?ids="+ids,function(data){
				
				if(data == "yes"){
					$.messager.show({
						title:'提示',
						msg:'删除成功.',
						timeout:5000,
						showType:'slide'
					});
					searchChannel();
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
			msg:'请至少选择一行数据.',
			timeout:5000,
			showType:'slide'
		});
	}

}
//还原被删除的栏目
function updateGarbage(){
	var selectRows=$("#channelTable").datagrid("getSelections");
	if(selectRows.length >= 1){
		$.messager.confirm('提示','还原栏目不仅要还原本栏目还有本栏目的父栏目，确定要还原栏目吗?',function(r){
		if(r){
			var ids;
			if(selectRows.length == 1){
				ids = selectRows[0].channelId;
			}else{
				var idarr = [];
				for(var i in selectRows){
					idarr.push(selectRows[i].channelId);
				}
				ids = idarr.join(",");
			}
			
			$.post("${path}/channel!updateGarbageChannel.action?ids="+ids,function(data){
				
				if(data == "yes"){
					$.messager.show({
						title:'提示',
						msg:'还原成功.',
						timeout:5000,
						showType:'slide'
					});
					searchChannel();
				}else{
					$.messager.show({
						title:'提示',
						msg:'还原失败.',
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
			msg:'请至少选择一行数据.',
			timeout:5000,
			showType:'slide'
		});
	}
}
function searchChannel(){
	var params = {
		"channel.title":$("#channeltitle").val(),
		"channel.channelName":$("#channelname").val()
	};
	$.post("${path}/channel!findGarbageChannel.action",params,function(data){
		$('#channelTable').datagrid('loadData',data);
	},"json");
	
}
function clearCondition(){
	$("#channeltitle").val("");
	$("#channelname").val("");
	
}
function reloadChannel(){
	$('#channelTable').datagrid('reload');
}
