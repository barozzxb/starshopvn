<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<br>

<div class="col-xs-3">
	<div class="panel panel-default">
		<div class="panel-heading">Account setting</div>
		<div class="panel-body">
			<ul class="nav nav-pills nav-stacked">
				<li><a href="#accountinf">Account Information</a></li>
				<li><a href="#changepass">Change Password</a></li>
				<li><a href="#delete">Delete Account</a></li>
			</ul>
		</div>
	</div>
</div>

<div class="col-xs-9">

	<div class="panel panel-default" id="accountinf">
		<div class="panel-heading text-center">
			<strong>Account Infomation</strong>
		</div>
		<div class="container-fluid">
			<div class="fix-5"></div>
			<div class="col-md-8 col-md-offset-2">
				<c:if test="${not empty alert}">
					<div class="alert alert-danger text-center" role="alert">
						${alert}</div>
				</c:if>
				<form action="${pageContext.request.contextPath}/admin/profile"
					method="post">
					<div class="form-group mb-3">
						<label for="userid"><i class="fa fa-user"></i> User ID</label> <input
							type="text" class="form-control" id="userid"
							value="${account.userid }" name="userid" readonly>
					</div>
					<div class="form-group mb-3">
						<label for="name"><i class="fa fa-user"></i> User ID</label> <input
							type="text" class="form-control" id="name"
							placeholder="Enter your name" name="name"
							value="${account.name }">
					</div>
					<div class="form-group mb-3">
						<label for="email"><i class="fa fa-envelope"></i> Email</label> <input
							type="email" class="form-control" id="email"
							placeholder="Enter your Email" name="email">
					</div>
					<div class="form-group mb-3">
						<label for="phonenum"><i class="fa fa-user"></i> Phone
							number</label> <input type="text" class="form-control" id="phonenum"
							placeholder="Enter your phone number" name="phonenum"
							value="${account.phonenum }">
					</div>
					<div class="form-group mb-3">
						<label for="dob"><i class="fa fa-user"></i> Date of Birth</label>
						<input type="date" class="form-control" id="dob" name="dob"
							value="${account.dob}" placeholder="Enter your Date of Birth">
					</div>
					<div class="form-group mb-3">
						<label><i class="fa fa-user"></i> Gender: </label> <label
							class="radio-inline"> <input type="radio" name="gender"
							value="male"
							<c:if test="${account.gender == 'male' }">checked</c:if>>
							Male
						</label> <label class="radio-inline"> <input type="radio"
							name="gender" value="female"
							<c:if test="${account.gender == 'female' }">checked</c:if>>
							Female
						</label>
					</div>
					<div class="form-group mb-3">
						<button type="submit" class="btn btn-primary btn-block">Update</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div class="panel panel-default" id="changepass">
		<div class="panel-heading text-center">
			<strong>Change your password</strong>
		</div>
		<div class="container-fluid">
		<div class="fix-5"></div>
			<div class="col-md-8 col-md-offset-2">
				<c:if test="${not empty alert}">
					<div class="alert alert-danger text-center" role="alert">
						${alert}</div>
				</c:if>
				<form
					action="${pageContext.request.contextPath}/admin/profile/changepass"
					method="post">
					<div class="form-group mb-3">
						<label for="userid"><i class="fa fa-user"></i> User ID</label> <input
							type="text" class="form-control" id="userid"
							value="${account.userid }" name="userid" readonly>
					</div>
					<div class="form-group mb-3">
						<label for="email"><i class="fa fa-envelope"></i> Email</label> <input
							type="email" class="form-control" id="email"
							placeholder="Enter your Email" name="email">
					</div>
					<div class="form-group mb-3">
						<label for="oldpassword"><i class="fa fa-password"></i>
							Old Password</label> <input type="password" class="form-control"
							id="oldpassword" name="oldpassword"
							placeholder="Enter your Old Password">
					</div>
					<div class="form-group mb-3">
						<label for="newpassword"><i class="fa fa-password"></i>
							New Password</label> <input type="password" class="form-control"
							id="newpassword" name="newpassword"
							placeholder="Enter your New Password">
					</div>
					<div class="form-group mb-3">
						<label for="c-newpassword"><i class="fa fa-password"></i>
							Confirm Password</label> <input type="password" class="form-control"
							id="c-newpassword" name="c-newpassword"
							placeholder="Confirm your New Password">
					</div>
					<div class="form-group mb-3">
						<button type="button" class="btn btn-primary btn-block"
							data-toggle="modal" data-target="#validate">Update</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div class="modal fade" id="validate" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Validation</h4>
				</div>

				<!-- Modal Body -->
				<div class="modal-body">
					<div class="container">
						<div class="row">
							<div class="col-md-4 col-md-offset-1 text-center">
								<form class="form-horizontal" role="form">
									<div class="form-group mt-2">
										<label class="control-label" for="code">Enter OTP sent
											to your email</label>
										
										<input type="text" class="form-control mt-2"
											id="inputEmail3" placeholder="OTP" name="otpcode" />
									</div>
									
									<div class="form-group mb-3">
										<div class="col-xs-12">
											<button type="submit" class="btn btn-primary">Finish</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>

	<div class="panel panel-default" id="delete">
		<div class="panel-heading text-center">
			<strong>Delete your account</strong>
		</div>
		<div class="container-fluid">
		<div class="fix-5"></div>
			<div class="col-md-8 col-md-offset-2">
				<c:if test="${not empty alert}">
					<div class="alert alert-danger text-center" role="alert">
						${alert}</div>
				</c:if>
				<form action="${pageContext.request.contextPath}/admin/profile/changepass"
					method="post">
					<div class="alert alert-danger mt-2 text-center">
  						<strong>Danger!</strong> You are about to delete your account with id <strong>${account.userid }</strong>
					</div>

					<div class="form-group mb-3">
						<label for="userid"><i class="fa fa-user"></i> User ID</label> <input
							type="text" class="form-control" id="userid"
							value="${account.userid }" name="userid" readonly>
					</div>
					<div class="form-group mb-3">
						<label for="email"><i class="fa fa-envelope"></i> Email</label> <input
							type="email" class="form-control" id="email"
							placeholder="Enter your Email" name="email">
					</div>

					<div class="form-group mb-3">
						<label for="password"><i class="fa fa-password"></i>
							Enter Password</label> <input type="password" class="form-control"
							id="password" name="password"
							placeholder="Enter your Password">
					</div>
					<div class="form-group mb-3">
						<button type="submit" class="btn btn-danger btn-block">Proceed to Delete your Account</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
