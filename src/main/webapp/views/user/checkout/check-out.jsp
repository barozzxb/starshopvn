<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>

<c:url value="/" var="URL" />

<div class="hero">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-lg-12 text-center">
                <div class="intro-excerpt">
                    <h1>Xác nhận Thanh toán</h1>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="untree_co-section">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-lg-8">
                <div class="p-5 bg-white border rounded shadow-sm">
                    <h2 class="h4 mb-4 text-center">Thông tin đơn hàng</h2>

                    <table class="table table-bordered">
                        <tbody>
                            <tr>
                                <th scope="row">Mã đơn hàng</th>
                                <td>${order.oid}</td>
                            </tr>
                            <tr>
                                <th scope="row">Thời gian đặt</th>
                                <td><fmt:formatDate value="${order.odate}" pattern="dd/MM/yyyy HH:mm:ss" /></td>
                            </tr>
                            <tr>
                                <th scope="row">Tổng giá trị</th>
                                <td><fmt:formatNumber value="${order.cost}" type="number" currencySymbol="" /> vnđ</td>
                            </tr>
                            <tr>
                                <th scope="row">Phương thức thanh toán</th>
                                <td>${order.payment}</td>
                            </tr>
                        </tbody>
                    </table>

                    <div class="text-center mt-4">
                        <a href="${pageContext.request.contextPath }/user/checkout-completed" class="btn btn-primary btn-lg">Xác nhận</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
