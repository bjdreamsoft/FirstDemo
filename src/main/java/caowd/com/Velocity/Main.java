package caowd.com.Velocity;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import caowd.com.Velocity.service.VelocityService;

public class Main {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:demo1/caowd/spring-jdbc.xml");
		
		VelocityService service = (VelocityService) context.getBean("velocityService");
		
		String result = service.writeOutData();
		
		System.out.println(result);
	}
}
