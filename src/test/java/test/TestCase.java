package test;

import java.sql.Connection;
import java.util.List;

import org.junit.Test;

import Util.DBUtil;
import dao.EmployeeDAO;
import entity.Employee;

public class TestCase {
	@Test
	//≤‚ ‘ DBUtilπ§æﬂ¿‡
	public void test1() throws Exception {
		Connection conn=DBUtil.getConnection();
		System.out.println(conn);
	}
	@Test
	//≤‚ ‘EmployeeDAO
	public void test2() {
		EmployeeDAO dao=new EmployeeDAO();
		Employee emp=new Employee();
		emp.setName("DMETAL");
		emp.setAge(21);
		emp.setSalary(8000);
		dao.save(emp);
	}
	@Test
	//≤‚ ‘ EmployeeDAO findAll()
	public void test3() {
		EmployeeDAO dao=new EmployeeDAO();
		List<Employee> employees=dao.findAll();
		System.out.println(employees);
	}
	@Test
	//≤‚ ‘ EmployeeDAO delete()
	public void test4() {
		EmployeeDAO dao=new EmployeeDAO();
		dao.delete(3);
	}
	@Test
	//≤‚ ‘ EmployeeDAO findById()
	public void test5() {
		EmployeeDAO dao=new EmployeeDAO();
		Employee emp=dao.findById(5);
		System.out.println(emp);
	}
	@Test
	//≤‚ ‘ EmployeeDAO modify()
	public void test6() {
		EmployeeDAO dao=new EmployeeDAO();
		Employee emp=dao.findById(5);
		emp.setName("bbb");
		emp.setAge(30);
		emp.setSalary(6500);
		dao.modify(emp);
	}
}
