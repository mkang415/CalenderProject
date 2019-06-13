package board.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import board.dao.face.BoardDao;
import dbutil.DBConn;
import dto.Board;
import util.Paging;

public class BoardDaoImpl implements BoardDao {
	// BoardDao에서 insert(), selectAll(), updateHit(), selectBoardByBoardno, selectBoardno() 누락되서 발생하는 error 표시 

	// DB 관련 객체 
	private Connection conn = DBConn.getConnection();
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public List selectAll(Paging paging) {
		
		
		String sql = "";
		sql += "SELECT * FROM (";
		sql += " SELECT rownum rnum, B.* FROM (";
		sql += " 	SELECT boardno, nickname, title, content, gamedate, team, insertdate, hit FROM board";
		sql += " 	ORDER BY boardno DESC";
		sql += " )B";
		sql += " ORDER BY rnum";
		sql += " )";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		List list = new ArrayList();
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, paging.getStartNo()); 
			ps.setInt(2, paging.getEndNo()); 
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Board board = new Board();
				
				board.setBoardno(rs.getInt("boardno"));
				board.setNickname(rs.getString("nickname"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setGamedate(rs.getDate("gamedate"));
				board.setTeam(rs.getString("team"));
				board.setInsertdate(rs.getDate("insertdate"));
				board.setHit(rs.getInt("hit"));
				
				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null)
				try {
					if(rs!=null)	rs.close();
					if(ps!=null)	ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
		}
		return list;
	}

	//게시글 수 조회
	@Override
	public int selectCntAll() {
		
		String sql = "";
		sql += "SELECT count(*)";
		sql += " FROM board";
		
		int totalCount = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				totalCount = rs.getInt(1);
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
		return totalCount;
		
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
		sql += "SELECT boardno, nickname, title, content, gamedate, team, insertdate, hit FROM board";
		sql += " WHERE boardno = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, viewBoard.getBoardno());
		
			rs = ps.executeQuery();
			
			while (rs.next()) {
				viewBoard.setBoardno(rs.getInt("boardno"));
				viewBoard.setNickname(rs.getString("nickname"));
				viewBoard.setTitle(rs.getString("title"));
				viewBoard.setContent(rs.getString("content"));
				viewBoard.setGamedate(rs.getDate("gamedate"));
				viewBoard.setTeam(rs.getString("team"));
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
		
		return viewBoard;
	}

	// gameDate 기반으로 지역 조회
	@Override
	public Board selectBoardByGamedate(Board view){

		String sql = "";
		sql += "select region from team where teamname = (select hometeam from schedule where scheduleno = (select scheduleno from board where boardno = ?));";

		try {

		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)	rs.close();
				if(ps!=null)	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return viewBoard;
	}


	// 게시글 조회 
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
	
	
	//게시글 입력
	@Override
	public void insert(Board board) {
		
		//다음 게시글 번호 조회
		String sql = "";
		sql += "INSERT INTO board(BOARDNO, NICKNAME, TITLE, CONTENT, GAMEDATE, TEAM, INSERTDATE, HIT)";
		sql += " VALUES(?,?,?,?,?,?,0)";
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, board.getBoardno());
			ps.setString(2, board.getNickname());
			ps.setString(3, board.getTitle());
			ps.setString(4, board.getContent());
			ps.setDate(5, (Date)board.getGamedate());
			ps.setString(6, board.getTeam());
			ps.setDate(7, (Date) board.getInsertdate());
			
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


	//게시글 수정
	@Override
	public void update(Board board) {
		
		String sql ="";
		sql += "UPDATE board";
		sql += " SET title = ?,";
		sql += " content = ?";
		sql += " WHERE boardno = ?";
		
		//DB객체
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContent());
			ps.setInt(3, board.getBoardno());
			
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


	//게시글 삭제
	@Override
	public void delete(Board board) {
		
		String sql = "";
		sql += "DELETE board";
		sql += " WHERE boardno = ?";
		
		//DB객체
		PreparedStatement ps = null;
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, board.getBoardno());
			
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

	//---------------------------------------------
//	@Override
//	public List getList(String event, String team, String region) {
//		
//		
//		
//		return null;
//	}

	
	
	// 내가 쓴 글 보기 
	@Override
	public void myBoard(Board board) {
		
		String sql = "";
		sql += "SELECT  * FROM (";
		sql += " SELECT boardno, nickname, title, content, scheduleno, inserdate, hit FROM board";
		sql += " ) ORDER BY insertdate";
		sql += " WHERE userid = ?";
		
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, board.getNickname());
			
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
}