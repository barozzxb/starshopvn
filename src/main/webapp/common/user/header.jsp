<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<nav
	class="custom-navbar navbar navbar navbar-expand-md navbar-dark bg-dark" arial-label="Furni navigation bar">

	<div class="container">
		<a class="navbar-brand" href="${pageContext.request.contextPath }/user/home">StarShop<span>.</span></a>

		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarsFurni" aria-controls="navbarsFurni"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarsFurni">
			<ul class="custom-navbar-nav navbar-nav ms-auto mb-2 mb-md-0">
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath }/user/home">Trang chủ</a></li>
				<li><a class="nav-link" href="${pageContext.request.contextPath }/user/products">Cửa hàng</a></li>
				<li><a class="nav-link" href="${pageContext.request.contextPath }/user/about">Thông tin</a></li>
				<li><a class="nav-link" href="${pageContext.request.contextPath }/user/services">Dịch vụ</a></li>
				<li><a class="nav-link" href="${pageContext.request.contextPath }/user/posts">Bài đăng</a></li>
				<li><a class="nav-link" href="${pageContext.request.contextPath }/user/contact">Liên hệ</a></li>
			</ul>

			<ul class="custom-navbar-cta navbar-nav mb-2 mb-md-0 ms-5">

				<c:choose>
					<c:when test="${sessionScope.account != null}">
						<li><a class="nav-link"
							href="${pageContext.request.contextPath}/user/settings"> <span>
									<c:choose>
										<c:when test="${sessionScope.account.name == null}">
										${sessionScope.account.userid}
									</c:when>
										<c:otherwise>
										${sessionScope.account.name}
									</c:otherwise>
									</c:choose>
							</span>
						</a></li>
						<li><a class="nav-link"
							href="${pageContext.request.contextPath }/logout">Đăng Xuất</a></li>
					</c:when>
				</c:choose>
				<li><a class="nav-link"
					href="${pageContext.request.contextPath}/user/product/favorites"><i
						class="fa fa-heart"></i></a></li>
				
				<li>
				    <a class="nav-link" href="${pageContext.request.contextPath}/user/cart/view" data-bs-toggle="tooltip" title="Xem giỏ hàng">
				        <i class="fa fa-shopping-bag"></i>
				    </a>
				</li>
				<li>
				    <a class="nav-link" href="${pageContext.request.contextPath}/user/orders" data-bs-toggle="tooltip" title="Theo dõi đơn hàng">
				        <i class="fa fa-truck"></i>
				    </a>
				</li>
			</ul>
		</div>
	</div>

</nav>