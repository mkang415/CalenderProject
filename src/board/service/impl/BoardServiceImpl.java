package board.service.impl;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import board.dao.face.BoardDao;
import board.dao.impl.BoardDaoImpl;
import board.service.face.BoardService;
import dto.Board;
import dto.Reply;
import dto.Schedule;
import reply.dao.face.ReplyDao;
import reply.dao.impl.ReplyDaoImpl;
import util.Paging;

public class BoardServiceImpl implements BoardService {
	
	//ReplyDAO 객체
	private ReplyDao replyDao = new ReplyDaoImpl();

	// BoardDao객체
	private BoardDao boardDao = new BoardDaoImpl();
	

	@Override //list 전체검색
	public List getList(Paging paging) {
		return boardDao.selectAll(paging);
	}
	
	// 게시글 curPage 파싱
	@Override
	public Paging getCurPage(HttpServletRequest req) {
		//전달파라미터 curPage 파싱
		String param = req.getParameter("curPage");
		int curPage = 0;
		if(param!=null &&!"".equals(param)) {
			curPage = Integer.parseInt(param);
		}

		// 전체 게시글 수
		int totalCount = boardDao.selectCntAll();
		
		// 페이징 객체 생성
		Paging paging = new Paging(totalCount, curPage);
		
		
		return paging;
	}
		
//----------------------------------------------------------------------------------
	
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

//------------------------------------------------------------------------------------------
	
	@Override //게시판 상세보기
	public Board view(Board viewBoard) {
		
		//게시글 조회수 +1
		boardDao.updateHit(viewBoard);
		
		//게시글 조회 반환		
		return boardDao.selectBoardByBoardno(viewBoard);
	}


//------------------------------------------------------------------------------------------
	
	//게시글 작성 **
	@Override
	public void write(HttpServletRequest req) {
		
		Board board = new Board();
//		Schedule schedule = null;
//		schedule = new Schedule();
		
	//	req.getAttribute("baord");
		
		
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		board.setNickname((String) req.getSession().getAttribute("nickname"));
		board.setTitle(req.getParameter("title"));
		board.setContent(req.getParameter("content"));

		String team = req.getParameter("team");
		String gamedate = req.getParameter("insertdate");

		int scheduleno = boardDao.scheduleno(team, gamedate);
		
	//	board.setScheduleno(scheduleno);
		board.setTeam(req.getParameter("team"));
		

		
		
//		System.out.println("보드서비스에서 리퀘스터로 받은 값"+board);
		

	
//		Board board = null;
//		board = new Board();
//		int boardno = boardDao.selectBoardno();
//		
//		boardDao.insert(board);

		
//		if (board != null) {
//			board.setBoardno(boardno);
//		
//		if(board.getTitle()==null || "".equals(board.getTitle())){
//			board.setTitle("(제목없음)");
//		
//			
//		}
		

	}


	
//------------------------------------------------------------------------------------------
	
	
	//글 작성자인지 판단하기
	@Override
	public boolean checkWriter(HttpServletRequest req) {
		
		//로그인한 세션 ID 얻기
		String loginmember = (String) req.getSession().getAttribute("nickname");
		
		//작성한 게시글 번호 얻기
		Board board = getBoardno(req);
		
		//게시글 얻어오기
		board = boardDao.selectBoardByBoardno(board);
		
		//게시글의 작성자와 로그인 아이디 비교
		if(!loginmember.equals(board.getNickname())) {
			return false;
		}
		return true;
	}

//------------------------------------------------------------------------------------------	
	
	
	//게시글 수정 **
	@Override
	public void update(HttpServletRequest req) {
		
		Board board = new Board();
		
		try {
			req.setCharacterEncoding("UTF-8"); //한글 인코딩
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String bono = req.getParameter("boardno");
		int boardno= Integer.parseInt(bono);
		
		board.setTitle(req.getParameter("title"));
		board.setContent(req.getParameter("content"));
		board.setBoardno(boardno);
		
		
		boardDao.update(board);
		
		 
		}

//------------------------------------------------------------------------------------------
	
	//게시글 삭제
	@Override
	public void delete(Board board) {

		boardDao.delete(board);
	}

	
//------------------------------------------------------------------------------------------

	//댓글꺼내기
	@Override
	public Reply getReply(HttpServletRequest req) {
		try {
			req.setCharacterEncoding("UTF-8"); //한글인코딩
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String boardno = (String) req.getParameter("boardno");
		String nickname = (String) req.getParameter("nickname");
		String recontent = (String) req.getParameter("recontent");
		
		Reply reply = new Reply();
		reply.setBoardno(Integer.parseInt(boardno));
		reply.setNickname(nickname);
		reply.setReplyContent(recontent);
		
		return reply;
	}

	
//--------------------------------------------------------------------------------------
	
	//댓글 입력
	@Override
	public void insertReply(Reply reply) {
		replyDao.insertReply(reply);
	}

//--------------------------------------------------------------------------------------
	
	//댓글리스트
	@Override
	public List getReplyList(Board board) {
		return replyDao.selectReply(board);
	}


//------------------------------------------------------------------------------------------
	
	//댓글삭제
	@Override
	public boolean deleteReply(Reply reply) {
		replyDao.deleteReply(reply);
		
		if(replyDao.countReply(reply)>0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public List selectBoardByTeamRegion(String event, String team, String region) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}