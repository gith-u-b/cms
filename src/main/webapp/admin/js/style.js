function openStyleTable(){  //展开树
	//$('#table').treegrid('expandAll');  先注释掉，文件比较多，读取太慢了
}
function resourceHelp(){
	$.messager.alert('HELP','Help...','info');
}

//打开上传窗体
function openWindowForUpload(){
	var row=$("#table").treegrid("getSelected");
	if(row){
		var n=row.text.indexOf(".");
		if(n!=-1){
			$.messager.alert('提示','请选择文件夹...');
		}else{
			$("#windowformforadd").dialog("open");
		}
		
	}else{
		$.messager.show({
			title:'提示',
			msg:'请选择父节点',
			timeout:3000,
			showType:'slide'
		});
	}
}

//上传
function doUpload(){
	var row=$("#table").treegrid("getSelected");
	$("#form").form("submit",{
		url:'resource!uploadStyle.action?ppath='+row.path+"/",
		success:function(data){
			if(data=='yes'){
				$.messager.show({
					title:'提示',
					msg:'上传成功',
					timeout:3000,
					showType:'slide'
				});
				$("#windowformforadd").dialog("close");
		 		$("#table").treegrid("reload");
			}
			else{
				$.messager.show({
					title:'提示',
					msg:'上传失败',
					timeout:3000,
					showType:'slide'
				});
				$("#windowformforadd").window("close");
			}
		},
		onSubmit:function(){
			var up=$("#up").val();
			if(up==""){
				$.messager.alert("提示","请选择要上传的文件(或者zip压缩文件).....");
				return false;
			}else{
				return true;
			}
		}
		
	});
	
}

//删除

function deleteResource(){
	var row=$("#table").treegrid("getSelected");
	if(row.text=="news"){
		$.messager.alert("错误","不能对此进行操作.....");
	}else{
		$.messager.confirm('确认','确定要进行删除？(如果删除，部分网页将无法正常显示)',function(r){    
		    if (r){    
		    	$.post(
						"${path}/resource!deleteStyle.action",
						{
							"ppath":row.path
						},
						function(data){
							if(data=="true"){
								$.messager.show({
									title:'提示',
									msg:'删除成功',
									timeout:3000,
									showType:'slide'
								});
								$("#table").treegrid("reload");
							}else{
								$.messager.show({
									title:'提示',
									msg:'删除失败',
									timeout:3000,
									showType:'slide'
								});
							}
						}
						
					);   
		    }    
		});  
	}
}

//打开编辑窗体
function openWindowForUpdate(){
	var row=$("#table").treegrid("getSelected");
	if(row){
		
		if(row.text=="news"){
			$.messager.alert("错误","不能不能对此进行操作.....");
		}else{
			var d=row.text.lastIndexOf(".");
			if(d>0){
				$("#resourceName").val(row.text.substr(0,d));
			}else{
				$("#resourceName").val(row.text);
			}
			$("#hiddenRes").val(row.path);
			$("#windowformforupdate").dialog("open");
		}
		
	}else{
		$.messager.alert('提示','请选择要编辑行...'); 
	}
}

//编辑
function doUpdate(){
	var row=$("#table").treegrid("getSelected");
	var rname=$("#resourceName").val();
	var d=row.text.lastIndexOf(".");
	if(d>0){
		rname+=row.text.substring(d);
	}
	var path=row.path.substring(0,row.path.length-row.text.length)+rname;
	$("#updateForm").form("submit",{
		url:'${path}/resource!updateStyle.action?ppath='+path,
		success:function(data){
			if(data=='true'){
				$.messager.show({
					title:'提示',
					msg:'编辑成功',
					timeout:3000,
					showType:'slide'
				});
				$("#windowformforupdate").dialog("close");
		 		$("#table").treegrid("reload");
			}
			else{
				$.messager.show({
					title:'提示',
					msg:'编辑失败',
					timeout:3000,
					showType:'slide'
				});
				$("#windowformforupdate").window("close");
			}
		
		},
		onSubmit:function(){
			var name=$("#resourceName").val();
			if(name==""){
				$.messager.alert('提示','文件或文件夹名不能为空...'); 
				return false;
			}else{
				return true;
			}
		}
		
	});
}