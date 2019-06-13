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
		
 		String event=(String)req.getParameter("event");	//	선택된 종목 값 전달받아 저장.
		int chkEvent=0;	//	저장된 종목 값 숫자로 변환화여 저장할 변수 생성
		if(event!=null) {
			chkEvent=Integer.parseInt(event);	//	종목 값 숫자로 변환하여 저장 1: 야구, 2: 축구
		}
		String team=null;	//	전달 받은 팀 이름 저장할 변수 생성
		String region=null;	//	전달 받은 지역 이름 저장할 변수 생성
		if(chkEvent==1) {	//	야구 팀, 지역 저장
			team=(String)req.getParameter("baseballTeam");
			region=(String)req.getParameter("BBregion");
		} else if(chkEvent==2){	//	축구 팀, 지역 저장
			team=(String)req.getParameter("soccerTeam");
			region=(String)req.getParameter("SCregion");
		} else {
			team = "all";
			region = "all";
		}
 		
 		Map<Integer, List> monthMap = new HashMap<>();	//	hashmap 생성
 		if(event==null) {	//	종목 값 없을 경우 월 일정 모두 검색하여 저장
			for(int i=0; i<lastDate; i++) {	//	각 날짜에 해당 날짜에 있는 경기 일정 저장.
				List monthList = scheduleService.getmonthList(year, month, i+1);
				monthMap.put(i, monthList);
			}
		} else {	//	종목 값 있을 경우 상세검색으로 들어온 종목, 팀이름, 지역에 맞춰 검색.
			for(int i=0; i<lastDate; i++) {	//	각 날짜에 해당 날짜에 있는 경기 일정 저장.
				List monthList = scheduleService.searchSchedule(year, month, i+1, event, team, region);
				monthMap.put(i, monthList);
			}
		} 
 		
 		// 아이콘 DB 값 iconList에 저장
 		List iconList = scheduleService.iconList();
 		
		req.setAttribute("month", month);
		req.setAttribute("week", week);
		req.setAttribute("monthWeek", monthWeek);
		req.setAttribute("prevMonthWeek", prevMonthWeek);
		req.setAttribute("startDay", startDay);
		req.setAttribute("lastDate", lastDate);
		req.setAttribute("sunOfWeek", sunOfWeek);
		req.setAttribute("monthMap", monthMap);
		req.setAttribute("iconList", iconList);
		
		if(chkEvent==0) {
			req.setAttribute("chkEvent", chkEvent);
			
		} else {
			req.setAttribute("chkEvent", chkEvent);
			req.setAttribute("team", team);
			req.setAttribute("region", region);
		}
		
		req.getRequestDispatcher(	//	jsp 경로 지정
				"/WEB-INF/views/schedule/scheduleWeek.jsp").forward(req, resp);
 		

	}

}
