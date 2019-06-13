<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<script type="text/javascript"></script>

<script type="text/javascript">
$(document).ready(function() {
	//목록버튼 동작
	$("#btnList").click(function() {
		$(location).attr("href","/manage/member");
	});
	
	//팝업창
	$("#btnDelete").click(function() {
		$(location).attr("href","/manage/out");
	});
	
});


</script>



<div><h1>회원 정보</h1></div>

<table class="table table-bordered">
<tbody>

<tr>
	<td><label for="userid">아이디 : </label>${infoMember.userid }</td>
</tr>

<tr>
	<td><label for="age">나이 : </label>${infoMember.age }</td>
</tr>

<tr>
	<td><label for="gender">성별 : </label>${infoMember.gender }</td>
</tr>

<tr>
	<td><label for="nickname">닉네임 : </label>${infoMember.nickname }</td>
	<td><label for="joindate">가입일 : </label><fmt:formatDate value="${infoMember.joindate }" pattern="yyyy-MM-dd" /></td>
</tr>

<tr>
	<td><label for="teamname">좋아하는 팀 : </label>${infoMember.teamname }</td>
	<td><label for="writing">활동내역 : </label> 게시글 = ${cntBoard } , 댓글수 = ${cntReply }</td>
</tr>

<tr>
	<td><label for="introduce">자기 소개 : </label>${infoMember.introduce }</td>
</tr>

</tbody>
</table>


<div id="vbtn">	
	<span><button id="btnList" class="btn btn-primary">목록</button></span>
	
	<span><button id="btnDelete" class="btn btn-warning">영구 탈퇴</button></span>

</div>



<c:import url="/WEB-INF/views/layout/footer.jsp" />
    