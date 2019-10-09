function reloadChannel(){
	$('#channelTable').treegrid('load');
}
function openChannelTable(){
	$('#channelTable').treegrid('expandAll');
}
function resetUpdateChannel(){
	var selectRows =  $("#channelTable").treegrid("getSelections");
	$("#ucid").val(selectRows[0].id);
	$("#ucname").val(selectRows[0].text);
	$("#ucpage").val(selectRows[0].attributes.page);
	$("#uctitle").val(selectRows[0].attributes.title);
	$("#uckeywords").val(selectRows[0].attributes.keywords);
	$("#ucdescription").val(selectRows[0].attributes.description);
	$('#uctemplate').combobox('setValue', selectRows[0].attributes.template.templateId);

}
function resetAddChannel(){
	$("#acname").val("");
	$("#acpage").val("");
	$("#actitle").val("");
	$("#ackeywords").val("");
	$("#acdescription").val("");
	$("#acpath").val("");
	
}
function submitUpdateChannelForm(){
	$.messager.progress();	// 显示一个进度条 
	$('#updateChannelForm').form('submit', {
		url: "${path}/channel!updateChannel.action",
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
					msg:'更改成功.',
					timeout:5000,
					showType:'slide'
				});
				$('#channelTable').treegrid('reload');
				$("#updateChannelWin").dialog("close");
				
			}else{
				$.messager.show({
					title:'提示',
					msg:'更改失败.',
					timeout:5000,
					showType:'slide'
				});
			}
			
			$.messager.progress('close');	// 当成功提交之后隐藏进度条
			
		}
	});


	
}
function updateChannel(){
	var selectRows =  $("#channelTable").treegrid("getSelections");
	if(selectRows.length == 1){
		$("#updateChannelWin").dialog("open");
		$("#ucid").val(selectRows[0].id);
		$("#ucname").val(selectRows[0].text);
		$("#ucpage").val(selectRows[0].attributes.page);
		$("#uctitle").val(selectRows[0].attributes.title);
		$("#uckeywords").val(selectRows[0].attributes.keywords);
		$("#ucdescription").val(selectRows[0].attributes.description);
		$('#uctemplate').combobox('setValue', selectRows[0].attributes.template.templateId);

	}else{
		$.messager.show({
			title:'提示',
			msg:'请选择一行数据.',
			timeout:5000,
			showType:'slide'
		});
	}
	
}
function deleteChannel(){
	var selectRows =  $("#channelTable").treegrid("getSelections");
	if(selectRows.length == 1){
	$.messager.confirm('提示', '你确定要删除这些（这个）栏目吗?', function(r){
	if (r){
		$.post("${path}/channel!deleteChannel.action?channel.channelId="+selectRows[0].id,function(data){
			if(data == "yes"){
				$.messager.show({
					title:'提示',
					msg:'删除成功，请到回收站进行永久删除.',
					timeout:5000,
					showType:'slide'
				});
				$('#channelTable').treegrid('reload');
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
			msg:'请选择一行数据.',
			timeout:5000,
			showType:'slide'
		});
	}
	
}
function channelHelp(){
	$.messager.alert('HELP','Help...!','info');
}
function addChannel(){
	document.getElementById("addChannelForm").reset();
	$("#addChannelWin").dialog("open");
	
}
function submitAddChannelForm(){
	$.messager.progress();	// 显示一个进度条 
	$('#addChannelForm').form('submit', {
		url: "${path}/channel!addChannel.action",
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
				$('#channelTable').treegrid('reload');
				$("#addChannelWin").dialog("close");
			
				
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


}