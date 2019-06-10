package manage.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Board;
import manage.dao.face.ManageDao;
import manage.dao.impl.ManageDaoImpl;
import manage.service.face.ManageService;
import util.Paging;

public class ManageServiceImpl implements ManageService{
	
	private ManageDao manageDao = new ManageDaoImpl();

	@Override
	public List getList(Paging paging) {
		return manageDao.selectAll(paging);
	}

	@Override
	public Paging getCurPage(HttpServletRequest req) {
		// 전달파라미터 curPage 파싱
				String param = req.getParameter("curPage");
				int curPage = 0;
				if (param != null && !"".equals(param)) {
					curPage = Integer.parseInt(param);
				}

				// 전체 게시글 수
				int totalCount = manageDao.selectCntAll();

				// 페이징 객체 생성
				Paging paging = new Paging(totalCount, curPage);
//				System.out.println(paging); //TEST

				return paging;
	}

	@Override
	public Board getBoardno(HttpServletRequest req) {

		String param = req.getParameter("boardno");
		int boardno = 0;
		if (param != null && !"".equals(param)) {
			boardno = Integer.parseInt(param);
		}
		
		Board board = new Board();
		board.setBoardno(boardno);
		
		return board;
	}

	@Override
	public Board view(Board viewBoard) {
		
		return manageDao.selectBoardByBoardno(viewBoard);
	}

	@Override
	public void delete(String names) {

		manageDao.delete(names);
		
	}

	

	
	
	
}
