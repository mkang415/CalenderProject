<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:import url="/WEB-INF/views/layout/header.jsp"></c:import>

<script type="text/javascript">

$(document).ready(function(){
	
	$("#btnUpdate").click(function(){
		
		if($("#newPw").val() == $("#newPwConfirm").val()) {
			
			alert("비밀번호가 변경되었습니다");
			$("#updateform").submit();
			
		} else {
			alert("변경할 비밀번호를 다시 확인해주세요");
		}
		
	})
	
})

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
<div class="title"><h1>비밀번호 변경</h1></div>
<hr>

<div class="wrapper" align="center">

<form action="/mypage/pwupdate" method="post" id="updateform">

<table>
	<tr>
		<td>현재 비밀번호</td>
		<td><input type="password" name="pwConfirm" id="pwConfirm" /></td>
	</tr>
	<tr>
		<td>새 비밀번호</td>
		<td><input type="password" name="newPw" id="newPw" /></td>
	</tr>
	<tr>
		<td>새 비밀번호 확인</td>
		<td><input type="password" name="newPwConfirm" id="newPwConfirm" /></td>
	</tr>
</table>

<!-- 현재 비밀번호 : <input type="password" name="pwConfirm" id="pwConfirm" /><br> -->
<!-- 새 비밀번호 : <input type="password" name="newPw" id="newPw" /><br> -->
<!-- 새 비밀번호 확인 : <input type="password" name="newPwConfirm" id="newPwConfirm" /><br> -->

<button type="button" id="btnUpdate">변경하기</button>

</form>

</div>

</div>


<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>