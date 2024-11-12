<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<nav id="navigation" class="">
	<div class="container">
		<div id="responsive-nav">
			<ul class="nav navbar-nav nav-tabs nav-justified">
				<li><a href="${pageContext.request.contextPath}/admin/dashboard" class="nav-tab">Dashboard</a></li>
				<li><a href="${pageContext.request.contextPath}/admin/accounts" class="nav-tab">Manage Users</a></li>
				<li><a href="${pageContext.request.contextPath}/admin/genres" class="nav-tab">Genres</a></li>
				<li><a href="${pageContext.request.contextPath}/admin/products" class="nav-tab">Products</a></li>
				<li><a href="${pageContext.request.contextPath}/admin/posts" class="nav-tab">Posts</a></li>
				<li><a href="${pageContext.request.contextPath}/admin/settings" class="nav-tab">Settings</a></li>
			</ul>
		</div>
	</div>
</nav>