<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<c:url value="/" var="URL" />

<div class="container my-5">
	<h2 class="text-center mb-4">Welcome to the Admin Dashboard</h2>

	<!-- Dashboard cards -->
	<div class="row g-4">
		<div class="col-12 col-sm-6 col-lg-3">
			<div class="card text-white bg-primary">
				<div class="card-header d-flex align-items-center">
					<i class="fa fa-user-o me-2"></i> Users
				</div>
				<div class="card-body text-center">
					<h3 class="card-title">${acc_num }</h3>
					<p class="card-text">Total Users</p>
				</div>
			</div>
		</div>
		<div class="col-12 col-sm-6 col-lg-3">
			<div class="card text-white bg-danger">
				<div class="card-header d-flex align-items-center">
					<i class="fa fa-newspaper-o me-2"></i> Posts
				</div>
				<div class="card-body text-center">
					<h3 class="card-title">30</h3>
					<p class="card-text">Total Posts</p>
				</div>
			</div>
		</div>
		<div class="col-12 col-sm-6 col-lg-3">
			<div class="card text-white bg-secondary">
				<div class="card-header d-flex align-items-center">
					<i class="fa fa-tags me-2"></i> Genres
				</div>
				<div class="card-body text-center">
					<h3 class="card-title">${genres_num }</h3>
					<p class="card-text">Total Genres</p>
				</div>
			</div>
		</div>
		<div class="col-12 col-sm-6 col-lg-3">
			<div class="card text-white bg-warning">
				<div class="card-header d-flex align-items-center">
					<i class="fa fa-shopping-bag me-2"></i> Products
				</div>
				<div class="card-body text-center">
					<h3 class="card-title">${prod_num }</h3>
					<p class="card-text">Total Products</p>
				</div>
			</div>
		</div>
	</div>

	<!-- Revenue Chart -->
	<div class="row mt-5">
		<div class="col-12">
			<h2 class="text-center mb-3">Revenue Chart</h2>
			<div class="card shadow-sm">
				<div class="card-body">
					<canvas id="revenueChart"></canvas>
				</div>
			</div>
		</div>
	</div>

	<!-- New Accounts Table -->
	<div class="row mt-5">
		<div class="col-12">
			<h2 class="text-center mb-3">New Accounts</h2>
			<div class="table-responsive">
				<table class="table table-hover table-bordered align-middle">
					<thead class="table-dark">
						<tr>
							<th scope="col">User ID</th>
							<th scope="col">Email</th>
							<th scope="col">Account Status</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${newest_acc }" var="acc">
							<tr>
								<td>${acc.userid }</td>
								<td>${acc.email }</td>
								<td>
									<c:if test="${acc.deactivated}">
										<span class="badge bg-danger">Deactivated</span>
									</c:if>
									<c:if test="${!acc.deactivated}">
										<span class="badge bg-success">Active</span>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
