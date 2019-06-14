<%@page import="dto.Icon"%>
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
	height : 200px;
	text-align: center;
	font-size: 13px;
	
}
</style>

<script type="text/javascript">
$(document).ready(function() {

	
    $('#btnDelete').click(function(){
        var $checkboxes = $("input:checkbox[name='checkRow']:checked");
        
        // 체크된 대상들을 map으로 만들고 map을 문자열로 만들기
        var map = $checkboxes.map(function() {
           return $(this).val();
        });
        var names = map.get().join(",");
        
        // 전송 폼
        var $form = $("<form>")
           .attr("action", "/manage/deleteSchedule")
           .attr("method", "post")
           .append(
              $("<input>")
                 .attr("type", "hidden")
                 .attr("name", "names")
                 .attr("value", names)
           );
        $(document.body).append($form);
        $form.submit();
        
     });
	
});
</script>

<!-- 전달받은 값 저장 -->
<%	int month = (int)request.getAttribute("month"); %>
<%	int lastDate = (int)request.getAttribute("lastDate"); %>
<%	int startDay = (int)request.getAttribute("startDay"); %>

<%	Map<Integer, List> monthMap = new HashMap<>(); %>
<%	for(int i=0; i<lastDate; i++) {
	List<Schedule> monthList = (List)((HashMap)request.getAttribute("monthMap")).get(i);
	monthMap.put(i, monthList);
}%>

<!-- 서블릿에서 전달한 iconList 저장 -->
<% List<Icon> iconList = (List)request.getAttribute("iconList"); %>

<!-- icon 사용을 위해 hashmap 사용. 키는 아이콘이름=팀이름. 값은 아이콘 파일명 -->
<% Map<String, String> chkIcon = new HashMap<>(); %>
<%  for(int i=0; i<iconList.size(); i++) {
	chkIcon.put(iconList.get(i).getIconname(), iconList.get(i).getStorename());
}%>

<!-- 좌측 레이아웃 -->
<div style="width:200px; background: #b4b4b4; float:left; height:1790px; text-align: center">
</div>

<div style="width:1200px; height: 1750px;">
<br><br><br>
<!-- 달력 테이블 생성 -->
<table>
	<tr>
		<td style="width: 302px;">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="/manage/inputSchedule" style="font-size: 16px">추가</a>
		&nbsp;&nbsp;&nbsp; 
		<a href="/schedule/week" style="font-size: 16px">일정</a>
		</td>
		<td style= "width: 454px; height: 30px; text-align: center;">
		<% if(month != 1) { %>
				<a href="/manage/deleteSchedule?mno=${month-1}">이전달</a>
		<% } %>	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<strong style="font-size: 18px;">${month}월</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<% if(month != 12) {%>
				<a href="/manage/deleteSchedule?mno=${month+1}">다음달</a>
		<% } %>
		</td>
		<td style="width: 302px; text-align: right;">
			<button id="btnDelete" class="btn btn-danger">삭제</button>
		</td>
	</tr>
</table>
<br>
<br>

<form action="/manage/deleteSchedule" method="post">
<table border="1">
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
			<tr style="height: 270px;">
		<% } %>
		<td>
		<% if(i>startDay) { %>	<!-- 달력 1일 날짜 위치에서 시작하기 위한 조건문 -->
		<div class="date">
			<%=i-startDay %>&nbsp;&nbsp;&nbsp;	<!-- 해당 날짜 출력 -->
		</div>
		<div class="schedule">
			<% List<Schedule> monthList = monthMap.get(i-startDay-1);
				for(int j=0; j<monthList.size(); j++) { %>	<!-- 해당날짜 일정 출력 -->
					<input type="checkbox" name="checkRow" value="<%= monthList.get(j).getScheduleno() %>"/>
					<a href="/board/list?schno=<%= monthList.get(j).getScheduleno() %>">
					<!-- 홈팀 이름으로 아이콘 주소 값 가져와서 이미지 불러옴 -->
					<img src="/logo/<%= chkIcon.get(monthList.get(j).getHometeam())%>"
						style="width: 21px; height: 21px;"/>
					<%= (monthList.get(j).getHometeam()) %>
						 : <%= (monthList.get(j).getAwayteam()) %>
					<!-- 어웨이팀 이름으로 아이콘 주소 값 가져와서 이미지 불러옴 -->
					<img src="/logo/<%= chkIcon.get(monthList.get(j).getAwayteam())%>"
						style="width: 21px; height: 21px;"/><br>
					</a>
				<% } %>
			<% } %>
		</div>	
		</td>
		<% if(i%7==0) { %>
			</tr>
		<% } %>
	<% } %>		
</table>
</form>
<br>
<br>
</div>
<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>
