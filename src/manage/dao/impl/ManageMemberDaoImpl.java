package manage.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbutil.DBConn;
import dto.Member;
import manage.dao.face.ManageMemberDao;
import util.Paging;

public class ManageMemberDaoImpl implements ManageMemberDao {
	
	private Connection conn = DBConn.getConnection();

	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public List selectAll(Paging paging) {
		
		System.out.println(paging.toString());
		
		//게시글 목록 조회쿼리
		String sql = "";
		sql += "SELECT * FROM (";
		sql += " SELECT rownum rnum, B.* FROM (";
		sql += " SELECT userid, nickname, age, gender, introduce, joinDate FROM member";
		sql += " ORDER BY joinDate DESC";
		sql += " ) B";
		sql += " ORDER BY rnum";
		sql += " ) member";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		List list = new ArrayList();
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Member member = new Member();
				
				member.setUserid(rs.getString("userid"));
				member.setNickname(rs.getString("nickname"));
				member.setAge(rs.getInt("age"));
				member.setGender(rs.getString("gender"));
				member.setIntroduce(rs.getString("introduce"));
				member.setJoindate(rs.getDate("joinDate"));
				
				System.out.println(member.toString());
				list.add(member);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 자원 해제
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}

	@Override
	public int selectCntAll() {
		// 전체 게시글 수 조회 쿼리
				String sql = "";
				sql += "SELECT count(*)";
				sql += " FROM member";
				
				int totalCount = 0;
				try {
					ps = conn.prepareStatement(sql);
					
					rs = ps.executeQuery();
					
					while (rs.next()) {
						totalCount = rs.getInt(1);
						System.out.println(totalCount);
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						// 자원 해제
						if (rs != null)
							rs.close();
						if (ps != null)
							ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
				return totalCount;
	}

	

	@Override
	public Member view(String param) {
		
		Member viewMember = new Member();
		
		String sql = "";
		sql += "SELECT userid, age, gender, iconno, nickname, joinDate, teamname, introduce FROM Member";
		sql += " WHERE userid = ?";
		
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, param);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				
				viewMember.setUserid(rs.getString("userid"));
				viewMember.setAge(rs.getInt("age"));
				viewMember.setGender(rs.getString("gender"));
				viewMember.setIconno(rs.getInt("iconno"));
				viewMember.setNickname(rs.getString("nickname"));
				viewMember.setJoindate(rs.getDate("joindate"));
				viewMember.setTeamname(rs.getString("teamname"));
				viewMember.setIntroduce(rs.getString("introduce"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return viewMember;
	}

	@Override
	public int cntReply(String reply) {
		
		String sql = "";
		sql += "select count(*) from reply where nickname = ?";
		
		int totalCount = 0;
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, reply);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				totalCount = rs.getInt(1);
				System.out.println(totalCount);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 자원 해제
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return totalCount;
	}

	@Override
	public int cntBoard(String cntBoard) {

		String sql = "";
		sql += "select count(*) from board where nickname = ?";

		int totalCount = 0;
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, cntBoard);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				totalCount = rs.getInt(1);
				System.out.println(totalCount);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 자원 해제
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return totalCount;
		
	}

}
