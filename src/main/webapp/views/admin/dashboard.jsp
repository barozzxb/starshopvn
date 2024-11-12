<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<c:url value="/" var="URL" />

<div class="col-md-12 content">
	<h2>Welcome to the Admin Dashboard</h2>

	<!-- Dashboard cards -->
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-3">
			<div class="panel panel-default bluecard">
				<p class="panel-heading bold"><i class="fa fa-user-o"></i> &emsp;Users</p>
				<div class="panel-body">
					<h3>${acc_num }</h3>
					<p>Total Users</p>
				</div>
			</div>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-3">
			<div class="panel panel-default redcard">
				<p class="panel-heading bold"><i class="fa fa-newspaper-o"></i> &emsp;Posts</p>
				<div class="panel-body">
					<h3>30</h3>
					<p>Total Posts</p>
				</div>
			</div>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-3">
			<div class="panel panel-default purplecard">
				<p class="panel-heading bold"><i class="fa fa-tags"></i> &emsp;Genres</p>
				<div class="panel-body">
					<h3>${genres_num }</h3>
					<p>Total genres</p>
				</div>
			</div>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-3">
			<div class="panel panel-default yellowcard">
				<p class="panel-heading bold"><i class="fa fa-shopping-bag"></i> &emsp;Products</p>
				<div class="panel-body">
					<h3>${prod_num }</h3>
					<p>Total Products</p>
				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="container mt-5">
			<h2 class="text-center">Revenue Chart</h2>
			<div class="card">
				<div class="card-body">
					<canvas id="revenueChart"></canvas>
				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="container">
			<div class="table-responsive">
				<h2 class="text-center">New Accounts</h2>
				<table class=" table hover">
					<thead>
						<tr>
							<th>User ID</th>
							<th>Email</th>
							<th>Account Status</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${newest_acc }" var="acc">
							<tr>
								<td>${acc.userid }</td>
								<td>${acc.email }</td>
								<td>
									<c:if test="${acc.deactivated}"><p style="color: red;">Deactivated</p></c:if>
									<c:if test="${!acc.deactivated}"><p style="color: green;">Active</p></c:if>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
