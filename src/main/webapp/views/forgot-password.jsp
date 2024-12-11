<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>

<div class="container d-flex justify-content-center align-items-center min-vh-100 border">
    <div class="col-md-6 mx-auto">
        <h3 class="text-center mb-4">Forgot Password</h3>
        <c:if test="${not empty alert}">
            <div class="alert alert-danger text-center" role="alert">
                ${alert}
            </div>
        </c:if>
        <form action="${pageContext.request.contextPath}/forgot" method="post">
            <div class="form-group mb-3">
                <label for="email"><i class="fa fa-envelope"></i> Enter your Email</label>
                <input type="email" class="form-control" id="email" placeholder="Enter your Email" name="email" required>
            </div>
            <div class="form-group mb-3">
                <button type="submit" class="btn btn-primary btn-block w-100"><strong>Send</strong></button>
            </div>
        </form>
    </div>
</div>