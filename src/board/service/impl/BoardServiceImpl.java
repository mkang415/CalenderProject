package board.service.impl;

<<<<<<< Updated upstream
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import board.dao.face.BoardDao;
import board.dao.impl.BoardDaoImpl;
import board.service.face.BoardService;
import dto.Board;
import util.Paging;

public class BoardServiceImpl implements BoardService {

	//BoardDao객체
	private BoardDao boardDao = new BoardDaoImpl();

	@Override //list 전체검색
	public List getList() {
		return boardDao.selectAll();
	}

	@Override //게시판 글번호 꺼내오기?
	public Board getBoardno(HttpServletRequest req) {
		
		//전달파라미터 boardno 파싱
		String param = req.getParameter("boardno");
		int boardno = 0;
		if(param!=null && !"".equals(param)) {
			boardno = Integer.parseInt(param);
		}
		
		//board객체 생성
		Board board = new Board();
		board.setBoardno(boardno);
		return null;
	}

	@Override //게시판 상세보기
	public Board view(Board viewBoard) {
		
		//게시글 조회수 +1
		boardDao.updateHit(viewBoard);
		
		//게시글 조회 반환		
		return boardDao.selectBoardByBoardno(viewBoard);
	}

	
	//게시글 작성
	@SuppressWarnings("unused")
	@Override
	public void write(HttpServletRequest req) {
		
		Board board = null;
		
		int boardno = boardDao.selectBoardno();
		
		if (board != null) {
				board.setBoardno(boardno);
			
			if(board.getTitle()==null || "".equals(board.getTitle())){
				board.setTitle("(제목없음)");
			
				//작성자 아이디 처리
				board.setContent((String) req.getSession().getAttribute("userid"));
			}
			boardDao.insert(board);
		}
		
		
		
	}

	
	//글 작성자인지 판단하기
	@Override
	public boolean checkWriter(HttpServletRequest req) {
		
		//로그인한 세션 ID 얻기
		String loginId = (String) req.getSession().getAttribute("userid");
		
		//작성한 게시글 번호 얻기
		Board board = getBoardno(req);
		
		//게시글 얻어오기
		board = boardDao.selectBoardByBoardno(board);
		
		//게시글의 작성자와 로그인 아이디 비교
//		if(!loginId.equals(board).getWriter())) {
//			return false;
//		}
		return true;
	}

	
	//게시글 수정
	@Override
	public void update(HttpServletRequest req) {
		
			
		}
			
	}
	
				






























=======
import board.dao.face.BoardDao;
import board.dao.impl.BoardDaoImpl;
import board.service.face.BoardService;

public class BoardServiceImpl implements BoardService {

	// DAO 객체 
	private BoardDao boardDao = new BoardDaoImpl();
	private CommentDao commentDao = new CommentDaoImpl();
	
	
}
>>>>>>> Stashed changes
