//启用会员
function Usable(){
	var selectRows = $("#table").datagrid("getSelections");
	if(selectRows.length >= 1){
			var ids;
			if(selectRows.length == 1){
				ids = selectRows[0].userId;
			}else{
				var idarr = [];
				for(var i in selectRows){
					idarr.push(selectRows[i].userId);
				}
				ids = idarr.join(",");
			}
			
			$.post("${path}/user!doUsableUser.action?ids="+ids,function(data){
				
				if(data == "yes"){
					$.messager.show({
						title:'提示',
						msg:'启用成功.',
						timeout:5000,
						showType:'slide'
					});
					$('#table').datagrid('load');
				}else{
					$.messager.show({
						title:'提示',
						msg:'启用失败.',
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


//禁用会员
function Disable(){
	var selectRows = $("#table").datagrid("getSelections");
	if(selectRows.length >= 1){
			var ids;
			if(selectRows.length == 1){
				ids = selectRows[0].userId;
			}else{
				var idarr = [];
				for(var i in selectRows){
					idarr.push(selectRows[i].userId);
				}
				ids = idarr.join(",");
			}
			
			$.post("${path}/user!disableUser.action?ids="+ids,function(data){
				
				if(data == "yes"){
					$.messager.show({
						title:'提示',
						msg:'禁用成功.',
						timeout:5000,
						showType:'slide'
					});
					$('#table').datagrid('load');
				}else{
					$.messager.show({
						title:'提示',
						msg:'禁用失败.',
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
function clearCondition(){
	$("#tjName").val("");
	$("#sdate").datebox('setValue', '');
	$("#edate").datebox('setValue', '');
}
$(function(){
	$(".datebox :text").attr("readonly","readonly");  //日期控件禁止 输入
});
/*
 * 按条件查询会员
 */
function searchMember(){
	
	var param={
			"user.userName":$("#tjName").val(),
			begin:$("#sdate").datebox('getValue'),
			end:$("#edate").datebox('getValue')
	};
	$('#table').datagrid('load',param);
}
//设置会员组
function setGroup(){
	var mygroup = $("#mygroup").combobox("getValue"); 
	if(mygroup == "" || mygroup == null){
		$.messager.show({
			title:'提示',
			msg:'请选会员组.',
			timeout:5000,
			showType:'slide'
		});
	}else{
		var selectRows = $("#table").datagrid("getSelections");
		var ids;
		if(selectRows.length == 1){
			ids = selectRows[0].userId;
		}else{
			var idarr = [];
			for(var i in selectRows){
				idarr.push(selectRows[i].userId);
			}
			ids = idarr.join(",");
		}
		
		$.post("${path}/user!setUserGroup.action?ids="+ids+"&user.cmsGroup.groupId="+mygroup,function(data){
			if(data == "yes"){
				$.messager.show({
					title:'提示',
					msg:'设置成功.',
					timeout:5000,
					showType:'slide'
				});
				$("#windowform").dialog("close");
				$('#table').datagrid('load');
			}else{
				$.messager.show({
					title:'提示',
					msg:'设置失败.',
					timeout:5000,
					showType:'slide'
				});
			}
			
		});
		
	}
	
}

//设置会员组win
function setGroupWin(){
	
		var selectRows = $("#table").datagrid("getSelections");
		if(selectRows.length >= 1){
				$("#windowform").dialog("open");
		}else{
			$.messager.show({
				title:'提示',
				msg:'请至少选中一条数据.',
				timeout:5000,
				showType:'slide'
			});
		}
		
}

function userHelp(){
		$.messager.alert('HELP','Help...!','info');
}

function deleteUser(){
	var selectRows = $("#table").datagrid("getSelections");
	if(selectRows.length >= 1){
			var ids;
			if(selectRows.length == 1){
				ids = selectRows[0].userId;
			}else{
				var idarr = [];
				for(var i in selectRows){
					idarr.push(selectRows[i].userId);
				}
				ids = idarr.join(",");
			}
			
			$.post("${path}/user!deleteUser.action?ids="+ids,function(data){
				
				if(data == "yes"){
					$.messager.show({
						title:'提示',
						msg:'删除成功.',
						timeout:5000,
						showType:'slide'
					});
					$('#table').datagrid('load');
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