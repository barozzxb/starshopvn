<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<c:url value="/" var="URL" />

<div class="container">
	<div class="row">
		<div class="col-md-12">
			<div class="table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
							<th class="heading-title">Genres list</th>
							<th class="heading-title"><button type="button"
									class="btn-upper btn" data-toggle="modal"
									data-target="#addGenre"><i class="fa fa-plus"></i></button></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listgenre}" var="genre">
							<tr data-id="${genre}">
								<td class="col-md-2 col-sm-6 col-xs-6"><img
									src="assets/images/products/p1.jpg" alt="imga"></td>
								<td class="col-md-7 col-sm-6 col-xs-6">
									<div class="product-name">
										<input type="hidden" value="${genre.gid }" id="gid"/> 
										<a href="#"
											class="section-title">${genre.gname}</a>
									</div> <br></br>
									<div class="quantity">130 products</div>
								</td>
								<td class="col-md-2 "><a
									href="${pageContext.request.contextPath }/admin/genre/edit?gid=${genre.gid}"
									class="btn-upper btn btn-primary">Edit genre</a></td>
								<td class="col-md-1 close-btn"><a
									href="${pageContext.request.contextPath }/admin/genre/delete?gid=${genre.gid}"
									class=""><i class="fa fa-times"></i></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>


<div class="modal fade" id="addGenre" tabindex="-1" role="dialog"
	aria-labelledby="addGenreModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="addGenreModalLabel">Add Genre</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form action="${pageContext.request.contextPath}/admin/genre/add"
					role="form" method="post">
					<div class="form-group">
						<label class="info-title" for="gid">Genre ID <span>*</span></label>
						<input type="text"
							class="form-control" id="gid"
							placeholder="" name="gid">
					</div>
					<div class="form-group">
						<label class="info-title" for="gname">Genre Name <span>*</span></label>
						<input type="text"
							class="form-control" id="gname"
							placeholder="" name="gname">
					</div>
					<div class="form-group">
						<label class="info-title" for="gdescription">Genre
							Description <span>*</span>
						</label>
						<textarea class="form-control"
							id="gdescription" placeholder="" name="gdescription" rows="20"></textarea>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary">Add Genre</button>
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>


<div class="modal fade" id="infoGenre" tabindex="-1" role="dialog"
	aria-labelledby="infoGenreModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="infoGenreModalLabel">Genre information</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form action="${pageContext.request.contextPath}/admin/genre/edit"
					role="form" method="post">
					<div class="form-group">
						<label class="info-title" for="e-gid">Genre ID <span>*</span></label>
						<input type="text"
							class="form-control" id="e-gid"
							placeholder="" name="e-gid" >
					</div>
					<div class="form-group">
						<label class="info-title" for="e-gname">Genre Name <span>*</span></label>
						<input type="text"
							class="form-control" id="e-gname"
							placeholder="" name="e-gname">
					</div>
					<div class="form-group">
						<label class="info-title" for="e-gdescription">Genre
							Description <span>*</span>
						</label>
						<textarea class="form-control"
							id="e-gdescription" placeholder="" name="e-gdescription" rows="20"></textarea>
					</div>
					<div class="modal-footer">
						<div class="modal-footer">
							<button type="button" class="btn btn-primary" id="editButton">Edit</button>
							<button type="submit" class="btn btn-primary" id="saveButton" disabled>Save</button>
							<button type="button" class="btn btn-secondary"	data-dismiss="modal">Close</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>