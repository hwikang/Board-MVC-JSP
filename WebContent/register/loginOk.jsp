<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page trimDirectiveWhitespaces="true" %>
${vo.loginStatus}
<c:if test="${vo.loginStatus=='Y'}">
	<script>
	alert("성공");
		location.href="<%=request.getContextPath()%>/index.do";
	</script>
</c:if>
<c:if test="${vo.loginStatus=='N'}">
	<script>

	alert("실패");
		history.back();
	</script>
</c:if>

