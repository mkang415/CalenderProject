package schedule.service.impl;

import java.util.Calendar;
import java.util.List;

import schedule.dao.face.ScheduleDao;
import schedule.dao.impl.ScheduleDaoImpl;
import schedule.service.face.ScheduleService;

public class ScheduleServiceImpl implements ScheduleService{

	ScheduleDao scheduleDao = new ScheduleDaoImpl();
	
	@Override
	public int getLastDate(int year, int month) {	//	해당 월 일수 구해서 값 반환

		int day=0;
		
		if(month==2) {
			if (((year % 400) == 0 || (year % 4) == 0 && (year % 100) != 0)) {
				day = 29;
			} else {
				day = 28;
			}
		} else if(month==4 || month==6 || month==9 || month==11) {
			day = 30;
		} else {
			day = 31;
		}
		return day;
	}

	@Override
	public int getstartday(int year, int month) {	//	달의 시작 요일 구해서 숫자로 반환
		int startday = -1;
		String day = scheduleDao.startday(year, month);
		switch (day) {
		case "일":
			startday = 0;
			break;
		case "월":
			startday = 1;
			break;
		case "화":
			startday = 2;
			break;
		case "수":
			startday = 3;
			break;
		case "목":
			startday = 4;
			break;
		case "금":
			startday = 5;
			break;
		case "토":
			startday = 6;
			break;

		default:
			startday = 7;
			break;
		}
		return startday;
	}

	@Override
	public List getmonthList(int year, int month, int day) {	//	sql에 저장된 날짜의 일정 list에 저장
		
		List monthList = scheduleDao.getmonthList(year, month, day);
		return monthList;
	}

	@Override
	public int getMonthWeek(int year, int month) {
		int week = 0;
		int lastDate = getLastDate(year, month);
		int getStartDay = getstartday(year, month);
		switch (getStartDay) {
		case 0:
			if (lastDate>=29) {
				week = 5;
			} else
				week = 4;
			break;
		case 1:
			if (lastDate>=28) {
				week = 5;
			}
			break;
		case 2:
			if (lastDate>=27) {
				week = 5;
			}
			break;
		case 3:
			if (lastDate>=26) {
				week = 5;
			}
			break;
		case 4:
			if (lastDate>=25) {
				week = 5;
			}
			break;
		case 5:
			if (lastDate==31) {
				week = 6;
			} else
				week = 5;
			break;
		case 6:
			if (lastDate>=30) {
				week = 6;
			} else
				week = 5;
			break;

		default:
			System.out.println("오류");
			break;
		}
		
		return week;
	}

	@Override
	public int getSysWeek() {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int date = c.get(Calendar.DAY_OF_MONTH);
		int getStartDay = getstartday(year, month);
		
		int week = 0;
		switch (getStartDay) {
		case 0:
			if (date<=7) {
				week = 1;
			} else if (date<=14) {
				week = 2;
			} else if (date<=21) {
				week = 3;
			} else if (date<=28) {
				week = 4;
			} else if (date<=31) {
				week = 5;
			} else
				System.out.println("오류");
			break;
		case 1:
			if (date<=6) {
				week = 1;
			} else if (date<=13) {
				week = 2;
			} else if (date<=20) {
				week = 3;
			} else if (date<=27) {
				week = 4;
			} else if (date<=31) {
				week = 5;
			} else
				System.out.println("오류");
			break;
		case 2:
			if (date<=5) {
				week = 1;
			} else if (date<=12) {
				week = 2;
			} else if (date<=19) {
				week = 3;
			} else if (date<=26) {
				week = 4;
			} else if (date<=31) {
				week = 5;
			} else
				System.out.println("오류");
			break;
		case 3:
			if (date<=4) {
				week = 1;
			} else if (date<=11) {
				week = 2;
			} else if (date<=18) {
				week = 3;
			} else if (date<=25) {
				week = 4;
			} else if (date<=31) {
				week = 5;
			} else
				System.out.println("오류");
			break;
		case 4:
			if (date<=3) {
				week = 1;
			} else if (date<=10) {
				week = 2;
			} else if (date<=17) {
				week = 3;
			} else if (date<=24) {
				week = 4;
			} else if (date<=31) {
				week = 5;
			} else
				System.out.println("오류");
			break;
		case 5:
			if (date<=2) {
				week = 1;
			} else if (date<=9) {
				week = 2;
			} else if (date<=16) {
				week = 3;
			} else if (date<=23) {
				week = 4;
			} else if (date<=30) {
				week = 5;
			} else
				week = 6;
			break;
		case 6:
			if (date==1) {
				week = 1;
			} else if (date<=8) {
				week = 2;
			} else if (date<=15) {
				week = 3;
			} else if (date<=22) {
				week = 4;
			} else if (date<=29) {
				week = 5;
			} else
				week = 6;
			break;

		default:
			System.out.println("오류");
			break;
		}
		
		return week;
	}

	@Override
	public int getsunOfWeek(int year, int month, int week) {
		int getStartDay = getstartday(year, month);
		int sunOfWeek = 0;
		switch (getStartDay) {
		case 0:
			switch (week) {
			case 2:
				sunOfWeek = 8;
				break;
			case 3:
				sunOfWeek = 15;
				break;
			case 4:
				sunOfWeek = 22;
				break;
			case 5:
				sunOfWeek = 29;
				break;
			default:
				break;
			}
			break;
		case 1:
			switch (week) {
			case 2:
				sunOfWeek = 7;
				break;
			case 3:
				sunOfWeek = 14;
				break;
			case 4:
				sunOfWeek = 21;
				break;
			case 5:
				sunOfWeek = 28;
				break;
			default:
				break;
			}
			break;
		case 2:
			switch (week) {
			case 2:
				sunOfWeek = 6;
				break;
			case 3:
				sunOfWeek = 13;
				break;
			case 4:
				sunOfWeek = 20;
				break;
			case 5:
				sunOfWeek = 27;
				break;
			default:
				break;
			}
			break;
		case 3:
			switch (week) {
			case 2:
				sunOfWeek = 5;
				break;
			case 3:
				sunOfWeek = 12;
				break;
			case 4:
				sunOfWeek = 19;
				break;
			case 5:
				sunOfWeek = 26;
				break;
			default:
				break;
			}
			break;
		case 4:
			switch (week) {
			case 2:
				sunOfWeek = 4;
				break;
			case 3:
				sunOfWeek = 11;
				break;
			case 4:
				sunOfWeek = 18;
				break;
			case 5:
				sunOfWeek = 25;
				break;
			default:
				break;
			}
			break;
		case 5:
			switch (week) {
			case 2:
				sunOfWeek = 3;
				break;
			case 3:
				sunOfWeek = 10;
				break;
			case 4:
				sunOfWeek = 17;
				break;
			case 5:
				sunOfWeek = 24;
				break;
			case 6:
				sunOfWeek = 31;
				break;
			default:
				break;
			}
			break;
		case 6:
			switch (week) {
			case 2:
				sunOfWeek = 2;
				break;
			case 3:
				sunOfWeek = 9;
				break;
			case 4:
				sunOfWeek = 16;
				break;
			case 5:
				sunOfWeek = 23;
				break;
			case 6:
				sunOfWeek = 30;
				break;
			default:
				break;
			}
			break;
		}
		
		return sunOfWeek;
	}

	@Override
	public List searchSchedule(int year, int month, int day, String event, String team, String region) {
		
		return scheduleDao.getsearchList(year, month, day, event, team, region);
		
	}

	@Override
	public List iconList() {
		
		return scheduleDao.iconList();
	}
	
}
