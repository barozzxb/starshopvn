<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>

<div class="container d-flex justify-content-center align-items-center min-vh-100 border">
	<div class="col-md-6 mx-auto">
		<h3 class="text-center mb-4">Đăng nhập</h3>
		<c:if test="${not empty alert}">
			<div class="alert alert-danger text-center" role="alert">
				${alert}
			</div>
		</c:if>
		<form action="${pageContext.request.contextPath}/login" method="post">
			<div class="form-group mb-3">
				<label for="userid"><i class="fa fa-user"></i> User ID</label>
				<input type="text" class="form-control" id="userid" placeholder="Enter your User ID" name="userid" required>
			</div>
			<div class="form-group mb-3">
                <label for="password"><i class="fa fa-lock"></i> Mật khẩu</label>
                <input type="password" class="form-control" id="password" placeholder="Enter your Password" name="password" required>
               <a href="${pageContext.request.contextPath}/forgot" class="float-end">Quên mật khẩu?</a>
               <div class = "form-check">
               		<input class="form-check-input" type="checkbox" value="true" id="remember" name="remember">
               		<label class = "form-check-label" for = "remember">Ghi nhớ đăng nhập</label>
               </div>
            </div>
			<div class="form-group mb-3">
				<button type="submit" class="btn btn-primary btn-block w-100"><strong>Đăng nhập</strong></button>
			</div>
			<div class="form-group mb-3">
				<p class="text-center">Chưa có tài khoản? Hãy</p>
			</div>
			<div class="form-group mb-3 text-center">
				<a class="btn btn-info" href="${pageContext.request.contextPath}/signup" id="create">Đăng kí tài khoản</a>
			</div>
		</form>
	</div>
</div>