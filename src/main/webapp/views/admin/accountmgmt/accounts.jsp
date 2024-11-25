<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<c:url value="/" var="URL" />

<div class="container">
	<p class="h4 text-center my-4">Accounts Management</p>
	<div class="row">
		<div class="col-12">
			<div class="table-responsive">
				<table class="table table-bordered table-hover align-middle">
					<thead class="table-dark">
						<tr>
							<th scope="col">User ID</th>
							<th scope="col">Name</th>
							<th scope="col">DoB</th>
							<th scope="col">Gender</th>
							<th scope="col">Email</th>
							<th scope="col">Phone Number</th>
							<th scope="col">Address</th>
							<th scope="col">Status</th>
							<th scope="col">Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${accounts}" var="acc">
							<tr>
								<td>${acc.userid}</td>
								<td>${acc.name}</td>
								<td class="text-center">${acc.dob}</td>
								<td class="text-center">${acc.gender}</td>
								<td>${acc.email}</td>
								<td class="text-center">${acc.phonenum}</td>
								<td>${acc.address}</td>
								<td class="text-center">
									<c:choose>
										<c:when test="${acc.deactivated}">
											<span class="text-danger"><i class="fa fa-circle"></i></span>
										</c:when>
										<c:otherwise>
											<span class="text-success"><i class="fa fa-circle"></i></span>
										</c:otherwise>
									</c:choose>
								</td>
								<td class="text-center">
									<c:choose>
										<c:when test="${acc.deactivated}">
											<a class="btn btn-success btn-sm" 
												href="${pageContext.request.contextPath}/admin/account/setActive?uid=${acc.userid}" 
												title="Activate">
												<i class="fa fa-check"></i>
											</a>
										</c:when>
										<c:otherwise>
											<a class="btn btn-warning btn-sm" 
												href="${pageContext.request.contextPath}/admin/account/setDeactive?uid=${acc.userid}" 
												title="Deactivate">
												<i class="fa fa-flag"></i>
											</a>
										</c:otherwise>
									</c:choose>
									<span class="mx-2">|</span>
									<a class="btn btn-danger btn-sm" 
										href="${pageContext.request.contextPath}/admin/account/delete?uid=${acc.userid}" 
										title="Delete">
										<i class="fa fa-close"></i>
									</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
