<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="body-content">
	<div class="container">
		<div class="sign-in-page">
			<div class="row">
				<div class="col-md-4 ">
					<form action="${pageContext.request.contextPath }/admin/genre/add"
						class="register-form" role="form" method=post>
						<div class="form-group">
							<label class="info-title" for="gid">Genre ID <span>*</span></label>
							<input type="text"
								class="form-control unicase-form-control text-input" id="gid"
								placeholder="" name="gid"> <label class="info-title"
								for="gname">Genre Name <span>*</span></label> <input type="text"
								class="form-control unicase-form-control text-input" id="gname"
								placeholder="" name="gname"> <label class="info-title"
								for="gid">Genre Description <span>*</span></label>
							<textarea class="form-control unicase-form-control text-input"
								id="gid" placeholder="" name="gid" rows=20></textarea>
							<div class="col-md-12 outer-bottom-small m-t-20">
								<button type="submit"
									class="btn-upper btn btn-primary checkout-page-button">Add
									Genre</button>
								<a href="${pageContext.request.contextPath }/admin/genres"
									class="btn-upper btn btn-primary">Back</a>
							</div>

						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>