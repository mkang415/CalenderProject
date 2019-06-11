package schedule.service.face;

import java.util.List;

import dto.Schedule;

public interface ScheduleService {

	public int getLastDate(int year, int month);
	public int getstartday(int year, int month);
	public List getmonthList(int year, int month, int day);
	public int getMonthWeek(int year, int month);
	public int getSysWeek();
	public int getsunOfWeek(int year, int month, int week);
}
