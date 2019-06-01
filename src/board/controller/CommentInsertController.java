package src.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.Comment;

import board.service.face.BoardService;
import board.service.impl.BoardServiceImpl;


@WebServlet("/comment/insert")
public class CommentInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private BoardService boardService = new BoardServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Comment comment = boardService.getComment(request);
	}

}
