package manage.service.impl;

import javax.servlet.http.HttpServletRequest;

import dto.Schedule;
import manage.dao.face.ManageScheduleDao;
import manage.dao.impl.ManageScheduleDaoImpl;
import manage.service.face.ManageScheduleService;

public class ManageScheduleServiceImpl implements ManageScheduleService{

	ManageScheduleDao manageScheduleDao = new ManageScheduleDaoImpl();
	
	@Override
	public void inputSchedule(HttpServletRequest req) {
		
		int event = 0;
		String homeTeam = null;
		String awayTeam = null;
		String gameDate = req.getParameter("gamedate");
		String param = req.getParameter("event");
		if(param != null) {
			event = Integer.parseInt(param);
			
			if(event == 1) {
				homeTeam = req.getParameter("bsHome");
				awayTeam = req.getParameter("bsAway");
			} else if(event == 2) {
				homeTeam = req.getParameter("scHome");
				awayTeam = req.getParameter("scAway");
			}
			
			manageScheduleDao.inputSchedule(event, homeTeam, awayTeam, gameDate);
		}
		
	}

	@Override
	public void deleteSchedule(int no) {
		
		manageScheduleDao.deleteSchedule(no);
		
	}

}
