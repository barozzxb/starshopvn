<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>

<div class="container mt-4">
    <h2>Sản phẩm trong thể loại: ${genre.gname}</h2>
    <div class="row">
        <!-- Lặp qua danh sách sản phẩm -->
        <c:forEach var="product" items="${prod}">
            <div class="col-12 col-md-4 col-lg-3 mb-5">
                <c:url value="/image?fname=${product.ppicture}" var="imgUrl"></c:url>
                <a class="product-item" href="product-detail?pid=${product.pid}">
                    <img src="${imgUrl}" class="img-fluid product-thumbnail">
                    <h3 class="product-title">${product.pname}</h3>
                    <strong class="product-price">
                        <fmt:formatNumber value="${product.pprice}" type="currency" currencySymbol="" /> vnđ
                    </strong>
                </a>
            </div>
        </c:forEach>
    </div>
</div>
