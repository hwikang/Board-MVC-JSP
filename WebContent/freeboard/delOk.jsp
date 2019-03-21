<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page trimDirectiveWhitespaces="true" %>
<c:if test="${cnt>0 }">
			<!-- post 처럼 보내려면 -->
	<form id="delForm" action="list.do">
		<input type="hidden" name="num" value="${vo.num}"/>
		<input type="hidden" name="recordNo" value="${vo.recordNo }"/>
	</form>
	<script>
		alert("삭제완료");
		document.getElementById("delForm").submit();
	</script>	
</c:if>
<c:if test="${cnt<=0 }">
	<script>
		alert("삭제실패");
		history.back();
	</script>	
</c:if>