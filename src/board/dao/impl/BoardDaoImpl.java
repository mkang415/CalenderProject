package board.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import board.dao.face.BoardDao;
import dbutil.DBConn;

public class BoardDaoImpl implements BoardDao {

	// DB 관련 객체 
	private Connection conn = DBConn.getConnection();
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	
}
