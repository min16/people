<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   
<header>
	<div class="head">
		<div class="logo"><a href="/"><img alt="background" src="/resources/images/ci_logo_large.png"></a></div>
	</div>
</header>

<nav>
	<a class="mobile-menu"><span>menu</span></a>
	<ul class="menus">
		<li class="item">
			<div>
				<a href="javascript:void(0)" onclick="goList('NOTICE')">공지</a>
			</div>
			<ul class="list">
				<c:forEach items="${navPosts}" var="post">
					<c:if test="${post.category == 'NOTICE'}">
						<li><a href="javascript:void(0)" onclick="goToPost('${post.no}')">${post.title}</a></li>
					</c:if>
				</c:forEach>
			</ul>
		</li>
		<li class="item">
			<div>
				<a href="javascript:void(0)" onclick="goList('WELCOME')">환영</a>
			</div>
			<ul class="list">
				<c:forEach items="${navPosts}" var="post">
					<c:if test="${post.category == 'WELCOME'}">
						<li><a href="javascript:void(0)" onclick="goToPost('${post.no}')">${post.title}</a></li>
					</c:if>
				</c:forEach>
			</ul>
		</li>
		<li class="item">
			<div>
				<a href="javascript:void(0)" onclick="goList('PERSONNEL')">인사 </a>
			</div>
			<ul class="list">
				<c:forEach items="${navPosts}" var="post">
					<c:if test="${post.category == 'PERSONNEL'}">
						<li><a href="javascript:void(0)" onclick="goToPost('${post.no}')">${post.title}</a></li>
					</c:if>
				</c:forEach>
			</ul>
		</li>
		<li class="item">
			<div>
				<a href="javascript:void(0)" onclick="goList('VACATION')">휴가</a>
			</div>
			<ul class="list">
				<c:forEach items="${navPosts}" var="post">
					<c:if test="${post.category == 'VACATION'}">
						<li><a href="javascript:void(0)" onclick="goToPost('${post.no}')">${post.title}</a></li>
					</c:if>
				</c:forEach>
			</ul>
		</li>
		<li class="item">
			<div>
				<a href="javascript:void(0)" onclick="goList('REQUIREMENT')">요청사항</a>
			</div>
			<ul class="list">
				<c:forEach items="${navPosts}" var="post">
					<c:if test="${post.category == 'REQUIREMENT'}">
						<li><a href="javascript:void(0)" onclick="goToPost('${post.no}')">${post.title}</a></li>
					</c:if>
				</c:forEach>
			</ul>
		</li>
		<c:if test="${not empty sessionScope.admin}">
			<div class="admin">관리자 모드</div>
		</c:if>
	</ul>
</nav>