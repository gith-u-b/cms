package com.aaa.yf.action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {

	private static Logger logger = Logger.getLogger("SYSTEM");
	
	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		BaseAction.logger = logger;
	}

	public HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
	}
	
	public HttpServletResponse getResponse(){
		return ServletActionContext.getResponse();
	}
	
	public HttpSession getSession(){
		return ServletActionContext.getRequest().getSession();
	}
	
	public ServletContext getApplication(){
		return ServletActionContext.getServletContext();
	}
	
	public void printJSON(String str) throws Exception{
		
		getResponse().setCharacterEncoding("utf-8");
		PrintWriter out = getResponse().getWriter();
		out.print(str);
		out.flush();
		out.close();
		
	}
	
	
}
