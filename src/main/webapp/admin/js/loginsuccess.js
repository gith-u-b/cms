function findLoginLog(){
	var param={
			begin:$("#sdate").datebox('getValue'),
			end:$("#edate").datebox('getValue'),
			ip:$("#ip").val(),
			"cls.user.userName":$("#tjName").val()
	};
	$('#tabs').datagrid('reload',param);
}
function cleanCondition(){
	$("#tjName").val("");
	$("#ip").val("");
	$("#sdate").datebox('setValue', '');
	$("#edate").datebox('setValue', '');
}
function deleteLoginLog(){
	var selectRows = $("#tabs").datagrid("getSelections");
	if(selectRows.length >= 1){
			var ids;
			if(selectRows.length == 1){
				ids = selectRows[0].id;
			}else{
				var idarr = [];
				for(var i in selectRows){
					idarr.push(selectRows[i].id);
				}
				ids = idarr.join(",");
			}
			
			$.post("${path}/login-log!deleteLoginSucLog.action?ids="+ids,function(data){
				
				if(data == "yes"){
					$.messager.show({
						title:'提示',
						msg:'删除成功.',
						timeout:5000,
						showType:'slide'
					});
					$('#tabs').datagrid('load');
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


$(function(){
    
	$(".datebox :text").attr("readonly","readonly");  //日期控件禁止 输入
	 
});
$('#table').datagrid({
	toolbar: "#toolbar"
});

//信息
function format(value,row,index){
	return "<font color='blue'>登陆成功</font>";
}


//用户名
function format3(value,row,index){
	if(row.user){
		return "<font color='red'>"+row.user.userName+"</font>";
	}
	
}


