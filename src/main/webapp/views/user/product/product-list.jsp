<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/my-styles.css">

<div class="product-section">
	<div class="container">
		<div class="row">
			<!-- Genres List -->
			<div class="col-md-3">
				<h3 class="mb-4">Genres</h3>
				<ul class="list-group">
					<c:forEach items="${listgenre}" var="genre">
						<li class="list-group-item"><a
							href="${pageContext.request.contextPath}/user/genres/products?gid=${genre.gid}">
								${genre.gname} </a></li>
					</c:forEach>
				</ul>
			</div>

			<!-- Loop through each product and create a link to the product detail page -->
			<c:forEach items="${products}" var="prod">
				<div class="col-12 col-md-4 col-lg-3 mb-5 mb-md-0">
					<c:url value="/image?fname=${prod.ppicture}" var="imgUrl"></c:url>

					<!-- Link to product detail page when clicking the image -->
					<a class="product-item"
						href="${pageContext.request.contextPath}/user/product/detail?pid=${prod.pid}">
						<img src="${imgUrl}" class="img-fluid product-thumbnail"
						alt="${prod.pname}">
						<h3 class="product-title">${prod.pname}</h3> <strong
						class="product-price"> <fmt:formatNumber
								value="${prod.pprice}" type="currency" currencySymbol="" /> vnđ
					</strong>
					</a>

					<!-- Add to Cart button -->
					<form action="${pageContext.request.contextPath}/cart/add"
						method="post" class="add-to-cart-form">
						<input type="hidden" name="pid" value="${prod.pid}" />
						<button type="submit" class="btn btn-success add-to-cart-btn">
							<span>+</span> Add to Cart
						</button>
					</form>
				</div>
			</c:forEach>

		</div>
	</div>
</div>

<div class="post-section py-4">
	<div class="container">
		<div class="row">
			<c:if test="${not empty posts}">
				<c:forEach items="${posts}" var="post">
					<div class="col-12 col-md-6 col-lg-4 mb-4">
						<div class="post-item card h-100">
							<img src="${post.image}" class="card-img-top" alt="${post.title}">
							<div class="card-body">
								<h5 class="card-title">${post.title}</h5>
								<p class="card-text text-muted">${post.author}•
									${post.createdat}</p>
								<c:choose>
									<c:when
										test="${post.content != null && post.content is String}">
										<p class="card-text">
											<c:choose>
												<c:when test="${post.content != null}">
													<p class="card-text">${post.content.length > 100 ? post.content.substring(0, 100) + '...' : post.content}</p>
												</c:when>
												<c:otherwise>
													<p class="card-text">No content available</p>
												</c:otherwise>
											</c:choose>

										</p>
									</c:when>
									<c:otherwise>
										<p class="card-text">${post.content}</p>
									</c:otherwise>
								</c:choose>

								<a
									href="${pageContext.request.contextPath}/user/post/detail?pid=${post.id}"
									class="btn btn-primary btn-sm">Read More</a>
							</div>
						</div>
					</div>
				</c:forEach>


			</c:if>
			<c:if test="${empty posts}">
				<p>No posts available at the moment.</p>
			</c:if>
		</div>
	</div>
</div>
