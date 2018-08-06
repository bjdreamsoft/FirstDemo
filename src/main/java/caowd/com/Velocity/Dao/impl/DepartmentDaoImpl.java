package caowd.com.Velocity.Dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import caowd.com.Velocity.Dao.DeptmentDao;
import wanghl.com.velocityDemo.bean.Department;

public class DepartmentDaoImpl implements DeptmentDao{

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
			
			Department dt = null;
			
			while(rs.next()) {
				
				dt = new Department();
				
				dt.setDeptId(rs.getInt("DEPT_ID"));
				dt.setDeptName(rs.getString("DEPT_NAME"));
				dt.setDeptNo(rs.getString("DEPT_NO"));
				dt.setLocation(rs.getString("LOCATION"));
				
				list.add(dt);
			}
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			if(conn!=null) {
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}
		
		return list;
	}

}
