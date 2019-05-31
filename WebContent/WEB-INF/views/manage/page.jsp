<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<h1>관리자 페이지</h1>
<hr>

<table class="table table-striped table-hoverr table-condensed">

<thead>
	<tr>
		<th style="width: 10%">체크</th>
		<th style="width: 10%">번호</th>
		<th style="width: 45%">제목</th>
		<th style="width: 15%">작성자</th>
		<th style="width: 20%">작성일</th>
	</tr>
</thead>

<tbody>
<c:forEach items="${list }" var="i">
	<tr>
		<td>선택체크박스</td>
		<td>${i.boardno }</td>
		<td>${i.title }</td>
		<td>${i.uderid }</td>
		<td><fmt:formatDate value="${i.insertdate }" pattern="yyyy-MM-dd" /></td>
	</tr>
</c:forEach>
</tbody>

</table>

<div id="pagingBox">
<c:import url="/WEB-INF/views/layout/paging.jsp" />

</div>






<c:import url="/WEB-INF/views/layout/footer.jsp" />
    