<%@page import="dto.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
<h1>게시글</h1>
<hr>
<br>

<%   Board board = (Board)request.getAttribute("b"); %>

<table>
<tr>
   <th><%=board.getTitle() %></th>
   <th><%=board.getInsertdate() %></th>
</tr>
<tr>
   <td>작성자 : <%=board.getUserid() %></td>
   <td>조회수 : <%=board.getHit() %></td>
</tr>
<tr>
   
   <td>
   <% if(session.getAttribute("unick").equals(board.getUserid())) { %>
   <a href="/board/delete?bno=<%=board.getBoardno()%>">삭제</a></td>
   <% }%>
</tr>
<tr>
<td  colspan="2"><%=board.getContent() %></td>
</tr>
</table>
<br>
<a href="/board/list">게시판으로</a>
</body>
</html>