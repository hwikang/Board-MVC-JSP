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
<script src="https://cdn.ckeditor.com/4.11.2/full/ckeditor.js"></script>
</head>
<body>
<%@ include file="../menu.jspf" %>
<c:if test="${logStatus=='N' ||logStatus==null }"><!-- 로그인 안되있을때 -->
	<script>
		alert("로그인후 글을 등록할수있습니다");
		location.href ="<%=request.getContextPath()%>${login}"
	</script>
</c:if>
	<div class="container">
		<h1>WRITE</h1>
		<form method="get" id="freeFrm" action="<%=request.getContextPath() %>/freeboard/writeOk.do" class="form-horizontal">
			<div class="form-group row">
				<label class="col-sm-2">ID</label>
				<input type="text" id="userid" name="userid" 
				class="form-control col-sm-10" value="${logid }" readonly/>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">Title</label>
				<input type="text" id="title" name="title" class="form-control col-sm-10"/>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">글내용</label>
				<textarea type="text" id="content" name="content" class="form-control col-sm-10"></textarea>
				<script>
					CKEDITOR.replace("content");
				</script>
			</div>
			<input type="submit" value="글등록" class="btn btn-dark form-control"/>
		</form>
	</div>
	<div class="list-group">
		<div class="list-group-item">
			<a href="<%=request.getContextPath()%>/index.do">홈</a>
			<a href="<%=request.getContextPath()%>/freeboard/list.do">리스트</a>
		</div>
	</div>
</body>
</html>