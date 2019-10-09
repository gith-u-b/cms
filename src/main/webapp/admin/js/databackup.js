//一键备份所有
function allBackup(){
	$.post("${path}/data!dataTableBackUp.action",function(data){
		if (data=="true"){
			$.messager.show({
				title:'温馨提示',
				msg:'备份成功',
				timeout:3000,
				showType:'slide'
			});
		}else{
			$.messager.show({
				title:'温馨提示',
				msg:'备份失败',
				timeout:3000,
				showType:'slide'
			});
		}
		$("#table").datagrid('load'); 
	});
}

//备份数据库表操作
function backup(){
	
	var rows=$("#table").datagrid("getSelections");
	if (rows.length==0){
		$.messager.show({
			title:'提示',
			msg:'请选择要备份的数据库表.',
			timeout:3000,
			showType:'slide'
		});
	}else{
		
		var tableNames="";
		for(var i in rows){
			tableNames+=rows[i].tableName+" ";
		}
		$.post("${path}/data!dataTableBackUp.action?tableNames="+tableNames,function(data){
			if (data=="true"){
				$.messager.show({
					title:'温馨提示',
					msg:'备份成功',
					timeout:3000,
					showType:'slide'
				});
			}else{
				$.messager.show({
					title:'温馨提示',
					msg:'备份失败',
					timeout:3000,
					showType:'slide'
				});
			}
			$("#table").datagrid('load'); 
		});
	}
}


//查看某张表的详细信息
function queryColum(){
	var rows=$("#table").datagrid("getSelections");
	if (rows.length==0){
		$.messager.show({
			title:'提示',
			msg:'请选择一行数据.',
			timeout:5000,
			showType:'slide'
		});
	}else{
		if (rows.length>1){
			$.messager.show({
				title:'提示',
				msg:'一次只能查询一张表.',
				timeout:5000,
				showType:'slide'
			});
			
		}else{
			window.location="tableinfo.jsp?tableName="+rows[0].tableName;
		}
	}
}