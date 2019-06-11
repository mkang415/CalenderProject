<%@page import="dto.Schedule"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/WEB-INF/views/layout/header.jsp"></c:import>

<style type="text/css">

.date {
	height : 30px;
	text-align: right;
}

.schedule {
	height : 190px;
	text-align: center;
	font-size: 13px;
	
}
</style>

<%	int month = (int)request.getAttribute("month"); %>
<%	int week = (int)request.getAttribute("week"); %>
<%	int monthWeek = (int)request.getAttribute("monthWeek"); %>
<%	int startDay = (int)request.getAttribute("startDay"); %>
<%	int lastDate = (int)request.getAttribute("lastDate"); %>
<%	int sunOfWeek = (int)request.getAttribute("sunOfWeek"); %>

<%	Map<Integer, List> monthMap = new HashMap<>(); %>
<% for(int i=0; i<lastDate; i++) {
	List<Schedule> monthList = (List)((HashMap)request.getAttribute("monthMap")).get(i);
	monthMap.put(i, monthList);
}%>
<div style="width:200px; background: #b4b4b4; float:left; height:660px; text-align: center">
<br>
<br>
<h4>상세검색</h4>

</div>
<div style="width:1200px; height: 660px;">
<br>
<br>
<a href="/schedule/month?mno=${month}">월</a>
<br>
<br>
<table border="1">
	<tr>
		<td align="center">
		<% if(month != 1 || week != 1) { %>
			<% if(week == 1) { %>
				<a href="/schedule/week?mno=${month-1}&wno=${prevMonthWeek}">지난주</a>
			<% } else {%>
				<a href="/schedule/week?mno=${month}&wno=${week-1}">지난주</a>
			<% }
		} %></td>
		<td colspan="5" align="center" style= "height: 30px;">
			${month}월 ${week}주차</td>
		<td align="center" id="next">
		<% if(month != 12 || week != 5) { %>
			<% if(week == monthWeek) { %>
				<a href="/schedule/week?mno=${month+1}&wno=1">다음주</a>
			<% } else {%>
				<a href="/schedule/week?mno=${month}&wno=${week+1}">다음주</a>
			<% }
		} %></td>
		</tr>
		
	<tr align="center">									
		<td align="center" style="width: 150px;">일</td> 	
		<td align="center" style="width: 150px;">월</td>	
		<td align="center" style="width: 150px;">화</td>
		<td align="center" style="width: 150px;">수</td> 
		<td align="center" style="width: 150px;">목</td> 
		<td align="center" style="width: 150px;">금</td>
		<td align="center" style="width: 150px;">토</td> 
	</tr>
	<tr style="height: 220px;">
	<% if(week==1) { 
		for(int i=1; i<=7; i++) { %>
		<td>
		<% if(startDay<i) { %>
			<div class="date">
			<%= i-startDay %>&nbsp;&nbsp;&nbsp;
			</div>
			<div class="schedule">
			<br>
			<% List<Schedule> monthList = monthMap.get(i-startDay-1);
					for(int j=0; j<monthList.size(); j++) { %>
						<%= (monthList.get(j).getHometeam()) %>
							 : <%= (monthList.get(j).getAwayteam()) %><br>
					<% } %>
			</div>
		<% } %>
		</td>
	<% }
	} else if(week==monthWeek) {
		for(int i=sunOfWeek; i<=lastDate; i++) { %>
		<td align="center">
			<div class="date">
			<%= i %>&nbsp;&nbsp;&nbsp;
			</div>
			<div class="schedule">
			<br>
			<% List<Schedule> monthList = monthMap.get(i-1);
					for(int j=0; j<monthList.size(); j++) { %>
						<%= (monthList.get(j).getHometeam()) %>
							 : <%= (monthList.get(j).getAwayteam()) %><br>
					<% } %>
			</div>
		<% } %>
		</td>
	<% } else {
		for(int i=sunOfWeek; i<sunOfWeek+7; i++) { %>
		<td align="center">
			<div class="date">
			<%= i %>&nbsp;&nbsp;&nbsp;
			</div>
			<div class="schedule">
			<br>
			<% List<Schedule> monthList = monthMap.get(i-1);
					for(int j=0; j<monthList.size(); j++) { %>
						<%= (monthList.get(j).getHometeam()) %>
							 : <%= (monthList.get(j).getAwayteam()) %><br>
					<% } %>
			</div>
		<% } %>
		</td>
	<% } %>
	</tr>	
</table>

<br>
<br>
</div>
<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>