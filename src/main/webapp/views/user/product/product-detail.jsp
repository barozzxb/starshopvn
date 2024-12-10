<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/my-styles.css">

<div class="product-detail-section">
	<div class="container">
		<div class="row">
			<!-- Left side - Product Image -->
			<div class="col-md-12 col-lg-6 mb-4 mb-lg-0">
				<!-- Product Image -->
				<div class="product-image">
					<c:url value="/image?fname=${product.ppicture}" var="imgUrl"></c:url>
					<!-- Phóng to hình ảnh khi hover -->
					<img src="${imgUrl}" alt="${product.pname}"
						class="img-fluid zoomed-image">
				</div>
			</div>

			<!-- Right side - Product Details -->
			<div class="col-md-12 col-lg-6">
				<h2 class="product-title">${product.pname}</h2>

				<div class="product-description mt-4">
					<h3>Description</h3>
					<p>${product.pinfo}</p>
				</div>

				<div class="product-price mt-4">
					<strong><fmt:formatNumber value="${product.pprice}"
							type="currency" currencySymbol="" /> vnđ</strong>
				</div>

				<div class="product-rating mt-3">
					<strong>Rating:</strong> <span>${product.prating} / 5</span>
				</div>

				<form action="cart.html" method="post" class="mt-3">
					<input type="number" name="quantity" value="1" min="1"
						class="form-control" style="width: 80px;">
					<button type="submit" class="btn btn-primary mt-2">Add to
						Cart</button>
				</form>

				<form action="${pageContext.request.contextPath}/user/product/like"
					method="post" class="mt-3">
					<input type="hidden" name="pid" value="${product.pid}">
					<button type="submit" class="btn btn-danger mt-2">
						<i class="fa fa-heart"></i> Add to Favorite
					</button>
				</form>
			</div>
		</div>

		<!-- Recently Viewed Products -->
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

		<div class="text-center mb-4">
			<a
				href="${pageContext.request.contextPath}/user/product/review?pid=${product.pid}"
				class="btn btn-primary">Đánh giá sản phẩm</a>
		</div>
		<!-- Product Reviews -->
		<div class="product-reviews mt-5">
			<h3>Reviews</h3>
			<c:if test="${not empty reviews}">
				<ul class="reviews-list">
					<c:forEach var="review" items="${reviews}">
						<li class="review">
							<div class="review-header">
								<strong>${review.account.name}</strong> - ${review.rating} sao
							</div>
							<div class="review-content">
								<p>${review.comment}</p>
								<c:if test="${not empty review.mediaUrl}">
									<c:choose>
										<c:when
											test="${fn:endsWith(review.mediaUrl, '.mp4') || fn:endsWith(review.mediaUrl, '.avi')}">
											<video controls class="img-fluid"
												style="max-width: 100%; height: auto;">
												<source
													src="${pageContext.request.contextPath}${review.mediaUrl}"
													type="video/mp4">
												Video không hỗ trợ.
											</video>
										</c:when>
										<c:otherwise>
											<img
												src="${pageContext.request.contextPath}${review.mediaUrl}"
												alt="Review Image" class="img-fluid">


										</c:otherwise>
									</c:choose>
								</c:if>

							</div> <small>${review.createdAt}</small>
						</li>
					</c:forEach>
				</ul>
			</c:if>
			<c:if test="${empty reviews}">
				<p>No reviews yet.</p>
			</c:if>
		</div>
	</div>
</div>

