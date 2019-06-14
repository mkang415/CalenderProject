<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- header 영역 -->
<c:import url="/WEB-INF/views/layout/header.jsp"/>

<div class="main_content">
	<!-- bootstrap 적용된 index.jsp import -->
	<c:import url="/WEB-INF/views/main/index.jsp"/>
</div>

<!-- footer 영역 -->
<c:import url="/WEB-INF/views/layout/footer.jsp"/>