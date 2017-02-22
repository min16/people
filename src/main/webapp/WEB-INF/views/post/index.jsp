<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="/WEB-INF/views/common/common.jsp" />
	<script type="text/javascript" src="/resources/js/index.js"></script>
	<link rel="stylesheet" type="text/css" href="/resources/css/index.css" />
	<title>${post.title}</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<div class="container">
		<div class="title">
				<h2>${post.title}</h2>
				<p>
					<c:choose>
					<c:when test="${post.updateDate != null}">
						<fmt:parseDate value="${post.updateDate}" var="parsedDate" pattern="yyyy-MM-dd HH:mm"/>
						updated <fmt:formatDate value="${parsedDate}" type="both" dateStyle="medium" timeStyle="short"/>
					</c:when>
					<c:otherwise> 
						<fmt:parseDate value="${post.regDate}" var="parsedDate" pattern="yyyy-MM-dd HH:mm"/>
						<fmt:formatDate value="${parsedDate}" type="both" dateStyle="medium" timeStyle="short"/>
					</c:otherwise>
					</c:choose>
				</p>
		</div>
		<hr>
		<div class="content">
			${post.content}
		</div>
		
		
		<div class="button">
			<c:if test="${not empty sessionScope.admin}">
		  	  	<input onclick="update(${post.no})" class="btn btn-default admin" value="수정" readonly>
		  	  	<input onclick="remove('${post.category}', ${post.no})" class="btn btn-success admin" value="삭제" readonly>
	  	  	</c:if>
  	 		<input id="goBack" class="btn btn-default goBack" value="목록으로" readonly/>
  	 		<input type="hidden" id="postCategory" value="${post.category}">
  	 		<input type="hidden" id="category" value="${category}">
  	 		<input type="hidden" id="query" value="${query}">
  	 		<input type="hidden" id="queryType" value="${queryType}">
  	 		<input type="hidden" id="pageNo" value="${pageNo}">
  	 	</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>