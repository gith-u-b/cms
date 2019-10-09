<?xml version="1.0" encoding="UTF-8" ?>
<%
	String path = request.getContextPath();
%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>Insert title here</title>


<!-- 切记顺序导入样式表 -->
<script type="text/javascript" src="<%=path %>/jquery/jquery.js"></script>
<script type="text/javascript" src="<%=path %>/jquery/jquery.flot.js"></script>
<script type="text/javascript" src="<%=path %>/jquery/jquery.flot.navigate.js"></script>
<!-- 以上是 报表所需样式-->
 <link rel="stylesheet" href="<%=path %>/easyui/themes/default/easyui.css" type="text/css"></link>
  <link rel="stylesheet" href="<%=path %>/easyui/themes/icon.css" type="text/css"></link>
  <script type="text/javascript" src="<%=path %>/easyui/jquery.easyui.min.js"></script>
  <script type="text/javascript" src="<%=path %>/easyui/locale/easyui-lang-zh_CN.js"></script>
  
</head>
<body>

<center>
<h2>会员注册量统计<font color="#ccc" size="3">（x轴表示时间，y轴表示数量）</font></h2>
<div>

选择年份：<select id="year" onchange="chooseYear(this.value)">
	<option value="1">默认</option>
	<%
		for(int i = 1980; i<3000 ; i++){
			%>
				<option value="<%=i %>"><%=i %></option>
			<%
		}
	%>
</select>

<input type="radio" name="times" value="m" checked="checked" />月统计
<input type="radio" name="times" value="w" />周统计(0-6代表周一到周日))
<input type="radio" name="times" value="d" />日统计(1日-31日)

<a id="btnBar" href="javascript:barChart()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">条形统计图</a> 
<a id="btnLine" href="javascript:lineChart()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">折线统计图</a>
</div>
<div id="placeholder" style=" width:1000px;height:350px"></div>

</center>
<script type="text/javascript">

function chooseYear(value){     //如果选中为全部，则设置其他单选条件为不可用
	var radios = document.getElementsByName("times");
	if(value == "1"){
		for(var i in radios){
			radios[i].disabled = true;
		}
	}else{
		for(var i in radios){
			radios[i].disabled = false;
		}
	}
}
		var optionsBar = {       //条形报表
	       bars: { show: true },
	       zoom:{interactive:true},
	       pan:{interactive:true}
	       
	    };
		var optionsLine = {     //折线报表
			points: { show: true },
			lines: { show: true },
			zoom:{interactive:true},
			pan:{interactive:true}
		};


$(function(){    //当页面打开。查看的是全部也即是默认（按年分统计）报表
	var radios = document.getElementsByName("times");
	if(document.getElementById("year").value == "1"){
		for(var i in radios){
			radios[i].disabled = true;
		}
	}
	
    var placeholder = $("#placeholder");
    
    $.ajax({
        url: "<%=path%>/user!registerCountChart.action?way=no&year=1",
        method: 'GET',
        dataType: 'json',
        success: onDataReceived
    });
    function onDataReceived(data){
    	$.plot(placeholder,[data],optionsBar);
    }
    
    
});

//time 代表选中年份 选中全部则是所有年份 进行统计 则不能进行月周日日统计 
//way  前提是选中了年份，则选择这一年中的月周日进行统计
function barChart(){    //选择这条行报表
	var time = document.getElementById("year").value;
	var way = "no";
	var radios = document.getElementsByName("times");
	for(var i in radios){
		if(radios[i].checked == true){
			way = radios[i].value;
		}
	}
	$.ajax({
        url: "<%=path%>/user!registerCountChart.action?way="+way+"&year="+time,
        method: 'GET',
        dataType: 'json',
        success: onDataReceived1
    });
}


function lineChart(){   //选择折线报表
	var time = document.getElementById("year").value;
	var way = "no";
	var radios = document.getElementsByName("times");
	for(var i in radios){
		if(radios[i].checked == true){
			way = radios[i].value;
		}
	}
	$.ajax({
        url: "<%=path%>/user!registerCountChart.action?way="+way+"&year="+time,
        method: 'GET',
        dataType: 'json',
        success: onDataReceived2
    });
}



function onDataReceived1(data){
	$.plot(placeholder,[data],optionsBar);
	
}
function onDataReceived2(data){
	$.plot(placeholder,[data],optionsLine);
}


</script>


</body>
</html>