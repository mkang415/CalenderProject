package schedule.controller;

import java.io.IOException;
import java.util.ArrayList;
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
		
//		System.out.println(year+"/"+month+"/"+lastDate);
		
		String event=(String)req.getParameter("event");	//	선택된 종목 값 전달받아 저장.
		int chkEvent=0;	//	저장된 종목 값 숫자로 변환화여 저장할 변수 생성
		if(event!=null) {
			chkEvent=Integer.parseInt(event);	//	종목 값 숫자로 변환하여 저장 (1: 야구, 2: 축구)
		}
//		System.out.println(chkEvent);
//		System.out.println(event);
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
		
//		System.out.println(team+"/"+region);
		
		Map<Integer, List> monthMap = new HashMap<>();	//	hashmap 생성
		if(event==null) {	//	종목 값 없을 경우 월 일정 모두 검색하여 저장
			
			for(int i=0; i<lastDate; i++) {	//	각 날짜에 해당 날짜에 있는 경기 일정 저장.
				List monthList = scheduleService.getmonthList(year, month, i+1);
				monthMap.put(i, monthList);
//				for(int j=0; j<monthMap.get(i).size(); j++) {
//				System.out.println(monthMap.get(i).get(j).toString());
//				}
			}
		} else {	//	종목 값 있을 경우 상세검색으로 들어온 종목, 팀이름, 지역에 맞춰 검색.
			for(int i=0; i<lastDate; i++) {	//	각 날짜에 해당 날짜에 있는 경기 일정 저장.
				List monthList = scheduleService.searchSchedule(year, month, i+1, event, team, region);				
				monthMap.put(i, monthList);
			}
		} 
			
		// 아이콘 DB 값 iconList에 저장
		List iconList = scheduleService.iconList();
		
				
		//	값 전달.
		req.setAttribute("month", month);
		req.setAttribute("lastDate", lastDate);
		req.setAttribute("startDay", startDay);
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
				"/WEB-INF/views/schedule/scheduleMonth.jsp").forward(req, resp);
	}

}
