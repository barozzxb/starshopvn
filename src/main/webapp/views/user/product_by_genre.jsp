<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>

<c:choose>
    <c:when test="${not empty products}">
        <div class="product-list">
            <c:forEach var="product" items="${products}">
                <div class="product-item">
                    <img src="${pageContext.request.contextPath}/images/${product.ppicture}" alt="${product.pname}" class="product-img">
                    <div class="product-info">
                        <h2>${product.pname}</h2>
                        <p>Price: ${product.pprice} VND</p>
                        <p>Rating: ${product.prating}</p>
                        <p>Quantity: ${product.pquantity}</p>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:when>
    <c:otherwise>
        <p>No products found for this genre.</p>
    </c:otherwise>
</c:choose>
