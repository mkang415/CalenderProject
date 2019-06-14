package reply.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.face.BoardService;
import board.service.impl.BoardServiceImpl;
import dto.Reply;


@WebServlet("/reply/insert")
public class ReplyInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private BoardService boardService = new BoardServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); //한글인코딩
		
//		request.setAttribute("reply", request);
		
		Reply reply = boardService.getReply(request);
		
//		System.out.println(reply.toString());
		
		boardService.insertReply(reply);
		
		response.sendRedirect("/board/view?boardno="+reply.getBoardno());
		
	}

}
