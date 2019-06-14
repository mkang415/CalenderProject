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
import reply.dao.face.ReplyDao;

public class ReplyDaoImpl implements ReplyDao {
	
	// DB 연결 객체 
	private Connection conn = DBConn.getConnection();
	
	// JDBC 객체 
	private PreparedStatement ps;
	private ResultSet rs;
	
	//댓글 꺼내기
	@Override
	public List selectReply(Board board) {
		String sql
		= "SELECT * FROM ("
				+ "SELECT rownum rnum, B.* FROM ("
				+ "	SELECT"
				+ "		replyno,"
				+ "		nickname,"
				+ "		boardno,"
				+ "		recontent,"
				+ "		insertdate"
				+ "	FROM reply"
				+ "	WHERE boardno = ?"
				+ "	ORDER BY insertdate DESC"
				+ "	) B"
				+ ") ORDER BY rnum";
		
		List replyList = new ArrayList();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, board.getBoardno());
			
			// ResultSet 반환 
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Reply reply = new Reply();
				
				reply.setReplyno(rs.getInt("replyno"));
				reply.setNickname(rs.getString("nickname"));
				reply.setBoardno(rs.getInt("boardno"));
				reply.setReplyContent(rs.getString("recontent"));
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
		System.out.println(replyList);
		return replyList;
	}

	
//--------------------------------------------------------------------------------------
	
	//댓글 입력
	@Override
	public void insertReply(Reply reply) {
		String sql
		= "INSERT INTO reply ("
			+ "		replyno,"
			+ " 	nickname,"
			+ " 	boardno,"	
			+ " 	recontent )"
			+ " VALUES ("
			+ " 	reply_seq.nextval,"
			+ " 	?,"
			+ " 	?,"
			+ " 	? )";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1,  reply.getNickname());
			ps.setInt(2,  reply.getBoardno());
			ps.setString(3,  reply.getReplyContent());
			
			ps.executeUpdate();
			
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
		= "DELETE reply"
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

	
	//---------------------------------
	
	//댓글수
	@Override
	public int countReply(Reply reply) {
		
		String sql = "SELECT COUNT(*) FROM reply WHERE replyno=?";
		int cnt=0;
		
		try {
			ps=conn.prepareStatement(sql);
			
			ps.setInt(1, reply.getReplyno());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
			cnt = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)	rs.close();
				if(ps!=null) 	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return cnt;
	}

}
