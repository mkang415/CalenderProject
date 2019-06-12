<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>직관하러 가즈아</title>

<!-- jQuery 2.2.4 -->
<script type="text/javascript"
 src="http://code.jquery.com/jquery-2.2.4.min.js"></script>

<!-- Bootstrap 3 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<style type="text/css">
#header {
	text-align: left;
	background: #828282;
	height: 80px;
	width: 1200px;
	
}


#footer {
	text-align: center;
	display: table;
	background: #828282;
	height: 60px;
	width: 1200px;
	
}
.p {
	display: table-cell;
	vertical-align: middle;
	text-align: center;
}


.botton {
	text-align: center;
}

.more {
	display: block;
	width: 80px;
	hight: 20px;
	background-image:url('/logo_upload/semi_default.png');
	background-position: 80px -78px;
	
}

.blind{
	postion: absolute;
	overflow: hidden;
	clip: rect(0 0 0 0);
	margin: -1px;
	width: 1px;
	height: 1px;
}

.more:hover, .close:hover {
  cursor:pointer;
}

.close {
  display:block;
  width: 80px;
  height: 16px;
  background-position: -166px -78px;
}


.board {
  font-size:13px;
  position:absolute;
  top:60px;
  right: 300px;
  width:150px;
  height:80px;
  background: #dcdcdc;
  visibility:hidden;
}

.list {
  float:left;
}

.list li {
  list-style:none;
  margin-top:5px;
}

.list li:hover {
  text-decoration:underline;
  cursor:pointer;
}
</style>


<script type="text/javascript">
$(document).ready(function(){
	  $('.more').click(function(){
	    if($('.more').hasClass('more')){
	       $('.more').addClass('close').removeClass('more');
	       $('.board').css('visibility', 'visible');
	    }else if($('.close').hasClass('close')){
	       $('.close').addClass('more').removeClass('close');  
	       $('.board').css('visibility', 'hidden');
	    }
	  });
	});

</script>
</head>
<body>







<div id="header">

	<span style="font-size:3.5em;" id="h"><a href="/main">직관의 민족</a></span>
	<span>
	<button type="button" onclick='location.href="/schedule/week";'>일정</button></span>
	
	<span>
	<button type="button" onclick="location.href='/board/list';">직관매칭</button></span>
	
	
	
	<!-- 비로그인상태 -->
	<c:if test="${not login }">
	<button onclick='location.href="/login";'>로그인</button>
	<button onclick='location.href="/signup";'>회원가입</button>
	</c:if>
	
	
	<!-- 로그인상태 -->
	<c:if test="${not login }">
	<span style="float:right; padding: 20px 10px;" class="more">
	<span class="blind">
	<img src="/logo_upload/semi_default.png" width="60px" height="50px">
	</span>
	</span>
	
	<div class="board">
  	<ul class="list">
  	<li> 프로필 </li>
  	<li onclick='location.href="/mypage";'> 개인정보수정 </li>
  	<li onclick='location.href="/logut";'> 로그아웃</li>
  	</ul>
	</div>
	
	</c:if>
</div>	





<!-- <div class="wrap"> -->


<!-- <div class="container"> -->

