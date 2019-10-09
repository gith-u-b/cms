function validateNull(row){  //当某个父节点的子节点为空时设置其展不开
	if(row.children.length == 0){
		return false;
	}
}
//编辑文件
function doEdit(){
	var id=$("#bjId").val();
	var bjName=$("#bjName").val();
	var content=editor.html();
	if(bjName!=""){
		$.post(
				"${path}/template!doUpdateText.action",
				{
					"template.templateId":id,
					"template.templateName":bjName,
					"text":content
				},
				function(data){
					if(data=="true"){
						$.messager.show({
							title:'温馨提示',
							msg:'修改成功',
							timeout:3000,
							showType:'slide'
						});
						$("#windowBJ").dialog("close");
						$("#table").treegrid("reload");
					}else{
						$.messager.show({
							title:'温馨提示',
							msg:'修改失败',
							timeout:3000,
							showType:'slide'
						});
					}
				}
				
			);
		
	}else{
		$.messager.show({
			title:'提示',
			msg:'请输入文件名.',
			timeout:3000,
			showType:'slide'
		});
	}
	
	
}


//打开编辑或重命名窗口
function openWindow(){
	var row=$("#table").treegrid("getSelected");
	if(row){
		if(row.text.indexOf(".")>0){
			loadEdit();//加载编辑框中的数据
			$("#windowBJ").dialog("open");
		}else{
			$("#cmmName").val(row.text);
			$("#windowCMM").dialog("open");
		}
	}else{
		$.messager.show({
			title:'提示',
			msg:'请选择一行数据.',
			timeout:3000,
			showType:'slide'
		});
	}
}
//加载要编辑的数据
function loadEdit(){
	var row=$("#table").treegrid("getSelected");
	var id=row.id;
	$.post(
			"${path}/template!getTemplateText.action",
			{
				"template.templateId":id
			},
			function(data){
				editor.html(data);
				$("#bjName").val(row.text);
				$("#bjId").val(row.id);
			}
			
	);
	
}

//重命名
function doReName(){
	var row=$("#table").treegrid("getSelected");
	var id=row.id;
	var mlName=$("#cmmName").val();
	$.post(
			"${path}/template!updateTemplateName.action",
			{
				"template.templateId":id,
				"mlName":mlName
			},
			function(data){
				if(data=="true"){
					$.messager.show({
						title:'温馨提示',
						msg:'修改成功',
						timeout:3000,
						showType:'slide'
					});
					$("#table").treegrid("reload");
				}else{
					$.messager.show({
						title:'温馨提示',
						msg:'修改失败',
						timeout:3000,
						showType:'slide'
					});
				}
			}
			
		);
	$("#windowCMM").dialog("close");
}


//执行添加
function doAdd(){
	var row=$("#table").treegrid("getSelected");
	var type=$("#cc2").combobox("getValue");
	if(type==""){
		$.messager.alert("提示","请选择模板类型!");
		return;
	}
	var id=row.id;
	var tjName=$("#tjName").val();
	var content=editor2.html();
	if(tjName!=""&&content!=""){
		$.post(
				"${path}/template!addTemplate.action",
				{
					"template.templateId":id,
					"template.templateName":tjName,
					"text":content,
					"template.cmsTemplateType.typeId":type
				},
				function(data){
					if(data=="true"){
						$.messager.show({
							title:'温馨提示',
							msg:'添加成功',
							timeout:3000,
							showType:'slide'
						});
						$("#table").treegrid("reload");
					}else{
						$.messager.show({
							title:'温馨提示',
							msg:'添加失败',
							timeout:3000,
							showType:'slide'
						});
					}
				}
				
			);
		$("#windowTJ").dialog("close");
	}else{
		$.messager.show({
			title:'提示',
			msg:'输入文件名以及模板内容.',
			timeout:3000,
			showType:'slide'
		});
	}
	
}

