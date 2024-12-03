<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/my-styles.css">

<div class="product-section">
    <div class="container">
        <div class="row">

            <div class="col-md-12 col-lg-3 mb-5 mb-lg-0">
                <h2 class="mb-4 section-title">Crafted with excellent material.</h2>
                <p class="mb-4">Donec vitae odio quis nisl dapibus malesuada.
                    Nullam ac aliquet velit. Aliquam vulputate velit imperdiet dolor
                    tempor tristique.</p>
                <p>
                    <a href="shop.html" class="btn">Explore</a>
                </p>
            </div>

            <!-- Loop through each product and create a link to the product detail page -->
            <c:forEach items="${products}" var="prod">
                <div class="col-12 col-md-4 col-lg-3 mb-5 mb-md-0">
                    <c:url value="/image?fname=${prod.ppicture}" var="imgUrl"></c:url>
                    
                    <!-- Link to product detail page when clicking the image -->
                    <a class="product-item" href="${pageContext.request.contextPath}/user/product/detail?pid=${prod.pid}">
                        <img src="${imgUrl}" class="img-fluid product-thumbnail" alt="${prod.pname}">
                        <h3 class="product-title">${prod.pname}</h3>
                        <strong class="product-price">
                            <fmt:formatNumber value="${prod.pprice}" type="currency" currencySymbol="" /> vnđ
                        </strong>
                    </a>

                    <!-- Add to Cart button -->
                    <form action="${pageContext.request.contextPath}/cart/add" method="post" class="add-to-cart-form">
                        <input type="hidden" name="pid" value="${prod.pid}"/>
                        <button type="submit" class="btn btn-success add-to-cart-btn">
                            <span>+</span> Add to Cart
                        </button>
                    </form>
                </div>
            </c:forEach>

        </div>
    </div>
</div>
