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
		//处理中文参数值
		request.setCharacterEncoding("UTF-8");
		//获得请求资源路径
		String uri=request.getRequestURI();
		//分析请求资源路径
		String action=uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		System.out.println("action:"+action);
		if("/login".equals(action)) {
			//读取用户名和密码
			String username=request.getParameter("username");
			String pwd=request.getParameter("pwd");
			System.out.println("username:"+username+"pwd:"+pwd);
			//查询数据库
			UserDAO dao=new UserDAO();
			try {
				User user=dao.findByUsername(username);
				if(user!=null&&user.getPassword().equals(pwd)) {
					//登陆成功
					HttpSession session=request.getSession();
					session.setAttribute("user", user);
					
					response.sendRedirect("empList.jsp");
				}else {
					//登录失败
					request.setAttribute("login_failed","用户名或密码错误");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
			
			
		}
	}

}
