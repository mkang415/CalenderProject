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


@WebServlet("/reply/delete")
public class ReplyDeleteController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	private BoardService boardService = new BoardServiceImpl();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Reply reply = new Reply();
				
		String replyno = (String) request.getParameter("replyno");
		
		reply.setReplyno(Integer.parseInt(replyno));
		
		boolean deleted = boardService.deleteReply(reply);  // 삭제 처리 결과 저장하는 boolean 타입 변수
		
		response.getWriter().append("{\"+success\": "+deleted+"}"); // 결과 출력
		
	}

}
