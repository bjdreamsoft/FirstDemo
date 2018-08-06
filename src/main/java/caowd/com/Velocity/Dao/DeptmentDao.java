package caowd.com.Velocity.Dao;

import java.util.List;

import wanghl.com.velocityDemo.bean.Department;

public interface DeptmentDao {

	//通过数据库查询所有数据 
	List<Department> getAllDepartment();
}
