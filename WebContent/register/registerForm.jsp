<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
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
		//아이디 중복검사
		$("#idChk").click(function(){
			window.open("idCheck.do?userid="+$("#userid").val(),"idCheck","width=400px, height=400px");
		});
		
		$("#userid").keyup(function(){
			$("idChkStatus").val("N");	
		});
		//우편번호검색
		$("#zipSearch").click(function(){
			window.open("<%=request.getContextPath()%>/register/zipSearch.do?doro=","zip","width=450px height=400px");
		});
		
		//등록버튼 누를때
		$("#regFrm").submit(function(){
			//중복검사
			if($("#idChkStatus").val()=="N"){
				alert("중복검사해야함");
				return false;
			}
			return true;
		});
		
		
	});
</script>
</head>
<body>
<%@include file="../menu.jspf" %>
	<h1>회원가입폼</h1>
	<form method="get" action="<%=request.getContextPath() %>/register/registerEditOk.do" id="regFrm">
		id<input type="text" name="userid" id="userid"/>
		<input type="button" value="중복확인" id="idChk"/>
		<!-- id 중복검사결과 -->
		<input type="hidden" name="idChkStatus" id="idChkStatus" value="N"/>
		<br/>
		password<input type="password" name="userpwd" id="userpwd"/><br/>
		passwordchk<input type="password" name="userpwdchk" id="userpwdchk"/><br/>
		name<input type="text" name="username" id="username"/><br/>
		birthday<input type="date" name="birthday" id="birthday"/><br/>
		tel <input type="text" name="tel" id="tel"/><br/>
		<script>
			var hobby=['여행','등산','스키','영화감상','쇼핑','게임','바이크','웹툰'];
			for(var i=0;i<hobby.length;i++){
				document.write("<input type='checkbox' name='hobby' value='"+hobby[i]+"'/>"+hobby[i]);
			};
		</script>
		<br/>
		우편번호 <input type="text" name="zipcode" id="zipcode"/>
		<input type="button" value="우편번호" id="zipSearch"/>
		기본주소 <input type="text" name="addr" id="addr"/><br/>
		상세주소 <input type="text" name="addrDetail" id="addrDetail"/><br/>
		<input type="submit" value="회원등록"/>
	</form>
	</body>
</html>