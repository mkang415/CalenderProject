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
	height : 180px;
	text-align: center;
	font-size: 12px;
	
}
</style>

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

<!-- 전달받은 값 저장 -->
<%	int month = (int)request.getAttribute("month"); %>
<%	int lastDate = (int)request.getAttribute("lastDate"); %>
<%	int startDay = (int)request.getAttribute("startDay"); %>

<%	Map<Integer, List> monthMap = new HashMap<>(); %>
<% for(int i=0; i<lastDate; i++) {
	List<Schedule> monthList = (List)((HashMap)request.getAttribute("monthMap")).get(i);
	monthMap.put(i, monthList);
}%>

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
		<input type="radio" id = "bb" name = "event" checked="checked" 
			value = "1" onclick="setDisplay(this.value,'selBB','selSC','BBregion','SCregion')"/>
		<label for="bb">야구 </label>&nbsp;&nbsp;&nbsp;
		<input type="radio" id = "sc" name = "event" value = "2"
			onclick="setDisplay(this.value,'selBB','selSC','BBregion','SCregion')"/>
		<label for="sc">축구</label>
	</div>
	<div>
		<Strong style="font-size: 15px;"><br><br>팀&nbsp;&nbsp;선&nbsp;&nbsp;택</Strong>
		<br>
		<br>
	</div>
	<div id="selBB">	
		<select name="baseballTeam">	<!-- 야구 팀 선택 -->
			<option value="all">--- 야구팀 ---</option>
			<option value="KT">KT 위즈</option>
			<option value="LG">LG 트윈스</option>
			<option value="NC">NC 다이노스</option>
			<option value="SK">SK 와이번스</option>
			<option value="기아">기아 타이거즈</option>
			<option value="두산">두산 베어스</option>
			<option value="롯데">롯데 자이언츠</option>
			<option value="삼성">삼성 라이온즈</option>
			<option value="키움">키움 히어로즈</option>
			<option value="한화">한화 이글스</option>
		</select>
	</div>
	
	<div id="selSC" style="display: none">
		<select name="soccerTeam">	<!-- 축구 팀 선택 -->
			<option value="all">--- 축구팀 ---</option>
			<option value="서울">FC 서울</option>
			<option value="강원">강원 FC</option>
			<option value="경남">경남 FC</option>
			<option value="대구">대구 FC</option>
			<option value="상주">상주 FC</option>
			<option value="성남">성남 FC</option>
			<option value="수원">수원 삼성</option>
			<option value="울산">울산 현대</option>
			<option value="인천">인천 유나이티드</option>
			<option value="전북">전북 현대모터스</option>
			<option value="제주">제주 유나이티드</option>
			<option value="포항">포항 스틸러스</option>
		</select>
	</div>
	
	<div>
		<Strong style="font-size: 15px;"><br><br>지&nbsp;&nbsp;&nbsp;역</Strong>
		<br>
		<br>
	</div>
	<div id="BBregion">	<!-- 야구 팀 지역 선택 -->
		<select name="BBregion">
			<option value="all">--- 전국 ---</option>
			<option value="서울">서울 잠실</option>
			<option value="서울">서울 목동</option>
			<option value="인천">인천</option>
			<option value="수원">수원</option>
			<option value="대전">대전</option>
			<option value="대구">대구</option>
			<option value="광주">광주</option>
			<option value="부산">부산</option>
			<option value="창원">창원</option>
		</select>
	</div>
	
	<div id="SCregion" style="display: none">
		<select name="SCregion">	<!-- 축구 팀 지역 선택 -->
			<option value="all">--- 전국 ---</option>
			<option value="서울">서울</option>
			<option value="춘천">춘천</option>
			<option value="인천">인천</option>
			<option value="성남">성남</option>
			<option value="수원">수원</option>
			<option value="상주">상주</option>
			<option value="포항">포항</option>
			<option value="전주">전주</option>
			<option value="대구">대구</option>
			<option value="울산">울산</option>
			<option value="창원">창원</option>
			<option value="제주">제주</option>
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
			<% if(month != 1) { %>
			<a href="/schedule/month?mno=${month-1}">이전달</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<% } %>	
			<strong style="font-size: 18px;">${month}월</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<% if(month != 12) { %>
			<a href="/schedule/month?mno=${month+1}">다음달</a>
		<% } %>
		</td>
		<td style="width: 302px; text-align: right;">
			<a href="/schedule/week?mno=${month}">주</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
			<tr style="height: 210px;">
		<% } %>
		<td>
		<% if(i>startDay) { %>	<!-- 달력 1일 날짜 위치에서 시작하기 위한 조건문 -->
		<div class="date">
			<%=i-startDay %>&nbsp;&nbsp;&nbsp;	<!-- 해당 날짜 출력 -->
		</div>
		<div class="schedule">
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
