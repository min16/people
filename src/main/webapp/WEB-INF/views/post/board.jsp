<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="/WEB-INF/views/common/common.jsp" />
	<title>문의 및 신청</title>
	<script type="text/javascript" src="/resources/js/board.js"></script>
	<script type="text/javascript" src="/resources/js/jquery.bootpag.js"></script>
	<link rel="stylesheet" type="text/css" href="/resources/css/board.css" />
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<div class="container-fluid">
		<form id = boardForm method="GET" action="/posts" class="boardForm">
			<div class="header">
				<div class="category">
					<select name="category" id="category" class="form-control" onchange="categorySubmit()">
					  <option value="all">전체 </option>
					  <option value="NOTICE" <c:if test="${category == 'NOTICE'}">selected='selected'</c:if>>공지</option>
					  <option value="WELCOME" <c:if test="${category == 'WELCOME'}">selected='selected'</c:if>>환영</option>
					  <option value="PERSONNEL" <c:if test="${category == 'PERSONNEL'}">selected='selected'</c:if>>인사</option>
					  <option value="VACATION" <c:if test="${category == 'VACATION'}">selected='selected'</c:if>>휴가</option>
					  <option value="REQUIREMENT" <c:if test="${category == 'REQUIREMENT'}">selected='selected'</c:if>>요청사항</option>
					</select>
				</div>
				<div class="search">
					<div class="option">
						<select name= queryType id="queryType" class="form-control">
							<option value="all">전체</option>  
							<option value="title" <c:if test="${queryType == 'title'}">selected='selected'</c:if>>제목</option>
							<option value="content" <c:if test="${queryType == 'content'}">selected='selected'</c:if>>내용</option>
							<option value="userName" <c:if test="${queryType == 'userName'}">selected='selected'</c:if>>작성자</option>
						</select>
					</div>
					<div class="search-text">
						<input name="query" id="searchLText" type="text" class="form-control" value="${query}">
					</div>
					<div class="search-button">
						<button id="search" class="btn btn-info">검색</button>
					</div>
				</div>
			</div>
			<div class="content">
				<table class="table">
			 		<thead>
			 			<tr>
				 			<td>번호</td>
				 			<td>제목</td>
				 			<td>작성일</td>
				 			<td>조회</td>
			 			</tr>
			 		</thead>
			 		<tbody>
			 			<c:forEach items="${posts}" var="post">
			 				<tr onclick="goDetail('${post.no}','${category}','${query}','${queryType}','${pageNo}')">
			 					<td>${post.no}</td>
		 						<td>${post.title}</td>
			 					<td>
			 						<div class="mobile">
			 							<fmt:parseDate value="${post.regDate}" var="parsedDate" pattern="yyyy-MM-dd HH:mm"/>
										<fmt:formatDate value="${parsedDate}" pattern="MM/dd"/>
			 						</div>
			 						<div class="web">
			 							<fmt:parseDate value="${post.regDate}" var="parsedDate" pattern="yyyy-MM-dd HH:mm"/>
										<fmt:formatDate value="${parsedDate}" type="both" dateStyle="short" timeStyle="short"/>
			 						</div>
			 					</td>
			 					<td>${post.pageView}</td>
			 				</tr>
			 			</c:forEach>
			 		</tbody>
				</table>	
			</div>
			<p id="page-selection"></p>
			<input type="hidden" name="pagingTotal" id="pagingTotal" value="${pagingTotal}"/>
			<input type="hidden" name="pageNo" id="pageNo" value="${pageNo}"/>
		</form>
		<c:if test="${not empty sessionScope.admin}">
			<div class="write">
				<a class="btn btn-primary" href="/posts/proc">글쓰기</a> 
			</div> 
		</c:if>
	</div> <!-- container-fluid -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>