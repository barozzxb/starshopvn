<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url value="/" var="URL"></c:url>

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
					<a class="product-item" href="cart.html"> <img src="${imgUrl}"
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