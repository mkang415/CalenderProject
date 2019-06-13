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

// 회원 작성 글 반환 서블릿

@WebServlet("/mypage/myboard")
public class MyBoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MemberService memberservice = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// session 객체
		HttpSession session = req.getSession();
			
		// session 에서 userid 불러오기
		String nickname = (String)session.getAttribute("nickname");
		
		// 요청파라미터에서 curPage 얻어오기
		Paging paging = memberservice.getCurPage(req, nickname);
					
		// MODEL로 Paging 객체 넣기
		req.setAttribute("paging", paging);
					
		List<Board> boardList = memberservice.getList(paging, nickname);
					
		req.setAttribute("boardList", boardList);
				
		

		req.getRequestDispatcher("/WEB-INF/views/member/myboardlist.jsp").forward(req, resp);
	
	}
	
	
}
