package manage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Board;
import manage.service.face.ManageService;
import manage.service.impl.ManageServiceImpl;

@WebServlet("/manage/view")
public class ManageViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ManageService manageService = new ManageServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Board viewBoard = manageService.getBoardno(req);
		
		viewBoard = manageService.view(viewBoard); 
		
		req.setAttribute("viewBoard", viewBoard);
		
		req.getRequestDispatcher("/WEB-INF/views/manage/page.jsp").forward(req, resp);
	}
}
