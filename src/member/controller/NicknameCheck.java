package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.face.MemberService;
import member.service.impl.MemberServiceImpl;
import net.sf.json.JSONObject;

@WebServlet("/nicknameCheck")
public class NicknameCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MemberService memberservice = new MemberServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		String nickname = req.getParameter("nickname");
		
		boolean res = memberservice.nicknameCheck(nickname);

		PrintWriter pw = resp.getWriter();
		
		pw.print(res);
		
	}
	
}
