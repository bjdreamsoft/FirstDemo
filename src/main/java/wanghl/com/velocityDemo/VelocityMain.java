package wanghl.com.velocityDemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import wanghl.com.velocityDemo.service.VelocityService;

public class VelocityMain {
	
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:demo1/wanghl/spring-jdbc.xml");
		
		VelocityService service = (VelocityService) context.getBean("velocityService");
		
		String result = service.assemblyData();
		
		System.out.println(result);
		
	}

}
