<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="/WEB-INF/views/common/common.jsp" />
	<script type="text/javascript" src="/resources/js/index.js"></script>
 		<link rel="stylesheet" type="text/css" href="/resources/css/list.css" />	
	<title>${category}</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<div class="container">
		<div class="list">
			<c:forEach items="${posts}" var="post">
				<a href="#<c:out value='${post.no}'/>">#${post.title}</a>
			</c:forEach>
		</div>
		<c:forEach items="${posts}" var="post">
			<div id="${post.no}" class="post">
				<div class="title">
					<h2>${post.title}</h2>
					<div class="web">
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
				</div>
				<div class="content">
					${post.content}
				</div>
				<div class="button">
					<c:if test="${not empty sessionScope.admin}">
				  	  	<input onclick="update(${post.no})" class="btn btn-default" value="수정" readonly>
				  	  	<input onclick="remove('${post.category}', ${post.no})" class="btn btn-success" value="삭제" readonly>
			  	  	</c:if>
		  	 	</div>
		  	 	<hr>
		  	 </div>
		</c:forEach>
		<c:if test="${not empty sessionScope.admin}">
			<div class="write">
				<a class="btn btn-primary write-btn" href="/posts/proc">글쓰기</a> 
			</div> 
		</c:if>	
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />			
</body>
</html>