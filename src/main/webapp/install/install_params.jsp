<%@ page contentType="text/html; charset=gbk" language="java"%>
<%
	String path=request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>ϵͳ��������--JEECMS��װ��</title>
<link href="img/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function formSubmit() {
	if(document.getElementById('dbPassword').value==''){
		if(!confirm("��û����д���ݿ����룬��ȷ�����ݿ�����Ϊ����")) {
			return false;
		}
	}
	document.getElementById('beforeSubmit').style.display = "none";
	document.getElementById('afterSubmit').style.display = "";
}
</script>
</head>

<body>
<form action="<%=path %>/install/install_setup.jsp" method="get" onsubmit="return formSubmit();">
<table width="900" align="center" style="border:#106DBA 1px solid; margin-top:30px;">
  <tr>
    <td bgcolor="#D1E9FA"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="27%" height="60" rowspan="2" align="center"><img src="img/logo.gif" border="0"/></td>
        <td width="73%" height="30" class="f14b">2��ϵͳ��������<span style="color:#FF0000">������Ҫ��jdk1.5�����ϡ�tomcat5.5�����ϡ�mysql5.0�����ϣ�</span></td>
      </tr>
      <tr>
        <td height="20" valign="top">&nbsp;&nbsp;&nbsp;&nbsp;������ϵͳ��ز���</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="400" align="center" bgcolor="#F0F8FD"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td width="30%" height="30" align="right">���ݿ�������</td>
        <td width="22%" align="left"><input name="dbHost" type="text" class="input" id="dbHost" value="127.0.0.1" /></td>
        <td align="left">���ݿ��ip��ַ������Ǳ�������Ķ�</td>
      </tr>      <tr>
        <td width="30%" height="30" align="right">���ݿ�˿ںţ�</td>
        <td width="22%" align="left"><input name="dbPort" type="text" class="input" id="dbPort" value="3306" /></td>
        <td align="left">���ݿ�Ķ˿ںţ�һ������Ķ�</td>
      </tr>
      <tr>
        <td height="30" align="right">���ݿ����ƣ�</td>
        <td align="left"><input name="dbName" type="text" class="input" id="dbName" value="mycms" /></td>
				<td align="left">&nbsp;</td>
      </tr>
      <tr>
        <td height="30" align="right">���ݿ��û���</td>
        <td align="left"><input name="dbUser" type="text" class="input" id="dbUser" value="root" /></td>
				<td align="left">&nbsp;</td>
      </tr>
      <tr>
        <td height="30" align="right">���ݿ����룺</td>
        <td align="left"><input name="dbPassword" type="password" class="input" id="dbPassword" /></td>
		<td align="left">��װ���ݿ�ʱ���������</td>
      </tr>
      <tr>
        <td height="30" align="right">�Ƿ񴴽����ݿ⣺</td>
        <td align="left">
			<input type="radio" name="isCreateDb" value="true" checked="checked"/>��
			<input type="radio" name="isCreateDb" value="false" />��
		</td>
		<td align="left">������Լ��ֹ����������ݿ⣬��ѡ��</td>
      </tr>
      <tr>
        <td height="30" align="right">�Ƿ񴴽���</td>
        <td align="left">
				<input type="radio" name="isCreateTable" value="true" checked="checked"/>��
				<input type="radio" name="isCreateTable" value="false" />��
				</td>
				<td align="left">������Լ��ֹ������˱���ѡ��</td>
      </tr>
      <tr>
        <td height="30" align="right">�Ƿ��ʼ�����ݣ�</td>
        <td align="left">
				<input type="radio" name="isInitData" value="true" checked="checked"/>��
				<input type="radio" name="isInitData" value="false" />��
				</td>
				<td align="left">������Լ��ֹ���ʼ�������ݣ���ѡ��</td>
      </tr>
      <tr>
        <td height="30" align="right">������</td>
        <td align="left"><input name="domain" type="text" class="input" value="<%=request.getServerName()%>"/></td>
				<td align="left">ϵͳ�Ѿ�������������������Ķ�</td>
      </tr>
      <tr>
        <td height="30" align="right">����·����</td>
        <td align="left"><input name="cxtPath" type="text" class="input" value="<%=request.getContextPath()%>"/></td>
				<td align="left">ϵͳ�Ѿ��������Ĳ���·��������Ķ�</td>
      </tr>
      <tr>
        <td height="30" align="right">�˿ںţ�</td>
        <td align="left"><input name="port" type="text" class="input" value="<%=request.getServerPort()%>"/></td>
				<td align="left">ϵͳ�Ѿ��������Ķ˿ںţ�����Ķ�</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="30" align="center" bgcolor="#D1E9FA">
		<span id="beforeSubmit"><input type="submit" class="btn" value=" �� �� " />
		</span>
		<span id="afterSubmit" style="display:none;color:red;">��װ��Ҫʮ�����ʱ�䣬�������ĵȴ�...</span>
	</td>
  </tr>
</table>
</form>
</body>
</html>
