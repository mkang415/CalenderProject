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
	display: inline;
	width: 80px;
	hight: 20px;
/*  	background-image:url('/logo/semi_default.png');  */ 
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
  right: 320px;
  width:150px; 
  height:80px; 
  background: #dcdcdc;
  visibility:hidden; 
}

.list {
  float:left;
  position: fixed;
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

<!-- boostrap script -->
	<script src="js/jquery-3.3.1.min.js"></script>
      <script src="js/jquery-migrate-3.0.1.min.js"></script>
      <script src="js/popper.min.js"></script>
      <script src="js/bootstrap.min.js"></script>
      <script src="js/owl.carousel.min.js"></script>
      <script src="js/jquery.stellar.min.js"></script>
      <script src="js/jquery.fancybox.min.js"></script>
      <script src="js/jquery.easing.1.3.js"></script>
      
      
      
      <script src="js/aos.js"></script>
      
      <script src="js/bootstrap-datepicker.js"></script> 
      <script src="js/jquery.timepicker.min.js"></script> 

      <script src="js/main.js"></script>

<!-- bootstrap Stylesheet -->
<meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <title>|잘노는 남매들| 직관갈 사람들 여기여기 모여라 </title>
      <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

      <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=|Roboto+Sans:400,700|Playfair+Display:400,700">

      <link rel="stylesheet" href="css/bootstrap.min.css">
      <link rel="stylesheet" href="css/animate.css">
      <link rel="stylesheet" href="css/owl.carousel.min.css">
      <link rel="stylesheet" href="css/aos.css">
      <link rel="stylesheet" href="css/bootstrap-datepicker.css">
      <link rel="stylesheet" href="css/jquery.timepicker.css">
      <link rel="stylesheet" href="css/fancybox.min.css">
      
      <link rel="stylesheet" href="fonts/ionicons/css/ionicons.min.css">
      <link rel="stylesheet" href="fonts/fontawesome/css/font-awesome.min.css">

      <!-- Theme Style -->
      <link rel="stylesheet" href="css/style.css">

</head>
<body data-spy="scroll" data-target="#templateux-navbar" data-offset="200"> 
<!-- bootstrap 속성 추가 -->

<div id="header">

	<span style="font-size:3.5em;" id="h"><a href="/main">직관의 민족</a></span>
	<span>
	<button type="button" onclick='location.href="/schedule/week";'>일정</button></span>
	<span>
	<button type="button" onclick="location.href='/board/list'">직관매칭</button></span>
	
	
	
	    <!-- 비로그인상태 -->
    <c:if test="${not login }">
    <button onclick='location.href="/login";'>로그인</button>
    <button onclick='location.href="/signup";'>회원가입</button>
    </c:if>
	
	    <!-- 로그인상태 -->
    <c:if test="${login }">
	
	
	
	
	<span style="float:right; padding: 20px 10px;" class="more">
	<span class="blind">
	<img src="/logo/semi_default.png" width="60px" height="50px">
	</span>
	</span>
	
	<div class="board">
  	<ul class="list">
  	<li> 프로필 </li>
  	<li onClick="location.href='/mypage/update'"> 개인정보수정 </li>
  	<li onClick="location.href='/logout'"> 로그아웃</li>
  	</ul>
	</div>
	
</c:if>
</div>	

<!-- <div class="wrap"> -->


<!-- <div class="container"> -->

