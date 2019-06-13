package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.face.MemberService;
import member.service.impl.MemberServiceImpl;

// 비밀번호 변경 서블릿

@WebServlet("/mypage/pwupdate")
public class PwUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MemberService memberservice = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/WEB-INF/views/member/pwUpdate.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if(memberservice.pwUpdate(req)==true) {
			req.getRequestDispatcher("/WEB-INF/views/member/pwUpdateSuccess.jsp").forward(req, resp);			
		} else {
			req.getRequestDispatcher("/WEB-INF/views/member/pwUpdateFailed.jsp").forward(req, resp);						
		}
		
	}
	
}
