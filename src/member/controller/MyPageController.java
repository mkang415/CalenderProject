package member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Board;
import member.service.face.MemberService;
import member.service.impl.MemberServiceImpl;
import util.Paging;

@WebServlet("/mypage")
public class MyPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MemberService memberservice = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// session 객체
		HttpSession session = req.getSession();
			
		// session 에서 userid 불러오기
		String userid = (String)session.getAttribute("userid");
		
		// 요청파라미터에서 curPage 얻어오기
		Paging paging = memberservice.getCurPage(req, userid);
					
		// MODEL로 Paging 객체 넣기
		req.setAttribute("paging", paging);
					
		List<Board> boardList = memberservice.getList(paging, userid);
					
		req.setAttribute("boardList", boardList);
				
		

		req.getRequestDispatcher("/WEB-INF/views/member/mypage.jsp").forward(req, resp);
	
	}
	
	
}
