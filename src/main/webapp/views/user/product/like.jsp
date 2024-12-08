<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>

<div class="container mt-5">
    <h2 class="text-center mb-4">Your Favorite Products</h2>

    <c:if test="${empty likedProducts}">
        <p class="text-center">No products added to favorites yet.</p>
    </c:if>

    <c:if test="${not empty likedProducts}">
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
                <c:forEach var="product" items="${likedProducts}">
                    <tr>
                        <td>
                            <c:url value="/image?fname=${product.ppicture}" var="imgUrl"></c:url>
                            <img src="${imgUrl}" class="img-fluid" alt="${product.pname}" style="max-width: 100px;">
                        </td>
                        <td>${product.pname}</td>
                        <td>
                            <fmt:formatNumber value="${product.pprice}" type="currency" currencySymbol="" /> vnđ
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/user/product/detail?pid=${product.pid}" class="btn btn-info btn-sm">View Details</a>
                            <a href="${pageContext.request.contextPath}/user/product/delete?pid=${product.pid}" class="btn btn-danger btn-sm">Remove</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    
  
</div>
