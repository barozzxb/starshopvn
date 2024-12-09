<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>

<div class="container d-flex justify-content-center align-items-center min-vh-100 border">
	<div class="col-md-6 mx-auto">
		<h3 class="text-center mb-4">Login</h3>
		<c:if test="${not empty alert}">
			<div class="alert alert-danger text-center" role="alert">
				${alert}
			</div>
		</c:if>
		<form action="${pageContext.request.contextPath}/login" method="post">
			<div class="form-group mb-3">
				<label for="userid"><i class="fa fa-user"></i> User ID</label>
				<input type="text" class="form-control" id="userid" placeholder="Enter your User ID" name="userid">
			</div>
			<div class="form-group mb-3">
                <label for="password"><i class="fa fa-lock"></i> Password</label>
                <input type="password" class="form-control" id="password" placeholder="Enter your Password" name="password">
               <a href="${pageContext.request.contextPath}/forgot" class="float-end">Forgot password?</a>
               <div class = "form-check">
               		<input class="form-check-input" type="checkbox" value="true" id="remember" name="remember">
               		<label class = "form-check-label" for = "remember">Remember me</label>
               </div>
            </div>
			<div class="form-group mb-3">
				<button type="submit" class="btn btn-primary btn-block w-100"><strong>Login</strong></button>
			</div>
			<div class="form-group mb-3">
				<p class="text-center">Do not have an account? Try</p>
			</div>
			<div class="form-group mb-3 text-center">
				<a class="btn btn-info" href="${pageContext.request.contextPath}/signup" id="create">Create an Account</a>
			</div>
		</form>
	</div>
</div>
