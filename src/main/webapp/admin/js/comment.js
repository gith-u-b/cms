function replyComment(){
	var selectRows = $("#table").datagrid("getSelections");
	if(selectRows[0].isChecked == 1){
		var content = $("#reply").val();
		if(content.trim() != ""){
			var isRecommend;
			var radios2 = document.getElementsByName("comment.isRecommend");  //得到是否推荐的单选
			for(var i in radios2){
				if(radios2[i].checked = true){
					isRecommend = radios2[i].value;
				}
			}
			var params = {
				"comment.isRecommend":isRecommend,
				"comment.comId":selectRows[0].comId,
				"comment.replyContent":$("#reply").val()
			};
			$.post("${path}/comment!replyComment.action",params,function(data){
				if(data == "yes"){
					$.messager.show({
						title:'提示',
						msg:'回复成功.',
						timeout:5000,
						showType:'slide'
					});
					$("#windowReply").dialog("close");
					$('#table').datagrid('load');
				}else{
					$.messager.show({
						title:'提示',
						msg:'回复失败.',
						timeout:5000,
						showType:'slide'
					});
				}
			});
			
		}else{
			$.messager.show({
				title:'提示',
				msg:'请输入回复内容.',
				timeout:5000,
				showType:'slide'
			});
		}
	}else{
		$.messager.show({
			title:'提示',
			msg:'请先审核.',
			timeout:5000,
			showType:'slide'
		});
	}
}
/*
 * 回复窗口
 */
function replyCommentWin(){
	var selectRows = $("#table").datagrid("getSelections");
	if(selectRows.length == 1){
		$("#windowReply").dialog("open");
		$("#title").html(selectRows[0].cmsContent.title);
		$("#user").html(selectRows[0].userName);
		$("#timeLabel").html(selectRows[0].createTime);
		$("#content").html(selectRows[0].content);
		var radios2 = document.getElementsByName("comment.isRecommend");  //得到是否推荐的单选
		
		
		for(var i in radios2){
			if(selectRows[0].isRecommend == radios2[i].value){
				radios2[i].checked = true;
			}
		}
		if(selectRows[0].replyContent != "" && selectRows[0].replyContent != null){
			$("#reply").val(selectRows[0].replyContent);
			document.getElementById("reply").disabled="disabled";
			document.getElementById("btnReply").style.display = "none";
		}else{
			document.getElementById("reply").disabled="";
			document.getElementById("btnReply").style.display = "inline";
			document.getElementById("reply").value="";
		}
		
	}else{
		$.messager.show({
			title:'提示',
			msg:'请选择一行数据.',
			timeout:5000,
			showType:'slide'
		});
	}
}


/*
 * 已经评论不能再评论
 */

//推荐
function recommend(){
	var selectRows = $("#table").datagrid("getSelections");
	if(selectRows.length >= 1){
			var ids;
			if(selectRows.length == 1){
				ids = selectRows[0].comId;
			}else{
				var idarr = [];
				for(var i in selectRows){
					idarr.push(selectRows[i].comId);
				}
				ids = idarr.join(",");
			}
			
			$.post("${path}/comment!recommendComment.action?ids="+ids,function(data){
				
				if(data == "yes"){
					$.messager.show({
						title:'提示',
						msg:'推荐成功.',
						timeout:5000,
						showType:'slide'
					});
					$('#table').datagrid('load');
				}else{
					$.messager.show({
						title:'提示',
						msg:'推荐失败.',
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
//删除评论
function deleteComment(){
	var selectRows = $("#table").datagrid("getSelections");
	if(selectRows.length >= 1){
			var ids;
			if(selectRows.length == 1){
				ids = selectRows[0].comId;
			}else{
				var idarr = [];
				for(var i in selectRows){
					idarr.push(selectRows[i].comId);
				}
				ids = idarr.join(",");
			}
			
			$.post("${path}/comment!deleteComment.action?ids="+ids,function(data){
				
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

//状态
function format2(value,row,index){
	if(row.isChecked==1){
		return "<font color='blue'>已审核</font>";
	}else{
		return "<font color='red'>未审核</font>";
	}
}

function checkComment(){
	var selectRows = $("#table").datagrid("getSelections");
	if(selectRows.length >= 1){
			var ids;
			if(selectRows.length == 1){
				ids = selectRows[0].comId;
			}else{
				var idarr = [];
				for(var i in selectRows){
					idarr.push(selectRows[i].comId);
				}
				ids = idarr.join(",");
			}
			
			$.post("${path}/comment!checkComment.action?ids="+ids,function(data){
				
				if(data == "yes"){
					$.messager.show({
						title:'提示',
						msg:'审核成功.',
						timeout:5000,
						showType:'slide'
					});
					$('#table').datagrid('load');
				}else{
					$.messager.show({
						title:'提示',
						msg:'审核失败.',
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
//评论用户
function format3(value,row,index){
	return row.userName;
}

//评论内容
function format4(value,row,index){
	if(value.indexOf("<script>")>=0||value.indexOf("<\/script>")>=0){
		return "<font color='red'>含有非法字符</font>";
	}else{
		return value;
	}
}

