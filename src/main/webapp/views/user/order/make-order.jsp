<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<c:url value="/" var="URL"></c:url>

<div class="hero">
	<div class="container">
		<div class="row justify-content-between">
			<div class="col-lg-12">
				<div class="intro-excerpt">
					<h1>Đặt hàng và thanh toán</h1>
				</div>
			</div>
			<div class="col-lg-7"></div>
		</div>
	</div>
</div>

<div class="untree_co-section">
	<div class="container">
		<form action="${pageContext.request.contextPath }/user/order/make" method="post">
			<div class="row">
				<div class="col-md-6 mb-5 mb-md-0">
					<h2 class="h3 mb-3 text-black">Thông tin</h2>

					<div class="form-group p-3 p-lg-5 border bg-white">
					    <div class="col p-3 border">
							<div class="form-check">
								<input class="form-check-input" type="radio"
									name="address_choice" id="address_choice_existing" value="existing_address" checked> <label
									class="form-check-label" for="address_choice_existing">
									Địa chỉ có sẵn </label>
							</div>

							<div id="tab_existing_address" class="tab-content">
						        <p>Chọn từ các địa chỉ có sẵn:</p>
						        <select class="form-select" aria-label="Default select example">
						            <c:forEach items="${listDeliveryInfos}" var="info">
						                <option value="${info.id}">${info.name}</option>
						            </c:forEach>
						        </select>
						    </div>
					    </div>
					    
					    <div class="col p-3 border">
						    <div class="form-check">
								<input class="form-check-input" type="radio"
									name="address_choice" id="address_choice_new" value="new_address"> <label
									class="form-check-label" for="address_choice_new">
									Địa chỉ mới </label>
							</div>
						    
						    <div id="tab_existing_address" class="tab-content">
						    	<input type="hidden" class="form-control" id="userid" name="userid" value="${account.userid }">
						    	<div class="mb-3">
							        <label for="account" class="form-label">Tài khoản nhận hàng</label>
							        <input type="text" class="form-control" id="account" name="account" value="${account.name != null ? account.name  : account.userid }" disabled>
							    </div>
							    
						    	<div class="mb-3">
							        <label for="deinfoid" class="form-label">ID Địa chỉ</label>
							        <input type="text" class="form-control" id="deinfoid" name="deinfoid" required>
							    </div>
							
							    <div class="mb-3">
							        <label for="daddress" class="form-label">Địa chỉ</label>
							        <input type="text" class="form-control" id="daddress" name="daddress" required>
							    </div>
							
							    <div class="mb-3">
							        <label for="ddistrict" class="form-label">Quận/Huyện</label>
							        <input type="text" class="form-control" id="ddistrict" name="ddistrict" required>
							    </div>
							
							    <div class="mb-3">
							        <label for="dcity" class="form-label">Thành phố</label>
							        <input type="text" class="form-control" id="dcity" name="dcity" required>
							    </div>
							
							    <div class="mb-3">
							        <label for="dprovince" class="form-label">Tỉnh/Thành phố</label>
							        <input type="text" class="form-control" id="dprovince" name="dprovince" required>
							    </div>
							
							    <div class="mb-3">
							        <label for="dcountry" class="form-label">Quốc gia</label>
							        <input type="text" class="form-control" id="dcountry" name="dcountry" required>
							    </div>
							
							    <div class="mb-3">
							        <label for="dzipcode" class="form-label">Mã bưu chính</label>
							        <input type="text" class="form-control" id="dzipcode" name="dzipcode" required>
							    </div>
							
							    <div class="mb-3">
							        <label for="dtype" class="form-label">Loại địa chỉ</label>
							        <select class="form-select" aria-label="Default select example" name="dtype">
						                <option value="Nhà riêng">Nhà riêng</option>
						                <option value="Công việc">Công việc</option>
						                <option value="Khác">Khác</option>
						        	</select>
							    </div>	    	
						    </div>
						    
					    </div>
					</div>
				</div>

				<div class="col-md-6">
					<div class="row mb-5">
						<div class="col-md-12">
							<h2 class="h3 mb-3 text-black">Đơn hàng của bạn</h2>
							<div class="p-3 p-lg-5 border bg-white">
								<table class="table site-block-order-table mb-5">
									<thead>
										<th><p class="text-start">Sản phẩm</p></th>
										<th><p class="text-center">Số lượng</p></th>
										<th><p class="text-end">Tổng</p></th>
									</thead>
									<tbody>
										<c:forEach items="${cartItems}" var="item">
											<tr>
												<td>${item.product.pname }</td>
												<td class="text-center"><strong class="mx-2">x</strong>
													${item.quantity}</td>
												<td class="text-end"><fmt:formatNumber
														value="${item.product.pprice*item.quantity }"
														type="number" currencySymbol="" /> vnđ</td>
											</tr>
										</c:forEach>
										<tr>
											<td class="text-black font-weight-bold"><strong>Cart
													Subtotal</strong></td>
											<td class="text-center"></td>
											<td class="text-black text-end"><fmt:formatNumber
													value="${totalPrice }" type="number" currencySymbol="" />
												vnđ</td>
										</tr>
										<tr>
											<td class="text-black font-weight-bold"><strong>Order
													Total</strong></td>
											<td class="text-center"></td>
											<td class="text-black font-weight-bold text-end"><strong><fmt:formatNumber
														value="${totalPrice }" type="number" currencySymbol="" />
													vnđ</strong></td>
										</tr>
									</tbody>
								</table>

								<div class="col-md-12 py-3">
									<h4 class="text-black">Lời nhắn</h4>
									<label for="c_code" class="text-black mb-3">Nếu có gì cần shop chú ý thì hãy nhắn ở đây nhé!</label>
									<div class="input-group couponcode-wrap">
										<textarea class="form-control me-2" id="note"
											placeholder="Ghi chú" aria-label="Note"
											aria-describedby="button-addon2" name="note" rows="10"></textarea>
									</div>
								</div>

								<div class="col-md-12 py-3">
									<h4 class="text-black">Mã giảm giá</h4>
									<label for="c_code" class="text-black mb-3">Nhập mã giảm giá nếu bạn có để nhận ưu đãi nhé!</label>
									<div class="input-group couponcode-wrap">
										<input type="text" class="form-control me-2" id="c_code"
											placeholder="Coupon Code" aria-label="Coupon Code"
											aria-describedby="button-addon2">
										<div class="input-group-append">
											<button class="btn btn-black btn-sm" type="button"
												id="button-addon2">Apply</button>
										</div>
									</div>
								</div>

								<div class="row">
									<div class="col-12 mb-3">
										<h4 class="text-black">Phương thức thanh toán</h4>
									</div>

									<div class="col-md-6">
										<div class="card border-primary">
											<div class="card-body">
												<div class="form-check">
													<input class="form-check-input" type="radio"
														name="payment_method" id="payment_cod" value="COD" checked>
													<img src="${URL }assets/images/cod.png"> <label
														class="form-check-label" for="payment_cod"> <strong>Thanh
															toán khi nhận hàng (COD)</strong><br> <small
														class="text-muted">Bạn sẽ thanh toán khi nhận hàng
															tại địa chỉ giao hàng.</small>
													</label>
												</div>
											</div>
										</div>
									</div>

									<div class="col-md-6">
										<div class="card border-success">
											<div class="card-body">
												<div class="form-check">
													<input class="form-check-input" type="radio"
														name="payment_method" id="payment_vnpay" value="VNPAY">
													<img src="${URL }assets/images/vnpay.png"> <label
														class="form-check-label" for="payment_vnpay"> <strong>Thanh
															toán qua VNPAY</strong><br> <small class="text-muted">Thanh
															toán an toàn qua cổng VNPAY bằng QR code hoặc thẻ ngân
															hàng.</small>
													</label>
												</div>
											</div>
										</div>
									</div>
								</div>


								<div class="form-group text-center py-5">
								    <button type="submit" class=" btn-success btn-lg py-3 rounded-pill shadow-sm">
								        Đặt hàng và thanh toán
								    </button>
								</div>


							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>