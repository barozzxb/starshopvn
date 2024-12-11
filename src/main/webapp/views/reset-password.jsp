<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>

<div class="container d-flex justify-content-center align-items-center min-vh-100 border">
    <div class="col-md-6 mx-auto">
        <h3 class="text-center mb-4">Reset Password</h3>
        <c:if test="${not empty alert}">
			<div class="alert alert-danger text-center" role="alert">
				${alert}
			</div>
		</c:if>
        <form action="${pageContext.request.contextPath}/reset-password" method="post">
            <div class="form-group mb-3">
                <label for="new-password"><i class="fa fa-lock"></i> New Password</label>
                <input type="password" class="form-control" id="new-password" placeholder="Enter your New Password" name="new-password">
            </div>
			<div class="form-group mb-3">
				<label for="c-password"><i class="fa fa-lock"></i> Confirm Password</label>
				<input type="password" class="form-control" id="c-password" placeholder="Confirm your Password" name="c-password">
			</div>
            <div class="form-group mb-3">
                <button type="submit" class="btn btn-success btn-block w-100"><strong>Reset Password</strong></button>
            </div>
        </form>
    </div>
</div>
