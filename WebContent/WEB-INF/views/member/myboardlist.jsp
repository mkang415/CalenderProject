<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:import url="/WEB-INF/views/layout/header.jsp"></c:import>

<style type="text/css">
table, th {
	text-align: center;
}

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

</head>
<body>

<div class="center" style="width:1200px; height: 660px;">
<br>
<div class="title"><h1>나의 게시글</h1></div>
<hr>

<div class="wrapper">

<div align="right">
<button type="button">게시글</button> <button type="button">댓글 단 게시글</button>
</div>
<br>

<table border="1" style="margin-left: auto;
	margin-right: auto;">
<thead>
	<tr>
		<th style="width: 5%;">번호</th>
		<th style="width: 10%;">작성자</th>
		<th style="width: 10%;">제목</th>
		<th style="width: 35%;">내용</th>
		<th style="width: 10%;">스케줄</th>
		<th style="width: 20%;">작성일</th>
		<th style="width: 10%;">조회수</th>
	</tr>
</thead>
<c:forEach items="${boardList}" var="i">
	<tr>
 		<td>${i.boardno }</td>
	 	<td>${i.nickname }</td>
	 	<td><a href="/board/view?boardno=${i.boardno}">${i.title }</a></td>
	 	<td>${i.content }</td>
	 	<td>${i.scheduleno }</td>
	 	<td><fmt:formatDate value="${i.insertdate }" pattern="yyyy-MM-dd"/></td>
	 	<td>${i.hit }</td>
	</tr>
</c:forEach>
	
</table>

<div id="pagingBox">
<c:import url="/WEB-INF/views/layout/myPagePaging.jsp" />
</div>

</div>

</div>

<c:import url="/WEB-INF/views/layout/footer.jsp" />