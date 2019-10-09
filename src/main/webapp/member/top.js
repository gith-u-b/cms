var html="游客您好，您还没有登录哦！ <a href='member/register.jsp'>免费注册</a>|";
html+="<a href='member/login.jsp'>登录</a>";
$(
		function(){
			$("#logincontent").html(html);
		}
);