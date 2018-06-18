package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Util.DBUtil;
import entity.Employee;

public class EmployeeDAO {
	//修改
	public void modify(Employee emp) {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="UPDATE t_emp "+
					   "SET name=?,age=?,salary=? "+
					   "WHERE id=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(4, emp.getId());
			ps.setString(1, emp.getName());
			ps.setInt(2, emp.getAge());
			ps.setDouble(3, emp.getSalary());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		}
	}
	/*
	 *通过id查找员工信息
	 *
	 * @param id
	 */
	public Employee findById(int id) {
		Employee emp=null;
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT id,name,age,salary "+
					   "FROM t_emp "+
					   "WHERE id=? ";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				emp=new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setAge(rs.getInt("age"));
				emp.setSalary(rs.getDouble("salary"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		}
		return emp;
	}
	//删除
	/*
	 * 依据id删除员工
	 */
	public void delete(int id) {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="DELETE "+
						"FROM t_emp "+
						"WHERE id=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		}
	}
	//查找所有
	public List<Employee> findAll(){
		List<Employee> employees=new ArrayList<Employee>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT id,name,age,salary "+
					   "FROM t_emp ";
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Employee emp=new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setAge(rs.getInt("age"));
				emp.setSalary(rs.getDouble("salary"));
				employees.add(emp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return employees;
	}
	//添加
	public void save(Employee emp){
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="INSERT INTO t_emp "+
					   "(id,name,age,salary) "+
					   "VALUES "+
					   "(t_emp_seq.NEXTVAL,?,?,?) ";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, emp.getName());
			ps.setInt(2, emp.getAge());
			ps.setDouble(3, emp.getSalary());
			ps.executeUpdate();
		} catch (Exception e) {
			//记日志
			e.printStackTrace();
			/*
			 * 看异常能否恢复，如果不能恢复(比如数据库服务暂停，网络中断等
			 * 								 这些异常一般称之为系统异常)
			 * 则提示用户稍后重试。
			 */
			//将异常抛给调用者
			throw new RuntimeException(e);
		}finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		}
	}
}
