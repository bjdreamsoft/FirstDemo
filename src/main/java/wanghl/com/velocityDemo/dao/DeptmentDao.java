package wanghl.com.velocityDemo.dao;

import java.util.List;

import wanghl.com.velocityDemo.bean.Department;

public interface DeptmentDao {

	/**
	 * 获取department标签全部数据
	 * @return 封装Department类型集合
	 */
	List<Department> getAllDepartment();
	
}
