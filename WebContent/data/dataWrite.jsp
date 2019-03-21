<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width , initial-scale=1" >
<title>자료실</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
<script src="https://cdn.ckeditor.com/4.11.2/full/ckeditor.js"></script>
<script>
	$(function(){
		$("#dataFrm").submit(function(){
			var cnt =0;
			var f1 = $("#filename").val();
			var f2 = $("#filename2").val();
			
			//파일명만
			if(f1!=""){				
				cnt++
				var ext = f1.substring(f1.lastIndexOf(".")+1).toLowerCase(); //확장자
				alert(ext);
				if(!(ext=="gif" || ext=="jpg" || ext=="png")){
					alert("이미지파일만가능함");
					return false;
				}
			}
			if(f2!=""){
				cnt++
				var ext = f1.substring(f1.lastIndexOf(".")+1).toLowerCase(); //확장자
				alert(ext);
				if(!(ext=="gif" || ext=="jpg" || ext=="png")){
					alert("이미지파일만가능함");
					return false;
				}
			}
			if(cnt<1){
				alert("첨부파일이 1개도업음");
				return false;
			}
		
		});

		

	});
</script>

<c:if test="${logStatus==null||logStatus=='N' }">
	<script>
		alert("로그인후 이용하세요");
		location.href="<%=request.getContextPath()%>/register/login.do";
	</script>
</c:if> 
</head>
<body>
<%@include file="/menu.jspf" %>

	<div class="container">
		<h1>자료실 글쓰기</h1>																<!--enctype : 파일 업로드 폼의경우 반드시 있어야함  -->
		<form method="post" id="dataFrm" action="dataWriteOk.do" class="form-horizontal" enctype="multipart/form-data">
			<div class="form-group row">
				<label class="col-sm-2">ID</label>	
				<input type="text" id="userid" name="userid" 
				class="form-control col-sm-10" value="${logid}" readonly/>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">Title</label>
				<input type="text" id="title" name="title" class="form-control col-sm-10"/>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2">첨부file</label>
				<input type="file" id="filename" name="filename" class="form-control col-sm-10"/>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">첨부file2</label>
				<input type="file" id="filename2" name="filename2" class="form-control col-sm-10"/>
			</div>
			<input type="submit" value="Up	load" class="btn btn-dark form-control"/>
		</form>
	</div>
	<div class="form-group">
		<div class="form-group-item">
			<a href="<%=request.getContextPath() %>/data/dataList.do">리스트</a>
		</div>
	</div>
</body>
</html>