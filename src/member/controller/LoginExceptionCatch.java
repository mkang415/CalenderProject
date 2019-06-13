package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 로그인이 필요한 서비스를 비로그인 회원이 접근할 시 로그인 필요하다고 알려주는 서블릿

@WebServlet("/denied")
public class LoginExceptionCatch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/WEB-INF/views/member/denied.jsp").forward(req, resp);
	
	}

}
