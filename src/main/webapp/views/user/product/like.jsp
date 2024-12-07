<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>

<div class="container mt-5">
    <h2 class="text-center mb-4">Your Favorite Products</h2>

    <c:if test="${empty likedFavorites}">
        <p class="text-center">No products added to favorites yet.</p>
    </c:if>

    <c:if test="${not empty likedFavorites}">
        <table class="table table-striped text-center">
            <thead>
                <tr>
                    <th>Image</th>
                    <th>Product</th>
                    <th>Price</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="favorite" items="${likedFavorites}">
                    <tr>
                        <td>
                            <c:url value="/image?fname=${favorite.product.ppicture}" var="imgUrl"></c:url>
                            <img src="${imgUrl}" class="img-fluid" alt="${favorite.product.pname}" style="max-width: 100px;">
                        </td>
                        <td>${favorite.product.pname}</td>
                        <td>
                            <fmt:formatNumber value="${favorite.product.pprice}" type="currency" currencySymbol="" /> vnđ
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/user/product/detail?pid=${favorite.product.pid}" class="btn btn-info btn-sm">View Details</a>
                            <a href="${pageContext.request.contextPath}/user/product/delete?fid=${favorite.fid}" class="btn btn-danger btn-sm">Remove</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
