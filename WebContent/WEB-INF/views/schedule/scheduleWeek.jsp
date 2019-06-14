<%@page import="dto.Icon"%>
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
	height : 200px;
	text-align: center;
	font-size: 12px;
	
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

<!-- 서블릿에서 전달한 iconList 저장 -->
<% List<Icon> iconList = (List)request.getAttribute("iconList"); %>

<!-- icon 사용을 위해 hashmap 사용. 키는 아이콘이름=팀이름. 값은 아이콘 파일명 -->
<% Map<String, String> chkIcon = new HashMap<>(); %>
<%  for(int i=0; i<iconList.size(); i++) {
	chkIcon.put(iconList.get(i).getIconname(), iconList.get(i).getStorename());
}%>

<!-- 상세 검색 조건 유지를 위해 값 전달받음 -->
<%	int chkEvent = (int)request.getAttribute("chkEvent"); %>
<%	String team = null; %>
<%	String region = null; %>
<%	if(chkEvent != 0) {
	team = (String)request.getAttribute("team");
	region = (String)request.getAttribute("region");
}%>

<script type="text/javascript">	//	종목 선택에 따라 팀, 지역 다르게 출력
	function setDisplay(v,idB,idS,Brg,Srg) {
		if(v=="1") {
			document.getElementById(idB).style.display="block";
			document.getElementById(idS).style.display="none";
			document.getElementById(Brg).style.display="block";
			document.getElementById(Srg).style.display="none";
		}else if(v=="2") {
			document.getElementById(idB).style.display="none";
			document.getElementById(idS).style.display="block";
			document.getElementById(Brg).style.display="none";
			document.getElementById(Srg).style.display="block";
		}
				
	};
</script>

<!-- 좌측 레이아웃 -->
<div style="width:200px; background: #b4b4b4; float:left; height:660px; text-align: center">
<br>
<br>
<br>

