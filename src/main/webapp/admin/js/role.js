function submitGrantForm(){
	$.post("${path}/power!findAllPowers.action",function(data){
		var selectPowers = $("#powertree").tree("getChecked");
		var selectRoles = $("#roleTable").datagrid("getSelections");
		var powerArr = [];
		
		for(var i in selectPowers){
					powerArr.push(selectPowers[i].id);
					for(var j in data){
						if(data[j].id == selectPowers[i].attributes.pid){
							powerArr.push(data[j].id);
						}
					}
		}
	
		var ids = powerArr.join(",");
		var rid = selectRoles[0].roleId;
		var params = {
				'ids':ids,
				'role.roleId':rid
		};
		
		$.post("${path}/role!grantRole.action",params,function(data){
			
			if(data == 'yes'){
				$("#grantRoleWin").dialog("close");
				$.messager.show({
				title:'提示',
				msg:'授权成功.',
				timeout:5000,
				showType:'slide'
				});
				
			}else{
				
				$.messager.show({
				title:'提示',
				msg:'授权失败.',
				timeout:5000,
				showType:'slide'
				});
			}
			
		})
			
			
			
		},"json");	
		
		
		
	
	
}
//重置权限
function resetPower(){
	var selectRows =  $("#roleTable").datagrid("getSelections");
		
		$.post("${path}/power!findAllPower.action?role.roleId="+selectRows[0].roleId,function(data){
			var powers = [];
			for(var i in data){
				
				if(data[i]._parentId == 0){
					powers.push(data[i]);
				}
			}
			
			for(var j in powers){
				
				var power = powers[j]; 
				recursion(power,data);
			}
			$('#powertree').tree("loadData",powers);
			
		},"json");
		
}
function grantRole(){
	
	var selectRows =  $("#roleTable").datagrid("getSelections");
	if(selectRows.length ==1){
			
		$('#grantRoleWin').dialog("open");
		
		$.post("${path}/power!findAllPower.action?role.roleId="+selectRows[0].roleId,function(data){
			var powers = [];
			for(var i in data){
				
				if(data[i]._parentId == 0){
					powers.push(data[i]);
				}
			}
			
			for(var j in powers){
				
				var power = powers[j]; 
				recursion(power,data);
			}
			$('#powertree').tree("loadData",powers);
			
		},"json");
		
		
	}else{
		$.messager.show({
			title:'提示',
			msg:'请选择一行数据.',
			timeout:5000,
			showType:'slide'
		});
	}
	
	
}

function  recursion(parent,data){
	parent.attributes = {};
    parent.attributes.pid = parent._parentId;
	if(!parent.url){
		parent.children = [];
		for(var k in data){
			if(parent.id == data[k]._parentId){
				parent.children.push(data[k]);
				recursion(data[k],data);
			}
		}
		
	 }
	
	
}
//继续更改，角色的权限的中间表还需要删除
function deleteRole(){
	var selectRows =  $("#roleTable").datagrid("getSelections");
	if(selectRows.length >=1){
		
		$.messager.confirm('提示', '你确定要删除这些角色吗?', function(r){
			
			if (r){
				$.messager.progress();
				var ids = "";
				if(selectRows.length == 1){
					ids = selectRows[0].roleId; 
				}else{
					var arrs = [] ;
					for(var i in selectRows){
						arrs.push(selectRows[i].roleId);
					}
					ids = arrs.join(",");
				}
				
				$.post("${path}/role!deleteRole.action?ids="+ids,function(data){
					
					if(data == "yes"){
						$.messager.show({
							title:'提示',
							msg:'删除成功.',
							timeout:5000,
							showType:'slide'
						});
						$('#roleTable').datagrid('reload');
					}
					if(data == "no"){
						$.messager.show({
							title:'提示',
							msg:'删除失败.',
							timeout:5000,
							showType:'slide'
						});
					}
					if(data == "noyes"){
						$.messager.show({
							title:'提示',
							msg:'有角色关联着用户，暂不能删除.',
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
function roleHelp(){
	
	$.messager.alert('HELP','Help...!','info');
}

function showAddRoleWin(){
	$("#addRoleWin").dialog("open");
}
function showUpdateRoleWin(){
	
	var selectRows =  $("#roleTable").datagrid("getSelections");
	if(selectRows.length == 1){
		$("#updateRoleWin").dialog("open");
		$("#updaterid").val(selectRows[0].roleId);
		$("#updatername").val(selectRows[0].roleName);
	}else{
		$.messager.show({
			title:'提示',
			msg:'请选择一行数据.',
			timeout:5000,
			showType:'slide'
		});
	}
	
}
function submitAddRoleForm(){
	
	$.messager.progress();	// 显示一个进度条 
	$('#addRoleForm').form('submit', {
		url: "${path}/role!addRole.action",
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
				$("#addRoleWin").dialog("close");
				$('#roleTable').datagrid('reload');    // reload the current page data 
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
function submitUpdateRoleForm(){
	
	$.messager.progress();	// 显示一个进度条 
	$('#updateRoleForm').form('submit', {
		url: "${path}/role!updateRole.action",
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
					msg:'编辑成功.',
					timeout:5000,
					showType:'slide'
				});
				$("#updateRoleWin").dialog("close");
				$('#roleTable').datagrid('reload');    // reload the current page data 
			}else{
				$.messager.show({
					title:'提示',
					msg:'编辑失败.',
					timeout:5000,
					showType:'slide'
				});
			}
			
			$.messager.progress('close');	// 当成功提交之后隐藏进度条
		}
	});

}

