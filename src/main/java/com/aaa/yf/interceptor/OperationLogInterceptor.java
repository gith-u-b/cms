package com.aaa.yf.interceptor;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.apache.struts2.ServletActionContext;

import com.aaa.yf.entity.CmsUser;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class OperationLogInterceptor extends MethodFilterInterceptor {
	private static Logger logger = Logger.getLogger("OPERATION");
	
	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {
		
		if(ServletActionContext.getRequest().getSession().getAttribute("user") != null){
				CmsUser user = (CmsUser)ServletActionContext.getRequest().getSession().getAttribute("user");
				MDC.put("uid", user.getUserId());
				//action的完整类名
				String actionName = arg0.getAction().getClass().getName();
				//action的方法名。
				String methodName = arg0.getProxy().getMethod();
				//log4j.properties中插入数据库
				   
				MDC.put("url", actionName);
				MDC.put("methodName", methodName);
				logger.info("操作日志记录");  //执行把操作记录插入数据库
		}
		
		
		return arg0.invoke();	
		
	}

	
}
