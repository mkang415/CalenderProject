package board.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import board.dao.face.BoardDao_yj;
import dbutil.DBConn;

public class BoardDaoImpl_yj implements BoardDao_yj {

	// DB 관련 객체 
	private Connection conn = DBConn.getConnection();
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	
}
