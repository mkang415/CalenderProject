package manage.service.face;

import javax.servlet.http.HttpServletRequest;

import dto.Schedule;

public interface ManageScheduleService {

	public void inputSchedule(HttpServletRequest req);

	public void deleteSchedule(int no);


	
}
