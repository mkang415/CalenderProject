<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<style type="text/css">
table, th {
	text-align: center;
}
</style>

</head>
<body>
<div class="wrapper">

<h3 align="center">나의 글</h3>

<button type="button">게시글</button> <button type="button">댓글 단 게시글</button>

<table>
<thead>
	<tr>
		<th>글번호</th>
		<th>제목</th>
		<th>작성일</th>
		<th>댓글수(미구현)</th>
	</tr>
</thead>
<c:forEach items="${boardList}" var="i">
	<tr>
		<td>${i.boardno }</td>
		<td><a href="/mypage?boardno=${i.boardno }">${i.title }</a></td>
		<td><fmt:formatDate value="${i.insertdate }" pattern="yyyy-MM-dd" /></td>
		<td>1234(미구현)</td>
	</tr>
</c:forEach>
	
</table>

<button type="button" onClick="location.href='/member/update'">개인정보 수정</button> 
<button type="button">비밀번호 변경</button> 
<button type="button">회원 탈퇴</button>
</div>

<c:import url="/WEB-INF/views/layout/myPagePaging.jsp" />
</body>
</html>