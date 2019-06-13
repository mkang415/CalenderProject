<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<script type="text/javascript">
$(document).ready(function() {

	
    $('#btnDelete').click(function(){
        var $checkboxes = $("input:checkbox[name='checkRow']:checked");
        
        // 체크된 대상들을 map으로 만들고 map을 문자열로 만들기
        var map = $checkboxes.map(function() {
           return $(this).val();
        });
        var names = map.get().join(",");
        
        // 전송 폼
        var $form = $("<form>")
           .attr("action", "/manage/delete")
           .attr("method", "post")
           .append(
              $("<input>")
                 .attr("type", "hidden")
                 .attr("name", "names")
                 .attr("value", names)
           );
        $(document.body).append($form);
        $form.submit();
        
     });
	
});
</script>

<h1>관리자 페이지</h1>
<hr>

<table class="table table-striped table-hover table-condensed">

<thead>
	<tr>
		<th style="width: 10%">선택</th>
		<th style="width: 10%">글번호</th>
		<th style="width: 45%">제목</th>
		<th style="width: 15%">작성자</th>
		<th style="width: 20%">작성일</th>
	</tr>
</thead>
</table>

<form name=chk method="post" action="delete.jsp">
<table>
<tbody>
<c:forEach items="${list }" var="i">
	<tr>
		<td><input type="checkbox" name="checkRow" value="${i.boardno }"/></td>
		<td>${i.boardno }</td>
		<td><a href="/board/view?boardno=${i.boardno}">${i.title }</a></td>
		<td>${i.userid }</td>
		<td><fmt:formatDate value="${i.insertdate }" pattern="yyyy-MM-dd" /></td>
	</tr>
</c:forEach>
</tbody>
</table>
</form>


<div id="pagingBox">
<c:import url="/WEB-INF/views/layout/managePaging.jsp" />


</div>

<div class="text-center">	
	
	<button id="btnDelete" class="btn btn-danger">삭제</button>
	
	
</div>



<c:import url="/WEB-INF/views/layout/footer.jsp" />
    