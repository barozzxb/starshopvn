<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<nav
	class="custom-navbar navbar navbar navbar-expand-md navbar-dark bg-dark" arial-label="Furni navigation bar">

	<div class="container">
		<a class="navbar-brand" href="${pageContext.request.contextPath }/home">StarShop<span>.</span></a>

		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarsFurni" aria-controls="navbarsFurni"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarsFurni">
			<ul class="custom-navbar-nav navbar-nav ms-auto mb-2 mb-md-0">
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath }/home">Trang chủ</a></li>
				<li><a class="nav-link" href="${pageContext.request.contextPath }/products">Cửa hàng</a></li>
				<li><a class="nav-link" href="${pageContext.request.contextPath }/about">Thông tin</a></li>
				<li><a class="nav-link" href="${pageContext.request.contextPath }/services">Dịch vụ</a></li>
				<li><a class="nav-link" href="${pageContext.request.contextPath }/posts">Bài đăng</a></li>
				<li><a class="nav-link" href="${pageContext.request.contextPath }/contact">Liên hệ</a></li>
			</ul>

			<ul class="custom-navbar-cta navbar-nav mb-2 mb-md-0 ms-5">
				<li><a class="nav-link" href="${pageContext.request.contextPath }/login">Đăng nhập</a></li>
				<li><a class="nav-link" href="cart.html"><i class="fa fa-shopping-bag"></i></a></li>
			</ul>
		</div>
	</div>

</nav>