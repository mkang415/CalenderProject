package manage.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Schedule;
import manage.service.face.ManageScheduleService;
import manage.service.impl.ManageScheduleServiceImpl;

@WebServlet("/manage/inputSchedule")
public class ManageScheduleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ManageScheduleService manageScheduleService = new ManageScheduleServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		
		
		req.getRequestDispatcher("/WEB-INF/views/manage/inputSchedule.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		
		manageScheduleService.inputSchedule(req);
	
		resp.sendRedirect("/manage/inputSchedule");
	}
	
}
