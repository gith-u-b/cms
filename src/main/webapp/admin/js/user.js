$(function(){
	$(".datebox :text").attr("readonly","readonly");//日期控件禁止输入
});
function showAddUserWin(){
	$('#addUserWin').dialog('open');  
}
function showUpdateUserWin(){
	var selectRows =  $("#userTable").datagrid("getSelections");
	if(selectRows.length ==1){
		$('#updateUserWin').dialog('open');
		$("#updateuname").val(selectRows[0].userName);
		$("#updateuemail").val(selectRows[0].userEmail);
		$('#updateurole').combobox('setValue', selectRows[0].cmsRole.roleId);
		$('#updateugroup').combobox('setValue', selectRows[0].cmsGroup.groupId);
		$("#updateuid").val(selectRows[0].userId);

	}else{
		$.messager.show({
			title:'提示',
			msg:'请选择一行数据.',
			timeout:5000,
			showType:'slide'
		});
	}
	
}
function submitAddUserForm(){
	$.messager.progress();	// 显示一个进度条 
	$('#addUserform').form('submit', {
		url: "${path}/user!addUser.action",
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
				$('#userTable').datagrid('reload');    // reload the current page data 
				$('#addUserWin').dialog('close');
			}
			if(data == "no"){
				$.messager.show({
					title:'提示',
					msg:'添加失败.',
					timeout:5000,
					showType:'slide'
				});
			}
			if(data == "noyes"){
				
				$.messager.show({
					title:'提示',
					msg:'用户名已被注册.',
					timeout:5000,
					showType:'slide'
				});
			}
			$.messager.progress('close');	// 当成功提交之后隐藏进度条
		}
	});

	
}


function submitUpdateForm(){
	
	$.messager.progress();	// 显示一个进度条 
	$('#updateUserform').form('submit', {
		url: "${path}/user!updateUser.action",
		onSubmit: function(){
			var isValid = $(this).form('validate');
			if (!isValid){
				$.messager.progress('close');	// 当form不合法的时候隐藏工具条
			}
			return isValid;	// 返回false将停止form提交 
		},
		success: function(data){
			
			$.messager.progress('close');	// 当成功提交之后隐藏进度条
			if(data == "yes"){
				$.messager.show({
					title:'提示',
					msg:'编辑成功.',
					timeout:5000,
					showType:'slide'
				});
				$('#userTable').datagrid('reload');    // reload the current page data 
				$('#updateUserWin').dialog('close');
			}else{
				$.messager.show({
					title:'提示',
					msg:'编辑失败.',
					timeout:5000,
					showType:'slide'
				});
			}
		}
	});
}

function findByCondition(){
	
	var params = {
			"user.userName":$("#conditionUName").val(),
			begin:$('#conditionBegin').datebox('getValue'),
			end:$('#conditionEnd').datebox('getValue')
	};
	$("#userTable").datagrid('load',params);
}
function clearCondition(){
	$("#conditionUName").val("");
	$('#conditionBegin').datebox('setValue','');
	$('#conditionEnd').datebox('setValue','');
}
function clearAddForm(){
	$("#adduname").val("");
	$("#adduemail").val("");
	$('#addurole').combobox('setValue', "");
	$('#addugroup').combobox('setValue', "");
}
function clearUpdateForm(){
	var selectRows =  $("#userTable").datagrid("getSelections");
	$("#updateuname").val(selectRows[0].userName);
	$("#updateuemail").val(selectRows[0].userEmail);
	$('#updateurole').combobox('setValue', selectRows[0].cmsRole.roleId);
	$('#updateugroup').combobox('setValue', selectRows[0].cmsGroup.groupId);
}
function userHelp(){
	$.messager.alert('HELP','Help...','info');
}
function disableUser(){
	
	var selectRows =  $("#userTable").datagrid("getSelections");
	if(selectRows.length >= 1){
		$.messager.confirm('提示', '你确定要禁用这些用户吗?', function(r){
			if (r){
				$.messager.progress();
				var ids = "";
				if(selectRows.length == 1){
					ids = selectRows[0].userId; 
				}else{
					var arrs = [] ;
					for(var i in selectRows){
						arrs.push(selectRows[i].userId);
					}
					ids = arrs.join(",");
				}
				
				$.post("${path}/user!disableUser.action?ids="+ids,function(data){
					
					if(data == "yes"){
						$.messager.show({
							title:'提示',
							msg:'操作成功.',
							timeout:5000,
							showType:'slide'
						});
						$('#userTable').datagrid('reload');
					}else{
						$.messager.show({
							title:'提示',
							msg:'操作失败.',
							timeout:5000,
							showType:'slide'
						});
					}
					
				});
				
				$.messager.progress('close');
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
function usableUser(){
	
	var selectRows =  $("#userTable").datagrid("getSelections");
	if(selectRows.length >= 1){
		$.messager.progress();
		var ids = "";
		if(selectRows.length == 1){
			ids = selectRows[0].userId; 
		}else{
			var arrs = [] ;
			for(var i in selectRows){
				arrs.push(selectRows[i].userId);
			}
			ids = arrs.join(",");
		}
		
		$.post("${path}/user!usableUser.action?ids="+ids,function(data){
			
			if(data == "yes"){
				$.messager.show({
					title:'提示',
					msg:'操作成功.',
					timeout:5000,
					showType:'slide'
				});
				$('#userTable').datagrid('reload');
			}else{
				$.messager.show({
					title:'提示',
					msg:'操作失败.',
					timeout:5000,
					showType:'slide'
				});
			}
			
		});
		
		$.messager.progress('close');
	}else{
		$.messager.show({
			title:'提示',
			msg:'请至少选择一行数据.',
			timeout:5000,
			showType:'slide'
		});
	}
}
