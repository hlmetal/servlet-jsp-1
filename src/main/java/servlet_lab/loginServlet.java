package servlet_lab;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import entity.User;

public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(
			HttpServletRequest request, 
			HttpServletResponse response) 
		throws ServletException, IOException {
		//�������Ĳ���ֵ
		request.setCharacterEncoding("UTF-8");
		//���������Դ·��
		String uri=request.getRequestURI();
		//����������Դ·��
		String action=uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		System.out.println("action:"+action);
		if("/login".equals(action)) {
			//��ȡ�û���������
			String username=request.getParameter("username");
			String pwd=request.getParameter("pwd");
			System.out.println("username:"+username+"pwd:"+pwd);
			//��ѯ���ݿ�
			UserDAO dao=new UserDAO();
			try {
				User user=dao.findByUsername(username);
				if(user!=null&&user.getPassword().equals(pwd)) {
					//��½�ɹ�
					HttpSession session=request.getSession();
					session.setAttribute("user", user);
					
					response.sendRedirect("empList.jsp");
				}else {
					//��¼ʧ��
					request.setAttribute("login_failed","�û������������");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
			
			
		}
	}

}
