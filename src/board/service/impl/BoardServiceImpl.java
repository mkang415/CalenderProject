package board.service.impl;

import board.dao.face.BoardDao;
import board.dao.impl.BoardDaoImpl;
import board.service.face.BoardService;
import reply.dao.face.ReplyDao;
import reply.dao.impl.ReplyDaoImpl;

public class BoardServiceImpl implements BoardService {
	
	// DAO 객체 
	private BoardDao boardDao = new BoardDaoImpl();
	private ReplyDao replyDao = new ReplyDaoImpl();
	
}