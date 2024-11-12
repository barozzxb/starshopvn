<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<div class="body-content">
	<div class="container">
		<div class="sign-in-page">
			<div class="row">
				<div class="col-md-4 ">
					<form action="${pageContext.request.contextPath }/admin/product/edit"
						class="register-form" role="form" method="post" enctype="multipart/form-data">
						<div class="form-group">
							<label class="info-title" for="pid">Product ID <span>*</span></label>
							<input type="text" class="form-control unicase-form-control text-input" id="pid"
								placeholder="" name="pid" value="${prod.pid }" disabled> 
							<label class="info-title" for="pname">Product Name <span>*</span></label> 
							<input type="text" class="form-control unicase-form-control text-input" id="pname"
								placeholder="" name="pname" value="${prod.pname }"> 
							<label class="info-title" for="ppicture">Product Image <span>*</span></label> 
							<input type="file" class="form-control unicase-form-control" id="ppicture"
								placeholder="" name="ppicture" value="${prod.ppicture }"> 
							<label class="info-title" for="pprice">Product Price <span>*</span></label> 
							<input type="text" class="form-control unicase-form-control text-input" id="pprice"
								placeholder="" name="pprice" value="${prod.pprice }"> 
							<label class="info-title" for="pquantity">Quantity <span>*</span></label> 
							<input type="text" class="form-control unicase-form-control text-input" id="pquantity"
								placeholder="" name="pquantity" value="${prod.pquantity }"> 
							<label class="info-title" for="pinfo">Info <span>*</span></label>
							<textarea class="form-control unicase-form-control text-input"
								id="pinfo" placeholder="" name="pinfo" rows=20>${prod.pinfo}</textarea>
								
								
							<label class="info-title" for="pmanufacturer">Manufacturer <span>*</span></label> 
							<input type="text" class="form-control unicase-form-control text-input" id="pmanufacturer"
								placeholder="" name="pmanufacturer" value="${prod.pmanufacturer}"> 
							
							<label class="info-title" for="gid">Genre <span>*</span></label> 
							<select class="btn-group dropright" name="pgenre" id="pgenre">
								<c:forEach items="${listgenre}" var="g">
									<option value="${g.gid}" <c:if test="${p.gid == g.gid}">selected</c:if>>${g.gname}</option>
								</c:forEach>
							</select>
								
							<div class="col-md-12 outer-bottom-small m-t-20">
								<button type="submit"
									class="btn-upper btn btn-primary checkout-page-button">Add
									Product</button>
								<a href="${pageContext.request.contextPath }/admin/products"
									class="btn-upper btn btn-primary">Back</a>
							</div>

						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>