package com.aaa.test;


import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.aaa.yf.service.ICmsUserService;



public class Test {

	ApplicationContext ac = null;
	
	@Before
	public void before(){
		ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	@org.junit.Test
	public void test03(){
		ICmsUserService cuservice =(ICmsUserService) ac.getBean("cuservice");
		
		
	}

}

