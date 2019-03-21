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
		
		//우편번호검색
		$("#zipSearch").click(function(){
			window.open("zipSearch.jsp?doro=","zip","width=450px height=400px");
		});
		
		//등록버튼 누를때
		$("#regFrm").submit(function(){

			if($("#userpwd").val() != $("#userpwdchk").val()){
				alert("비밀번호 두개 다름");
				return false;
			}
			return true;
		});

			
		
		
	});
</script>
</head>
<body>
	<h1>회원가입폼${cnt}0</h1>
	<form method="get" action="<%=request.getContextPath() %>/register/registerEditOk.do" id="regFrm">
		id<input type="text" name="userid" id="userid" value="${vo.userid}"/>
		<input type="button" value="중복확인" id="idChk"/>
		<br/>
		password<input type="password" name="userpwd" id="userpwd"/><br/>
		passwordchk<input type="password" name="userpwdchk" id="userpwdchk"/><br/>
		name<input type="text" name="username" id="username" value="${vo.username}"/><br/>
		birthday<input type="date" name="birthday" id="birthday" value="${vo.birthday }"/><br/>
		tel <input type="text" name="tel" id="tel" value="${vo.tel }"/><br/>

		<c:forEach var="h" items="여행,독서,스키,영화감상,쇼핑,게임,웨이트,사진">
			<input type="checkbox" name="hobby" value="${h}"
				<c:forTokens var="j" items="${vo.hobbyStr}" delims="/">
					<c:if test="${h==j}">
						checked
					</c:if>
				</c:forTokens>
			/>${h}
		</c:forEach>
		<br/>
		우편번호 <input type="text" name="zipcode" id="zipcode" value="${vo.zipcode }"/>
		<input type="button" value="우편번호" id="zipSearch"/>
		기본주소 <input type="text" name="addr" id="addr" value="${vo.addr }"/><br/>
		상세주소 <input type="text" name="addrDetail" id="addrDetail" value="${vo.addrDetail}"/><br/>
		<input type="submit" value="회원등록"/>
	</form>
	</body>
</html>