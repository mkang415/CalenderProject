package manage.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
	
	
}
