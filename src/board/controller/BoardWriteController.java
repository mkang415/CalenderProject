package board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.face.BoardService;
import board.service.impl.BoardServiceImpl;

@WebServlet("/board/write")
public class BoardWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//Board Service 객체
	private BoardService boardService = new BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	//로그인 되어있지 않으면 리다이렉트	
//	if(req.getSession().getAttribute("login")== null) {
//		resp.sendRedirect("/main");
//		return;
//	}
	
	//view 지정
	req.getRequestDispatcher("/WEB-INF/views/board/write.jsp").forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//작성 글 삽입
		boardService.write(req);
		
		//목록으로 리다이렉션
		resp.sendRedirect("/board/list");
	
	
	}
	
}
