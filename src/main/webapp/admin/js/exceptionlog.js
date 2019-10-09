$(function(){
	$(".datebox :text").attr("readonly","readonly");//日期控件禁止输入
});
function clearCondition(){
	$("#startDate").datebox('setValue','');
	$("#endDate").datebox('setValue','');
	$("#logSite").val("");
	$('#logMsg').val("");
}
function searchLog(){
	var param={
			begin:$("#startDate").datebox('getValue'),
			end:$("#endDate").datebox('getValue'),
			"cel.logSite":$("#logSite").val(),
			"cel.logMsg":$("#logMsg").val()
	};
	$('#tabs').datagrid('reload',param);
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
			
			$.post("${path}/log!deleteExceptionLog.action?ids="+ids,function(data){
				
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