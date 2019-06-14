package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.service.face.MemberService;
import member.service.impl.MemberServiceImpl;

// 회원탈퇴용 비밀번호 일치 여부 확인 ajax 서블릿

@WebServlet("/pwChk")
public class PwCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MemberService memberservice = new MemberServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		boolean res = memberservice.pwCheck(req);

		System.out.println(res);
		
		PrintWriter pw = resp.getWriter();
		
		pw.print("{\"result\":"+res+"}");
	
	}
	
	
}
