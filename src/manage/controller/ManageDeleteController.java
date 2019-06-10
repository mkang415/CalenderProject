package manage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manage.service.face.ManageService;
import manage.service.impl.ManageServiceImpl;

@WebServlet("/manage/delete")
public class ManageDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ManageService manageService = new ManageServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String names = req.getParameter("names");

		if (!"".equals(names) && names != null) {
			manageService.delete(names);
		}

		resp.sendRedirect("/manage/page");
	}

}
