
package manage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manage.service.face.ManageService;
import manage.service.impl.ManageServiceImpl;
import util.Paging;

@WebServlet("/manage/page")
public class ManagePageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ManageService manageService = new ManageServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//요청파라미터에서 CurPage 얻어오기
		Paging paging = manageService.getCurPage(req);
		
		//MODEL로 Paging 객체 넣기
		req.setAttribute("paging", paging);
		
		
		//게시판 목록 조회
		List list = manageService.getList(paging);
		
	
		//MODEL로 조회 결과 넣기
		req.setAttribute("list", list);
		
		req.getRequestDispatcher("/WEB-INF/views/manage/page.jsp").forward(req, resp);;
		
	}
	
	
	
}
