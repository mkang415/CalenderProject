package manage.dao.face;

import dto.Schedule;

public interface ManageScheduleDao {

	public void inputSchedule(int event, String homeTeam, String awayTeam, String gameDate);

	public void deleteSchedule(int no);

}
