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
</head>
<body>

${vo.startPage }
<div class="container">
	<h1>게시판 리스트</h1>
	<div class="list-group">
		<div class="list-group-item list-group-item-action">pages ${vo.num }/${vo.totalPage }</div>
		<div class="list-group-item list-group-item-action">
			<div class="row">
				<div class="col-sm-1">번호</div>
				<div class="col-sm-6">제목</div>
				<div class="col-sm-2">아이디</div>
				<div class="col-sm-1">조회수</div>
				<div class="col-sm-2">등록일</div>
			</div>
		</div>
		<c:forEach var="vo1" items="${list}">
		<div class="list-group-item list-group-item-action">
			<div class="row">
				<div class="col-sm-1">${vo1.recordNo}</div>
				<div class="col-sm-6 wordCut"><a href="<%=request.getContextPath() %>/freeboard/view.do?num=${vo.num}&recordNo=${vo1.recordNo}">${vo1.title}</a></div>
				<div class="col-sm-2">${vo1.userid }</div>
				<div class="col-sm-1">${vo1.hit }</div>
				<div class="col-sm-2">${vo1.regdate }</div>
			</div>
		</div>
		</c:forEach>
		
	</div>
	<!-- paging -->
	<ul class="pagination pagination-sm justify-content-center">
		<li class="page-item"><a href="list.do?num=${vo.num-1}&searchKey=${vo.searchKey }&searchWord=${vo.searchWord}" class="page-link">&laquo;</a></li>
	
		<c:forEach var="i" begin="${vo.startPage}" end="${vo.startPage+vo.pageNumCount-1 }">
			<c:if test ="${i<=vo.totalPage}">
				<c:choose>
					<c:when test="${i==vo.num }">
						<li class="page-item active"><a href="list.do?num=${i}&searchKey=${vo.searchKey }&searchWord=${vo.searchWord}" class="page-link">${i}</a></li>
					</c:when>
					<c:when test="${i!=vo.num }">
						<li class="page-item"><a href="list.do?num=${i}&searchKey=${vo.searchKey }&searchWord=${vo.searchWord}" class="page-link">${i}</a></li>
					</c:when>					
				</c:choose>
			</c:if>			
		</c:forEach> 
	
		<li class="page-item"><a href="list.do?num=${vo.num+1}&searchKey=${vo.searchKey }&searchWord=${vo.searchWord}" class="page-link">&raquo;</a></li>
	
	</ul>
	
	<!-- 검색어 -->
	<form class="form-inline justify-content-center" id="searchFrm" action="<%=request.getContextPath()%>/freeboard/list.do">
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
			<a href="<%=request.getContextPath() %>/index.do">홈</a>
			<a href="<%=request.getContextPath() %>/freeboard/list.do">리스트</a>
			<c:if test="${logStatus==null||logStatus=='N'}">
			<a href="#">글쓰기</a>
			</c:if>
			<c:if test="${logStatus=='Y'}">
			<a href="<%=request.getContextPath() %>/freeboard/write.do">글쓰기</a>
			</c:if>
			

		</div>
	</div>
	
</div>
</body>
</html>