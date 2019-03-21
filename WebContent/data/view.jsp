<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
<script>
	function download(filename, recordNo){
		location.href="download.jsp?filename="+encodeURI(filename)+"&recordNo="+recordNo;
	}
</script>
</head>
<body>
<%@include file ="/menu.jspf" %>
<c:if test="${logStatus==null || logStatus == 'N'}">
	<script>
		alert("로그인 후 글을 볼 수 있어요.");
		location.href="${ctx}register/Login.do";
		
	</script>
</c:if>
<div class="container">
	<h1>자료실 글 내용보기</h1>
	<div class="form-group row">
		<label class="col-sm-2">번호</label>
		<label class="form-control col-sm-10">${vo.recordNo }</label>
	</div>
	<div class="form-group row">
		<label class="col-sm-2">작성자</label>
		<label class="form-control col-sm-10">${vo.userid }</label>
	</div>
	<div class="form-group row">
		<label class="col-sm-2">작성일</label>
		<label class="form-control col-sm-6">${vo.regdate }</label>
		<label class="col-sm-2">Down</label>
		<label class="form-control col-sm-2">${vo.downCount }</label>
	</div>
	<div class="form-group row">
		<label class="col-sm-2">제목</label>
		<label class="form-control col-sm-10">${vo.title }</label>
	</div>
	<div class="form-group row">
		<label class="col-sm-2">첨부파일</label>
		<label class="form-control col-sm-10">
			<c:if test="${vo.filename !=null && vo.filename !=''}">
				<a href="javascript:download('${vo.filename}', ${vo.recordNo})">${vo.filename}</a>
			</c:if>
			<c:if test="${vo.filename2 !=null && vo.filename2 !=''}">
				<a href="javascript:download('${vo.filename2}', ${vo.recordNo})">${vo.filename2}</a>
			</c:if>
		</label>
	</div>
</div>
<div class="list-group">
	<div class="list-group-item list-group-item-warning">
		<a href="dataEdit.do?recordNo=${vo.recordNo}">수정</a>
		<a href="dataDelete.do?recordNo=${vo.recordNo}">삭제</a>
	</div>
</div>
</body>
</html>