<!-- 상세 검색 값 폼으로 get방식 전달 -->
<form action="/schedule/week" method="get">
<fieldset>
<legend style="font-size: 18px;">상 세 검 색</legend>
<div style="width:200px; text-align: center;">
	<div>
		<input type="hidden" name="mno" value="${month}">	<!-- 선택 월 전달 -->
		<input type="hidden" name="wno" value="${week}">	<!-- 선택 주 전달 -->
		<strong style="font-size: 15px;"><br>종&nbsp;&nbsp;&nbsp;목</strong>
		<br>
		<br>
	</div>
	<div>	
			<!-- 야구, 축구 종목 라디오 버튼으로 선택 -->
		<% if(chkEvent!=2) { %>
		<input type="radio" id = "bb" name = "event" checked="checked" value = "1" 
			onclick="setDisplay(this.value,'selBB','selSC','BBregion','SCregion')"/>
		<label for="bb">야구 </label>&nbsp;&nbsp;&nbsp;
		<input type="radio" id = "sc" name = "event" value = "2"
			onclick="setDisplay(this.value,'selBB','selSC','BBregion','SCregion')"/>
		<label for="sc">축구</label>
		<% } else {%>
		<input type="radio" id = "bb" name = "event" value = "1"
			onclick="setDisplay(this.value,'selBB','selSC','BBregion','SCregion')"/>
		<label for="bb">야구 </label>&nbsp;&nbsp;&nbsp;
		<input type="radio" id = "sc" name = "event" checked="checked" value = "2"
			onclick="setDisplay(this.value,'selBB','selSC','BBregion','SCregion')"/>
		<label for="sc">축구</label>
		<% } %>
	</div>
	<div>
		<Strong style="font-size: 15px;"><br><br>팀&nbsp;&nbsp;선&nbsp;&nbsp;택</Strong>
		<br>
		<br>
	</div>
	<div id="selBB" <% if(chkEvent==2) { %>style="display: none"<% } %>>	
		<select  id="bbt" name="baseballTeam">	<!-- 야구 팀 선택 -->
			<option value="all"<% if("all".equals(team)) { %>selected<% } %>>--- 야구팀 ---</option>
			<option value="KT"<% if("KT".equals(team)) { %>selected<% } %>>KT 위즈</option>
			<option value="LG"<% if("LG".equals(team)) { %>selected<% } %>>LG 트윈스</option>
			<option value="NC"<% if("NC".equals(team)) { %>selected<% } %>>NC 다이노스</option>
			<option value="SK"<% if("SK".equals(team)) { %>selected<% } %>>SK 와이번스</option>
			<option value="기아"<% if("기아".equals(team)) { %>selected<% } %>>기아 타이거즈</option>
			<option value="두산"<% if("두산".equals(team)) { %>selected<% } %>>두산 베어스</option>
			<option value="롯데"<% if("롯데".equals(team)) { %>selected<% } %>>롯데 자이언츠</option>
			<option value="삼성"<% if("삼성".equals(team)) { %>selected<% } %>>삼성 라이온즈</option>
			<option value="키움"<% if("키움".equals(team)) { %>selected<% } %>>키움 히어로즈</option>
			<option value="한화"<% if("한화".equals(team)) { %>selected<% } %>>한화 이글스</option>
		</select>
	</div>
	
	<div id="selSC" <% if(chkEvent!=2) { %>style="display: none"<% } %>>
		<select id="sct" name="soccerTeam">	<!-- 축구 팀 선택 -->
			<option value="all"<% if("all".equals(team)) { %>selected<% } %>>--- 축구팀 ---</option>
			<option value="서울"<% if("서울".equals(team)) { %>selected<% } %>>FC 서울</option>
			<option value="강원"<% if("강원".equals(team)) { %>selected<% } %>>강원 FC</option>
			<option value="경남"<% if("경남".equals(team)) { %>selected<% } %>>경남 FC</option>
			<option value="대구"<% if("대구".equals(team)) { %>selected<% } %>>대구 FC</option>
			<option value="상주"<% if("상주".equals(team)) { %>selected<% } %>>상주 FC</option>
			<option value="성남"<% if("성남".equals(team)) { %>selected<% } %>>성남 FC</option>
			<option value="수원"<% if("수원".equals(team)) { %>selected<% } %>>수원 삼성</option>
			<option value="울산"<% if("울산".equals(team)) { %>selected<% } %>>울산 현대</option>
			<option value="인천"<% if("인천".equals(team)) { %>selected<% } %>>인천 유나이티드</option>
			<option value="전북"<% if("전북".equals(team)) { %>selected<% } %>>전북 현대모터스</option>
			<option value="제주"<% if("제주".equals(team)) { %>selected<% } %>>제주 유나이티드</option>
			<option value="포항"<% if("포항".equals(team)) { %>selected<% } %>>포항 스틸러스</option>
		</select>
	</div>
	
	<div>
		<Strong style="font-size: 15px;"><br><br>지&nbsp;&nbsp;&nbsp;역</Strong>
		<br>
		<br>
	</div>
		<!-- 야구 팀 지역 선택 -->
	<div id="BBregion" <% if(chkEvent==2) { %>style="display: none"<% } %>>	
		<select id="bbr" name="BBregion">
			<option value="all"<% if("all".equals(region)) { %>selected<% } %>>--- 전국 ---</option>
			<option value="서울"<% if("서울".equals(region)) { %>selected<% } %>>서울</option>
			<option value="인천"<% if("인천".equals(region)) { %>selected<% } %>>인천</option>
			<option value="수원"<% if("수원".equals(region)) { %>selected<% } %>>수원</option>
			<option value="대전"<% if("대전".equals(region)) { %>selected<% } %>>대전</option>
			<option value="대구"<% if("대구".equals(region)) { %>selected<% } %>>대구</option>
			<option value="광주"<% if("광주".equals(region)) { %>selected<% } %>>광주</option>
			<option value="부산"<% if("부산".equals(region)) { %>selected<% } %>>부산</option>
			<option value="창원"<% if("창원".equals(region)) { %>selected<% } %>>창원</option>
		</select>
	</div>
	
	<div id="SCregion" <% if(chkEvent!=2) { %>style="display: none"<% } %>>
		<select id="scr" name="SCregion">	<!-- 축구 팀 지역 선택 -->
			<option value="all"<% if("all".equals(region)) { %>selected<% } %>>--- 전국 ---</option>
			<option value="서울"<% if("서울".equals(region)) { %>selected<% } %>>서울</option>
			<option value="춘천"<% if("춘천".equals(region)) { %>selected<% } %>>춘천</option>
			<option value="인천"<% if("인천".equals(region)) { %>selected<% } %>>인천</option>
			<option value="성남"<% if("성남".equals(region)) { %>selected<% } %>>성남</option>
			<option value="수원"<% if("수원".equals(region)) { %>selected<% } %>>수원</option>
			<option value="상주"<% if("상주".equals(region)) { %>selected<% } %>>상주</option>
			<option value="포항"<% if("포항".equals(region)) { %>selected<% } %>>포항</option>
			<option value="전주"<% if("전주".equals(region)) { %>selected<% } %>>전주</option>
			<option value="대구"<% if("대구".equals(region)) { %>selected<% } %>>대구</option>
			<option value="울산"<% if("울산".equals(region)) { %>selected<% } %>>울산</option>
			<option value="창원"<% if("창원".equals(region)) { %>selected<% } %>>창원</option>
			<option value="제주"<% if("제주".equals(region)) { %>selected<% } %>>제주</option>
		</select>
	</div>
	<div>
		<br><br><br>
		<input type="submit" value="일정검색"/>	<!-- 값 전달 버튼 -->
	</div>
