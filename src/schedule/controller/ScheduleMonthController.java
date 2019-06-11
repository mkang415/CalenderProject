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

@WebServlet("/schedule/month")
public class ScheduleMonthController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ScheduleService scheduleService = new ScheduleServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		Calendar c = Calendar.getInstance();
		
		int year = c.get(Calendar.YEAR);	// 올해 년도
		int month = 0;
		if(req.getParameter("mno") != null) {	//	parameter에 월 값이 있을 경우 해당 월로 값 지정
			String getMonth = req.getParameter("mno");
			month = Integer.parseInt(getMonth);
		} else {								//	parameter에 월 값이 없을 경우 이번 달로 값 지정
			month = c.get(Calendar.MONTH)+1;
		}
			
		
		int lastDate = scheduleService.getLastDate(year, month);	//	월의 마지말 날짜 구해서 저장
		int startDay = scheduleService.getstartday(year, month);	//	월의 시작 요일 구해서 저장.
		
		Map<Integer, List> monthMap = new HashMap<>();	//	hashmap 생성
		for(int i=0; i<lastDate; i++) {	//	각 날짜에 해당 날짜에 있는 경기 일정 저장.
			List monthList = scheduleService.getmonthList(year, month, i+1);
			monthMap.put(i, monthList);
		}
		
		//	값 전달.
		req.setAttribute("month", month);
		req.setAttribute("lastDate", lastDate);
		req.setAttribute("startDay", startDay);
		req.setAttribute("monthMap", monthMap);
		

		req.getRequestDispatcher(	//	jsp 경로 지정
				"/WEB-INF/views/schedule/scheduleMonth.jsp").forward(req, resp);
	}

}
