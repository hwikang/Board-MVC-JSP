<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page trimDirectiveWhitespaces="true" %>
<!-- 뢰원가입 성공시 로그인 폼으로, 실패하면 이전 페이지로  -->
<c:if test="${cnt==0}">
	<script>
		history.back();
	</script>
</c:if>
<c:if test="${cnt==1}">
	<script>
		location.href="<%=request.getContextPath()%>/register/login.do";
	</script>
</c:if>