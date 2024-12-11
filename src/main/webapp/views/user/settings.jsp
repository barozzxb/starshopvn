<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<div class="container my-4">
    <div class="row">
        <!-- Sidebar -->
        <div class="col-md-3 mb-3">
            <div class="card shadow-sm">
                <div class="card-header text-center fw-bold">
                    Account Settings
                </div>
                <ul class="nav nav-pills flex-column" id="settingsTabs" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link active" id="accountinf-tab" data-bs-toggle="pill" href="#accountinf" role="tab" aria-controls="accountinf" aria-selected="true">Account Information</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="changepass-tab" data-bs-toggle="pill" href="#changepass" role="tab" aria-controls="changepass" aria-selected="false">Change Password</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-danger" id="delete-tab" data-bs-toggle="pill" href="#delete" role="tab" aria-controls="delete" aria-selected="false">Delete Account</a>
                    </li>
                </ul>
            </div>
        </div>

        <!-- Content -->
        <div class="col-md-9">
            <div class="tab-content" id="settingsTabsContent">
                <!-- Account Information -->
                <div class="tab-pane fade show active" id="accountinf" role="tabpanel" aria-labelledby="accountinf-tab">
                    <div class="card shadow-sm mb-4">
                        <div class="card-header text-center fw-bold">
                            Account Information
                        </div>
                        <div class="card-body">
                            <c:if test="${not empty alert}">
                                <div class="alert alert-danger text-center" role="alert">
                                    ${alert}
                                </div>
                            </c:if>
                            <form action="${pageContext.request.contextPath}/user/settings/profile" method="post">
                                <div class="mb-3">
                                    <label for="userid" class="form-label"><i class="fa fa-user"></i> User ID</label>
                                    <input type="text" class="form-control" id="userid" value="${account.userid}" name="userid" readonly>
                                </div>
                                <div class="mb-3">
                                    <label for="name" class="form-label"><i class="fa fa-user"></i> Name</label>
                                    <input type="text" class="form-control" id="name" placeholder="Enter your name" name="name" value="${account.name}">
                                </div>
                                <div class="mb-3">
                                    <label for="email" class="form-label"><i class="fa fa-envelope"></i> Email</label>
                                    <input type="email" class="form-control" id="email" placeholder="Enter your Email" name="email" value="${account.email}">
                                </div>
                                <div class="mb-3">
                                    <label for="phonenum" class="form-label"><i class="fa fa-phone"></i> Phone Number</label>
                                    <input type="text" class="form-control" id="phonenum" placeholder="Enter your phone number" name="phonenum" value="${account.phonenum}">
                                </div>
                                <div class="mb-3">
                                    <label for="dob" class="form-label"><i class="fa fa-calendar"></i> Date of Birth</label>
                                    <input type="date" class="form-control" id="dob" name="dob" value="${account.dob}">
                                </div>
                                <div class="mb-3">
                                    <label class="form-label"><i class="fa fa-venus-mars"></i> Gender</label>
                                    <div>
                                        <input type="radio" id="male" name="gender" value="male" class="form-check-input" <c:if test="${account.gender == 'male'}">checked</c:if>>
                                        <label for="male" class="form-check-label">Male</label>
                                        <input type="radio" id="female" name="gender" value="female" class="form-check-input ms-3" <c:if test="${account.gender == 'female'}">checked</c:if>>
                                        <label for="female" class="form-check-label">Female</label>
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-primary w-100">Update</button>
                            </form>
                        </div>
                    </div>
                </div>

                <!-- Change Password -->
                <div class="tab-pane fade" id="changepass" role="tabpanel" aria-labelledby="changepass-tab">
                    <div class="card shadow-sm mb-4">
                        <div class="card-header text-center fw-bold">
                            Change Password
                        </div>
                        <div class="card-body">
                            <form action="${pageContext.request.contextPath}/user/profile/changepass" method="post">
                                <div class="mb-3">
                                    <label for="oldpassword" class="form-label"><i class="fa fa-lock"></i> Old Password</label>
                                    <input type="password" class="form-control" id="oldpassword" name="oldpassword" placeholder="Enter your old password">
                                </div>
                                <div class="mb-3">
                                    <label for="newpassword" class="form-label"><i class="fa fa-lock"></i> New Password</label>
                                    <input type="password" class="form-control" id="newpassword" name="newpassword" placeholder="Enter your new password">
                                </div>
                                <div class="mb-3">
                                    <label for="c-newpassword" class="form-label"><i class="fa fa-lock"></i> Confirm Password</label>
                                    <input type="password" class="form-control" id="c-newpassword" name="c-newpassword" placeholder="Confirm your new password">
                                </div>
                                <button type="submit" class="btn btn-primary w-100">Update Password</button>
                            </form>
                        </div>
                    </div>
                </div>

                <!-- Delete Account -->
                <div class="tab-pane fade" id="delete" role="tabpanel" aria-labelledby="delete-tab">
                    <div class="card shadow-sm">
                        <div class="card-header text-center fw-bold text-danger">
                            Delete Account
                        </div>
                        <div class="card-body">
                            <form action="${pageContext.request.contextPath}/user/profile/delete" method="post">
                                <div class="alert alert-danger text-center">
                                    <strong>Warning!</strong> You are about to delete your account <strong>${account.userid}</strong>.
                                </div>
                                <div class="mb-3">
                                    <label for="password" class="form-label"><i class="fa fa-lock"></i> Password</label>
                                    <input type="password" class="form-control" id="password" name="password" placeholder="Enter your password">
                                </div>
                                <button type="submit" class="btn btn-danger w-100">Delete Account</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
