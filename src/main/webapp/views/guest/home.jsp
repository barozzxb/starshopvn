<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url value="/" var="URL"></c:url>

<div class="hero">
	<div class="container">
		<div class="row justify-content-between">
			<div class="col-lg-5">
				<div class="intro-excerpt">
					<h1>
						Shop hoa tươi <mark> Star Shop</mark>.</span>
					</h1>
					<p class="mb-4">Dồn cả tâm huyết để trao đến tay bạn những đóa hoa xinh đẹp và tươi tắn nhất.</p>
						<a href="${pageContext.request.contextPath }/products" class="btn btn-secondary me-2">Khám phá</a>
				</div>
			</div>
			<div class="col-lg-7">
				<div class="hero-img-wrap">
					<img src="${URL }assets/images/flowers.jpg" class="img-fluid rounded">
				</div>
			</div>
		</div>
	</div>
</div>

	<div class="container py-2 border">
<!-- 	    <div class="row"> -->
<!-- 	        <div class="col-12 text-center mb-5"> -->
<!-- 	            <h2 class="mb-4 section-title">Các thể loại sản phẩm</h2> -->
<!-- 	        </div> -->
<!-- 	    </div> -->

		<div id="demo" class="carousel slide" data-bs-ride="carousel">
		    <div class="carousel-inner">
		        <c:set var="slideIndex" value="0"/>
		        <c:forEach items="${genres}" var="genre" varStatus="status">
		            <!-- Mỗi slide mới bắt đầu sau 4 genre -->
		            <c:if test="${status.index % 4 == 0}">
		                <!-- Nếu là slide đầu tiên -->
		                <div class="carousel-item ${status.index == 0 ? 'active' : ''}">
		                    <div class="row justify-content-center">
		            </c:if>
		
		            <!-- Hiển thị genre -->
		            <div class="col-12 col-md-3 mb-5 mb-md-0">
		                <div class="card shadow-sm border-light cdbg-light-green">
		                    <div class="card-body text-center">
		                        <h5 class="card-title">${genre.gname}</h5>
		                        <p><em>${genre.productCount} sản phẩm</em></p>
		                        <a href="${pageContext.request.contextPath}/genre/products?gid=${genre.gid}" class="btn btn-primary">Xem chi tiết <i class="fa fa-arrow-right"></i></a>
		                    </div>
		                </div>
		            </div>
		
		            <!-- Kết thúc slide sau khi có 4 genre -->
		            <c:if test="${status.index % 4 == 3 || status.last}">
		                </div> <!-- End row -->
		                </div> <!-- End carousel-item -->
		            </c:if>
		        </c:forEach>
		    </div>
		
		    <!-- Left and right controls/icons -->
		    <button class="carousel-control-prev text-dark-gray" type="button" data-bs-target="#demo" data-bs-slide="prev">
		        <span class="carousel-control-prev-icon text-dark-gray"></span>
		    </button>
		    <button class="carousel-control-next text-dark-gray" type="button" data-bs-target="#demo" data-bs-slide="next">
				<span class="carousel-control-next-icon text-dark-gray"></span>
		    </button>
	</div>
	
<div class="product-section">		
	<div class="container py-5 border bg-white">
		<div class="col-12 text-center mb-5">
				<h2 class="mb-4 section-title">Sản phẩm mới.</h2>
			</div>
		<div class="row justify-content-center align-items-center g-4">
			<c:forEach items="${topprod}" var="prod">
				<div class="col-12 col-md-4 col-lg-3 mb-5 mb-md-0">
					<c:url value="/image?fname=${prod.ppicture}" var="imgUrl"></c:url>
					<a class="product-item" href="${pageContext.request.contextPath }/product/detail?pid=${prod.pid}"> 
					<img src="${imgUrl}" class="img-fluid product-thumbnail">
						<h3 class="product-title">${prod.pname}</h3> 
						<strong	class="product-price"><fmt:formatNumber
								value="${prod.pprice}" type="number" currencySymbol="" /> vnđ</strong> 
						<span class="icon-cross"> 
							<img src="${URL}assets/images/cross.svg" class="img-fluid">
						</span>
					</a>
				</div>
			</c:forEach>
		</div>
	</div>
	<br></br>

	<div class="container py-5 border bg-white">
		<div class="row">
			<div class="col-12 text-center mb-5">
				<h2 class="mb-4 section-title">Một số sản phẩm bán chạy.</h2>
			</div>
		</div>
		<div class="row justify-content-center align-items-center g-4">
			<c:forEach items="${topSelling}" var="prod">
				<div class="col-12 col-md-4 col-lg-3 mb-5 mb-md-0">
					<c:url value="/image?fname=${prod.product.ppicture}" var="imgUrl"></c:url>
					<a class="product-item" href="${pageContext.request.contextPath }/product/detail?pid=${prod.product.pid}"> 
					<img src="${imgUrl}" class="img-fluid product-thumbnail">
						<h3 class="product-title">${prod.product.pname}</h3> 
						<strong	class="product-price"><fmt:formatNumber
								value="${prod.product.pprice}" type="number" currencySymbol="" /> vnđ</strong> 
						<p><em>${prod.ordernumber} lượt bán</em></p>
						<span
						class="icon-cross"> <img
							src="${URL}assets/images/cross.svg" class="img-fluid">
					</span>
					</a>
				</div>
			</c:forEach>
		</div>
	</div>
	<br>

		</div>





		<!-- 	    <div class="row justify-content-center align-items-center g-4"> -->
<%-- 	        <c:forEach items="${genres}" var="genre"> --%>
<!-- 	            <div class="col-12 col-md-4 col-lg-3 mb-5 mb-md-0"> -->
<!-- 	                <div class="card shadow-sm border-light"> -->
<!-- 	                    <div class="card-body text-center"> -->
<%-- 	                        <h5 class="card-title">${genre.gname}</h5> --%>
<%-- 	                        <p class="card-text">${genre.gdescription}</p> --%>
<%-- 	                        <p><em>${genre.productCount} sản phẩm</em></p> --%>
<%-- 	                        <a href="${pageContext.request.contextPath}/genre/detail?gid=${genre.gid}" class="btn btn-primary">Xem chi tiết</a> --%>
<!-- 	                    </div> -->
<!-- 	                </div> -->
<!-- 	            </div> -->
<%-- 	        </c:forEach> --%>
<!-- 	    </div> -->
