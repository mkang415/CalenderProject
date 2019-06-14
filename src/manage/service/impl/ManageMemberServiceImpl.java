package manage.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Member;
import manage.dao.face.ManageMemberDao;
import manage.dao.impl.ManageMemberDaoImpl;
import manage.service.face.ManageMemberService;
import util.Paging;

public class ManageMemberServiceImpl implements ManageMemberService {

	private ManageMemberDao managememberDao = new ManageMemberDaoImpl();
	
	@Override
	public Paging getCurPage(HttpServletRequest req) {
		// 전달파라미터 curPage 파싱
		String param = req.getParameter("curPage");
		int curPage = 0;
		if (param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		}

		// 전체 게시글 수
		int totalCount = managememberDao.selectCntAll();

		// 페이징 객체 생성
		Paging paging = new Paging(totalCount, curPage);
//		System.out.println(paging); //TEST

		return paging;
	}
	
	

	@Override
	public List getList(Paging paging) {
		return managememberDao.selectAll(paging);
	}





	@Override
	public Member getUserid(HttpServletRequest req) {

		String param = req.getParameter("userid");
		
		
		return managememberDao.view(param);
	}



	@Override
	public int getReply(HttpServletRequest req) {
		
		String reply = req.getParameter("replyno");
		
		return managememberDao.cntReply(reply);
	}



	@Override
	public int getcntBoard(HttpServletRequest req) {
		
		String cntBoard = req.getParameter("nickname");
		
		return managememberDao.cntBoard(cntBoard);
	}
	
	



	
	
	

}
