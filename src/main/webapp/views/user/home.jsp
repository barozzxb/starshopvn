<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/my-styles.css">



<div class="search-bar-section bg-light py-4">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-12 col-md-8">
                <form action="${pageContext.request.contextPath}/user/product/search" method="get" class="search-bar-form d-flex">
                    <input 
                        type="text" 
                        name="query" 
                        class="form-control rounded-start" 
                        placeholder="Search for products, brands, or categories..." 
                        aria-label="Search"
                        required>
                    <button type="submit" class="btn btn-primary rounded-end">
                        <i class="fa fa-search"></i> <!-- Biểu tượng tìm kiếm -->
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>

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

			<!-- Product -->
			<c:forEach items="${topprod}" var="prod">
				<div class="col-12 col-md-4 col-lg-3 mb-5 mb-md-0">
					<c:url value="/image?fname=${prod.ppicture}" var="imgUrl"></c:url>
					<a class="product-item" href="${pageContext.request.contextPath}/user/product/detail?pid=${prod.pid}"> <img src="${imgUrl}"
						class="img-fluid product-thumbnail">
						
						<h3 class="product-title">${prod.pname}</h3> <strong
						class="product-price"><fmt:formatNumber
								value="${prod.pprice}" type="currency" currencySymbol="" /> vnđ</strong> <span
						class="icon-cross"> <img
							src="${URL}assets/images/cross.svg" class="img-fluid">
					</span>
					</a>
				</div>
			</c:forEach>
		</div>
	</div>
</div>



