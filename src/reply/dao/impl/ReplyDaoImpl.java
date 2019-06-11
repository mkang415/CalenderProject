package reply.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbutil.DBConn;
import dto.Board;
import dto.Reply;
import oracle.net.aso.e;
import reply.dao.face.ReplyDao;

public class ReplyDaoImpl implements ReplyDao {
	
	// DB 연결 객체 
	private Connection conn = DBConn.getConnection();
	
	// JDBC 객체 
	private PreparedStatement ps;
	private ResultSet rs;
	
	@Override
	public List selectReplyList(Board board) {
		String sql
		= "SELECT * FROM ("
				+ "SELECT rownum rnum, B.* FROM ("
				+ "	SELECT"
				+ "		replyno,"
				+ "		boardno,"
				+ "		userid,"
				+ "		replyContent,"
				+ "		insertdate"
				+ "	FROM commentTb"
				+ "	WHERE boardno = ?"
				+ "	ORDER BY writtendate DESC"
				+ "	) B"
				+ ") ORDER BY replyno";
		
		List replyList = new ArrayList();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, board.getBoardno());
			
			// ResultSet 반환 
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Reply reply = new Reply();
				
				reply.setReplyno(rs.getInt("replyno"));
				reply.setBoardno(rs.getInt("boardno"));
				reply.setUserid(rs.getString("userid"));
				reply.setReplyContent(rs.getString("replyContent"));
				reply.setInsertdate(rs.getDate("insertDate"));
				
				replyList.add(reply);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) 	rs.close();
				if(ps!=null) 	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return replyList;
	}
	
	@Override
	public void insertReply(Reply reply) {
		String sql
		= "INSERT INTO replyTB ("
			+ "		replyno,"
			+ " 	boardno,"	
			+ " 	userid,"	
			+ " 	replyContent )"
			+ " VALUES ("
			+ " 	replyTb_seq.nextval,"
			+ " 	?,"
			+ " 	?."
			+ " 	? )";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1,  reply.getBoardno());
			ps.setString(2,  reply.getUserid());
			ps.setString(3,  reply.getReplyContent());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null) 	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			
	}
	
	@Override
	public void deleteReply(Reply reply) {
		String sql
		= "DELETE replyTB"
			+ " WHERE replyno = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, reply.getReplyno());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null) 	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
