package board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.Comment;

import board.service.face.BoardService_yj;
import board.service.impl.BoardServiceImpl_yj;


@WebServlet("/comment/insert")
public class CommentInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private BoardService_yj boardService = new BoardServiceImpl_yj();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Comment comment = boardService.getComment(request);
	}

}
