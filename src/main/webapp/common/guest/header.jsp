<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<nav
	class="custom-navbar navbar navbar navbar-expand-md navbar-dark bg-dark"
	arial-label="Furni navigation bar">

	<div class="container">
		<a class="navbar-brand" href="index.jsp">Starshop<span>.</span></a>

		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarsFurni" aria-controls="navbarsFurni"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarsFurni">
			<ul class="custom-navbar-nav navbar-nav ms-auto mb-2 mb-md-0">
				<li class="nav-item active"><a class="nav-link"
					href="index.html">Home</a></li>
				<li><a class="nav-link" href="shop.html">Shop</a></li>
				<li><a class="nav-link" href="about.html">About us</a></li>
				<li><a class="nav-link" href="services.html">Services</a></li>
				<li><a class="nav-link" href="blog.html">Blog</a></li>
				<li><a class="nav-link" href="contact.html">Contact us</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-bs-toggle="dropdown" aria-expanded="false">
						Genres </a>
					<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
						<!-- Duyệt qua danh sách genres từ model -->
						<c:forEach var="genre" items="${genres}">
							<li><a class="dropdown-item"
								href="${pageContext.request.contextPath}/user/genres/products?gid=${genre.gid}">${genre.gname}</a></li>
						</c:forEach>
					</ul></li>
			</ul>

			<ul class="custom-navbar-cta navbar-nav mb-2 mb-md-0 ms-5">
				<li><c:choose>
						<c:when test="${sessionScope.account == null}">
							<li><a class="nav-link"
								href="${pageContext.request.contextPath}/login">Login</a></li>
						</c:when>
						<c:otherwise>
							<li><a class="nav-link"
								href="${pageContext.request.contextPath}/user/my"> <span>
										${sessionScope.account.name == null ? sessionScope.account.userid : sessionScope.account.name}
								</span>
							</a></li>
							<li><a class="nav-link"
								href="${pageContext.request.contextPath}/logout">Đăng Xuất</a></li>
						</c:otherwise>
					</c:choose></li>
				<li><a class="nav-link" href="cart.html"><i
						class="fa fa-shopping-bag"></i></a></li>
				<li><a class="nav-link"
					href="${pageContext.request.contextPath}/user/product/favorites">
						<i class="fa fa-heart"></i>
				</a></li>
			</ul>
		</div>
	</div>

</nav>