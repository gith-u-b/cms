function submitUpdatePowerForm(){
	$.messager.progress();	// 显示一个进度条 
	$('#updatePowerForm').form('submit', {
		url: "${path}/power!updatePower.action",
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
					msg:'修改成功.',
					timeout:5000,
					showType:'slide'
				});
				$('#powerTable').treegrid('reload');
				$("#updatePowerWin").dialog("close");
			}else{
				$.messager.show({
					title:'提示',
					msg:'修改失败.',
					timeout:5000,
					showType:'slide'
				});
			}
			$.messager.progress('close');	// 当成功提交之后隐藏进度条
			
		}
	});

	
}

function submitAddPowerForm(){
	$.messager.progress();	// 显示一个进度条 
	$('#addPowerForm').form('submit', {
		url: "${path}/power!addPower.action",
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
				$('#powerTable').treegrid('reload');
				$("#addPowerWin").dialog("close");
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

function  recursion(parent,data){
	if(!parent.url){
		parent.children = [];
		for(var k in data){
			if(parent.id == data[k]._parentId){
				parent.children.push(data[k]);
				recursion(data[k],data);
			}
		}
		
	 }else{
		 parent.attributes = {};
		 parent.attributes.url = parent.url;
	 }
	
}

function updatePower(){
	var selectRows =  $("#powerTable").treegrid("getSelections");
	if(selectRows.length == 1){
		
		$("#upid").val(selectRows[0].id);
		$("#upname").val(selectRows[0].text);
		$("#upicon").val(selectRows[0].iconCls);
		$("#upurl").val(selectRows[0].url);
		$('#upstate').combobox('setValue', selectRows[0].state);
		$("#updatePowerWin").dialog("open");
		var powers = [];
		$.post("${path}/power!findAllPowers.action",function(data){
			for(var i in data){
				if(data[i]._parentId == 0){
					powers.push(data[i]);
				}
			}
			for(var j in powers){
				var parent = powers[j];
				recursion(parent,data);
			}
			
			$('#comboxtree1').combotree('loadData',powers);
			$('#comboxtree1').combotree('setValue', selectRows[0]._parentId);
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

function powerHelp(){
	$.messager.alert('HELP','Help...!','info');
}

function deletePower(){
	var selectRows =  $("#powerTable").treegrid("getSelections");
	if(selectRows.length == 1){
		$.messager.confirm('提示', '你确定要删除这些（这个）权限吗?', function(r){
		if (r){
			var id = selectRows[0].id;
			$.post("${path}/power!deletePower.action?power.id="+id,function(data){
				if(data == "yes"){
					$.messager.show({
						title:'提示',
						msg:'删除成功.',
						timeout:5000,
						showType:'slide'
					});
					$('#powerTable').treegrid('reload');
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

function addPower(){
	
	$("#addPowerWin").dialog("open");
	var powers = [];
	$.post("${path}/power!findAllPowers.action",function(data){
		for(var i in data){
			if(data[i]._parentId == 0){
				powers.push(data[i]);
			}
		}
		for(var j in powers){
			var parent = powers[j];
			recursion(parent,data);
		}
		
		$('#comboxtree').combotree('loadData',powers);
		$('#comboxtree').combotree('setValue', 0);
	},"json");
	
	
}