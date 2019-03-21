<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<c:if test="${cnt>0}">
		<script>
			alert("수정완료");
			location.href="<%=request.getContextPath()%>/register/regEdit.do";
		</script>
	</c:if>
	