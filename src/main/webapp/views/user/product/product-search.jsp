<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/my-styles.css">
<div class="search-results">
    <h2>Search Results</h2>
    <!-- Kiểm tra nếu có sản phẩm tìm thấy -->
    <c:if test="${not empty products}">
        <div class="row">
            <c:forEach items="${products}" var="prod">
                <div class="col-12 col-sm-6 col-md-4 col-lg-3 mb-4">
                    <c:url value="/image?fname=${prod.ppicture}" var="imgUrl"></c:url>

                    <!-- Link to product detail page when clicking the image -->
                    <a class="product-item d-block text-center"
                       href="${pageContext.request.contextPath}/user/product/detail?pid=${prod.pid}">
                        <img src="${imgUrl}" class="img-fluid product-thumbnail" alt="${prod.pname}">
                        <h3 class="product-title">${prod.pname}</h3>
                        <strong class="product-price">
                            <fmt:formatNumber value="${prod.pprice}" type="currency" currencySymbol="" /> vnđ
                        </strong>
                    </a>

                    <!-- Add to Cart button -->
                    <form action="${pageContext.request.contextPath}/cart/add" method="post" class="add-to-cart-form mt-2">
                        <input type="hidden" name="pid" value="${prod.pid}" />
                        <button type="submit" class="btn btn-success add-to-cart-btn w-100">
                            <span>+</span> Add to Cart
                        </button>
                    </form>
                </div>
            </c:forEach>
        </div>
    </c:if>
    <!-- Nếu không có sản phẩm -->
    <c:if test="${empty products}">
        <p>No products found for your search.</p>
    </c:if>
</div>
