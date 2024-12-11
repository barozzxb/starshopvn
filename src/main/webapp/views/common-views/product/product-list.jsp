<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url value="/" var="URL"></c:url>

<div class="hero">
    <div class="container">
        <div class="row justify-content-center align-items-center">
            <div class="col-12 text-center">
                <!-- Tiêu đề "Shop" -->
                <div class="intro-excerpt mb-4">
                    <h1>Shop</h1>
                </div>

                <!-- Thanh tìm kiếm -->
                <form action="${pageContext.request.contextPath}/user/product/search" method="get" class="search-form">
                    <div class="input-group mb-4">
                        <input type="text" name="search" class="form-control" placeholder="Tìm kiếm sản phẩm..." value="${param.search}">
                        <button type="submit" class="btn btn-primary">Tìm kiếm</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="untree_co-section product-section before-footer-section">
    <div class="container">
        <div class="row">
            <!-- Sidebar Filter -->
            <div class="col-lg-3">
                <div class="card">
                    <div class="card-header">
                        <h4 class="mb-0">Bộ lọc</h4>
                    </div>
                    <div class="card-body">
                        <form action="
	                        <c:choose>
			                    <c:when test="${not empty sessionScope.account}">
			                        ${pageContext.request.contextPath}/user/product/filter
			                    </c:when>
			                    <c:otherwise>
			                        ${pageContext.request.contextPath}/product/filter
			                    </c:otherwise>
			                </c:choose>" method="get">

                            <div class="mb-3">
                                <h5>Thể loại</h5>
                                <c:forEach items="${genres}" var="genre">
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" name="genres" value="${genre.gid}" 
                                            <c:if test="${param.genre == genre.gid}">checked</c:if>>
                                        <label class="form-check-label">${genre.gname}</label>
                                    </div>
                                </c:forEach>
                            </div>

                            <!-- Filter by Price -->
                            <div class="mb-3">
                                <h5>Giá</h5>
                                <div class="form-check">
                                	<input type="radio" name="price" class="form-check-input" value="200000">
                                	<label class="form-check-label">Dưới 200.000</label>
                                </div>
                                <div class="form-check">
                                	<input type="radio" name="price" class="form-check-input" value="500000">
                                	<label class="form-check-label">Dưới 500.000</label>
                                </div>
                                <div class="form-check">
                                	<input type="radio" name="price" class="form-check-input" value="1000000">
                                	<label class="form-check-label">Dưới 1.000.000</label>
                                </div>
                                <div class="form-check">
                                	<input type="radio" name="price" class="form-check-input" value="2000000">
                                	<label class="form-check-label">Dưới 2.000.000</label>
                                </div>
                            </div>

                            <!-- Submit Filter -->
                            <button type="submit" class="btn btn-primary btn-block">Áp dụng bộ lọc</button>
                            <a href="
	                            <c:choose>
				                    <c:when test="${not empty sessionScope.account}">
				                        ${pageContext.request.contextPath}/user/product/reset
				                    </c:when>
				                    <c:otherwise>
				                        ${pageContext.request.contextPath}/product/reset
				                    </c:otherwise>
				                </c:choose>" class="btn btn-light">
				           	Đặt lại</a>
                        </form>
                    </div>
                </div>
            </div>

			<c:forEach items="${products }" var="prod">
				<div class="col-12 col-md-4 col-lg-3 mb-5">
					<c:url value="/image?fname=${prod.ppicture}" var="imgUrl"></c:url>
					<c:choose>
					    <c:when test="${account != null}">
					        <c:url value="product/detail" var="baseURL" />
					    </c:when>
					    <c:otherwise>
					        <c:url value="product/detail" var="baseURL" />
					    </c:otherwise>
					</c:choose>
					
					<c:url value="${baseURL}" var="detailURL">
					    <c:param name="pid" value="${prod.pid}" />
					</c:url>
					
					<a class="product-item" href="${detailURL}">
						<img src="${imgUrl }" class="img-fluid product-thumbnail rounded">
						<h3 class="product-title">${ prod.pname}</h3> 
						<strong class="product-price"><fmt:formatNumber
								value="${prod.pprice}" type="number" currencySymbol="" /> vnđ</strong> 
						<form action="${pageContext.request.contextPath }/user/cart/add" method="post" class="d-inline-block">
	                    	<input type="hidden" name="pid" value="${prod.pid}">
	                    	<button type="submit" class="icon-cross"><i class="fa fa-plus" style="color: white"></i></button>
	                	</form>
					</a>
				</div>
			</c:forEach>
		</div>
	</div>
</div>