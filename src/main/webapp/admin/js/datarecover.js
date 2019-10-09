//取消
function cancelBackUpCheck(){
	$('#showbackupwindow').dialog('close');
}
//恢复服务器中的备份文件
function doRecover1(){
	var sqlfile = $("#datafile1").val();
	if(sqlfile == ""){
		$.messager.show({
			title:'温馨提示',
			msg:'请点击选择文件选择要恢复的文件.',
			timeout:3000,
			showType:'slide'
		});
	}else{
		var dataBaseNames = $("#table").datagrid("getSelections");
		if (dataBaseNames.length == 0){
			$.messager.show({
				title:'温馨提示',
				msg:'请至少选择一个数据库.',
				timeout:3000,
				showType:'slide'
			});
		}else{
			var dbnames;
			if(dataBaseNames == 1){
				dbnames = dataBaseNames[0].dataBaseName;
			}else{
				var arrdbname = [];
				for(var i in dataBaseNames ){
					arrdbname.push(dataBaseNames[i].dataBaseName);
				}
				dbnames = arrdbname.join(",");
			}
			
			$.post("${path}/data!dataRecover1.action?dbnames="+dbnames+"&sqlfileFileName="+sqlfile,function(data){
				
				if(data == "yes"){
					$.messager.show({
						title:'温馨提示',
						msg:'还原成功.',
						timeout:3000,
						showType:'slide'
					});
				}else{
					$.messager.show({
						title:'温馨提示',
						msg:'还原失败.',
						timeout:3000,
						showType:'slide'
					});
				}
				
			});
			
		}
		
		
	}
	
}
//选中服务器中的备份文件
function checkBackUpFile(){
	var fileNames = $("#filetable").treegrid("getSelections"); 
	if (fileNames.length==0){
		$.messager.show({
			title:'温馨提示',
			msg:'请选择要恢复的文件.',
			timeout:3000,
			showType:'slide'
		});
	}else{
		if (fileNames.length>1){
			$.messager.show({
				title:'温馨提示',
				msg:'一次只能选择一个文件.',
				timeout:3000,
				showType:'slide'
			});
		}else{
			if (fileNames[0].path.lastIndexOf(".sql")==-1){
				$.messager.show({
					title:'温馨提示',
					msg:'请选择.sql文件.',
					timeout:3000,
					showType:'slide'
				});
			}else{
				$('#showbackupwindow').dialog('close');
				$("#datafile1").val(fileNames[0].text);
			}	
		}
	}
}
//选择文件
function showBackupWindow(){
	$("#filetable").treegrid("load");
	$('#showbackupwindow').dialog('open');  
}

//恢复文件（本地文件）
function doRecover(){
	var sqlfile = $("#datafile").val();
	
	if(sqlfile != ""){
		if(sqlfile.substring(sqlfile.lastIndexOf(".")+1,sqlfile.length) != "sql"){
			$.messager.show({
				title:'温馨提示',
				msg:'请选择.sql文件.',
				timeout:3000,
				showType:'slide'
			});
		}else{
			

			var dataBaseNames = $("#table").datagrid("getSelections");
			
			if (dataBaseNames.length == 0){
				$.messager.show({
					title:'温馨提示',
					msg:'请至少选择一个数据库.',
					timeout:3000,
					showType:'slide'
				});
			}else{
				var dbnames;
				if(dataBaseNames == 1){
					dbnames = dataBaseNames[0].dataBaseName;
				}else{
					var arrdbname = [];
					for(var i in dataBaseNames ){
						arrdbname.push(dataBaseNames[i].dataBaseName);
					}
					dbnames = arrdbname.join(",");
				}
				
				
				$.messager.progress();	// 显示一个进度条 
				$('#uploadfile').form('submit', {
					url: "${path}/data!dataRecover.action?dbnames="+dbnames,
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
								title:'温馨提示',
								msg:'还原成功.',
								timeout:3000,
								showType:'slide'
							});
						}else{
							$.messager.show({
								title:'温馨提示',
								msg:'还原失败.',
								timeout:3000,
								showType:'slide'
							});
						}
						$.messager.progress('close');	// 当成功提交之后隐藏进度条
					}
				});
				
			}
		}
	}else{
		$.messager.show({
			title:'温馨提示',
			msg:'请点击浏览选择本地sql文件.',
			timeout:3000,
			showType:'slide'
		});
	}
	
}