<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:import url="/WEB-INF/views/layout/header.jsp"></c:import>

<script type="text/javascript">

var pwConfirm; // pw 중복여부

function pwCheck() { // pw 일치 여부 검사 스크립트
	
	var password = $("#password").val();
// 	alert("테스트코드, 입력받은 비밀번호 : " + password);
	
	$.ajax({
		type:"post"
		, url:"/pwChk"
		, data:{"password" : password}
		, dataType:"json"
		, success: function(res) {
			console.log("성공");
			console.log(res);
			if(res.result) {
				$("#pwChk").hide();
				$("#resignreason").show();
				
			} else if(!res.result){
				alert("비밀번호를 다시 확인해주세요");
				
			}
		}
		, error: function() {
			console.log("실패");
		}
	});

};

$(document).ready(function() {
	
	$("#resignreason").hide();
	
});

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

.pwChk {
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
<div class="title"><h1>회원 탈퇴</h1></div>
<hr>

<div id="wrapper">

<div class="pwChk">
<table align="center">
	<tr>
		<td>비밀번호 입력 : </td>
		<td><input type="password" id="password" name="password" /></td>
	</tr>

	<tr>
		<td></td>
		<td><button type="button" onClick="pwCheck()">확인</button> <button type="button" onClick="location.href='/main'">취소</button></td>
	</tr>
</table>
</div>

<form action="" method="post">
<div id="resignreason" align="center">
탈퇴사유<br>
<textarea rows="10" cols="50" id="reason" name="reason"></textarea><br>
<button>탈퇴하기</button> <button type="button" onClick="location.href='/member/resign'">취소</button>
</div>
</form>

</div>
</div>



<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>