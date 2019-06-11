package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Member;
import member.service.face.MemberService;
import member.service.impl.MemberServiceImpl;

@WebServlet("/mypage/update")
public class MemberUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MemberService memberservice = new MemberServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Member member = memberservice.select(req);
		
		req.setAttribute("member", member);
		
		req.getRequestDispatcher("/WEB-INF/views/member/infoUpdate.jsp").forward(req, resp);
		
	}
		
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		memberservice.update(req);
		
		req.getRequestDispatcher("/WEB-INF/views/member/infoUpdate").forward(req, resp);
		
		
	}
	
}
