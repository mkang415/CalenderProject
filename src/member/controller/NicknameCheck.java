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

// 닉네임 중복여부 검사 ajax를 위한 서블릿

@WebServlet("/nicknameCheck")
public class NicknameCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MemberService memberservice = new MemberServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
				
		String nickname = req.getParameter("nickname");
		
		boolean res = memberservice.nicknameCheck(nickname);
		
		if(res==false&&(boolean)session.getAttribute("login")==true) {
			res = memberservice.isMyNickname(nickname, (String)session.getAttribute("userid"));
			
		}
		
		PrintWriter pw = resp.getWriter();
		
		pw.print(res);
		
	}
	
}
