<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<script type="text/javascript" 
src="http://code.jquery.com/jquery-2.2.4.min.js"></script>

<script type="text/javascript">
//윈도우 팝업
function wrapWindowByMask(){
 
 //화면의 높이와 너비를 구한다.
 var maskHeight = $(document).height();  
 var maskWidth = $(window).width();  

 //마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채운다.
 $("#mask").css({"width":maskWidth,"height":maskHeight});  

 //애니메이션 효과 - 일단 0초동안 까맣게 됐다가 60% 불투명도로 간다.

 $("#mask").fadeIn(0);      
 $("#mask").fadeTo("slow",0.5);    

 //윈도우 같은 거 띄운다.
 $(".window").show();

}

$(document).ready(function(){
 //검은 막 띄우기
 $(".openMask").click(function(e){
     e.preventDefault();

     console.log($(this))
     console.log($(this).attr("href"))
     
     $.ajax({
       type:"get"
       , url: $(this).attr("href")
        , dataType: "html"
        , success: function( h ) {
           console.log("s")
           $(".window").html( h );
        }
        , error: function() {
           console.log("e")
        }
     });
     
     wrapWindowByMask();
 });
 

 //닫기 버튼을 눌렀을 때
 $(".window .close").click(function (e) {  
     //링크 기본동작은 작동하지 않도록 한다.
     e.preventDefault();  
     $("#mask, .window").hide();  
 });       

 //검은 막을 눌렀을 때
 $("#mask").click(function () {  
     $(this).hide();  
     $(".window").hide();  

 });      

});

</script>


<div class = "contents">

<div class="clearfix">


<div id="modal">
   
    <div class="modal_content">
        <h2 style="text-align: center">${infoMember.userid }님의  제재 사유</h2>
        <hr>
        
        <table border="1" id="modal_table">
        <tr><td>
        <select>
        <option>---신고사유선택---</option>
        <option>욕설난무</option>
        <option>타인비방</option>
        <option>이상한말</option>        
        </select>
        </td></tr>
        
        
       <tr><td>
       <textarea placeholder="내용을 입력해 주세요" style="text-align: center" >내용을 입력해 주세요 </textarea>
       </td></tr>
        
       </table>
       
       <br><br>
       <div>
       	<input type="button" id="modal_write_btn" style="text-align: center" value="제재"/>
       	 &nbsp;&nbsp;&nbsp;
       	<input type="button" id="modal_close_btn" style="text-align: center" value="취소"/>
	</div>
    </div>
   
 	   <div class="modal_layer"></div>
    
    
</div>

</div>
</div>







