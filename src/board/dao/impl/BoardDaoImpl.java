package board.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import board.dao.face.BoardDao;
import dbutil.DBConn;
import dto.Board;

public class BoardDaoImpl implements BoardDao {

	//DB관련 객체
	private Connection conn = DBConn.getConnection();
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public List selectAll() {
		
		//파일 업로드 기록 조회쿼리
		String sql = "";
		sql += "SELECT * FROM board";
		sql += " ORDER BY boardno DESC";
		
		List list = new ArrayList();
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Board board = new Board();
				
				board.setBoardno(rs.getInt("boardno"));
				board.setUserid(rs.getString("userid"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setScheduleno(rs.getInt("scheduleno"));
				board.setInsertdate(rs.getDate("insertdate"));
				board.setHit(rs.getInt("hit"));
				
				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	
	//게시글 조회수 +1
	@Override 
	public void updateHit(Board viewBoard) {
		
		//파일업로드 기록 조회쿼리
		String sql = "";
		sql += "UPDATE board";
		sql += " SET hit = hit +1";
		sql += " WHERE boardno = ?";
		
		try {
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, viewBoard.getBoardno());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null)	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}

	
	//게시글 상세보기
	@Override
	public Board selectBoardByBoardno(Board viewBoard) {
		
		//게시글 조회
		String sql = "";
		sql += "SELECT * FROM board";
		sql += " WHERE boardno = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, viewBoard.getBoardno());
		
			rs = ps.executeQuery();
			
			while (rs.next()) {
				viewBoard.setBoardno(rs.getInt("boardno"));
				viewBoard.setBoardno(rs.getInt("boardno"));
				viewBoard.setUserid(rs.getString("userid"));
				viewBoard.setTitle(rs.getString("title"));
				viewBoard.setContent(rs.getString("content"));
				viewBoard.setScheduleno(rs.getInt("scheduleno"));
				viewBoard.setInsertdate(rs.getDate("insertdate"));
				viewBoard.setHit(rs.getInt("hit"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			try {
				if(rs!=null)	rs.close();
				if(ps!=null)	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}


	
	//게시글 입력
	@Override
	public void insert(Board board) {
		
		//다음 게시글 번호 조회
		String sql = "";
		sql += "INSERT INTO board(BOARDNO, USERID, TITLE, CONTENT, HIT)";
		sql += " VALUES(?,?,?,?,0)";
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, board.getBoardno());
			ps.setString(2, board.getUserid());
			ps.setString(3, board.getTitle());
			ps.setString(4, board.getContent());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null)	ps.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
	}


	@Override
	public int selectBoardno() {
		
		//다음 게시글 번호 조회
		String sql ="";
		sql += "SELECT board_seq.nextval";
		sql += " FROM dual";
		
		//게시글 번호
		int boardno = 0;
		
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				boardno = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)	rs.close();
				if(ps!=null)	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//게시글 번호 반환
		return boardno;
	}

}	



























