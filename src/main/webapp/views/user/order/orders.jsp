<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>

<div class="container align-self-center vh-100 py-5">
	<div class="container py-5 bg-white">
	    <h2 class="text-black text-center mb-4">Danh sách đơn hàng</h2>
	
	    <c:if test="${empty orders}">
	        <div class="alert alert-warning text-center">
	            Bạn chưa có đơn hàng nào.
	        </div>
	    </c:if>
	
	    <c:if test="${not empty orders}">
	        <table class="table table-responsive table-bordered table-striped text-center">
	            <thead>
	                <tr>
	                    <th>Mã đơn hàng</th>
	                    <th>Ngày đặt</th>
	                    <th>Trạng thái</th>
	                    <th>Hình thức thanh toán</th>
	                    <th>Tổng giá trị</th>
	                    <th>Chi tiết</th>
	                </tr>
	            </thead>
	            <tbody>
	                <c:forEach var="order" items="${orders}">
	                    <tr>
	                        <td>${order.oid}</td>
	                        <td><fmt:formatDate value="${order.odate}" pattern="dd/MM/yyyy HH:mm:ss" /></td>
	                        <td>${order.ostatus}</td>
	                        <td>${order.payment}</td>
	                        <td><fmt:formatNumber value="${order.cost}" type="number" currencySymbol="" /> vnđ</td>
	                        <td>
	                        	<a data-bs-toggle="collapse" href="#order_${order.oid }" role="button" aria-expanded="false" aria-controls="info">
									Xem chi tiết </a>
							</td>
	                    </tr>
	                    <tr class="collapse collapse-row-content" id="order_${order.oid }">
				            <td colspan="6">
				                <div class="p-3 bg-light">
				                    <strong>Ghi chú:</strong> ${order.note != null ? order.note : "Không có ghi chú"}<br />
				                    <strong>Thông tin giao hàng:</strong> ${order.deliveryinfo != null ? order.deliveryinfo : "Chưa cập nhật"}<br />
				                    <strong>Chi tiết sản phẩm:</strong>
				                    <ul class="list-group">
				                        <c:forEach var="detail" items="${order.orderDetails}">
				                            <li class="list-group-item">${detail.product.pname} &emsp;- Số lượng: ${detail.dquantity} &emsp;- Đơn giá: <fmt:formatNumber value="${detail.product.pprice}" type="number" currencySymbol="" /> vnđ</li>
				                        </c:forEach>
				                    </ul>
				                </div>
				            </td>
        				</tr>
	                </c:forEach>
	            </tbody>
	        </table>
	    </c:if>
	</div>
</div>

