package wanghl.com.velocityDemo.dao;

import java.util.List;

import wanghl.com.velocityDemo.bean.Department;

public interface DeptmentDao {

	/**
	 * ��ȡdepartment��ǩȫ������
	 * @return ��װDepartment���ͼ���
	 */
	List<Department> getAllDepartment();
	
}
