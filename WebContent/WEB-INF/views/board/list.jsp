<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<style type="text/css">
table, th{
	text-align: center;
}

.container { /* 게시판제목들 가운데정렬 */
	border-left: 1px solid #eee;
	border-right: 1px solid #eee;
}
.maching { /* 매칭게시판 가운데 정렬 */
 	text-align: center;
}

</style>



<div style="width:200px; background: #b4b4b4; float:left; height:800px;"> 
<br><br><br>
<div style="text-align: center;">상세검색</div><br><br>
<div style="text-align: center;"><label>종목</label></div>
<div style="text-align: center;"><input type="radio"><label>야구</label>
<input type="radio"><label>축구</label></div>
<br><br>
<div style="text-align: center;"> 팀 </div>
<div style="text-align: center;"><select >
<option >모든팀</option>
<option>LG트윈스</option>
<option>키움히어로즈</option>
</select></div>
<br>
<div style="text-align: center;"><select>
<option>모든지역</option>
<option>서울 특별시</option>
<option>인천</option>
</select></div>




</div>



<div class="wrap">
<div class="container">
<div class=maching><h1>직관 매칭 게시판</h1></div>
<hr>






<table class="table table-striped table-hover table-condensed">

<thead>
	<tr>
		<th>
<!-- 모두선택할수있는 체크박스 <input type="checkbox" id="checkAll" onclick="checkAll();" /> -->
		</th>
		<th style="width: 5%;">번호</th>
		<th style="width: 10%;">작성자</th>
		<th style="width: 20%;">제목</th>
		<th style="width: 35%;">내용</th>
		<th style="width: 10%;">조회수</th>
		<th style="width: 10%;">작성일</th>
		<th style="width: 10%;">작성일</th>
	</tr>
</thead>

<c:forEach items="${list }" var="i">
 <tr>
 	<td>${i.boardno }</td>
 	<td>${i.userid }</td>
 	<td>${i.title }</td>
 	<td>${i.content }</td>
 	<td>${i.scheduleno }</td>
 	<td>${i.insertdate }</td>
 	<td>${i.hit }</td>
 	<td><fmt:formatDate value="${i.insertdate }" pattern="yyyy-MM-dd"/></td>
 </tr>
 </c:forEach>
 </table>
 </div>
 </div>
 	

<tbody>
<c:import url="/WEB-INF/views/layout/footer.jsp" />

















