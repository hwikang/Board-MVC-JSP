<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page trimDirectiveWhitespaces="true" %>
<c:if test="${cnt>0 }">
	<script>
		alert("파일업로드 성공");
		location.href="<%=request.getContextPath()%>/data/dataList.do"
	</script>
	
</c:if>
<c:if test="${cnt<=0 }">
	<script>
		alert("파일업로드 실패");
		history.back();
	</script>
	
</c:if>