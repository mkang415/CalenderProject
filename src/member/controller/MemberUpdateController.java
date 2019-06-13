package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Member;
import member.service.face.MemberService;
import member.service.impl.MemberServiceImpl;

// 회원정보수정 서블릿

@WebServlet("/mypage/update")
public class MemberUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MemberService memberservice = new MemberServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		if((boolean)session.getAttribute("login")==false) {
			req.getRequestDispatcher("/denied").forward(req, resp);
			return;
		}
		
		Member member = memberservice.select(req);
		
		req.setAttribute("member", member);
		
		req.getRequestDispatcher("/WEB-INF/views/member/infoUpdate.jsp").forward(req, resp);

	}
		
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		memberservice.update(req);
		
		resp.sendRedirect("/mypage/update");
		
		
	}
	
}
