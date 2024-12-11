<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<link rel="stylesheet"
    href="${pageContext.request.contextPath}/assets/css/my-styles.css">

<div class="product-section">
<<<<<<< Updated upstream
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
=======
    <div class="container">
        <!-- Dòng chữ "New Product" -->
        <div class="row">
            <div class="col-12 text-center">
                <h2 class="section-label">New Product</h2>
            </div>
        </div>

        <!-- Product List -->
        <div class="row">
            <!-- Phần mô tả -->
            <div class="col-md-12 col-lg-3 mb-5 mb-lg-0">
                <h2 class="mb-4 section-title">Crafted with excellent material.</h2>
                <p class="mb-4">
                    Donec vitae odio quis nisl dapibus malesuada. Nullam ac aliquet velit. Aliquam vulputate velit imperdiet dolor tempor tristique.
                </p>
                <p>
                    <a href="shop.html" class="btn">Explore</a>
                </p>
            </div>

            <!-- Product List -->
            <c:forEach items="${topprod}" var="prod">
                <div class="col-12 col-md-4 col-lg-3 mb-5 mb-md-0">
                    <c:url value="/image?fname=${prod.ppicture}" var="imgUrl"></c:url>
                    <div class="product-item">
                        <a href="${pageContext.request.contextPath}/user/product/detail?pid=${prod.pid}">
                            <img src="${imgUrl}" class="img-fluid product-thumbnail">
                            <h3 class="product-title">${prod.pname}</h3>
                            <strong class="product-price">
                                <fmt:formatNumber value="${prod.pprice}" type="currency" currencySymbol="" /> vnđ
                            </strong>
                        </a>
                        <!-- Biểu tượng Add to Cart -->
                        <form action="${pageContext.request.contextPath}/user/cart.html" method="post" class="icon-cross-form">
                            <input type="hidden" name="productId" value="${prod.pid}" />
                            <button type="submit" class="icon-cross">
                                <i class="fa fa-plus"></i>
                            </button>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

<!-- CSS -->
<style>
/* Tiêu đề phần */
.section-label {
    font-size: 28px;
    font-weight: bold;
    margin-bottom: 30px;
    margin-top: 50px;
    color: #333;
}

/* Giao diện sản phẩm */
.product-item a {
    text-decoration: none;
    color: inherit;
}

.product-item a:hover {
    color: #007bff;
    text-decoration: none;
}

.product-thumbnail {
    width: 100%;
    height: 300px;
    object-fit: cover;
    margin-bottom: 10px;
}

.product-title {
    font-size: 16px;
    font-weight: bold;
    margin: 5px 0;
}

.product-price {
    font-size: 14px;
    color: #555;
}

/* Biểu tượng Add to Cart */
.icon-cross-form {
    margin-top: 10px;
}

.icon-cross {
    position: relative;
    display: inline-block;
    width: 50px;
    height: 50px;
    background-color: #ffffff; /* Nền màu trắng */
    border-radius: 50%; /* Hình tròn */
    box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1); /* Hiệu ứng đổ bóng */
    border: none;
    cursor: pointer;
    text-align: center;
    overflow: hidden;
}

.icon-cross i {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    font-size: 20px;
    color: #009966; /* Màu biểu tượng */
}

.icon-cross:hover {
    background-color: #009966; /* Màu nền khi hover */
}

.icon-cross:hover i {
    color: #ffffff; /* Màu biểu tượng khi hover */
}
</style>
>>>>>>> Stashed changes
