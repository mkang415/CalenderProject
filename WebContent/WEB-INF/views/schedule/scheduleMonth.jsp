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
<script type="text/javascript" 
src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

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
<div style="width:200px; background: #b4b4b4; float:left; height:1510px; text-align: center">
<br>
<br>
<br>

<!-- 상세 검색 값 폼으로 get방식 전달 -->
<form action="/schedule/month" method="get">
<fieldset>
<legend style="font-size: 18px;">상 세 검 색</legend>
<div style="width:200px; text-align: center;">
	<div>
		<input type="hidden" name="mno" value="${month}">	<!-- 선택 월 전달 -->
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
			<option value="all"<% if(team.equals("all")) { %>selected<% } %>>--- 야구팀 ---</option>
			<option value="KT"<% if(team.equals("KT")) { %>selected<% } %>>KT 위즈</option>
			<option value="LG"<% if(team.equals("LG")) { %>selected<% } %>>LG 트윈스</option>
			<option value="NC"<% if(team.equals("NC")) { %>selected<% } %>>NC 다이노스</option>
			<option value="SK"<% if(team.equals("SK")) { %>selected<% } %>>SK 와이번스</option>
			<option value="기아"<% if(team.equals("기아")) { %>selected<% } %>>기아 타이거즈</option>
			<option value="두산"<% if(team.equals("두산")) { %>selected<% } %>>두산 베어스</option>
			<option value="롯데"<% if(team.equals("롯데")) { %>selected<% } %>>롯데 자이언츠</option>
			<option value="삼성"<% if(team.equals("삼성")) { %>selected<% } %>>삼성 라이온즈</option>
			<option value="키움"<% if(team.equals("키움")) { %>selected<% } %>>키움 히어로즈</option>
			<option value="한화"<% if(team.equals("한화")) { %>selected<% } %>>한화 이글스</option>
		</select>
	</div>
	
	<div id="selSC" <% if(chkEvent!=2) { %>style="display: none"<% } %>>
		<select id="sct" name="soccerTeam">	<!-- 축구 팀 선택 -->
			<option value="all"<% if(team.equals("all")) { %>selected<% } %>>--- 축구팀 ---</option>
			<option value="서울"<% if(team.equals("서울")) { %>selected<% } %>>FC 서울</option>
			<option value="강원"<% if(team.equals("강원")) { %>selected<% } %>>강원 FC</option>
			<option value="경남"<% if(team.equals("경남")) { %>selected<% } %>>경남 FC</option>
			<option value="대구"<% if(team.equals("대구")) { %>selected<% } %>>대구 FC</option>
			<option value="상주"<% if(team.equals("상주")) { %>selected<% } %>>상주 FC</option>
			<option value="성남"<% if(team.equals("성남")) { %>selected<% } %>>성남 FC</option>
			<option value="수원"<% if(team.equals("수원")) { %>selected<% } %>>수원 삼성</option>
			<option value="울산"<% if(team.equals("울산")) { %>selected<% } %>>울산 현대</option>
			<option value="인천"<% if(team.equals("인천")) { %>selected<% } %>>인천 유나이티드</option>
			<option value="전북"<% if(team.equals("전북")) { %>selected<% } %>>전북 현대모터스</option>
			<option value="제주"<% if(team.equals("제주")) { %>selected<% } %>>제주 유나이티드</option>
			<option value="포항"<% if(team.equals("포항")) { %>selected<% } %>>포항 스틸러스</option>
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
			<option value="all"<% if(region.equals("all")) { %>selected<% } %>>--- 전국 ---</option>
			<option value="서울"<% if(region.equals("서울")) { %>selected<% } %>>서울</option>
			<option value="인천"<% if(region.equals("인천")) { %>selected<% } %>>인천</option>
			<option value="수원"<% if(region.equals("수원")) { %>selected<% } %>>수원</option>
			<option value="대전"<% if(region.equals("대전")) { %>selected<% } %>>대전</option>
			<option value="대구"<% if(region.equals("대구")) { %>selected<% } %>>대구</option>
			<option value="광주"<% if(region.equals("광주")) { %>selected<% } %>>광주</option>
			<option value="부산"<% if(region.equals("부산")) { %>selected<% } %>>부산</option>
			<option value="창원"<% if(region.equals("창원")) { %>selected<% } %>>창원</option>
		</select>
	</div>
	
	<div id="SCregion" <% if(chkEvent!=2) { %>style="display: none"<% } %>>
		<select id="scr" name="SCregion">	<!-- 축구 팀 지역 선택 -->
			<option value="all"<% if(region.equals("all")) { %>selected<% } %>>--- 전국 ---</option>
			<option value="서울"<% if(region.equals("서울")) { %>selected<% } %>>서울</option>
			<option value="춘천"<% if(region.equals("춘천")) { %>selected<% } %>>춘천</option>
			<option value="인천"<% if(region.equals("인천")) { %>selected<% } %>>인천</option>
			<option value="성남"<% if(region.equals("성남")) { %>selected<% } %>>성남</option>
			<option value="수원"<% if(region.equals("수원")) { %>selected<% } %>>수원</option>
			<option value="상주"<% if(region.equals("상주")) { %>selected<% } %>>상주</option>
			<option value="포항"<% if(region.equals("포항")) { %>selected<% } %>>포항</option>
			<option value="전주"<% if(region.equals("전주")) { %>selected<% } %>>전주</option>
			<option value="대구"<% if(region.equals("대구")) { %>selected<% } %>>대구</option>
			<option value="울산"<% if(region.equals("울산")) { %>selected<% } %>>울산</option>
			<option value="창원"<% if(team.equals("창원")) { %>selected<% } %>>창원</option>
			<option value="제주"<% if(team.equals("제주")) { %>selected<% } %>>제주</option>
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

