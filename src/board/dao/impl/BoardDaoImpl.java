package board.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import board.dao.face.BoardDao;
import dbutil.DBConn;
import util.Paging;

public class BoardDaoImpl implements BoardDao {
	// BoardDao에서 insert(), selectAll(), updateHit(), selectBoardByBoardno, selectBoardno() 누락되서 발생하는 error 표시 

	// DB 관련 객체 
	private Connection conn = DBConn.getConnection();
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	
}
