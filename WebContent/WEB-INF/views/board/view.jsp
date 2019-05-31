<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<script type="text/javascript">
$(document).ready(function() {
	//목록버튼 동작
	$("#btnList").click(function() {
	});
	
	//수정버튼 동작
	$("#btnUpdate").click(function() {
	});

	//삭제버튼 동작
	$("#btnDelete").click(function() {
	});
	
	
});


</script>

<style>
.maching {
	text-align: center;
}
</style>

<h1 class="maching">직관 매칭 게시판</h1>

<c:if test="${login }">
<button id="btnRecommend" class="btn pull-right" style="margin-top: 30px;"></button>
</c:if>

<div class="clearfix"></div>
<hr>

<table class="table table-bordered">
<tr>
<td>글번호</td><td>${viewBoard.boardno }</td>
<td>아이디</td><td colspan="3">${viewBoard.userid }
</tr>

<tr>
<td>제목</td><td>${viewBoard.title }</td>
<td>조회수</td><td>${viewBoard.hit }</td>
<td>작성일</td><td>${viewBoard.insertdate }</td>
</tr>

<tr>

</tr>

<tr><td class="info"  colspan="6">본문</td></tr>

<tr><td colspan="6">${viewBoard.content }</td></tr>

</table>


<div class="text-center">	
	<button id="btnList" class="btn btn-primary">목록</button>
	<c:if test="${userid eq viewBoard.userid }">
	<button id="btnUpdate" class="btn btn-info">수정</button>
	<button id="btnDelete" class="btn btn-danger">삭제</button>
	</c:if>
</div>





<c:import url="/WEB-INF/views/layout/footer.jsp" />
