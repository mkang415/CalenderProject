package board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.face.BoardService;
import board.service.impl.BoardServiceImpl;
import dto.Comment;


@WebServlet("/comment/delete")
public class CommentDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private BoardService boardService = new BoardServiceImpl();
	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Comment comment = new Comment();
		
		String commentNo = (String) request.getParameter("commentNo");
		
		comment.setCommentNo(Integer.parseInt(commentNo));
		
		boolean success = boardService.deleteComment(comment);
		
		response.getWriter().append("{\"success\":"+success+"}");
		
	}

}
