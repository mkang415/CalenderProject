package schedule.dao.face;

import java.util.List;

import dto.Schedule;

public interface ScheduleDao {

	public String startday(int year, int month);
	public List getmonthList(int year, int month, int day);
	public List getsearchList(int year, int month, int day, String event, String team, String region);
	public List iconList();
}
