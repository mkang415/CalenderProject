<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<h1>회원 관리</h1>
<hr>

<table class="table table-striped table-hover table-condensed">

<thead>
	<tr>
		<th style="width: 30%">아이디</th>
		<th style="width: 30%">닉네임</th>
		<th style="width: 40%">가입일</th>
	</tr>
</thead>
</table>

<form name=chk method="post" >
<table>
<tbody>
<c:forEach items="${list }" var="i">
	<tr>
		<td>${i.userid }</td>
		<td><a href="/manage/managemember?nickname=${i.userid }">${i.nickname }</a></td>
		<td><fmt:formatDate value="${i.joindate }" pattern="yyyy-MM-dd" /></td>
	</tr>
</c:forEach>
</tbody>
</table>
</form>


<div id="pagingBox">
<c:import url="/WEB-INF/views/layout/managememberPaging.jsp" />
</div>



<c:import url="/WEB-INF/views/layout/footer.jsp" />
    