<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인정보 수정</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
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

</head>
<body>

<h1>개인정보 수정 test form</h1>
<hr>
<div>
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
			<td><input type="radio" name="gender" value="M">남 
				<input type="radio" name="gender" value="F">여
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
				<option value="kia">기아</option>
				<option value="nexen">넥센</option>
				<option value="sk">sk</option>
				<option value="samsung">삼성</option>
				<option value="nc">nc</option>
				<option value="lotte">꼴데</option>
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

</body>
</html>