package board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.face.BoardService;
import board.service.impl.BoardServiceImpl;
import dto.Board;

@WebServlet("/board/update")
public class BoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//BoardService 객체
	private BoardService boardService = new BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//로그인한 사람의 글이 아니면 중단하고 목록으로 리다이렉트
		if(!boardService.checkWriter(req)) {
			resp.sendRedirect("/board/list");
			return;
		}  
		
		//게시글 번호 파싱
		Board viewBoard = boardService.getBoardno(req);
		
		//게시글 조회
		viewBoard = boardService.view(viewBoard);
		
		//model로 게시글 전달
		req.setAttribute("viewBoard", viewBoard);
		
		//view지정
		req.getRequestDispatcher("/WEB-INF/views/board/update.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//한글 인코딩 설정 
		req.setCharacterEncoding("UTF-8");
		
		//글 수정
		boardService.update(req);
		
		//목록으로 리다이렉션
		resp.sendRedirect("/board/list");
	
	}
}
