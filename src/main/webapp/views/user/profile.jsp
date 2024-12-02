<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>

<div class="container centered-container">
    <div class="col-xs-6">
        <h3 class="text-center mb-4">Account Information</h3>

        <!-- Hiển thị thông báo nếu có -->
        <c:if test="${not empty alert}">
            <div class="alert alert-info text-center" role="alert">
                ${alert} <!-- Hiển thị thông báo -->
            </div>
        </c:if>

        <!-- Form cập nhật thông tin người dùng -->
        <form action="${pageContext.request.contextPath}/user/profile" method="post">
            <div class="form-group mb-3">
                <label for="userid"><i class="fa fa-user"></i> User ID</label>
                <input type="text" class="form-control" id="userid" value="${account.userid}" name="userid" readonly>
            </div>
            <div class="form-group mb-3">
                <label for="name"><i class="fa fa-user"></i> Name</label>
                <input type="text" class="form-control" id="name" placeholder="Enter your name" name="name" value="${account.name}">
            </div>
            <div class="form-group mb-3">
                <label for="email"><i class="fa fa-envelope"></i> Email</label>
                <input type="email" class="form-control" id="email" placeholder="Enter your Email" name="email" value="${account.email}">
            </div>
            <div class="form-group mb-3">
                <label for="password"><i class="fa fa-lock"></i> Password</label>
                <input type="password" class="form-control" id="password" placeholder="Enter your Password" name="password" value="${account.password}">
            </div>
            <div class="form-group mb-3">
                <label for="phonenum"><i class="fa fa-user"></i> Phone number</label>
                <input type="text" class="form-control" id="phonenum" placeholder="Enter your phone number" name="phonenum" value="${account.phonenum}">
            </div>
            <div class="form-group mb-3">
                <label for="dob"><i class="fa fa-user"></i> Date of Birth</label>
                <input type="date" class="form-control" id="dob" name="dob" value="${account.dob}" placeholder="Enter your Date of Birth">
            </div>
            <div class="form-group mb-3">
                <label><i class="fa fa-user"></i> Gender: </label>
                <label class="radio-inline">
                    <input type="radio" name="gender" value="male" <c:if test="${account.gender == 'male' }">checked</c:if>> Male
                </label>
                <label class="radio-inline">
                    <input type="radio" name="gender" value="female" <c:if test="${account.gender == 'female' }">checked</c:if>> Female
                </label>
            </div>
            <div class="form-group mb-3">
                <button type="submit" class="btn btn-primary btn-block">Update</button>
            </div>
        </form>
    </div>
</div>
