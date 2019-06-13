<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<script type="text/javascript">

var pwConfirm; // pw 중복여부

$(document).ready(function() {
	
	$("#resignreason").hide();
	
});


function pwCheck() { // pw 일치 여부 검사 스크립트
	var password = $("#password").val();
	alert("테스트코드, 입력받은 비밀번호 : " + password);
	
	$.ajax({
		type:"post"
		, url:"/pwChk"
		, data:{"password" : password}
		, dataType:"text"
		, success: function(val) {
			console.log("성공");
			console.log(val);
			
			pwConfirm = val;
		}
		, error: function() {
			console.log("실패");
		}
	});
		
	if(pwConfirm) {
		
		$("#pwChk").hide();
		$("#resignreason").show();
		
	} else {

		alert("비밀번호를 다시 확인해주세요");
		
	}
}

</script>

</head>
<body>

<h1>회원탈퇴 폼</h1>
<hr>
<div id="wrapper">

<form action="/member/resign" method="post">
<div id="pwChk">
비밀번호 입력 : <input type="password" id="password" name="password" /><br>
<button type="button" onClick="pwCheck()">확인</button> <button type="button" onClick="location.href='/main'">취소</button>
</div>
<div id="resignreason" align="center">
탈퇴사유<br>
<textarea rows="10" cols="50" id="reason" name="reason"></textarea><br>
<button>탈퇴하기</button> <button type="button" onClick="location.href='/member/resign'">취소</button>
</div>
</form>

</div>
</body>
</html>