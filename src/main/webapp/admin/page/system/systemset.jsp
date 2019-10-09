<?xml version="1.0" encoding="UTF-8" ?>
<%
	String path = request.getContextPath();
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.aaa.yf.util.ConfigUtil" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
 <link rel="stylesheet" href="<%=path %>/easyui/themes/default/easyui.css" type="text/css"></link>
  <link rel="stylesheet" href="<%=path %>/easyui/themes/icon.css" type="text/css"></link>
  <script type="text/javascript" src="<%=path %>/easyui/jquery.min.js"></script>
  <script type="text/javascript" src="<%=path %>/easyui/jquery.easyui.min.js"></script>
  <script type="text/javascript" src="<%=path %>/easyui/locale/easyui-lang-zh_CN.js"></script>




<title>Insert title here</title>
</head>
<body>
<%
		String encoding=ConfigUtil.encoding;
		String saveDir=ConfigUtil.saveDir;
		//String waterMarkImg=ConfigUtil.waterMarkImg;
		String click=ConfigUtil.click;
		String clickCycle=ConfigUtil.clickCycle;
		String clickClear=ConfigUtil.clickClear;
		String logClear=ConfigUtil.logClear;
		String dataBackUp=ConfigUtil.dataBackUp;
		
		
		// 获取jdbc信息
		String jdbcPath = application.getRealPath("/install/config/jdbc.properties");
		String host = ConfigUtil.readValue(jdbcPath, "jdbc.host");
		String port	= ConfigUtil.readValue(jdbcPath, "jdbc.port");
		String userName = ConfigUtil.readValue(jdbcPath, "jdbc.username");
		String password = ConfigUtil.readValue(jdbcPath, "jdbc.password");
		String dbName = ConfigUtil.readValue(jdbcPath, "jdbc.dbName");

%>

<center>
<br/>
		<table border="1" width=80%" height="100%" cellspacing="0" cellpadding="0" style="font-size: 12px">
	 		<tr>
	 			<td colspan="2" height="40" align="center"><font size="5" >系统设置(清空则使用默认值)</font></td>
	 		</tr>
	 		<tr>
	 			<td colspan="2"><font color="red">系统参数设置</font></td>
	 		</tr>
	 		<tr>
	 			<td style="background-color: #E0ECFF"> 字符集:</td>
	 			<td><input class="easyui-validatebox" type="text" id="encoding" value="<%=encoding%>" style="width: 200px" data-options="required:true" ></td>
	 		</tr>
	 		<tr>
	 			<td style="background-color: #E0ECFF"> 图片上传路径:</td>
	 			<td><input type="text" class="easyui-validatebox" id="saveDir" value="<%=saveDir %>" style="width: 200px" data-options="required:true" ></td>
	 		</tr>
	 		<!--
	 		 <tr>
	 			<td style="background-color: #E0ECFF">水印图片路径:</td>
	 			<td> <input type="text" class="easyui-validatebox" id="waterMarkImg" value="" style="width: 200px" data-options="required:true" ></td>
	 		</tr>
	 		 -->
	 		<tr>
	 			<td style="background-color: #E0ECFF"> 点击量文件路径:</td>
	 			<td><input type="text" class="easyui-validatebox" id="clickDir" value="<%=click %>" style="width: 200px" data-options="required:true" ></td>
	 		</tr>
	 		<tr>
	 			<td colspan="2"><font color="red">jdbc参数设置</font></td>
	 		</tr>
	 		<tr>
	 			<td style="background-color: #E0ECFF"> 主机:</td>
	 			<td><input class="easyui-validatebox" type="text" id="host" value="<%=host%>" style="width: 200px" data-options="required:true" ></td>
	 		</tr>
	 		<tr>
	 			<td style="background-color: #E0ECFF"> 端口号:</td>
	 			<td><input class="easyui-validatebox" type="text" id="port" value="<%=port%>" style="width: 200px" data-options="required:true" ></td>
	 		</tr>
	 		<tr>
	 			<td style="background-color: #E0ECFF"> 用户名:</td>
	 			<td><input class="easyui-validatebox" type="text" id="userName" value="<%=userName%>" style="width: 200px" data-options="required:true" ></td>
	 		</tr>
	 		<tr>
	 			<td style="background-color: #E0ECFF"> 密码:</td>
	 			<td><input class="easyui-validatebox" type="text" id="password" value="<%=password%>" style="width: 200px" data-options="required:true" ></td>
	 		</tr>
	 		<tr>
	 			<td style="background-color: #E0ECFF"> 数据库名:</td>
	 			<td><input class="easyui-validatebox" type="text" id="dbName" value="<%=dbName%>" style="width: 200px" data-options="required:true" ></td>
	 		</tr>
	 		
	 		
	 		<tr>
	 			<td colspan="2"><font color="red">系统定时任务设置</font></td>
	 		</tr>
	 		<tr>
	 			<td style="background-color: #E0ECFF">点击量持久化周期:</td>
	 			<td>
	 				cron:<label id="clickCycle" style="color: blue;border: 1px"><%=clickCycle %></label>每<input type="text" id="t1" onblur="check('1')">
	 				<select id="s1">
	 						<option value="M" selected="selected">月</option>
	 						<option value="d">日</option>
	 						<option value="h">时</option>
	 						<option value="m">分</option>
	 						<option value="s">秒</option>
	 				</select>
	 			</td>
	 		</tr>
	 		<tr>
	 			<td style="background-color: #E0ECFF">点击量清空:</td>
	 			<td>cron:<label id="clickClear" style="color: blue;border: 1px"><%=clickClear %></label>
	 			每<input type="text" id="t2" onBlur="check('2')">
	 			<select id="s2">
	 						<option value="M" selected="selected">月</option>
	 						<option value="d">日</option>
	 						<option value="h">时</option>
	 						<option value="m">分</option>
	 						<option value="s">秒</option>
	 				</select>
	 			</td>
	 		</tr>
	 		<tr>
	 			<td style="background-color: #E0ECFF">日志清理:</td>
	 			<td>cron:<label id="logClear" style="color: blue;border: 1px"><%=logClear %></label>
	 			每<input type="text" id="t3" onBlur="check('3')">
	 			<select id="s3">
	 						<option value="M" selected="selected">月</option>
	 						<option value="d">日</option>
	 						<option value="h">时</option>
	 						<option value="m">分</option>
	 						<option value="s">秒</option>
	 				</select>
	 			</td>
	 		</tr>
	 		<tr>
	 			<td style="background-color: #E0ECFF">数据库备份:</td>
	 			<td>cron:<label id="dataBackUp" style="color: blue;border: 1px"><%=dataBackUp %></label>
	 			每<input type="text" id="t4" onBlur="check('4')">
	 			<select id="s4">
	 						<option value="M" selected="selected">月</option>
	 						<option value="d">日</option>
	 						<option value="h">时</option>
	 						<option value="m">分</option>
	 						<option value="s">秒</option>
	 				</select>
	 			</td>
	 		</tr>
	 		<tr>
	 			<td colspan="2">
	 			<input type="button" value="保存设置" onclick="submit()">
	 			</td>
	 		</tr>
	 </table>