//打开添加窗体
function openWindowForAdd(){
	var row=$("#table").treegrid("getSelected");
	if(row){
		if(row.text.indexOf(".")==-1){
			$("#windowTJ").dialog("open");
		}else{
			$.messager.alert('温馨提示','请选择文件夹...'); 
		}
	}else{
		$.messager.show({
			title:'提示',
			msg:'请选择一行数据',
			timeout:3000,
			showType:'slide'
		});
	}
}
//进行删除操作
function doDelete(){
	var row=$("#table").treegrid("getSelected");
	if(row){
		var id=row.id;
		$.messager.confirm('确认','确定要进行删除？(如果删除，部分功能将无法使用)',function(r){
			if(r){
				$.post(
						"${path}/template!deleteTemplate.action",
						{
							"template.templateId":id
						},
						function(data){
							if(data=="true"){
								$.messager.show({
									title:'温馨提示',
									msg:'删除成功',
									timeout:3000,
									showType:'slide'
								});
								$("#table").treegrid("reload");
							}else if(data=="false"){
								$.messager.show({
									title:'温馨提示',
									msg:'删除失败',
									timeout:3000,
									showType:'slide'
								});
							}else{
								$.messager.alert("温馨提示","该模板已被使用，禁止删除！");
							}
						}
						
					);	
			}
		});
		
	}else{
		$.messager.alert('温馨提示','请选择要操作的行...'); 
	}
}

//新建目录
function doXjml(){
	if($("#mlName").validatebox("isValid")&&$('#c1').combotree('getValue')!=""){//验证通过
		var mlName=$("#mlName").val();
		var id=$('#c1').combotree('getValue');
		$.post(
				"${path}/template!createTemplateDir.action",
				{
					"template.templateId":id,
					"mlName":mlName
				},
				function(data){
					if(data=="true"){
						$.messager.show({
							title:'温馨提示',
							msg:'创建成功',
							timeout:3000,
							showType:'slide'
						});
						$("#table").treegrid("reload");
					}else{
						$.messager.show({
							title:'温馨提示',
							msg:'创建失败',
							timeout:3000,
							showType:'slide'
						});
					}
				}
				
			);
		$("#windowXJML").dialog("close");
	}
}


//打开新建目录窗体
function openWindowForXJML(){
	//初始化窗体数据
	$('#c1').combotree({    
	    url: '${path}/template!findAllTemplate.action',
	    onLoadSuccess:function(){
	    	var t = $('#c1').combotree('tree');	// 获取树对象
	    	t.tree('expandAll');	
	    },
	    onSelect:function(){
	    	var t = $('#c1').combotree('tree');	// 获取树对象
	    	var n= t.tree('getSelected');
	    	if(n.text.indexOf(".")>0){
	    		$('#c1').combotree('clear');
	    	}
	    },
	    onBeforeExpand:function(n){
	    	var t = $('#c1').combotree('tree');	// 获取树对象
	    	var d= t.tree('getChildren',n.target);
	    	if(d==0){
	    		return false;
	    	}else{
	    		return true;
	    	}
			
		 }
	});   
	$("#windowXJML").dialog("open");

}



//打开上传模板窗体
function openWindowForUpload(){
	var row=$("#table").treegrid("getSelected");
	
	if(row != null){
		if(row.text.indexOf(".")>0){
			$.messager.alert('温馨提示','请选择文件夹...');
		}else{
			$("#windowSCMB").dialog("open");
		}
	}else{
		$.messager.show({
			title:'提示',
			msg:'请选择父节点.',
			timeout:3000,
			showType:'slide'
		});
	}
}
//上传模板
function doUpload(){
	var row=$("#table").treegrid("getSelected");
	$("#scmbForm").form("submit",{
		url:'${path}/template!uploadTemplate.action?template.templateId='+row.id,
		success:function(data){
			if(data=='true'){
				$.messager.show({
					title:'提示',
					msg:'上传成功',
					timeout:3000,
					showType:'slide'
				});
				$("#windowSCMB").window("close");
		 		$("#table").treegrid("reload");
			}
			else{
				$.messager.show({
					title:'提示',
					msg:'上传失败',
					timeout:3000,
					showType:'slide'
				});
				$("#windowSCMB").window("close");
			}
		
		},
		onSubmit:function(){
			var up=$("#mb").val();
			if(up==""){
				$.messager.alert("温馨提示","请选择要上传的模板.....");
				return false;
			}else{
				return true;
			}
		}
		
	});
}





function zk(){   //加载成功后展开树
	$("#table").treegrid("expandAll");
}
//格式化路径
function format1(value,row,index){
	if (row.attributes.path){
		return row.attributes.path;
	} else {
		return "";
	}
}

//日期
function format2(value,row,index){
	if (row.attributes.createtime){
		return row.attributes.createtime;
	} else {
		return value;
	}
}

//上传者
function format3(value,row,index){
	if (row.attributes.creator){
		return row.attributes.creator;
	} else {
		return "未知";
	}
}