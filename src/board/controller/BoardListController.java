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
	
		//게시판 목록 조회
		List list = boardService.getList(); // Paging 생성해야함 
		
		//view 지정
		req.getRequestDispatcher("/WEB-INF/views/board/list.jsp").forward(req, resp);
	
	}
}
