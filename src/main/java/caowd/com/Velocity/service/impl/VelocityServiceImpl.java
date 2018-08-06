package caowd.com.Velocity.service.impl;

import java.io.StringWriter;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import caowd.com.Velocity.Dao.DeptmentDao;
import caowd.com.Velocity.service.VelocityService;
import wanghl.com.velocityDemo.bean.Department;

public class VelocityServiceImpl implements VelocityService{

	private DeptmentDao deptmentDao;
	
	public void setDepartDao(DeptmentDao deptmentDao) {
		this.deptmentDao = deptmentDao;
	}
	
	@Override
	public String writeOutData() {
		VelocityEngine ve = new VelocityEngine();

		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");

		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

		ve.init();

		Template t = ve.getTemplate("demo1/caowd/department.vm");
		
		VelocityContext ctx = new VelocityContext();

		List<Department> list = deptmentDao.getAllDepartment();
		
		ctx.put("list", list);

		StringWriter sw = new StringWriter();

		t.merge(ctx, sw);

		System.out.println(sw.toString());

		return sw.toString();
	}

	
}
