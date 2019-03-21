
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
<style>
	.wordCut{
		white-space:nowrap;
		overflow:hidden;
		text-overflow:ellipsis;
	}
	
</style>
<script>
	function download(filename,recordNo){
		location.href="download.jsp?filename="+encodeURI(filename)+"&recordNo="+recordNo;
	}
</script>
</head>
<body>
<%@ include file="../menu.jspf" %>

<div class="container">
	<h1>자료실 리스트</h1>
	<div class="list-group">
		<div class="list-group-item list-group-item-action">page1/3</div>
		<div class="list-group-item list-group-item-action">
			<div class="row">
				<div class="col-sm-1">번호</div>
				<div class="col-sm-5">제목</div>
				<div class="col-sm-2">아이디</div>
				<div class="col-sm-1">첨부</div>
				<div class="col-sm-1">다운s</div>
				<div class="col-sm-2">등록일</div>
			</div>
		</div>
 		<c:forEach var="vo" items="${list}">
		<div class="list-group-item list-group-item-action">
			<div class="row">
				<div class="col-sm-1">${vo.recordNo}</div>
				<div class="col-sm-6 wordCut"><a href="dataView.do?recordNo=${vo.recordNo}">${vo.title }</a></div>
				<div class="col-sm-2">${vo.userid }</div>
				<div class="col-sm-1">
					<a href="javascript:download('${vo.filename}','${vo.recordNo}')"><img src="../img/disk.gif"/></a>
					<!-- 두번째 파일이 존재할경우에만 -->
					<c:if test="${vo.filename2 !='' }">
						<a href="javascript:download('${vo.filename2}','${vo.recordNo}')"><img src="../img/disk.gif"/></a>
					</c:if>
				</div>
				<div class="col-sm-1">${vo.downCount }</div>				
				<div class="col-sm-1">${vo.regdate }</div>
			</div>
		</div>
		</c:forEach>
	</div>
	
	
	<!-- 검색어 -->
	<form class="form-inline justify-content-center" id="searchFrm" action="list.jsp">
		<div class="list-group-item searchForm">
			<select name="searchKey" class="form-control">
				<option value="title">제목</option>
				<option value="content">글내용</option>
				<option value="userid">아이디</option>
			</select>
			<input type="text" name="searchWord" class="form-control"/>
			<input type="submit" value="Search" class="form-control"/>			
		</div>
	</form>
	<div class="list-group">
		<div class="list-group-item">
			<a href="<%=request.getContextPath() %>/index.jsp">홈</a>
			<a href="<%=request.getContextPath() %>/data/dataList.do">리스트</a>
			<c:if test="${logStatus==null||logStatus=='N'}">
			<a href="#">글쓰기</a>
			</c:if>
			<c:if test="${logStatus=='Y'}">			
			<a href="<%=request.getContextPath() %>/data/dataWrite.do">글쓰기</a>
			</c:if>
			
	</div>
	
</div>
</body>
</html>