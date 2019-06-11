<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>signup</title>

<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>

<script type="text/javascript">

$(document).ready(function() {
	$("#verifycode").click(function() {
		console.log("verify code send");
		
		var verifyemail = $("#verifyemail").val();
		
		$.ajax({
			type:"get"
			, url:"/send"
			, data:{"verifyemail" : verifyemail}
			, dataType:"text"
			, success: function(confirmcode) {
				console.log("성공");				
				console.log(confirmcode);
				
			}
			, error: function() {
				console.log("실패");
			}
		});
		
		
		document.getElementById('verifycodeptag').innerHTML="인증번호를 입력하세요";
	});
	
	$("#confirm").click(function() {
		console.log("confirm code check");

		var confirmcode = <% String confirmcode = (String)session.getAttribute("confirmcode"); %>

		console.log(confirmcode);
		console.log($("#emailcheck").val());
		
		if($("#emailcheck").val().equals(confirmcode)) {			
			document.getElementById('verifycodeptag').innerHTML="이메일 인증 성공";
		}

		if(! $("#emailcheck").val().equals(confirmcode)) {			
			document.getElementById('verifycodeptag').innerHTML="이메일 인증 실패";
		}		
		
	});	
	
});

</script>

</head>
<body>
<h1>signup test form</h1>
<hr>

<div><p id="verifycodeptag"></p></div>
email : <input type="text" id="verifyemail" name="email" placeholder="이메일 입력"/> <button type="button" id="verifycode">전송하기</button><br>

<form action="/signup" method="post">

인증번호 : <input type="text" id="emailcheck" name="emailcheck" placeholder="인증번호 입력"/> <button type="button" id="confirm">확인하기</button><br>

비밀번호 : <input type="text" name="password" placeholder="비밀번호 입력"/><br>

비밀번호 확인 : <input type="text" name="passwordcheck" placeholder="비밀번호 확인"/><br>

닉네임 : <input type="text" name="nickname" placeholder="닉네임 입력"/><br>

연령대 : 
<select name="age">
		<option value="10">10대</option>
		<option value="20">20대</option>
		<option value="30">30대</option>
		<option value="40">40대</option>
		<option value="50">50대</option>
		<option value="60">60대</option>
</select><br>

성별 : <input type="radio" name="gender" value="M">남 
	<input type="radio" name="gender" value="F">여<br>

<button>가입하기</button> <button type="button">돌아가기</button>

</form>

</body>
</html>