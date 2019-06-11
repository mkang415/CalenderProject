package schedule.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import schedule.service.face.ScheduleService;
import schedule.service.impl.ScheduleServiceImpl;

@WebServlet("/schedule/week")
public class ScheduleWeekController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ScheduleService scheduleService = new ScheduleServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);

		int month = 0;	//	현재 월 
		if(req.getParameter("mno") != null) {	
			String getMonth = req.getParameter("mno");
			month = Integer.parseInt(getMonth);
		} else {								
			month = c.get(Calendar.MONTH)+1;
		}
		int week = 0;	//	현재 주
		if(req.getParameter("wno") != null) {	
			String getMonth = req.getParameter("wno");
			week = Integer.parseInt(getMonth);
		} else {								
			week = scheduleService.getSysWeek();
		}
		
 		
 		int monthWeek = scheduleService.getMonthWeek(year, month);	//	현재 달 마지막 주
 		int prevMonthWeek = 0;
 		if(month>1) {
 			prevMonthWeek=scheduleService.getMonthWeek(year, month-1);
 		}
 		int startDay = scheduleService.getstartday(year, month);
 		int lastDate = scheduleService.getLastDate(year, month);
 		int sunOfWeek = scheduleService.getsunOfWeek(year, month, week);
		
 		Map<Integer, List> monthMap = new HashMap<>();	//	hashmap 생성
		for(int i=0; i<lastDate; i++) {	//	각 날짜에 해당 날짜에 있는 경기 일정 저장.
			List monthList = scheduleService.getmonthList(year, month, i+1);
			monthMap.put(i, monthList);
		}
 		
		req.setAttribute("month", month);
		req.setAttribute("week", week);
		req.setAttribute("monthWeek", monthWeek);
		req.setAttribute("prevMonthWeek", prevMonthWeek);
		req.setAttribute("startDay", startDay);
		req.setAttribute("lastDate", lastDate);
		req.setAttribute("sunOfWeek", sunOfWeek);
		req.setAttribute("monthMap", monthMap);
		
		req.getRequestDispatcher(	//	jsp 경로 지정
				"/WEB-INF/views/schedule/scheduleWeek.jsp").forward(req, resp);
 		

	}

}
