<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="dto.Schedule"%>
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

<!-- 전달받은 값 저장 -->
<%	int month = (int)request.getAttribute("month"); %>
<%	int lastDate = (int)request.getAttribute("lastDate"); %>
<%	int startDay = (int)request.getAttribute("startDay"); %>

<%	Map<Integer, List> monthMap = new HashMap<>(); %>
<% for(int i=0; i<lastDate; i++) {
	List<Schedule> monthList = (List)((HashMap)request.getAttribute("monthMap")).get(i);
	monthMap.put(i, monthList);
}%>
<div style="width:200px; background: #b4b4b4; float:left; height:1510px; text-align: center">
<br>
<br>
<form action="/schedule/month" method="post">
<fieldset>
<legend>상 세 검 색</legend>
</fieldset>
</form>

</div>
<div style="width:1200px; height: 1510px;">
<br>
<br>
<a href="/schedule/week?mno=${month}">주</a>
<br>
<br>
<!-- 달력 테이블 생성 -->
<table border="1">
	<tr>
		<td style="text-align: center;">
		<% if(month != 1) { %>
			<a href="/schedule/month?mno=${month-1}">이전달</a>
		<% } %></td>
		<td colspan="5" style= "height: 30px; text-align: center;">
			${month}월</td>
		<td style="text-align: center;">
		<% if(month != 12) { %>
			<a href="/schedule/month?mno=${month+1}">다음달</a>
		<% } %></td>
	</tr>
		
	<tr style="text-align: center;">	<!-- startDay 값 -->
		<td style="width: 150px;">일</td> 	<!-- 0 -->
		<td style="width: 150px;">월</td>	<!-- 1 -->
		<td style="width: 150px;">화</td> 	<!-- 2 -->
		<td style="width: 150px;">수</td> 	<!-- 3 -->
		<td style="width: 150px;">목</td> 	<!-- 4 -->
		<td style="width: 150px;">금</td> 	<!-- 5 -->
		<td style="width: 150px;">토</td> 	<!-- 6 -->
	</tr>
	<% for(int i=1; i<=lastDate+startDay; i++) { /* 달력 생성을 위한 반복문 */
		if(i%7==1) { %>
			<tr style="height: 220px;">
		<% } %>
		<td>
		<% if(i>startDay) { %>	<!-- 달력 1일 날짜 위치에서 시작하기 위한 조건문 -->
		<div class="date">
			<%=i-startDay %>&nbsp;&nbsp;&nbsp;	<!-- 해당 날짜 출력 -->
		</div>
		<div class="schedule">
			<br>
			<% List<Schedule> monthList = monthMap.get(i-startDay-1);
				for(int j=0; j<monthList.size(); j++) { %>	<!-- 해당날짜 일정 출력 -->
					<%= (monthList.get(j).getHometeam()) %>
						 : <%= (monthList.get(j).getAwayteam()) %><br>
				<% } %>
			<% } %>
		</div>	
		</td>
		<% if(i%7==0) { %>
			</tr>
		<% } %>
	<% } %>		
</table>

<br>
<br>
</div>
<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>
