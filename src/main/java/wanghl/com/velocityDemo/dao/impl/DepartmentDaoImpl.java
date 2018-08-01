package wanghl.com.velocityDemo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import wanghl.com.velocityDemo.bean.Department;
import wanghl.com.velocityDemo.dao.DeptmentDao;

public class DepartmentDaoImpl implements DeptmentDao {
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<Department> getAllDepartment() {
		
		List<Department> list = new ArrayList<Department>();
		
		Connection conn = null;
		
		String sql = "SELECT * FROM DEPARTMENT";
		
		try {
			
			conn = dataSource.getConnection();
			
			PreparedStatement prep = conn.prepareStatement(sql);
			
			ResultSet rs = prep.executeQuery();
			
			Department depart = null;
			
			while(rs.next()) {
				
				depart = new Department();
				
				depart.setDeptId(rs.getInt("DEPT_ID"));
				depart.setDeptName(rs.getString("DEPT_NAME"));
				depart.setDeptNo(rs.getString("DEPT_NO"));
				depart.setLocation(rs.getString("LOCATION"));
				
				list.add(depart);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return list;
	}

}
