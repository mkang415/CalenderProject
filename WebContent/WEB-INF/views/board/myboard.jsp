<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />
  
<style type="text/css">

.side{
	float: left;
}

.right {
	float: left;
}
</style>
  
<div class="side" style="width:200px; background: #b4b4b4; float:left; height:660px;"> 
<br><br><br>
<div style="text-align: center;">나의글</div><br><br>
<div style="text-align: center;">개인정보 수정</div>
<br><br>
<div style="text-align: center;">비밀번호 변경</div>
<br><br><br><br><br><br><br><br><br><br>
<div style="text-align: center;">회원탈퇴</div>
<br>
</div>
 
<div class="right" style="width:1000px; height:660px; background: #dcdcdc;">
<h3>나의 글 </h3>

<table >

<!-- table table-striped table-hover table-condensed -->
<thead>
	<tr>
		<th>
<!-- 모두선택할수있는 체크박스 <input type="checkbox" id="checkAll" onclick="checkAll();" /> -->
		</th>
		<th style="width: 5%;">번호</th>
		<th style="width: 10%;">작성자</th>
		<th style="width: 20%;">제목</th>
		<th style="width: 35%;">내용</th>
		<th style="width: 10%;">스케줄</th>
		<th style="width: 10%;">작성일</th>
		<th style="width: 10%;">조회수</th>
	</tr>
</thead>


</table>







</div>
<div style="clear: both;"></div>
 

 
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
<c:import url="/WEB-INF/views/layout/footer.jsp" />  