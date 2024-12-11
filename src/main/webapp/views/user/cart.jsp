<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>


<div class="untree_co-section before-footer-section">
	<div class="container">
		<div class="row mb-5">
			<c:if test="${not empty message}">
        		<div class="alert alert-success alert-dismissible fade show" role="alert">
            		<strong>Success!</strong> ${message}
            		<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        		</div>
    		</c:if>
			<c:choose>
				<c:when test="${cartItems == null || cartItems.isEmpty()}">
					<div class="row justify-content-center">
						<div class="col-md-8 text-center">
							<h2 class="h4 text-black">Giỏ hàng của bạn đang trống.</h2>
							<p>Có vẻ như bạn chưa thêm sản phẩm nào vào giỏ hàng của bạn. Hãy truy cập vào shop và chọn mặt hàng bạn muốn nhé</p>
							<a href="${pageContext.request.contextPath}/user/product"
								class="btn btn-outline-black btn-lg">Tiếp tục mua sắm</a>
						</div>
					</div>
				</c:when>

				<c:otherwise>

					<form class="col-md-12 bg-light" method="post">
						<div class="site-blocks-table">
							<table class="table">
								<thead>
									<tr>
										<th class="product-thumbnail">Hình ảnh</th>
										<th class="product-name">Tên sản phẩm</th>
										<th class="product-price">Đơn giá</th>
										<th class="product-quantity">Số lượng</th>
										<th class="product-total">Tổng</th>
										<th class="product-remove">Xóa</th>
									</tr>
								</thead>
								<tbody>

									<c:forEach items="${cartItems }" var="item">
										<tr>
											<td class="product-thumbnail"><c:url
													value="/image?fname=${item.product.ppicture}"
													var="productImage"></c:url> <img src="${productImage }"
												alt="Image" class="img-fluid"></td>
											<td class="product-name">
												<h2 class="h5 text-black">${item.product.pname }</h2>
											</td>
											<td><fmt:formatNumber
												value="${item.product.pprice }" type="number" currencySymbol="" /> vnđ</td>
											<td>
												<div
													class="input-group mb-3 d-flex align-items-center quantity-container"
													style="max-width: 120px;">
													<div class="input-group-prepend">
														<button class="btn btn-outline-black decrease"
															type="button">&minus;</button>
													</div>
													<input type="text"
														class="form-control text-center quantity-amount" value="${item.quantity }"
														placeholder="" aria-label="Example text with button addon"
														aria-describedby="button-addon1">
													<div class="input-group-append">
														<button class="btn btn-outline-black increase"
															type="button">&plus;</button>
													</div>
												</div>

											</td>
											<td><fmt:formatNumber
												value="${item.product.pprice*item.quantity }" type="number" currencySymbol="" /> vnđ</td>
											<td><a
												href="${pageContext.request.contextPath }/user/cart/remove?pid=${item.product.pid}"
												class="btn btn-danger btn-sm"><i class="fa-solid fa-circle-xmark fa-2xl"></i></a></td>
										</tr>
									</c:forEach>

								</tbody>
							</table>
						</div>
					</form>
						
					<div class="col-md-12 py-3 bg-light">
						<div class="row">
							<div class="col-md-6 text-right border-bottom mb-5">
								<h3 class="text-black h2 text-uppercase">Tổng giá trị trong giỏ hàng</h3>
							</div>
							<div class="col-md-6 text-end border-bottom mb-5">
								<strong class="text-black h4 fw-bolder text-orange"><fmt:formatNumber
									value="${totalPrice }" type="number" currencySymbol="" /> vnđ</strong>
							</div>
						</div>
						
						
						<div class="row">
							<div class="col-md-12 text-center">
								<a class="btn-lg py-3 btn-block btn btn-success"
									href="${pageContext.request.contextPath }/user/cart/make-order?cid=${cart.cartId}">Thực hiện mua hàng</a>
							</div>
						</div>
					</div>
						

					<div class="col-md-12 py-3 align-self-center">
					    <div class="row mb-5 justify-content-center">
					        <div class="col-md-4 mb-3">
					            <button class="btn btn-black btn-sm btn-block w-100">Cập nhật giỏ hàng</button>
					        </div>
					        <div class="col-md-4">
					            <a href="${pageContext.request.contextPath }/user/product" 
					               class="btn btn-outline-black btn-sm btn-block w-100">Tiếp tục mua sắm</a>
					        </div>
					    </div>
					</div>

				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>