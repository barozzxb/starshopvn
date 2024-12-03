<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/my-styles.css">

<div class="product-detail-section">
    <div class="container">
        <div class="row">

            <!-- Left side - Product Image and Details -->
            <div class="col-md-12 col-lg-6">
                <h2 class="product-title">${p.pname}</h2>
                
                <!-- Product Image -->
                <div class="product-image">
                    <c:url value="/image?fname=${p.ppicture}" var="imgUrl"></c:url>
                    <img src="${imgUrl}" class="img-fluid" alt="${p.pname}">
                </div>

                <!-- Product Description -->
                <div class="product-description mt-4">
                    <h3>Description</h3>
                    <p>${product.pinfo}</p>
                </div>

                <!-- Product Price -->
                <div class="product-price mt-4">
                    <strong><fmt:formatNumber value="${product.pprice}" type="currency" currencySymbol="" /> vnđ</strong>
                </div>

                <!-- Product Rating -->
                <div class="product-rating mt-3">
                    <strong>Rating:</strong>
                    <span>${product.prating} / 5</span>
                </div>

                <!-- Add to Cart -->
                <div class="add-to-cart mt-4">
                    <form action="cart.html" method="post">
                        <input type="number" name="quantity" value="1" min="1" class="form-control" style="width: 80px;">
                        <button type="submit" class="btn btn-primary mt-2">Add to Cart</button>
                    </form>
                </div>
            </div>

            <!-- Right side - Sidebar or Related Products -->
            <div class="col-md-12 col-lg-6">
                <div class="sidebar">
                    <h4>Related Products</h4>
                    <ul class="related-products">
                        <c:forEach items="${relatedProducts}" var="relatedProd">
                            <li>
                                <a href="product-detail?pid=${relatedProd.pid}">
                                    <c:url value="/image?fname=${relatedProd.ppicture}" var="relatedImgUrl"></c:url>
                                    <img src="${relatedImgUrl}" class="img-fluid" alt="${relatedProd.pname}">
                                    <p>${relatedProd.pname}</p>
                                    <strong><fmt:formatNumber value="${relatedProd.pprice}" type="currency" currencySymbol="" /> vnđ</strong>
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>

        </div>
    </div>
</div>
