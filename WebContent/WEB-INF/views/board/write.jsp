<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="../layout/header.jsp" />

<!-- 스마트 에디터 라이브러리 추가 -->
<script type="text/javascript"
 src="/resources/se2/js/service/HuskyEZCreator.js" charset="utf-8"></script>

<script type="text/javascript">
$(document).ready(function() {
	
	//작성버튼 동작
	$("#btnWrite").click(function() {
		
		//스마트에디터의 내용으로 <textarea>에 적용시키기
		submitContents($("#btnWrite"));
		
		//form submit 수행
		$("form").submit();
	});
	
	//취소버튼 동작
	$("#btnCancel").click(function() {
		history.go(-1);
	});
});
</script>

<style type="text/css">
#content {
	width: 99%;
}


#up {
	width: 1100px;
	height: 100px;
}

.container {
	max-width: 1200px;
	margin: 15px;
}

#hwrite {
	text-align: center;
}
</style>

<div class="container">

<h3 id="hwrite">게시글 쓰기</h3>
<hr>

<div style="text-align: center">
<form action="/board/write" method="post">
<table border="1" id="up" class="table tale-bordered">
<tr><td class="success" style="text-align: center">아이디</td><td>${userid }</td></tr>


<tr><td class="secondary" style="text-align: center">제목</td><td><input type="text" name="title" style="width:100%"/></td></tr>
<tr><td class="warning" style="text-align: center">직관날짜 <button>달력</button></td> <!-- 직관날짜는 boarddto에 없음 -->
<td style="text-align: center" class="info">응원하는 팀<br>
<select>
<option>야구</option>
<option>기아타이거즈</option>
<option>두산베어스</option>
<option>롯데자이언츠</option>
<option>삼성라이온즈</option>
<option>키움히어로즈</option>
<option>한화이글스</option>
<option>KT위즈</option>
<option>LG트윈즈</option>
<option>NC다이노스</option>
<option>SK와이번스</option>
</select>
<select>
<option>축구</option>
<option>강원FC</option>
<option>경남FC</option>
<option>대구FC</option>
<option>상주FC</option>
<option>성남FC</option>
<option>수원삼성블루윙즈</option>
<option>울산현대</option>
<option>인천유나이티드</option>
<option>전북현대모터스</option>
<option>제주유나이티드</option>




</select></td></tr>
<tr><td class="danger" colspan="2" style="text-align: center">본문</td></tr>
<tr><td colspan="2">
	<textarea id="content" name="content" rows="10" cols="100"></textarea>
</td></tr>
</table>
</form>
</div>


<div class="text-center">	
	<br><br><br>
	<button type="button" id="btnWrite" class="btn btn-info">작성</button>
	<button type="button" id="btnCancel" class="btn btn-danger">취소</button>
</div>
</div>

<jsp:include page="../layout/footer.jsp" />

<!-- 스마트 에디터를 생성하는 코드 -->
<!-- 스마트 에디터의 스킨을 입히는 코드 -->
<script type="text/javascript">
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "content",	//<textarea>의 id 를 입력
	sSkinURI: "/resources/se2/SmartEditor2Skin.html",
	fCreator: "createSEditor2",
	htParams: {
		bUseToolbar: true, //툴바 사용여부
		bUseVerticalResizer: false, //입력창 크기 조절 바
		bUseModeChanger: true //글쓰기 모드 탭
	}
});

//<form>의 submit이 수행되면 스마트에디터의 내용이 <textarea>에 적용됨
function submitContents(elClickedObj) {
	// 에디터의 내용이 textarea에 적용된다.
	oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);

	try {
		elClickedObj.form.submit();
	} catch (e) { }
}
</script>
