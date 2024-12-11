<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<c:url value="/" var="URL"></c:url>
<c:url value="/image?fname=${product.ppicture}" var="productImage"></c:url>

<div class="container my-5">
	<c:if test="${not empty message}">
    	<div class="alert alert-success alert-dismissible fade show" role="alert">
            <strong>Success!</strong> ${message}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    </c:if>
    <div class="row">
        <!-- Product Image -->
        <div class="col-md-6">
            <img src="${productImage}" alt="${product.pname}" class="img-fluid rounded shadow">
        </div>

        <!-- Product Information -->
        <div class="col-md-6">
            <h1 class="mb-3">${product.pname}</h1>
            <h4 class="text-primary mb-3">
                <fmt:formatNumber value="${product.pprice}" type="number" currencySymbol="" /> vnđ
            </h4>
            <p class="text-muted"><strong>Số lượng:</strong> ${product.pquantity}</p>
            <p class="mb-4"><strong>Thể loại:</strong> ${product.genre.gname}</p>

            <!-- Product Description -->
            <h5 class="mb-3">Thông tin sản phẩm:</h5>
            <p>${product.pinfo}</p>

            <!-- Ratings -->
            <div class="mb-3">
                <strong>Đánh giá:</strong>
                <span class="text-warning">
                    <c:forEach var="i" begin="1" end="${product.prating}">
                        <i class="fas fa-star"></i>
                    </c:forEach>
                    <c:forEach var="i" begin="${product.prating + 1}" end="5">
                        <i class="far fa-star"></i>
                    </c:forEach>
                </span>
            </div>

            <!-- Actions -->
            <div class="d-flex align-items-center gap-3">
                <form action="${pageContext.request.contextPath }/user/cart/add" method="post" class="d-inline-block">
                    <input type="hidden" name="pid" value="${product.pid}">
                    <button type="submit" class="btn btn-primary btn-lg">
                        <i class="fas fa-cart-plus"></i> Thêm vào giỏ hàng
                    </button>
                </form>
                <a href="${backUrl}" class="btn btn-outline-secondary btn-lg">Quay lại</a>
            </div>
        </div>
    </div>
    
    <c:if test="${not empty account }">
    	<div class="recently-viewed-products mt-5">
			<h3 class="text-center mb-4">Recently Viewed Products</h3>

			<c:if test="${not empty viewedProducts}">
				<div class="row">
					<c:forEach var="viewedProduct" items="${viewedProducts}">
						<div class="col-6 col-md-3 col-lg-2 text-center mb-4">
							<a
								href="${pageContext.request.contextPath}/user/product/detail?pid=${viewedProduct.pid}"
								class="viewed-product-link"> <c:url
									value="/image?fname=${viewedProduct.ppicture}" var="imgUrl"></c:url>
								<img src="${imgUrl}" alt="${viewedProduct.pname}"
								class="img-fluid mb-3 viewed-product-img">
								<p class="product-name">${viewedProduct.pname}</p> <strong
								class="product-price"> <fmt:formatNumber
										value="${viewedProduct.pprice}" type="currency"
										currencySymbol="" /> vnđ
							</strong>
							</a>
						</div>
					</c:forEach>
				</div>
			</c:if>

			<c:if test="${empty viewedProducts}">
				<p class="text-center">No products have been viewed yet.</p>
			</c:if>
		</div>
    </c:if>
</div>
