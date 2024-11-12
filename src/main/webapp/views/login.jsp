<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>

<div class="container centered-container border">
	<div class="col-md-6 col-md-offset-3 ">
		<h3 class="text-center mb-4">Login</h3>
		<c:if test="${not empty alert}">
			<div class="alert alert-danger text-center" role="alert">
				${alert}</div>
		</c:if>
		<form action="${pageContext.request.contextPath}/login" method="post">
			<div class="form-group mb-3">
				<label for="userid"><i class="fa fa-user"></i> User ID</label> <input
					type="text" class="form-control" id="userid"
					placeholder="Enter your User ID" name="userid">
			</div>
			<div class="form-group mb-3">
				<label for="password"><i class="fa fa-lock"></i> Password</label> <input
					type="password" class="form-control" id="password"
					placeholder="Enter your Password" name="password">
			</div>
			<div class="form-group mb-3">
				<button type="submit" class="btn btn-primary btn-block"><strong>Login</strong></button>
			</div>
			
			<div class="form-group mb-3">
			<p class="text-center">Do not have account? Try</p>
			</div>
			<div class="form-group mb-3 text-center">
			<a class="btn btn-info" href="${pageContext.request.contextPath}/signup" id="create">Create an Account</a>
			</div>
		</form>
	</div>
</div>