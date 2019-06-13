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
		<th style="width: 25%">아이디</th>
		<th style="width: 25%">닉네임</th>
		<th style="width: 25%">나이</th>
		<th style="width: 25%">가입일</th>
	</tr>
</thead>
</table>

<form name=chk method="post" >
<table>
<tbody>
<c:forEach items="${list }" var="i">
	<tr>
		<td><a href="/manage/memberinfo?userid=${i.userid }">${i.userid }</a></td>
		<td>${i.nickname }</td>
		<td>${i.age }</td>
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
    