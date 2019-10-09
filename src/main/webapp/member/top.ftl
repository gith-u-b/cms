
var html;

	<#if user??>
		html="欢迎您: ${user.userName} &nbsp; ${user.userName} &nbsp;";
		html+="<a href='#'>退出登录</a>";
	<#else>	
		 html="游客您好，您还没有登录哦！ <a href='member/register.jsp'>免费注册</a>|";
		 html+="<a href='member/login.jsp'>登录</a>";
	</#if>
	
	
$(
		function(){
			$("#logincontent").html(html);
		}
);