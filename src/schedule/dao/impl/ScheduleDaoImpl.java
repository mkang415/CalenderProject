package schedule.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbutil.DBConn;
import dto.Schedule;
import schedule.dao.face.ScheduleDao;

public class ScheduleDaoImpl implements ScheduleDao{

	private Connection conn = DBConn.getConnection();
	private PreparedStatement ps;
	private ResultSet rs;
	
		@Override
	public String startday(int year, int month) {	//	해당 월의 시작하는 요일 구해서 반환
		String getyear = String.valueOf(year);
		String getmonth = String.valueOf(month);
		String getstartday = getyear+"/"+getmonth+"/01";
		String day = null;
		
		String sql = "";
		sql += "select to_char(";
		sql	+= " to_date(?), 'dy') from dual";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, getstartday);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				day = rs.getString(1);
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
		return day;
	}

	@Override
	public List getmonthList(int year, int month, int day) { //	해당 날짜 일정 리스트에 저장하여 반환
		
		String getyear = String.valueOf(year);
		String getmonth = String.valueOf(month);
		String getday = String.valueOf(day);
		String getymd = getyear+"/"+getmonth+"/"+getday;
		
		List monthList = new ArrayList();
		
		String sql = "";
		sql += "select hometeam, awayteam, gamedate";
		sql += " from schedule"; 
		sql += " where gamedate = ? order by scheduleno";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, getymd);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Schedule schedule = new Schedule();
				schedule.setHometeam(rs.getString("hometeam"));
				schedule.setAwayteam(rs.getString("awayteam"));
				schedule.setGamedate(rs.getDate("gamedate"));
				
				monthList.add(schedule);
				
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
		return monthList;
		
	}
}
