package wanghl.com.velocityDemo.service.impl;

import java.io.StringWriter;
import java.util.List;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import wanghl.com.velocityDemo.bean.Department;
import wanghl.com.velocityDemo.dao.DeptmentDao;
import wanghl.com.velocityDemo.service.VelocityService;

public class VelocityServiceImpl implements VelocityService {

	private DeptmentDao departDao;
	
	public void setDepartDao(DeptmentDao departDao) {
		this.departDao = departDao;
	}

	@Override
	public String assemblyData() {
		
		//定义模板处理引擎
		VelocityEngine engine = new VelocityEngine();
		
		Properties prop = new Properties();
		
		//设置velocity资源加载方式为class
		prop.setProperty("resource.loader", "class");
        //设置velocity资源加载方式为file时的处理类
		prop.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
	
		//模板引擎初始化
		engine.init(prop);
		
		//从模板引擎中获取模板，并指定编码格式（防止乱码）
		Template template = engine.getTemplate("demo1/wanghl/department.vm", "UTF-8");
		
		//设置模板内容的上下文环境
		VelocityContext vcontext = new VelocityContext();
		
		//获取数据
		List<Department> list = departDao.getAllDepartment();
		
		//向上下文环境中加入数据
		vcontext.put("list", list);
		
		//定义结果接受writer对象
		StringWriter sw = new StringWriter();
		
		//数据拼装
		template.merge(vcontext, sw);
		
		return sw.toString();
		
	}

}