</div>
</fieldset>
</form>
</div>

<div style="width:1200px; height: 660px;">
<br>
<br>
<br>

<!-- 달력 테이블 생성 -->
<table>
	<tr>
		<td style="width: 302px;"></td>
		<td style= "width: 454px; height: 30px; text-align: center;">
		<% if(month != 1 || week != 1) { %>
			<% if(week == 1) {
				if(chkEvent==0) {%>	<!-- 상세검색 전달 -->
					<a href="/schedule/week?mno=${month-1}&wno=${prevMonthWeek}">지난주</a>
				<% } else if(chkEvent==1){%>
					<a href="/schedule/week?mno=${month-1}&wno=${prevMonthWeek}&event=${chkEvent}
						&baseballTeam=${team}&BBregion=${region}">지난주</a>
				<% } else {%>
					<a href="/schedule/week?mno=${month-1}&wno=${prevMonthWeek}&event=${chkEvent}
					&soccerTeam=${team}&SCregion=${region}">지난주</a>
				<% } %>
			<% } else {
				if(chkEvent==0) {%>	<!-- 상세검색 전달 -->
					<a href="/schedule/week?mno=${month}&wno=${week-1}">지난주</a>
				<% } else if(chkEvent==1){%>
					<a href="/schedule/week?mno=${month}&wno=${week-1}&event=${chkEvent}
						&baseballTeam=${team}&BBregion=${region}">지난주</a>
					<% } else {%>
					<a href="/schedule/week?mno=${month}&wno=${week-1}&event=${chkEvent}
						&soccerTeam=${team}&SCregion=${region}">지난주</a>
					<% } %>
				<% }
			} %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<strong style="font-size: 18px;">${month}월 ${week}주차</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<% if(month != 12 || week != 5) { %>
			<% if(week == monthWeek) {
				if(chkEvent==0) {%>
					<a href="/schedule/week?mno=${month+1}&wno=1">다음주</a>
				<% } else if(chkEvent==1){%>
					<a href="/schedule/week?mno=${month+1}&wno=1&event=${chkEvent}
						&baseballTeam=${team}&BBregion=${region}">다음주</a>
				<% } else {%>
					<a href="/schedule/week?mno=${month+1}&wno=1&event=${chkEvent}
						&soccerTeam=${team}&SCregion=${region}">다음주</a>
				<% } %>
			<% } else {
				if(chkEvent==0) {%>
					<a href="/schedule/week?mno=${month}&wno=${week+1}">다음주</a>
				<% } else if(chkEvent==1){%>
					<a href="/schedule/week?mno=${month}&wno=${week+1}&event=${chkEvent}
						&baseballTeam=${team}&BBregion=${region}">다음주</a>
				<% } else {%>
					<a href="/schedule/week?mno=${month}&wno=${week+1}&event=${chkEvent}
						&soccerTeam=${team}&SCregion=${region}">다음주</a>
				<% } %>
			<% }
		} %>
		</td>
		<td style="width: 302px; text-align: right;">
		<% if(chkEvent==0) {%>
			<a href="/schedule/month?mno=${month}" style="font-size: 16px">월간 일정</a>
		<% } else if(chkEvent==1){ %>
			<a href="/schedule/month?mno=${month}&event=${chkEvent}&baseballTeam=${team}
				&BBregion=${region}" style="font-size: 16px">월간 일정</a>
		<% } else {%>
			<a href="/schedule/month?mno=${month}&event=${chkEvent}&soccerTeam=${team}
				&SCregion=${region}" style="font-size: 16px">월간 일정</a>
		<% } %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</td>
	</tr>
