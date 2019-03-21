<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page trimDirectiveWhitespaces="true" %>
${cnt }
<c:if test="${cnt>0}">	
	<script>
		alert("글이 등록되쏘욤");
		location.href="list.do";
	</script>
</c:if>
<c:if test="${cnt<=0}">	
	<script>
		alert("글이 등록되지 안아쏘욤");
		history.back();
	</script>
</c:if>