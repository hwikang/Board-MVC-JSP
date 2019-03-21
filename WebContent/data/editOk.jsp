<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page trimDirectiveWhitespaces="true" %>
<c:if test="${cnt>0 }">
	<script>
		alert("자료실수정됨");
		location.href="<%=request.getContextPath()%>/data/dataView.do?recordNo=${vo.recordNo}";
	</script>
</c:if>
<c:if test="${cnt<=0 }">
	<script>
		alert("자료실수정 failed");
		history.back();
	</script>
</c:if>