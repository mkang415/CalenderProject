package board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.face.BoardService;
import board.service.impl.BoardServiceImpl;
import util.Paging;

@WebServlet("/board/list")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//BoardService 객체
	private BoardService boardService = new BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//요청 파라미터에서 curPage 얻어오기
		Paging paging = boardService.getCurPage(req);
		
		//model로 Paging 객체 넣기
		req.setAttribute("paging", paging);
//		System.out.println(paging);
		
		//게시판 목록 조회

		List list = boardService.getList(paging);
		

		
		
		
//		String event=(String)req.getParameter("event");	//	선택된 종목 값 전달받아 저장.
//		int chkevent=0;	//	저장된 종목 값 숫자로 변환화여 저장할 변수 생성
//		if(event!=null) {
//			chkevent=Integer.parseInt(event);	//	종목 값 숫자로 변환하여 저장 1: 야구, 2: 축구
//		}
//		String team=null;	//	전달 받은 팀 이름 저장할 변수 생성
//		String region=null;	//	전달 받은 지역 이름 저장할 변수 생성
//		if(chkevent==1) {	//	야구 팀, 지역 저장
//			team=(String)req.getParameter("baseballTeam");
//			region=(String)req.getParameter("BBregion");
//		} else if(chkevent==2){	//	축구 팀, 지역 저장
//			team=(String)req.getParameter("soccerTeam");
//			region=(String)req.getParameter("SCregion");
//		}
		
		
		
		

		
		
		//view 지정
		req.getRequestDispatcher("/WEB-INF/views/board/list.jsp").forward(req, resp);
	
	}
}
