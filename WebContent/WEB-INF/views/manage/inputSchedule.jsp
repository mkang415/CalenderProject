<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<script type="text/javascript">	//	종목 선택에 따라 팀, 지역 다르게 출력
	function setDisplay(v,bsHome, scHome, bsAway, scAway) {
		if(v=="1") {
			document.getElementById(bsHome).style.display="block";
			document.getElementById(scHome).style.display="none";
			document.getElementById(bsAway).style.display="block";
			document.getElementById(scAway).style.display="none";
		}else if(v=="2") {
			document.getElementById(bsHome).style.display="none";
			document.getElementById(scHome).style.display="block";
			document.getElementById(bsAway).style.display="none";
			document.getElementById(scAway).style.display="block";
		}
				
	};
</script>

<form action = "/manage/inputSchedule" method = "post">

<!-- form 태그 영역 감싸기 -->
<fieldset style="width: 300px;">

<!-- fieldset 설명 -->
<legend>경기 일정 입력</legend>

<table>
<tr>
	<td colspan="2">경기 종목</td>
</tr>
<tr>
	<td colspan="2">
		<input type="radio" id="bb" name = "event" checked="checked" value="1"
			onclick="setDisplay(this.value,'bsHome','scHome','bsAway','scAway')"/>
		<label for="bb">야구 </label>&nbsp;&nbsp;&nbsp;
		<input type="radio" id = "sc" name = "event" value = "2"
			onclick="setDisplay(this.value,'bsHome','scHome','bsAway','scAway')"/>
		<label for="sc">축구</label>
	</td>
</tr>
<tr>
	<td>Home</td>
	<td>Away</td>
</tr>
<tr>
	<td>
		<div id="bsHome">	
		<select name="bsHome">	<!-- 야구 팀 선택 -->
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
	
	<div id="scHome"style="display: none">
		<select name="scHome">	<!-- 축구 팀 선택 -->
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
	</td>
	<td>
		<div id="bsAway">	
		<select name="bsAway">	<!-- 야구 팀 선택 -->
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
	
		<div id="scAway"style="display: none">
		<select name="scAway">	<!-- 축구 팀 선택 -->
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
	</td>
</tr>
<tr>
	<td colspan="2">
	<input type="date" name="gamedate"/>
	</td>
</tr>
<tr>
<td><input type="submit" value="입력"></td>
</tr>
</table>

</fieldset>
</form>

</body>
</html>