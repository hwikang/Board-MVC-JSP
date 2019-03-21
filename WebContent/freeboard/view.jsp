<%@page import="kr.goott.freeboard.FreeboardDAO"%>
<%@page import="kr.goott.freeboard.FreeboardVO"%>
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
	.replyEditForm{
		display:none
	}
</style>
</head>
<body>
<%@ include file="../menu.jspf" %>
<c:if test="${logStatus==null || logStatus==''}">
	<script>
		alert("로그인 후 이용하세요");
		location.href="<%=request.getContextPath()%>/register/login.do";
	</script>
</c:if>

	<script>
		
		function delChk(){
			if(confirm("삭제하시겠습니까")){// true false return
				location.href="<%=request.getContextPath() %>/freeboard/delOk.do?recordNo=${vo.recordNo}&num=${vo.num}";
				
			}			
										
		}	
		
		$(function(){
			$("#replyFrm").submit(function(){
				if($("#replyContent").val()==""){
				alert("댓글 미입력");
				return false;
				}
				return true;
			});
		});
		
		function replyEdit(replyID){
			
			var css = window.getComputedStyle(document.getElementById("replyView"+replyID));
			var displayID = css.getPropertyValue("display"); //해당 아이디의 display상태		
			if(displayID=="block"){	
				document.getElementById("editLink"+replyID).innerHTML="수정취소";
				document.getElementById("replyView"+replyID).style.display="none";
				document.getElementById("edit"+replyID).style.display="block";
			}else{		
				document.getElementById("editLink"+replyID).innerHTML="수정";
				document.getElementById("replyView"+replyID).style.display="block";
				document.getElementById("edit"+replyID).style.display="none";
			}
		}
							//삭제,댓글번호,글번호,페이지
		function replyDelete(replyNo,recordNo,num){
			if(confirm("댓글을 삭제할거니?")){
				location.href= "<%=request.getContextPath()%>/freeboard/freeboardReplyDeleteOk.do?replyNo="+replyNo+"&recordNo="+recordNo+"&num="+num;
			}
		}
		</script>
	<div class="container">
		<h1>WRITE</h1>
		<form method="get" id="freeFrm" class="form-horizontal">
			<div class="form-group row">
				<label class="col-sm-1">ID</label>
				<label class="col-sm-3">${vo.userid }</label>
				<label class="col-sm-1">VIEWS</label>
				<label class="col-sm-2">${vo.hit }</label>
				<label class="col-sm-1">DATE</label>
				<label class="col-sm-4">${vo.regdate }</label>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">Title</label>
				<label class="col-sm-10">${vo.title}</label>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">글내용</label>
				<p class="col-sm-10">${vo.content }</p>
			</div>	

		</form>
			<form action="<%=request.getContextPath()%>/freeboard/replyWriteOk.do" id="replyFrm" method="get">
   				 <div class="form-group row">
           			 <label class="col-2">덧글쓰기</label>
     				   <textarea class="col-8" id="replyContent" name="replyContent"></textarea>
      				  <input type="submit"  class="col-2" value="등록"/>
  				  </div>
  				  <input type="hidden" name="recordNo" value="${vo.recordNo}"/>
  				  <input type="hidden" name="num" value="${vo.num}"/>  				  
			</form>
			<!-- 덧글목록 -->
			<c:forEach var="replyVO" items="${list }">
				<div class="form-group row">
					<label class="col-1">${replyVO.userid }</label>
					<label class="col-2">${replyVO.replyRegdate }</label>
					<c:if test="${logid==replyVO.userid }">
						<a href="javascript:replyEdit(${replyVO.replyNo})" id="editLink${replyVO.replyNo}">수정</a>
						<a href="javascript:replyDelete(${replyVO.replyNo},${vo.recordNo },${vo.num })">삭제</a>
					</c:if>
					<br/>
					<p id="replyView${replyVO.replyNo}">${replyVO.replyContent}</p>
				</div>
				<c:if test="${logid==replyVO.userid }">
					<form class="replyEditForm" id="edit${replyVO.replyNo }" method="get" action="<%=request.getContextPath() %>/freeboard/freeboardReplyOk.do">
						<div class="row">
							<textarea name="replyContent" id="replyContent" class="col-11">${replyVO.replyContent}</textarea>
							<input type="submit" value="수정" class="col-1" />
							<input type="hidden" name="recordNo" value="${vo.recordNo}">
		  				  	<input type="hidden" name="num" value="${vo.num}"> 
		  				  	<input type="hidden" name="replyNo" value="${replyVO.replyNo }">
						</div>
					</form>
				</c:if>
			</c:forEach>

	</div>
	
	<div>
		<div>
			
			<!-- 로그인한 아이디와 같은 글일경우 -->
			
			<c:if test="${vo.userid==logid}">
				 <%-- <%=request.getContextPath() %>/freeboard/ --%>
				<a href="edit.do?recordNo=${vo.recordNo}&num=${vo.num}">수정</a>
				<a href="javascript:delChk()">삭제</a>	<!-- delChk 함수를 실행 -->
			</c:if>
			<c:if test="${vo.userid!=logid}">
				<a href="#">수정</a>
				<a href="#">삭제</a>
			</c:if>
		</div>
	
	</div>
</body>
</html>