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
		$("#setID").on("click",function(){
			//회원가입폼에 id 입력, 창닫기
			opener.document.getElementById("userid").value= $("#idChk").text(); //opener-오픈한곳
			opener.document.getElementById("idChkStatus").value= "Y";
			self.close();//window.close();
		});

	});
</script>
</head>
<body>
<div class="container">
	<!-- 사용가능 id 일떄-->
${cnt}
	<c:if test="${cnt<=0}"> 
		<span id="idChk" style="color:red">${userid}</span>는 사용 가능한 id입니다
		<input type="button" value="사용하기" id="setID"/>
			
	</c:if>
	<!-- 중복 id 일때 -->
	<c:if test="${cnt>0 }">
		<span>${userid }는 사용 불가능한 아이디 입니다.</span>
	</c:if>
	<hr/>
	아이디 입력후 중복검사 버튼을 누르세요...<br/>
	<form method="post" action="<%=request.getContextPath() %>/register/idCheck.do">
	ID : <input type="text" name="userid" id="userid"/>
	<input type="submit" value="중복검사"/><br/>
	
	</form>
</div>
</body>
</html>