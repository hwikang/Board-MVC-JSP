<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page trimDirectiveWhitespaces="true" %>
<c:if test="${cnt>0 }">
			<!-- post 처럼 보내려면 -->
	<form id="editForm" action="view.do">
		<input type="hidden" name="num" value="${num}"/>
		<input type="hidden" name="recordNo" value="${recordNo }"/>
	</form>
	<script>
		alert("수정완료");
		document.getElementById("editForm").submit();
	</script>	
</c:if>
<c:if test="${cnt<=0 }">
	<script>
		alert("수정실패");
		history.back();
	</script>	
</c:if>