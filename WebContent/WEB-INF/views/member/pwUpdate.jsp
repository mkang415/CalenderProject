<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	
	$("#btnUpdate").click(function(){
		
		if($("#newPw").val() == $("#newPwConfirm").val()) {
			
			$("#updateform").submit();
			
		} else {
			alert("변경할 비밀번호를 다시 확인해주세요");
		}
		
	})
	
})

</script>

</head>
<body>

<h1>비밀번호 변경 폼</h1>
<hr>

<div>

<form action="/mypage/pwupdate" method="post" id="updateform">

현재 비밀번호 : <input type="text" name="pwConfirm" id="pwConfirm" /><br>
새 비밀번호 : <input type="text" name="newPw" id="newPw" /><br>
새 비밀번호 확인 : <input type="text" name="newPwConfirm" id="newPwConfirm" /><br>

<button type="button" id="btnUpdate">변경하기</button>

</form>

</div>

</body>
</html>