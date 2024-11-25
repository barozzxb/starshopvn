<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<nav class="navbar navbar-expand-lg navbar-light bg-white shadow-sm">
	<div class="container">
		<!-- Logo -->
		<a class="navbar-brand fw-bold text-primary" href="${pageContext.request.contextPath}/admin/dashboard">
			<i class="fas fa-leaf text-success me-2"></i> AdminPanel
		</a>

		<!-- Nút toggle cho thiết bị nhỏ -->
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#responsive-nav" aria-controls="responsive-nav" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<!-- Danh sách menu -->
		<div class="collapse navbar-collapse" id="responsive-nav">
			<ul class="navbar-nav mx-auto">
				<li class="nav-item">
					<a href="${pageContext.request.contextPath}/admin/dashboard" class="nav-link ${requestScope.active == 'dashboard' ? 'active fw-bold text-primary' : ''}">Dashboard</a>
				</li>
				<li class="nav-item">
					<a href="${pageContext.request.contextPath}/admin/accounts" class="nav-link ${requestScope.active == 'accounts' ? 'active fw-bold text-primary' : ''}">Manage Users</a>
				</li>
				<li class="nav-item">
					<a href="${pageContext.request.contextPath}/admin/genres" class="nav-link ${requestScope.active == 'genres' ? 'active fw-bold text-primary' : ''}">Genres</a>
				</li>
				<li class="nav-item">
					<a href="${pageContext.request.contextPath}/admin/products" class="nav-link ${requestScope.active == 'products' ? 'active fw-bold text-primary' : ''}">Products</a>
				</li>
				<li class="nav-item">
					<a href="${pageContext.request.contextPath}/admin/posts" class="nav-link ${requestScope.active == 'posts' ? 'active fw-bold text-primary' : ''}">Posts</a>
				</li>
				<li class="nav-item">
					<a href="${pageContext.request.contextPath}/admin/settings" class="nav-link ${requestScope.active == 'settings' ? 'active fw-bold text-primary' : ''}">Settings</a>
				</li>
			</ul>
			<ul class="navbar-nav ms-auto">
				<!-- Tài khoản -->
				<c:choose>
					<c:when test="${sessionScope.account != null}">
						<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle fw-semibold text-dark" href="#" id="accountDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
								<i class="fas fa-user-circle me-2"></i>
								<c:choose>
									<c:when test="${sessionScope.account.name != null}">
										${sessionScope.account.name}
									</c:when>
									<c:otherwise>
										${sessionScope.account.userid}
									</c:otherwise>
								</c:choose>
							</a>
							<ul class="dropdown-menu dropdown-menu-end" aria-labelledby="accountDropdown">
								<li><a class="dropdown-item nav-link small" href="${pageContext.request.contextPath}/admin/my">My Profile</a></li>
								<li><hr class="dropdown-divider"></li>
								<li><a class="dropdown-item text-danger nav-link small" href="${pageContext.request.contextPath}/logout">Log Out</a></li>
							</ul>
						</li>
					</c:when>
					<c:otherwise>
						<li class="nav-item">
							<a href="${pageContext.request.contextPath}/login" class="nav-link fw-semibold text-primary">Log In</a>
						</li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>
</nav>
