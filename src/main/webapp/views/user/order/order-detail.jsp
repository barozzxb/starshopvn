<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>

<div>
    <h4>Thông tin đơn hàng</h4>
    <p><strong>Mã đơn hàng:</strong> ${order.oid}</p>
    <p><strong>Ngày đặt:</strong> <fmt:formatDate value="${order.odate}" pattern="dd/MM/yyyy HH:mm:ss" /></p>
    <p><strong>Trạng thái:</strong> ${order.ostatus}</p>
    <p><strong>Tổng giá trị:</strong> <fmt:formatNumber value="${order.cost}" type="number" currencySymbol="" /> vnđ</p>
    
    <h4>Chi tiết sản phẩm</h4>
    <table class="table">
        <thead>
            <tr>
                <th>Tên sản phẩm</th>
                <th>Số lượng</th>
                <th>Giá</th>
                <th>Thành tiền</th>
                <th>Đánh giá</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="detail" items="${order.orderDetails}">
                <tr>
                    <td>${detail.product.pname}</td>
                    <td>${detail.dquantity}</td>
                    <td><fmt:formatNumber value="${detail.product.pprice}" type="number" currencySymbol="" /> vnđ</td>
                    <td><fmt:formatNumber value="${detail.dquantity * detail.product.pprice}" type="number" currencySymbol="" /> vnđ</td>
                    <td> 
                    <a href="${pageContext.request.contextPath}/user/product/review?pid=${detail.product.pid}"
										class="btn btn-primary">Đánh giá sản phẩm</a>
					</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
