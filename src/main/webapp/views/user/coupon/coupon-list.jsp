
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/my-styles.css">



<div class="container mt-5">
    <h2 class="mb-4">Danh Sách Mã Giảm Giá</h2>

    <!-- Hiển thị thông báo nếu có -->
    <c:if test="${not empty message}">
        <div class="alert alert-success">
            ${message}
        </div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="alert alert-danger">
            ${error}
        </div>
    </c:if>

    <!-- Nút mở Modal để chọn mã giảm giá -->
    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#couponModal">
        Chọn Mã Giảm Giá
    </button>

    <!-- Modal hiển thị mã giảm giá -->
    <div class="modal fade" id="couponModal" tabindex="-1" aria-labelledby="couponModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="couponModalLabel">Danh Sách Mã Giảm Giá</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>Mã Giảm Giá</th>
                                <th>Mô Tả</th>
                                <th>Hạn Sử Dụng</th>
                                <th>Áp Dụng</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Danh sách mã giảm giá sẽ được hiển thị tại đây -->
                            <c:forEach var="coupon" items="${coupons}">
                                <tr>
                                    <td>${coupon.cname}</td>
                                    <td>Giảm ${coupon.cpercent}%</td>
                                    <td>${coupon.end}</td>
                                    <td>
                                        <form action="${pageContext.request.contextPath}/user/coupon/apply" method="post">
                                            <input type="hidden" name="cid" value="${coupon.cid}">
                                            <input type="hidden" name="totalAmount" value="${totalAmount}">
                                            <button type="submit" class="btn btn-success">Áp Dụng</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                </div>
            </div>
        </div>
    </div>
</div>