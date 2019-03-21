<%@page import="kr.goott.register.ZipcodeVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.goott.register.RegisterDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width , initial-scale=1" >
<title>제목을넣으면되ㅎㅎ</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
<script>
	$(function(){
		$("#zip li").click(function(){
			var txt = $(this).text();
			alert(txt)
																		//0부터 5
			opener.document.getElementById("zipcode").value=txt.substring(0,5);
			opener.document.getElementById("addr").value=txt.substring(6);
			window.close();
		});
	});
</script>
</head>
<body>
	
	<h2>우편번호 검색</h2>
	도로명을 입력후 버튼을 클릭하세요<br/>
	예: 학의로 146<br/>
	<form method="get" action="zipSearch.do">
	도로명입력<input type="text" name="doro" id="doro" placeholder="학의로 146"/>
	<input type="submit" value="검색"/>
	</form>
	<hr/>
	<ul id="zip">
	<!-- 우편번호 , 시도 -->
		<c:if test="${size<=0}">
			<li>도로명을 입력하세요</li>
		</c:if>
		<c:if test="${size>0}">
		
			<c:forEach var="vo" items="${list }">
				<li style="cursor:pointer">${vo.zipcode}${" "}${vo.sido}${" "}${vo.sigungu}${" "}${vo.um}${" "}${vo.doro}${" "}${vo.bNum}${" "}${vo.bName}(${vo.dong}()${vo.liName})</li>	
			</c:forEach>
		</c:if>

</ul>
</body>
</html>