package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// 로그아웃 서블릿

@WebServlet("/logout")
public class MemberLogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		if((boolean)session.getAttribute("login") == true) {
		
			session.setAttribute("login", false);
			session.removeAttribute("userid");
			session.removeAttribute("nickname");
			
			resp.sendRedirect("/main");

		} else {
			resp.sendRedirect("/denied");
		}
	}
	
}