</center>
<script type="text/javascript">
	function submit(){
		
		if(check('1')&&check('2')&&check('3')&&check('4')){
			var encoding=$("#encoding").val().trim();
			var saveDir=$("#saveDir").val().trim();
			//var waterMarkImg=$("#waterMarkImg").val().trim();
			var clickDir=$("#clickDir").val().trim();
			var host=$("#host").val().trim();
			var port=$("#port").val().trim();
			var userName=$("#userName").val().trim();
			var password=$("#password").val().trim();
			var dbName=$("#dbName").val().trim();
			var clickCycle=format('1');
			var clickClear=format('2');
			var logClear=format('3');
			var dataBackUp=format('4');
			$.post("${path}/system!updateSystem.action",{
				"system.encoding":encoding,
				"system.saveDir":saveDir,
				//"system.waterMarkImg":waterMarkImg,
				"system.clickDir":clickDir,
				"system.clickCycle":clickCycle,
				"system.clickClear":clickClear,
				"system.logClear":logClear,
				"system.dataBackUp":dataBackUp,
				"system.host":host,
				"system.port":port,
				"system.userName":userName,
				"system.password":password,
				"system.dbName":dbName
			},function(data){
				if(data=="true"){
					$.messager.show({
						title:'提示',
						msg:'保存成功，重启服务器后生效.',
						timeout:5000,
						showType:'slide'
					});
				}else{
					$.messager.show({
						title:'提示',
						msg:'保存失败.',
						timeout:5000,
						showType:'slide'
					});
				}
			});
		}
	}
	
	
	function format(v){
		var n=$("#t"+v).val().trim();
		if(n==""){
			return "";
		}
		var s=$("#s"+v).val();
		if(s=="M"){
			return "0 * * * */"+n+" ?";
		}else if(s=="d"){
			return "0 * * */"+n+" * ?";
		}else if(s=="h"){
			return "0 * */"+n+" * * ?";
		}else if(s=="m"){
			return "0 */"+n+" * * * ?";
		}else{
			return "0/"+n+" * * * * ?";
		}
	}
	
	function check(v){
		var s=$("#s"+v).val();
		var n=$("#t"+v).val().trim();
		if(n!=""){
			if(n>='0'&&n<='9'){
				n=parseInt(n);
				if(s=="M"){
					if(n>12||n<1){
						$.messager.alert("提示","只能输入1-12之间的数");
						return false;
					}
				}else if(s=="d"){
					if(n>31||n<1){
						$.messager.alert("提示","只能输入1-31之间的数");
						return false;
					}
				}else if(s=="h"){
					if(n>24||n<1){
						$.messager.alert("提示","只能输入1-24之间的数");
						return false;
					}
				}else if(s=="m"){
					if(n>60||n<1){
						$.messager.alert("提示","只能输入1-60之间的数");
						return false;
					}
				}else{
					if(n>60||n<1){
						$.messager.alert("提示","只能输入1-60之间的数");
						return false;
					}
				}
				return true;
			}else{
				$.messager.alert('提示','只能输入数字...');
				return false;
			}
		}else{
			return true;
		}
	}
</script>

</body>
</html>