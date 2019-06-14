package manage.controller;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manage.service.face.ManageScheduleService;
import manage.service.impl.ManageScheduleServiceImpl;
import schedule.service.face.ScheduleService;
import schedule.service.impl.ScheduleServiceImpl;


@WebServlet("/manage/deleteSchedule")
public class ManageDeleteScheduleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ScheduleService scheduleService = new ScheduleServiceImpl();
	ManageScheduleService manageScheduleService = new ManageScheduleServiceImpl();
       
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
		
		Map<Integer, List> monthMap = new HashMap<>();
		
		for(int i=0; i<lastDate; i++) {	//	각 날짜에 해당 날짜에 있는 경기 일정 저장.
			List monthList = scheduleService.getmonthList(year, month, i+1);
			monthMap.put(i, monthList);
//			for(int j=0; j<monthMap.get(i).size(); j++) {
//			System.out.println(monthMap.get(i).get(j).toString());
		}
		
		List iconList = scheduleService.iconList();
		
		req.setAttribute("month", month);
		req.setAttribute("lastDate", lastDate);
		req.setAttribute("startDay", startDay);
		req.setAttribute("monthMap", monthMap);
		req.setAttribute("iconList", iconList);
		
    	req.getRequestDispatcher(	//	jsp 경로 지정
				"/WEB-INF/views/manage/deleteSchedule.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
    		throws ServletException, IOException {
    	
    	String names = req.getParameter("names");
    	int no = Integer.parseInt(names);
    	
    	if (!"".equals(names) && names != null) {
			manageScheduleService.deleteSchedule(no);
		}
    	
    	String address = null;
    	
    	if(req.getParameter("mno") != null) {	//	parameter에 월 값이 있을 경우 해당 월로 값 지정
			String getMonth = req.getParameter("mno");
			address = "/manage/deleteSchedule?mno="+getMonth;
		} else {								//	parameter에 월 값이 없을 경우 이번 달로 값 지정
			address = "/manage/deleteSchedule";
		}
    	
    	resp.sendRedirect(address);
    }
    
}
