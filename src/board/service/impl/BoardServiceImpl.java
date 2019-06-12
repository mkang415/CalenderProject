package board.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import board.dao.face.BoardDao;
import board.dao.impl.BoardDaoImpl;
import board.service.face.BoardService;
import dto.Board;
import dto.Reply;
import reply.dao.face.ReplyDao;
import reply.dao.impl.ReplyDaoImpl;
import util.Paging;

public class BoardServiceImpl implements BoardService {
	
	// DAO 객체 

	private ReplyDao replyDao = new ReplyDaoImpl();

	//BoardDao객체
	private BoardDao boardDao = new BoardDaoImpl();

	@Override //list 전체검색
	public List getList(Paging paging) {
		return boardDao.selectAll(paging);
	}
	
	//게시글 curPage 파싱
	@Override
	public Paging getCurPage(HttpServletRequest req) {
		//전달파라미터 curPage 파싱
		String param = req.getParameter("curPage");
		int curPage = 0;
		if(param!=null &&!"".equals(param)) {
			curPage = Integer.parseInt(param);
		}

		//전체 게시글 수
		int totalCount = boardDao.selectCntAll();
		
		//페이징 객체 생성
		Paging paging = new Paging(totalCount, curPage);
		
		
		return paging;
	}
		

	@Override //글번호로 게시글 조회
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
		
		
		return board;
	}

	@Override //게시판 상세보기
	public Board view(Board viewBoard) {
		
		//게시글 조회수 +1
		boardDao.updateHit(viewBoard);
		
		//게시글 조회 반환		
		return boardDao.selectBoardByBoardno(viewBoard);
	}

	
	//게시글 작성 **
	@Override
	public void write(HttpServletRequest req) {
		
		Board board = new Board();
		
		board.setTitle(req.getParameter("title"));
		board.setContent(req.getParameter("content"));
		
		boardDao.insert(board);
		
		
		
		
//		Board board = null;
//		board = new Board();
//		int boardno = boardDao.selectBoardno();
//		
//		boardDao.insert(board);
		
//		if (board != null) {
//				board.setBoardno(boardno);
//			
//			if(board.getTitle()==null || "".equals(board.getTitle())){
//				board.setTitle("(제목없음)");
//			
//				//작성자 아이디 처리
//				board.setUserid((String) req.getSession().getAttribute("userid"));
//			}
//			boardDao.insert(board);
//		}
		
		
		
	}

	
	//글 작성자인지 판단하기
	@Override
	public boolean checkWriter(HttpServletRequest req) {
		
		//로그인한 세션 ID 얻기
//		String loginId = (String) req.getSession().getAttribute("userid");
		
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

	
	//게시글 수정 **
	@Override
	public void update(HttpServletRequest req) {
		
		Board board = null;
		
		board = new Board();
		
		board.setTitle(req.getParameter("title"));
		board.setContent(req.getParameter("content"));
		
		boardDao.update(board);
		
		 
		
			
		}

	
	//게시글 삭제
	@Override
	public void delete(Board board) {

		boardDao.delete(board);
	}

	
	
	//------------------댓글
	@Override
	public Reply getReply(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertReply(Reply reply) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List getReplyList(Board board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteReply(Reply reply) {
		// TODO Auto-generated method stub
		return false;
	}

	


	
}