package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Member;
import member.service.face.MemberService;
import member.service.impl.MemberServiceImpl;

// 로그인 서블릿

@WebServlet("/login")
public class MemberLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MemberService memberservice = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/WEB-INF/views/member/login_test.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		PrintWriter out = resp.getWriter();
		
		Member loginmember = new Member();
		
		if(memberservice.login(memberservice.getLoginMember(req))==true) {
			loginmember = memberservice.getMemberByUserid(memberservice.getLoginMember(req));
			
			session.setAttribute("login", true);
			session.setAttribute("userid", loginmember.getUserid());
			session.setAttribute("nickname", loginmember.getNickname());
			session.setAttribute("grade", loginmember.getGrade());
			session.setMaxInactiveInterval(0);
		
			resp.sendRedirect("/main");
		
			return;
			
		} else {
			req.setCharacterEncoding("utf-8");
			out.print("잘못된 id/pw");
		}
		
	}
	
}
