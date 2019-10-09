function deleteGroup(){
	var selectRows =  $("#table").datagrid("getSelections");
	if(selectRows.length != 1){
		$.messager.show({
			title:'提示',
			msg:'请选择一行数据.',
			timeout:5000,
			showType:'slide'
		});
	}else{
		$.post("${path}/group!deleteGroup.action?group.groupId="+selectRows[0].groupId,function(data){
			
			if(data == "yes"){
				$.messager.show({
					title:'提示',
					msg:'删除成功.',
					timeout:5000,
					showType:'slide'
				});
				$("#table").datagrid("reload");
			}
			if(data == "no"){
				$.messager.show({
					title:'提示',
					msg:'删除失败.',
					timeout:5000,
					showType:'slide'
				});
			}
			if(data == "yesno"){
				$.messager.show({
					title:'提示',
					msg:'有相关联用户，不能删除.',
					timeout:5000,
					showType:'slide'
				});
			}
			
		});
	}

}
function submitUpdate(){
	$.messager.progress();	// 显示一个进度条 
	$('#form2').form('submit', {
		url: "${path}/group!updateGroup.action",
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
				$("#windowupdate").dialog("close");
				$("#table").datagrid("reload");
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
function submitAdd(){
	$.messager.progress();	// 显示一个进度条 
	$('#form1').form('submit', {
		url: "${path}/group!addGroup.action",
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
				$("#windowadd").dialog("close");
				$("#table").datagrid("reload");
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
function addGroupWin(){
	$("#windowadd").dialog("open");
}
function updateGroupWin(){
	
	var selectRows =  $("#table").datagrid("getSelections");
	if(selectRows.length != 1){
		$.messager.show({
			title:'提示',
			msg:'请选择一行数据.',
			timeout:5000,
			showType:'slide'
		});
	}else{
		$("#groupId").val(selectRows[0].groupId);
		$("#groupName").val(selectRows[0].groupName);
		$("#windowupdate").dialog("open");
	}
}
function groupHelp(){
	$.messager.alert('HELP','Help...!','info');
}