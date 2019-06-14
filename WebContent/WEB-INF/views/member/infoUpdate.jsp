<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:import url="/WEB-INF/views/layout/header.jsp"></c:import>

<script type="text/javascript">

var nicknameConfirm; // 닉네임 중복여부 

function nickChk() { // 닉네임 일치 여부 검사 스크립트
	var nickname = $("#nickname").val();
	alert(nickname);

	$.ajax({
		type:"post"
		, url:"/nicknameCheck"
		, data:{"nickname" : nickname}
		, dataType:"text"
		, success: function(val) {
			console.log("성공");
			console.log(val);
			nicknameConfirm = val;
//				console.log(nicknameConfirm);
		}
		, error: function() {
			console.log("실패");
		}
	});
	
	if(nicknameConfirm == false) {
// 		console.log("테스트 : 값이 false여야함" + nicknameConfirm);
		document.getElementById('same').innerHTML="이미 존재하는 닉네임입니다.";	
		document.getElementById('same').style.color='red';
	} else {
		document.getElementById('same').innerHTML="사용 가능한 닉네임입니다.";		
		document.getElementById('same').style.color='blue';
	}
};

function update() {
	
	if(nicknameConfirm) {
		
		console.log("회원정보 수정 성공");
		alert("정보 수정이 완료되었습니다!");
		$("#updateRequest").submit();

	} else {

		console.log("회원정보 수정 실패");
		document.getElementById('reject').innerHTML="입력한 정보를 다시 확인해주세요";
	}
}


</script>



<style type="text/css">

.side{
	float: left;
}

.right {
	float: left;
}

.title { /* 회원탈퇴 가운데 정렬 */
 	text-align: center;
 	width: 1200px;
}

</style>



<div class="side" style="width:200px; background: #b4b4b4; float:left; height:660px;"> 
<br><br><br>
<div style="text-align: center;"><a href="/mypage/myboard">나의글</a></div><br><br>
<div style="text-align: center;"><a href="/mypage/update">개인정보 수정</a></div>
<br><br>
<div style="text-align: center;"><a href="/mypage/pwupdate">비밀번호 변경</a></div>
<br><br><br><br><br><br><br><br><br><br>
<div style="text-align: center;"><a href="/member/resign">회원탈퇴</a></div>
<br>
</div>

<div class="center" style="width:1200px; height: 660px;">
<br>
<div class="title"><h1>회원정보 수정</h1></div>
<hr>

<div align="center">
<form id="updateRequest" action="/mypage/update" method="post">
	<table>
		<tr>
			<td>아이디</td> <td>${member.userid }</td>
		</tr>
		<tr>
			<td>연령대</td>
			<td>
			<select name="age">
				<option value="10">10대</option>
				<option value="20">20대</option>
				<option value="30">30대</option>
				<option value="40">40대</option>
				<option value="50">50대</option>
				<option value="60">60대</option>
			</select>
			</td>
		</tr>
		<tr>
			<td>성별</td>
			<td><input type="radio" name="gender" value="남성">남 
				<input type="radio" name="gender" value="여성">여
			</td>
		</tr>
		<tr>
			<td>닉네임</td>
			<td><input type="text" id="nickname" name="nickname" value="${member.nickname }"> <button type="button" id="nicknameCheck" onClick="nickChk()">중복확인</button>&nbsp;&nbsp;<span id="same"></span></td>
		</tr>
		<tr>
			<td>좋아하는 팀</td>
			<td>
			<select name="teamname">
				<option value="KT">KT</option>
				<option value="LG">LG</option>
				<option value="NC">NC</option>
				<option value="SK">SK</option>
				<option value="기아">기아</option>
				<option value="두산">두산</option>
				<option value="롯데">롯데</option>
				<option value="삼성">삼성</option>
				<option value="키움">키움</option>
				<option value="한화">한화</option>
				<option value="서울">서울</option>
				<option value="강원">강원</option>
				<option value="경남">경남</option>
				<option value="대구">대구</option>
				<option value="상주">상주</option>
				<option value="성남">성남</option>
				<option value="수원">수원</option>
				<option value="울산">울산</option>
				<option value="인천">인천</option>
				<option value="전북">전북</option>
				<option value="제주">제주</option>
				<option value="포항">포항</option>				
			</select>
			</td>
		</tr>
		<tr>
			<td>자기소개</td>
			<td><textarea rows="10" cols="50" name="introduce">${member.introduce }</textarea></td>
		</tr>
		
	</table>
	<p id="reject"></p>
	<button type="button" onClick="update()">수정</button> <button type="button" onClick="location.href='/main'">취소</button>
	
</form>
</div>
</div>



<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>