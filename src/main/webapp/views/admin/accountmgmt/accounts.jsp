<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<c:url value="/" var="URL" />


<div class="">
	<p class="heading-title">Accounts Management</p>
	<div class="row">
		<div class="col">
			<div class="table-responsive">
				<table class="table table-bordered table-hover list-account">
					<thead>
						<tr>
							<th>User ID</th>
							<th>Name</th>
							<th>DoB</th>
							<th>Gender</th>
							<th>Email</th>
							<th>Phone number</th>
							<th>Address</th>
							<th>Status</th>
							<th>Action</th>
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
									<c:if test="${acc.deactivated}">
										<p style="color: red;"><i class="fa fa-circle"></i></p>
									</c:if> 
									<c:if test="${!acc.deactivated}">
										<p style="color: green;"><i class="fa fa-circle"></i></p>
									</c:if>
								</td>
								<td class="text-center"><c:if test="${acc.deactivated}">
										<a
											href="${pageContext.request.contextPath }/admin/account/setActive?uid=${acc.userid}"><i
											class="fa fa-check"></i></a>
									</c:if> <c:if test="${!acc.deactivated}">
										<a
											href="${pageContext.request.contextPath }/admin/account/setDeactive?uid=${acc.userid}"><i
											class="fa fa-flag"></i></a>
									</c:if> <span class="separator">&ensp;|&ensp;</span> <a
									href="${pageContext.request.contextPath }/admin/account/delete?uid=${acc.userid}"><i
										class="fa fa-close"></i> </a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
