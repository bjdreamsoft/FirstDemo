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
		
		//����ģ�崦������
		VelocityEngine engine = new VelocityEngine();
		
		Properties prop = new Properties();
		
		//����velocity��Դ���ط�ʽΪclass
		prop.setProperty("resource.loader", "class");
        //����velocity��Դ���ط�ʽΪfileʱ�Ĵ�����
		prop.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
	
		//ģ�������ʼ��
		engine.init(prop);
		
		//��ģ�������л�ȡģ�壬��ָ�������ʽ����ֹ���룩
		Template template = engine.getTemplate("demo1/wanghl/department.vm", "UTF-8");
		
		//����ģ�����ݵ������Ļ���
		VelocityContext vcontext = new VelocityContext();
		
		//��ȡ����
		List<Department> list = departDao.getAllDepartment();
		
		//�������Ļ����м�������
		vcontext.put("list", list);
		
		//����������writer����
		StringWriter sw = new StringWriter();
		
		//����ƴװ
		template.merge(vcontext, sw);
		
		return sw.toString();
		
	}

}
