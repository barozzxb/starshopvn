<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<c:url value="/" var="URL"></c:url>

<div class="container">
	<p class="h4 text-center my-4">Products Management</p>
	<div class="row">
		<div class="col-md-12">
			<div class="table-responsive">
				<table class="table table-hover align-middle">
					<thead>
						<tr>
							<th colspan="4" class="text-center">
								<button type="button" class="btn btn-primary btn-sm"
									data-bs-toggle="modal" data-bs-target="#addProduct">
									<i class="fa fa-plus"></i> Add
								</button>
							</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listprod}" var="prod">
							<tr>
								<c:url value="/image?fname=${prod.ppicture}" var="imgUrl"></c:url>
								<td class="col-md-2 text-center py-3"><img width="100"
									height="100" src="${imgUrl}" alt="prodImage"></td>
								<td class="col-md-7 text-center py-3">
									<div class="product-name">
										<input type="hidden" value="${prod.pid}" /> <a href="#"
											class="section-title fw-bold fs-5">${prod.pname}</a>
									</div>
									<div class="quantity text-muted fs-6">${prod.pquantity}
										products remain</div>
								</td>
								<td class="col-md-2 text-center py-3">
									<div class="price">
										<p id="price">${prod.pprice}VND</p>
									</div>
								</td>
								<td class="col-md-2 text-center py-3"><a
									href="${pageContext.request.contextPath}/admin/product/edit?pid=${prod.pid}"
									class="btn btn-primary btn-sm"> Edit Product </a></td>
								<td class="col-md-1 text-center py-3"><a
									href="${pageContext.request.contextPath}/admin/product/delete?pid=${prod.pid}"
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

<!-- Add Product Modal -->
<div class="modal fade" id="addProduct" tabindex="-1" aria-labelledby="addProductModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addProductModalLabel">Add New Product</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="${pageContext.request.contextPath }/admin/product/add" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="pid">Product ID <span>*</span></label>
                        <input type="text" class="form-control" id="pid" name="pid" placeholder="Enter Product ID">

                        <label for="pname" class="mt-2">Product Name <span>*</span></label>
                        <input type="text" class="form-control" id="pname" name="pname" placeholder="Enter Product Name">

                        <label for="ppicture" class="mt-2">Product Image <span>*</span></label>
                        <input type="file" class="form-control" id="ppicture" name="ppicture">

                        <label for="pprice" class="mt-2">Product Price <span>*</span></label>
                        <input type="text" class="form-control" id="pprice" name="pprice" placeholder="Enter Product Price">

                        <label for="pquantity" class="mt-2">Quantity <span>*</span></label>
                        <input type="text" class="form-control" id="pquantity" name="pquantity" placeholder="Enter Product Quantity">

                        <label for="pinfo" class="mt-2">Product Information <span>*</span></label>
                        <textarea class="form-control" id="pinfo" name="pinfo" rows="5" placeholder="Enter Product Information"></textarea>

                        <label for="pgenre" class="mt-2">Genre <span>*</span></label>
                        <select class="form-select" name="pgenre" id="pgenre">
                            <c:forEach items="${listgenre}" var="g">
                                <option value="${g.gid}">${g.gname}</option>
                            </c:forEach>
                        </select>

                        <div class="mt-3">
                            <button type="submit" class="btn btn-primary">Add Product</button>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
