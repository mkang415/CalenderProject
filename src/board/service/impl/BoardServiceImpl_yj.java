package board.service.impl;

import board.dao.face.BoardDao_yj;
import board.dao.impl.BoardDaoImpl_yj;
import board.service.face.BoardService_yj;

public class BoardServiceImpl_yj implements BoardService_yj {

	// DAO 객체 
	private BoardDao_yj boardDao = new BoardDaoImpl_yj();
	private CommentDao commentDao = new CommentDaoImpl();
	
	
}
