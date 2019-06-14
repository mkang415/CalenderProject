package schedule.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbutil.DBConn;
import dto.Icon;
import dto.Schedule;
import schedule.dao.face.ScheduleDao;

public class ScheduleDaoImpl implements ScheduleDao {

	private Connection conn = DBConn.getConnection();
	private PreparedStatement ps;
	private ResultSet rs;

	@Override
	public String startday(int year, int month) { // 해당 월의 시작하는 요일 구해서 반환
		String getyear = String.valueOf(year);
		String getmonth = String.valueOf(month);
		String getstartday = getyear + "/" + getmonth + "/01";
		String day = null;

		String sql = "";
		sql += "select to_char(";
		sql += " to_date(?), 'dy') from dual";

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
	public List<Schedule> getmonthList(int year, int month, int day) { // 해당 날짜 일정 리스트에 저장하여 반환

		String getyear = String.valueOf(year);
		String getmonth = String.valueOf(month);
		String getday = String.valueOf(day);
		String getymd = getyear + "/" + getmonth + "/" + getday;
		
		List<Schedule> monthList = new ArrayList();

		String sql = "";
		sql += "select scheduleno, hometeam, awayteam";
		sql += " from schedule";
		sql += " where gamedate = ?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, getymd);

			rs = ps.executeQuery();

			while (rs.next()) {
				Schedule schedule = new Schedule();
				schedule.setScheduleno(rs.getInt("scheduleno"));
				schedule.setHometeam(rs.getString("hometeam"));
				schedule.setAwayteam(rs.getString("awayteam"));
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

	@Override
	public List<Schedule> getsearchList(int year, int month, int day, String event, String team, String region) {
		
		//DB에서 날짜 검색을 위해 연/월/일로 저장
		String getyear = String.valueOf(year);
		String getmonth = String.valueOf(month);
		String getday = String.valueOf(day);
		String getymd = getyear+"/"+getmonth+"/"+getday;
		
		
		List<Schedule> searchList = new ArrayList();
		try {
			if(event.equals("1")) {
				if(team.equals("all")) { 
					if(region.equals("all")) {	// 모든 야구팀 모든 지역 검색
						String sql="";	
						sql += "select scheduleno, hometeam, awayteam from schedule";
						sql += " where hometeam in(";
						sql += " select teamname from team ";
						sql += " where event = '1') and gamedate = ?";
					
						ps = conn.prepareStatement(sql);
						ps.setString(1, getymd);
						rs = ps.executeQuery();
						
						while (rs.next()) {
							Schedule schedule = new Schedule();
							schedule.setScheduleno(rs.getInt("scheduleno"));
							schedule.setHometeam(rs.getString("hometeam"));
							schedule.setAwayteam(rs.getString("awayteam"));
							searchList.add(schedule);
						}
						
					} else {	// 모든 야구팀 지역 선택 검색
						String sql="";
						sql += "select scheduleno, hometeam, awayteam from schedule";
						sql += " where hometeam in(";
						sql += " select teamname from team";
						sql += " where event = '1' and region = ?) and gamedate = ?";
						
						ps = conn.prepareStatement(sql);
						ps.setString(1, region);
						ps.setString(2, getymd);
						rs = ps.executeQuery();
						
						while (rs.next()) {
							Schedule schedule = new Schedule();
							schedule.setScheduleno(rs.getInt("scheduleno"));
							schedule.setHometeam(rs.getString("hometeam"));
							schedule.setAwayteam(rs.getString("awayteam"));;
							searchList.add(schedule);
						}
					}
					
				} else {
					if(region.equals("all")) {	// 선택된 야구팀 모든 지역 검색
						String sql="";
						sql += "select scheduleno, hometeam, awayteam from schedule";
						sql += " where (hometeam = ? or awayteam = ?)";
						sql += "and gamedate = ?";
						
						ps = conn.prepareStatement(sql);
						ps.setString(1, team);
						ps.setString(2, team);
						ps.setString(3, getymd);
						rs = ps.executeQuery();
						
						while (rs.next()) {
							Schedule schedule = new Schedule();
							schedule.setScheduleno(rs.getInt("scheduleno"));
							schedule.setHometeam(rs.getString("hometeam"));
							schedule.setAwayteam(rs.getString("awayteam"));
							searchList.add(schedule);
						}
						
					} else {	//	선택된 야구팀 선택된 지역 검색
						String sql="";
						sql += "select scheduleno, hometeam, awayteam from schedule";
						sql += " where hometeam in(select teamname from team";
						sql += "  where region = ?) and";
						sql += " (hometeam = ? or awayteam = ?) and gamedate = ?";
						
						ps = conn.prepareStatement(sql);
						ps.setString(1, region);
						ps.setString(2, team);
						ps.setString(3, team);
						ps.setString(4, getymd);
						rs = ps.executeQuery();
						
						while (rs.next()) {
							Schedule schedule = new Schedule();
							schedule.setScheduleno(rs.getInt("scheduleno"));
							schedule.setHometeam(rs.getString("hometeam"));
							schedule.setAwayteam(rs.getString("awayteam"));
							searchList.add(schedule);
						}
					}
				}
			} else if(event.equals("2")) {
				if(team.equals("all")) {
					if(region.equals("all")) {
						String sql="";	//	모든 축구팀 모든 지역 검색
						sql += "select scheduleno, hometeam, awayteam from schedule";
						sql += " where hometeam in(";
						sql += " select teamname from team";
						sql += " where event = '2') and gamedate = ?";
					
						ps = conn.prepareStatement(sql);
						ps.setString(1, getymd);
						rs = ps.executeQuery();
						
						while (rs.next()) {
							Schedule schedule = new Schedule();
							schedule.setScheduleno(rs.getInt("scheduleno"));
							schedule.setHometeam(rs.getString("hometeam"));
							schedule.setAwayteam(rs.getString("awayteam"));
							searchList.add(schedule);
						}
						
					} else {	//	모든 축구팀 지역 선택 검색
						String sql="";
						sql += "select scheduleno, hometeam, awayteam from schedule";
						sql += " where hometeam in(";
						sql += " select teamname from team";
						sql += " where event = '2' and region = ?) and gamedate = ?";
						
						ps = conn.prepareStatement(sql);
						ps.setString(1, region);
						ps.setString(2, getymd);
						rs = ps.executeQuery();
						
						while (rs.next()) {
							Schedule schedule = new Schedule();
							schedule.setScheduleno(rs.getInt("scheduleno"));
							schedule.setHometeam(rs.getString("hometeam"));
							schedule.setAwayteam(rs.getString("awayteam"));
							searchList.add(schedule);
						}
					}
				} else {
					if(region.equals("all")) {	//	선택된 축구팀 모든 지역 검색
						String sql="";
						sql += "select scheduleno, hometeam, awayteam from schedule";
						sql += " where (hometeam = ? or awayteam = ?)";
						sql += "and gamedate = ?";
						
						ps = conn.prepareStatement(sql);
						ps.setString(1, team);
						ps.setString(2, team);
						ps.setString(3, getymd);
						rs = ps.executeQuery();
						
						while (rs.next()) {
							Schedule schedule = new Schedule();
							schedule.setScheduleno(rs.getInt("scheduleno"));
							schedule.setHometeam(rs.getString("hometeam"));
							schedule.setAwayteam(rs.getString("awayteam"));
							searchList.add(schedule);
						}
						
					} else {	//	선택된 축구팀 선택 지역 검색
						String sql="";
						sql += "select scheduleno, hometeam, awayteam from schedule";
						sql += " where hometeam in(select teamname from team";
						sql += "  where region = ?) and";
						sql += " (hometeam = ? or awayteam = ?) and gamedate = ?";
						
						ps = conn.prepareStatement(sql);
						ps.setString(1, region);
						ps.setString(2, team);
						ps.setString(3, team);
						ps.setString(4, getymd);
						rs = ps.executeQuery();
						
						while (rs.next()) {
							Schedule schedule = new Schedule();
							schedule.setScheduleno(rs.getInt("scheduleno"));
							schedule.setHometeam(rs.getString("hometeam"));
							schedule.setAwayteam(rs.getString("awayteam"));
							searchList.add(schedule);
						}
					}
				}
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
		return searchList;
		
		
		
	}

	@Override
	public List<Icon> iconList() {	//	아이콘 DB List에 저장
		List<Icon> iconList = new ArrayList();
		;
		String sql = "";
		sql += "select iconno, iconname, storename";
		sql += " from icon order by iconno";

		try {
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				Icon icon = new Icon();
				icon.setIconno(rs.getInt("iconno"));
				icon.setIconname(rs.getString("iconname"));
				icon.setStorename(rs.getString("storename"));

				iconList.add(icon);

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
		return iconList;
	}
}