<div style="width:1200px; height: 1510px;">
<br>
<br>
<br>

<!-- 달력 테이블 생성 -->
<table>
	<tr>
		<td style="width: 302px;"></td>
		<td style= "width: 454px; height: 30px; text-align: center;">
		<% if(month != 1) { 
			if(chkEvent==0) {%>	<!-- 상세검색 전달 -->
				<a href="/schedule/month?mno=${month-1}">이전달</a>
			<% } else if(chkEvent==1){%>
				<a href="/schedule/month?mno=${month-1}&event=${chkEvent}
				&baseballTeam=${team}&BBregion=${region}">이전달</a>
			<% } else {%>
				<a href="/schedule/month?mno=${month-1}&event=${chkEvent}
				&soccerTeam=${team}&SCregion=${region}">이전달</a>
			<% } %>
		<% } %>	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<strong style="font-size: 18px;">${month}월</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<% if(month != 12) { 
			if(chkEvent==0) {%>
				<a href="/schedule/month?mno=${month+1}">다음달</a>
			<% } else if(chkEvent==1){%>
				<a href="/schedule/month?mno=${month+1}&event=${chkEvent}
					&baseballTeam=${team}&BBregion=${region}">다음달</a>
			<% } else {%>
				<a href="/schedule/month?mno=${month+1}&event=${chkEvent}
					&soccerTeam=${team}&SCregion=${region}">다음달</a>
			<% } %>
		<% } %>
		</td>
		<td style="width: 302px; text-align: right;">
		<% if(chkEvent==0) {%>
			<a href="/schedule/week?mno=${month}" style="font-size: 16px">주간 일정</a>
		<% } else if(chkEvent==1){ %>
			<a href="/schedule/week?mno=${month}&event=${chkEvent}&baseballTeam=${team}
				&BBregion=${region}" style="font-size: 16px">주간 일정</a>
		<% } else {%>
			<a href="/schedule/week?mno=${month}&event=${chkEvent}&soccerTeam=${team}
				&SCregion=${region}" style="font-size: 16px">주간 일정</a>
		<% } %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</td>
	</tr>
</table>
<br>
<br>
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
			<tr style="height: 230px;">
		<% } %>
		<td>
		<% if(i>startDay) { %>	<!-- 달력 1일 날짜 위치에서 시작하기 위한 조건문 -->
		<div class="date">
			<%=i-startDay %>&nbsp;&nbsp;&nbsp;	<!-- 해당 날짜 출력 -->
		</div>
		<div class="schedule">
			<% List<Schedule> monthList = monthMap.get(i-startDay-1);
				for(int j=0; j<monthList.size(); j++) { %>	<!-- 해당날짜 일정 출력 -->
					<!-- 홈팀 이름으로 아이콘 주소 값 가져와서 이미지 불러옴 -->
					<img src="/logo/<%= chkIcon.get(monthList.get(j).getHometeam())%>"
						style="width: 21px; height: 21px;"/>
					<%= (monthList.get(j).getHometeam()) %>
						 : <%= (monthList.get(j).getAwayteam()) %>
					<!-- 어웨이팀 이름으로 아이콘 주소 값 가져와서 이미지 불러옴 -->
					<img src="/logo/<%= chkIcon.get(monthList.get(j).getAwayteam())%>"
						style="width: 21px; height: 21px;"/><br>
						 		
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
