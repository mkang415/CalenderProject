package member.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbutil.DBConn;
import dto.Board;
import dto.Exit;
import dto.Member;
import member.dao.face.MemberDao;
import util.Paging;

public class MemberDaoImpl implements MemberDao{

	Connection conn = DBConn.getConnection();
	
	PreparedStatement ps = null;
	
	ResultSet rs = null;

	@Override
	public Member selectMemberByUserid(Member member) { // 유저 정보 반환할 쿼리

		String sql = "";
		sql += "SELECT * FROM member";
		sql += " WHERE userid=?";
		
		Member selectedMember = new Member(); // 아이디가 일치하는 회원의 id/pw/nickname를 담아 반환할 객체

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, member.getUserid());
			rs = ps.executeQuery();
		
			while(rs.next()) {
				
				selectedMember.setUserid(rs.getString("userid"));
				selectedMember.setPassword(rs.getString("password"));
				selectedMember.setNickname(rs.getString("nickname"));
				selectedMember.setIconno(rs.getInt("iconno"));
				selectedMember.setAge(rs.getInt("age"));
				selectedMember.setGender(rs.getString("gender"));
				selectedMember.setTeamname(rs.getString("teamname"));
				selectedMember.setIntroduce(rs.getString("introduce"));
				selectedMember.setJoindate(rs.getDate("joindate"));
				selectedMember.setGrade(rs.getInt("grade"));
				
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
		
		return selectedMember;
	}
	
	@Override
	public int selectCntMemberByUserid(Member member) { // 유저 정보 존재 여부 반환 쿼리
		
		// 로그인시 해당 회원 아이디와 패스워드 일치 여부를 검사하는 sql
		String sql = "";
		sql += "SELECT COUNT(*) FROM member";
		sql += " WHERE userid=? AND password=?";
		
		int cnt = 0; // 반환값을 담을 int형 변수 - 0이면 일치하는 회원 없음, 1이면 로그인 성공
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, member.getUserid());
			ps.setString(2, member.getPassword());
			rs=ps.executeQuery();
			
			while(rs.next()) {
				cnt = rs.getInt("count(*)");
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
		
		return cnt;
	}

	@Override
	public void insert(Member member) { // 회원가입

		System.out.println(member.getUserid());
		System.out.println(member.getPassword());
		System.out.println(member.getNickname());
		System.out.println(member.getAge());
		System.out.println(member.getGender());
		
		String sql = "";
		sql+="INSERT INTO MEMBER(userid, password, nickname, iconno, age, gender, teamname, introduce, joindate, grade)";
		sql+=" VALUES(?,?,?,1001,?,?,?,?,sysdate,1)";
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, member.getUserid());
			ps.setString(2, member.getPassword());
			ps.setString(3, member.getNickname());
			ps.setInt(4, member.getAge());
			ps.setString(5, member.getGender());
			ps.setString(6, "None");
			ps.setString(7, "Hello World!");
			
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
	public void update(Member member) { // 회원정보 수정 쿼리

		String sql="";
		sql+="UPDATE MEMBER SET age=?, gender=?, nickname=?, teamname=?, introduce=?";
		sql+=" WHERE userid=?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, member.getAge());
			ps.setString(2, member.getGender());
			ps.setString(3, member.getNickname());
			ps.setString(4, member.getTeamname());
			ps.setString(5, member.getIntroduce());
			ps.setString(6, member.getUserid());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}

	@Override
	public int selectCntAll(String nickname) { // 페이징을 위한 유저 게시글 수 조회 쿼리

		String sql = "";
		sql+="SELECT COUNT(*) FROM board";
		sql+=" WHERE nickname=?";
				
		int totalCount = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, nickname);
			rs = ps.executeQuery();

			while(rs.next()) {
				totalCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // 자원 해제
				try {
					if(rs!=null) rs.close();
					if(ps!=null) ps.close();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		return totalCount;
	}

	@Override
	public List selectAll(Paging paging, String nickname) { // 유저 게시판 작성 글 목록 을 위해 글 리스트 반환할 쿼리

		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, B.* FROM ("; 
		sql += "		SELECT * FROM board";
		sql += "		WHERE nickname=?";
		sql += "		ORDER BY BOARDNO desc";
		sql += "	) B";
		sql += "	ORDER BY RNUM"; 
		sql += " ) BOARD"; 
		sql += " WHERE RNUM BETWEEN ? AND ?";

		List boardList = new ArrayList();
				
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, nickname);
			ps.setInt(2, paging.getStartNo());
			ps.setInt(3, paging.getEndNo());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Board board = new Board();
				
				board.setBoardno(rs.getInt("boardno"));
				board.setNickname(rs.getString("nickname"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setScheduleno(rs.getInt("scheduleno"));
				board.setInsertdate(rs.getDate("insertdate"));
				board.setHit(rs.getInt("hit"));
				
				boardList.add(board);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return boardList;
	}

	@Override
	public boolean pwCheck(Member member) { // 비밀번호 확인 쿼리

		boolean res = false;
		
		String sql = "";
		sql += "SELECT password from MEMBER";
		sql += " WHERE userid=?"; 
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, member.getUserid());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				if(member.getPassword().equals(rs.getString("password"))) res = true;
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return res;
	}

	@Override
	public void pwUpdate(Member member) { // 비밀번호 변경 쿼리

		String sql = "";
		sql += "UPDATE MEMBER SET password=?";
		sql += " WHERE userid=?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, member.getPassword());
			ps.setString(2, member.getUserid());
			ps.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
				
	}

	@Override
	public boolean nicknameCheck(String nickname) { // 닉네임 중복검사 쿼리

		boolean res = false;
		
		String sql = "";
		sql+="SELECT count(*) FROM MEMBER";
		sql+=" WHERE nickname=?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, nickname);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				if(rs.getInt("count(*)")==0) {
					res = true;
				} else {
					res = false;
				}
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return res;
	}

	@Override
	public String isMyNickname(String nickname) { // 닉네임 중복시 내 닉네임인지 검사하는 쿼리

		String id = null;
		
		String sql = "";
		sql+="SELECT userid, nickname FROM MEMBER";
		sql+=" WHERE nickname=?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, nickname);
		
			rs=ps.executeQuery();
			
			while(rs.next()) {
				id = rs.getString("userid");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}

	@Override
	public void deleteMember(String userid) {

		String sql = "";
		sql+="DELETE FROM MEMBER";
		sql+=" WHERE userid=?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, userid);
			ps.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void exitReason(Exit exit) {

		String sql = "";
		sql+="INSERT INTO EXIT(exitid, exitreason)";
		sql+=" VALUES(?,?)";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, exit.getExitid());
			ps.setString(2, exit.getExitreason());		
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}

