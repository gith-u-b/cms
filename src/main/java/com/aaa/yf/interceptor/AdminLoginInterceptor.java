package com.aaa.yf.interceptor;


import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class AdminLoginInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {
		//验证管理员是否登陆，否则跳转到登陆页面
		if(ServletActionContext.getRequest().getSession().getAttribute("user") == null){
			return "logout";
		}else{
			return arg0.invoke();
		}
		
	}

}
