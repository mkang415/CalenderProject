package manage.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbutil.DBConn;
import dto.Member;
import dto.Schedule;
import manage.dao.face.ManageScheduleDao;

public class ManageScheduleDaoImpl implements ManageScheduleDao{

	private Connection conn = DBConn.getConnection();
	private PreparedStatement ps;
	private ResultSet rs;
	
	@Override
	public void inputSchedule(int event, String homeTeam, String awayTeam, String gameDate) {
		
		if(event == 1) {
			String sql = "";
			sql += "insert into schedule(";
			sql += " scheduleno, hometeam, awayteam, gamedate)";
			sql += " values(baseball_sche_seq.nextval, ?, ?, ?)";

			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, homeTeam);
				ps.setString(2, awayTeam);
				ps.setString(3, gameDate);
				
				ps.executeUpdate();
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
			
		} else {
			String sql = "";
			sql += "insert into schedule(";
			sql += " scheduleno, hometeam, awayteam, gamedate)";
			sql += " values(football_sche_seq.nextval, ?, ?, ?)";
			
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, homeTeam);
				ps.setString(2, awayTeam);
				ps.setString(3, gameDate);
				
				ps.executeUpdate();
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
		}
		
	}

	@Override
	public void deleteSchedule(int no) {
		
		String sql = "";
	    sql+= "delete from schedule where scheduleno in (?)";
	      
	      try {
	         ps = conn.prepareStatement(sql);
	         ps.setInt(1, no);
	         ps.executeUpdate();
	         
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
		
	}

}
