<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인정보 수정</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>

</head>
<body>

<h1>개인정보 수정 test form</h1>
<hr>
<div>
<form aciton="/mypage/update" method="post">
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
			<td><input type="text" name="nickname" value="${member.nickname }"></td>
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
	
	<button>수정</button> <button type="button">취소</button>
	
</form>
</div>

</body>
</html>