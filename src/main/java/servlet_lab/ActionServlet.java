package servlet_lab;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeeDAO;
import entity.Employee;


public class ActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void service(
			HttpServletRequest request, 
			HttpServletResponse response) 
		throws ServletException, IOException {
		
			request.setCharacterEncoding("UTF-8");
			String uri=request.getRequestURI();
			String action=uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));
			System.out.println("action:"+action);
			if("/list".equals(action)) {
				//查询出所有员工的信息
				EmployeeDAO dao=new EmployeeDAO();
				try {
					List<Employee> employees=dao.findAll();
					//依据查询到的员工信息，输出表格
					//因为servlet不方便输出，所以转发给listEmp2.jsp来生成表格
					
					//step1.将数据绑定到request对象上
					request.setAttribute("employees", employees);
					//step2.获得转发器
					RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/empList.jsp");
					//step3.转发
					rd.forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
					request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
					
				}
			}else if("/add".equals(action)) {
				//读取员工信息
				String name=request.getParameter("name");
				String age=request.getParameter("age");
				String salary=request.getParameter("salary");
				System.out.println(name+age+salary);
				//将员工信息插入到数据库
				EmployeeDAO dao=new EmployeeDAO();
				Employee emp=new Employee();
				emp.setName(name);
				emp.setAge(Integer.parseInt(age));
				emp.setSalary(Double.parseDouble(salary));
				try {
					dao.save(emp);
					//重定向到员工列表
					response.sendRedirect("list.do");
				} catch (Exception e) {
					e.printStackTrace();
					request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
					
				}
			}else if("/del".equals(action)) {
				//读取要删除的员工的id
				String id=request.getParameter("id");
				//删除员工
				EmployeeDAO dao=new EmployeeDAO();
				try {
					dao.delete(Integer.parseInt(id));
					//重定向到员工列表
					response.sendRedirect("list.do");
				} catch (Exception e) {
					e.printStackTrace();
					request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
				}
			}else if("/load".equals(action)) {
				//读取要修改的员工的id
				String id=request.getParameter("id");
				//依据id查询员工信息
				EmployeeDAO dao=new EmployeeDAO();
				try {
					Employee emp=dao.findById(Integer.parseInt(id));
					//依据查询到的员工信息，生成修改页面
					//step1.将数据绑定到request对象上
					request.setAttribute("emp", emp);
					//step2.获得转发器
					RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/updateEmp.jsp");
					//step3.转发
					rd.forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
					request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
				}
			}else if("/modify".equals(action)) {
				//获取员工信息r(String.p
				String id=request.getParameter("id");
				String name=request.getParameter("name");
				String age=request.getParameter("age");
				String salary=request.getParameter("salary");
				EmployeeDAO dao=new EmployeeDAO();
				Employee emp=new Employee();
				emp.setId(Integer.parseInt(id));
				emp.setName(name);
				emp.setAge(Integer.parseInt(age));
				emp.setSalary(Double.parseDouble(salary));
				try {
					dao.modify(emp);
					response.sendRedirect("list.do");
				} catch (Exception e) {
					request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
				}
			}else if("/toAdd".equals(action)) {
				request.getRequestDispatcher("/WEB-INF/addEmp.jsp").forward(request, response);
			}
	}

}