</table>
<br>
<br>
<table border="1">
	<tr align="center">									
		<td align="center" style="width: 150px;">일</td> 	
		<td align="center" style="width: 150px;">월</td>	
		<td align="center" style="width: 150px;">화</td>
		<td align="center" style="width: 150px;">수</td> 
		<td align="center" style="width: 150px;">목</td> 
		<td align="center" style="width: 150px;">금</td>
		<td align="center" style="width: 150px;">토</td> 
	</tr>
	<tr style="height: 230px;">
	<% if(week==1) { 
		for(int i=1; i<=7; i++) { %>
		<td>
		<% if(startDay<i) { %>
			<div class="date">
			<%= i-startDay %>&nbsp;&nbsp;&nbsp;
			</div>
			<div class="schedule">
			<% List<Schedule> monthList = monthMap.get(i-startDay-1);
				for(int j=0; j<monthList.size(); j++) { %>
					<!-- 홈팀 이름으로 아이콘 주소 값 가져와서 이미지 불러옴 -->
					<img src="/logo/<%= chkIcon.get(monthList.get(j).getHometeam())%>"
						style="width: 21px; height: 21px;"/>
					<%= (monthList.get(j).getHometeam()) %>
						 : <%= (monthList.get(j).getAwayteam()) %>
					<!-- 어웨이 팀 이름으로 아이콘 주소 값 가져와서 이미지 불러옴 -->
					<img src="/logo/<%= chkIcon.get(monthList.get(j).getAwayteam())%>"
						style="width: 21px; height: 21px;"/><br>
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
					<a href="/board/list?schno=<%= monthList.get(j).getScheduleno() %>">
					<!-- 홈팀 이름으로 아이콘 주소 값 가져와서 이미지 불러옴 -->
					<img src="/logo/<%= chkIcon.get(monthList.get(j).getHometeam())%>"
						style="width: 21px; height: 21px;"/>
					<%= (monthList.get(j).getHometeam()) %>
						 : <%= (monthList.get(j).getAwayteam()) %>
					<!-- 어웨이 팀 이름으로 아이콘 주소 값 가져와서 이미지 불러옴 -->
					<img src="/logo/<%= chkIcon.get(monthList.get(j).getAwayteam())%>"
						style="width: 21px; height: 21px;"/><br>
					</a>
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
					<!-- 홈팀 이름으로 아이콘 주소 값 가져와서 이미지 불러옴 -->
					<img src="/logo/<%= chkIcon.get(monthList.get(j).getHometeam())%>"
						style="width: 21px; height: 21px;"/>
					<%= (monthList.get(j).getHometeam()) %>
						 : <%= (monthList.get(j).getAwayteam()) %>
					<!-- 어웨이 팀 이름으로 아이콘 주소 값 가져와서 이미지 불러옴 -->
					<img src="/logo/<%= chkIcon.get(monthList.get(j).getAwayteam())%>"
						style="width: 21px; height: 21px;"/><br>
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