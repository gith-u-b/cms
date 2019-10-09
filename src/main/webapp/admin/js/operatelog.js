$(function(){
	$(".datebox :text").attr("readonly","readonly");//日期控件禁止输入
});
function searchLog(){
	var param={
			"col.cmsUser.userName":$("#userName").val(),
			begin:$("#startDate").datebox('getValue'),
			end:$("#endDate").datebox('getValue'),
			"col.logUrl":$("#logUrl").val(),
			"col.logMethode":$("#logMethode").val()
	};
	$('#tabs').datagrid('load',param);
}
function clearCondition(){
	$("#userName").val("");
	$("#startDate").datebox('setValue','');
	$("#endDate").datebox('setValue','');
	$("#logUrl").val("");
	$('#logMethode').val("");
}
function deleteLog(){
	
	var selectRows = $("#tabs").datagrid("getSelections");
	if(selectRows.length >= 1){
			var ids;
			if(selectRows.length == 1){
				ids = selectRows[0].logId;
			}else{
				var idarr = [];
				for(var i in selectRows){
					idarr.push(selectRows[i].logId);
				}
				ids = idarr.join(",");
			}
			
			$.post("${path}/log!deleteOperateLog.action?ids="+ids,function(data){
				
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