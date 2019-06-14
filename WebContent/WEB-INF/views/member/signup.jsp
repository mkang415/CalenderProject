<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="util.ConfirmCode" %>
    
<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:import url="/WEB-INF/views/layout/header.jsp"></c:import>

<script type="text/javascript">

<%ConfirmCode confirm = new ConfirmCode();%>
var code = <%= confirm.randomCode() %>;

var pwConfirm; // pw 일치여부 

var emailConfirm; // email 인증번호 일치여부

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
// 			console.log(val);
			nicknameConfirm = val;
//				console.log(nicknameConfirm);
		}
		, error: function() {
			console.log("실패");
		}
	});
	
	if(nicknameConfirm == false) {
// 		console.log("테스트 : 값이 false여야함" + nicknameConfirm);
		document.getElementById('sameNickname').innerHTML="이미 존재하는 닉네임입니다.";	
		document.getElementById('sameNickname').style.color='red';
	} else {
		document.getElementById('sameNickname').innerHTML="사용 가능한 닉네임입니다.";		
		document.getElementById('sameNickname').style.color='blue';
	}
}

function isSame() { // 비밀번호 일치 여부 검사 스크립트
	var pw = $('#pw').val();
	var confirmPW = $('#pwCheck').val();
	
	if(pw.length < 6 || pw.length > 16) {
		window.alert('비밀번호는 6자 이상 16자 이하만 이용 가능합니다.');
		document.getElementById('pw').value=document.getElementById('pwCheck').value='';
		document.getElementById('same').innerHTML='';
	}
	if(document.getElementById('pw').value!=''&& document.getElementById('pwCheck').value!='') {
		if(document.getElementById('pw').value==document.getElementById('pwCheck').value) {
			document.getElementById('same').innerHTML='비밀번호가 일치합니다.';
			document.getElementById('same').style.color='blue';
			pwConfirm = true;
			
			console.log(pwConfirm);
		}
		else {
			document.getElementById('same').innerHTML='비밀번호가 일치하지 않습니다.';
			document.getElementById('same').style.color='red';
			pwConfirm = false;
			console.log(pwConfirm);
		}
	}
	
}

$(document).ready(function() {
	$("#verifycode").click(function() { // 이메일 입력 시 해당 이메일 주소로 인증번호 발송하는 스크립트 - MailController와 연결
		console.log("verify code send");
		console.log(code);
		var verifyemail = $("#email").val();

		$.ajax({
			type:"get"
			, url:"/send"
			, data:{"verifyemail" : verifyemail,
				"code" : code}
			, dataType:"text"
			, success: function(code) {
				console.log("성공");								
			}
			, error: function() {
				console.log("실패");
			}
		});
				
		document.getElementById('verifycodeptag').innerHTML="인증번호를 입력하세요";
	});
	
	$("#confirm").click(function() { // 이메일 인증번호 확인 스크립트
		console.log("confirm code check");

// 		console.log(code);
		console.log($("#emailcheck").val());
		
		if($("#emailcheck").val()==code) {			
			document.getElementById('verifycodeptag').innerHTML="이메일 인증 성공";

			emailConfirm = true;
		
		}

		if($("#emailcheck").val()!=code) {			
			document.getElementById('verifycodeptag').innerHTML="이메일 인증 실패";

			emailConfirm = false;
		
		}		
		
	});	
	
});

function join() {

	if(emailConfirm && pwConfirm && nicknameConfirm) {

		console.log("모두 성공");
		alert("회원가입을 축하드립니다!");
		$("#sendJoinRequest").submit();
		
	} else {

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



<div class="center" style="width:1200px; height: 660px;">
<br>
<div class="title"><h1>회원 가입</h1></div>
<hr>

<div class="wrapper" align="center">
<div><p id="verifycodeptag"></p></div>

<form id="sendJoinRequest" action="/signup" method="post">

<table>
	<tr>
		<td>email </td>
		<td colspan="2"><input type="text" id="email" name="email" placeholder="이메일 입력"/> <button type="button" id="verifycode">전송하기</button></td>
	</tr>
	<tr>
		<td>인증번호 </td>
		<td colspan="2"><input type="text" id="emailcheck" name="emailcheck" placeholder="인증번호 입력"/> <button type="button" id="confirm">확인하기</button></td>	
	</tr>
	<tr>
		<td>비밀번호 </td>
		<td colspan="2"><input type="password" id="pw" name="password" placeholder="비밀번호 입력" onChange="isSame()"/></td>	
	</tr>
	<tr>
		<td>비밀번호 확인&nbsp; </td>
		<td><input type="password" id="pwCheck" name="pwCheck" placeholder="비밀번호 확인" onChange="isSame()" /></td>	
		<td><span id="same"></span></td>
	</tr>
	<tr>
		<td colspan="3"><p id="sameNickname"></p></td>
	</tr>
	<tr>
		<td>닉네임 </td>
		<td><input type="text" id="nickname" name="nickname" placeholder="닉네임 입력"/></td>	
		<td><button type="button" id="nicknameCheck" name="nicknameCheck" onClick="nickChk()">중복검사</button></td>
	</tr>
		<tr>
		<td>연령대 </td>
		<td colspan="2">
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
		<td>성별 </td>
		<td colspan="2"><input type="radio" name="gender" value="남성">남 
	<input type="radio" name="gender" value="여성">여</td>	
	</tr>
</table>

<div><p id="reject"></p></div>

<button type="button" id="joinConfirm" onClick="join()">가입하기</button> <button type="button">돌아가기</button>

<!-- email : <input type="text" id="email" name="email" placeholder="이메일 입력"/> <button type="button" id="verifycode">전송하기</button><br> -->

<!-- 인증번호 : <input type="text" id="emailcheck" name="emailcheck" placeholder="인증번호 입력"/> <button type="button" id="confirm">확인하기</button><br> -->

<!-- 비밀번호 : <input type="password" id="pw" name="password" placeholder="비밀번호 입력" onChange="isSame()"/><br> -->

<!-- 비밀번호 확인 : <input type="password" id="pwCheck" name="pwCheck" placeholder="비밀번호 확인" onChange="isSame()" />&nbsp;&nbsp;<span id="same"></span> <br> -->
<!-- <p id="sameNickname"></p> -->
<!-- 닉네임 : <input type="text" id="nickname" name="nickname" placeholder="닉네임 입력"/> <button type="button" id="nicknameCheck" name="nicknameCheck" onClick="nickChk()">중복검사</button><br> -->

<!-- 연령대 :  -->
<!-- <select name="age"> -->
<!-- 		<option value="10">10대</option> -->
<!-- 		<option value="20">20대</option> -->
<!-- 		<option value="30">30대</option> -->
<!-- 		<option value="40">40대</option> -->
<!-- 		<option value="50">50대</option> -->
<!-- 		<option value="60">60대</option> -->
<!-- </select><br> -->

<!-- 성별 : <input type="radio" name="gender" value="남성">남  -->
<!-- 	<input type="radio" name="gender" value="여성">여<br> -->

<!-- <div><p id="reject"></p></div> -->

<!-- <button type="button" id="joinConfirm" onClick="join()">가입하기</button> <button type="button">돌아가기</button> -->

</form>
</div>
</div>



<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>