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
				//��ѯ������Ա������Ϣ
				EmployeeDAO dao=new EmployeeDAO();
				try {
					List<Employee> employees=dao.findAll();
					//���ݲ�ѯ����Ա����Ϣ��������
					//��Ϊservlet���������������ת����listEmp2.jsp�����ɱ��
					
					//step1.�����ݰ󶨵�request������
					request.setAttribute("employees", employees);
					//step2.���ת����
					RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/empList.jsp");
					//step3.ת��
					rd.forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
					request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
					
				}
			}else if("/add".equals(action)) {
				//��ȡԱ����Ϣ
				String name=request.getParameter("name");
				String age=request.getParameter("age");
				String salary=request.getParameter("salary");
				System.out.println(name+age+salary);
				//��Ա����Ϣ���뵽���ݿ�
				EmployeeDAO dao=new EmployeeDAO();
				Employee emp=new Employee();
				emp.setName(name);
				emp.setAge(Integer.parseInt(age));
				emp.setSalary(Double.parseDouble(salary));
				try {
					dao.save(emp);
					//�ض���Ա���б�
					response.sendRedirect("list.do");
				} catch (Exception e) {
					e.printStackTrace();
					request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
					
				}
			}else if("/del".equals(action)) {
				//��ȡҪɾ����Ա����id
				String id=request.getParameter("id");
				//ɾ��Ա��
				EmployeeDAO dao=new EmployeeDAO();
				try {
					dao.delete(Integer.parseInt(id));
					//�ض���Ա���б�
					response.sendRedirect("list.do");
				} catch (Exception e) {
					e.printStackTrace();
					request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
				}
			}else if("/load".equals(action)) {
				//��ȡҪ�޸ĵ�Ա����id
				String id=request.getParameter("id");
				//����id��ѯԱ����Ϣ
				EmployeeDAO dao=new EmployeeDAO();
				try {
					Employee emp=dao.findById(Integer.parseInt(id));
					//���ݲ�ѯ����Ա����Ϣ�������޸�ҳ��
					//step1.�����ݰ󶨵�request������
					request.setAttribute("emp", emp);
					//step2.���ת����
					RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/updateEmp.jsp");
					//step3.ת��
					rd.forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
					request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
				}
			}else if("/modify".equals(action)) {
				//��ȡԱ����Ϣr(String.p
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
