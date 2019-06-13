package manage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Member;
import manage.service.face.ManageMemberService;
import manage.service.impl.ManageMemberServiceImpl;

@WebServlet("/manage/out")
public class ManageOutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ManageMemberService managememberService = new ManageMemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Member infoMember = managememberService.getUserid(req);
		
		req.setAttribute("infoMember", infoMember);
		
		req.getRequestDispatcher("/WEB-INF/views/manage/out.jsp").forward(req, resp);
	}
	
}
