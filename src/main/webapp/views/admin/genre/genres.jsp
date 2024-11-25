<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<c:url value="/" var="URL" />

<div class="container">
<p class="h4 text-center my-4">Genre Management</p>
	<div class="row">
		<div class="col-md-12">
			<div class="table-responsive">
				<table class="table table-hover align-middle">
					<thead>
						<tr>
							<th colspan="4" class="text-center">
								<button type="button" class="btn btn-primary btn-sm"
									data-bs-toggle="modal" data-bs-target="#addGenre">
									<i class="fa fa-plus"></i> Add
								</button>
							</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach items="${listgenre}" var="genre">
							<tr>
								<td class="col-md-7 align-middle py-5 px-5">
									<div class="product-name">
										<input type="hidden" value="${genre.gid}" id="gid" /> <a
											href="#" class="text-decoration-none fw-bold fs-5">${genre.gname}</a>
									</div>
									<div class="text-muted fs-6">${genre.getProductCount()}
										products</div>
								</td>
								<td class="col-md-2"><a
									href="${pageContext.request.contextPath}/admin/genre/edit?gid=${genre.gid}"
									class="btn btn-primary btn-sm">Edit Genre</a></td>
								<td class="col-md-1 text-end"><a
									href="${pageContext.request.contextPath}/admin/genre/delete?gid=${genre.gid}"
									class="btn btn-danger btn-sm"> <i class="fa fa-times"></i>
								</a></td>
							</tr>
						</c:forEach>
					</tbody>

				</table>
			</div>
		</div>
	</div>
</div>

<!-- Add Genre Modal -->
<div class="modal fade" id="addGenre" tabindex="-1" aria-labelledby="addGenreModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="addGenreModalLabel">Add Genre</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<form action="${pageContext.request.contextPath}/admin/genre/add" method="post">
					<div class="mb-3">
						<label for="gid" class="form-label">Genre ID <span class="text-danger">*</span></label>
						<input type="text" class="form-control" id="gid" name="gid">
					</div>
					<div class="mb-3">
						<label for="gname" class="form-label">Genre Name <span class="text-danger">*</span></label>
						<input type="text" class="form-control" id="gname" name="gname">
					</div>
					<div class="mb-3">
						<label for="gdescription" class="form-label">Genre Description <span class="text-danger">*</span></label>
						<textarea class="form-control" id="gdescription" name="gdescription" rows="3"></textarea>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary">Add Genre</button>
						<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<!-- Genre Information Modal -->
<div class="modal fade" id="infoGenre" tabindex="-1" aria-labelledby="infoGenreModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="infoGenreModalLabel">Genre Information</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<form action="${pageContext.request.contextPath}/admin/genre/edit" method="post">
					<div class="mb-3">
						<label for="e-gid" class="form-label">Genre ID <span class="text-danger">*</span></label>
						<input type="text" class="form-control" id="e-gid" name="e-gid">
					</div>
					<div class="mb-3">
						<label for="e-gname" class="form-label">Genre Name <span class="text-danger">*</span></label>
						<input type="text" class="form-control" id="e-gname" name="e-gname">
					</div>
					<div class="mb-3">
						<label for="e-gdescription" class="form-label">Genre Description <span class="text-danger">*</span></label>
						<textarea class="form-control" id="e-gdescription" name="e-gdescription" rows="3"></textarea>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary">Save</button>
						<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
