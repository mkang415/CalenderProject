<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login test form</title>
</head>
<body>

<h1>login test form</h1>
<hr>

<form action="/login" method="post">

아이디 : <input type="text" name="userid" placeholder="아이디를 입력하세요" /><br>
비밀번호 : <input type="password" name="password" placeholder="비밀번호를 입력하세요" /><br>

<button>로그인</button> <button type="button" onClick="history.go(-1)">취소</button>

</form>


</body>
</html>