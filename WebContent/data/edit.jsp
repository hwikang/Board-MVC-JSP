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
	$(function(){
		$("#del1").click(function(){			
			var f1= "<input type='file' name='filename' id='filename'/>";
			f1 +="<input type='hidden' name='delfile' id='delfile' value='${vo.filename}'/>";
			$(this).parent().html(f1);
		});
		$("#del2").click(function(){			
			var f2= "<input type='file' name='filename2' id='filename2'/>";
			f2 +="<input type='hidden' name='delfile2' id='delfile2' value='${vo.filename2}'/>";
			$(this).parent().html(f2);
		});
	});



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
	<form method="post" action="dataEditOk.do" enctype="multipart/form-data">	
		<input type="hidden" name="recordNo" id="recordNo" value="${vo.recordNo}"/>
		<input type="hidden" name="title" id="title" value="${vo.title}"/>
		<div class="form-group row">
			<label class="col-sm-2">번호</label>
			<label class="form-control col-sm-10" >${vo.recordNo }</label>
			
		</div>
		<div class="form-group row">
			<label class="col-sm-2">작성자</label>
			<label class="form-control col-sm-10">${logid}</label>
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
			<c:if test="${vo.filename !=null && vo.filename !='' }">
				<div><label>${vo.filename }</label><b id="del1">x</b></div>
			</c:if> <!-- 첨부파일있을때 -->
			<c:if test="${vo.filename ==null && vo.filename =='' }">
				<input type="file" id="filename" name="filename" class="form-control col-10"/>
			</c:if> <!-- 첨부파일없을때 -->		
		</div>
		<div class="form-group row">
			<label class="col-sm-2">첨부파일</label>
			<c:if test="${vo.filename2 !=null && vo.filename2 !='' }">
				<div><label>${vo.filename2 }</label> <b id="del2">x</b></div>
			</c:if> <!-- 첨부파일있을때 -->
			<c:if test="${vo.filename2 ==null || vo.filename2 =='' }">
				<div><input type="file" id="filename2" name="filename2" class="form-control col-10"/></div>
			</c:if> <!-- 첨부파일없을때 -->		
		</div>
		<input type="submit" value="자료실 글수정"/>
	</form>
</div>
<div class="list-group">
	<div class="list-group-item list-group-item-warning">		
		<a href="dataDelete.do">삭제</a>
	</div>
</div>
</body>
</html>

