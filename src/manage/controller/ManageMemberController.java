package manage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manage.service.face.ManageMemberService;
import manage.service.impl.ManageMemberServiceImpl;
import util.Paging;

@WebServlet("/manage/member")
public class ManageMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ManageMemberService managememberService = new ManageMemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Paging paging = managememberService.getCurPage(req);
		
		req.setAttribute("paging", paging);
		
		List list = managememberService.getList(paging);
		
		req.setAttribute("list", list);
		
		req.getRequestDispatcher("/WEB-INF/views/manage/member.jsp").forward(req, resp);;
		
	}
	
}
