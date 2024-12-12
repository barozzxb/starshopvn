<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="/common/taglib.jsp" %>

<div class="search-bar-section bg-light py-4">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-12 col-md-8">
                <form action="${pageContext.request.contextPath}/user/product/search" method="get" class="search-bar-form d-flex">
                    <!-- Tìm kiếm sản phẩm -->
                    <input 
                        type="text" 
                        name="query" 
                        class="form-control rounded-start" 
                        placeholder="Search for products, brands, or categories..." 
                        aria-label="Search"
                        value="${param.query}">
                    <button type="submit" class="btn btn-primary rounded-end">
                        <i class="fa fa-search"></i> <!-- Biểu tượng tìm kiếm -->
                    </button>
                </form>
            </div>
        </div>
        
        <!-- Các bộ lọc -->
        <div class="row justify-content-center mt-4">
            <div class="col-12 col-md-8">
                <form action="${pageContext.request.contextPath}/user/product/search" method="get">
                    <!-- Rating Filter -->
                    <div class="mb-3">
                        <label for="rating" class="form-label">Rating</label>
                        <select class="form-select" id="rating" name="rating">
                            <option value="">All Ratings</option>
                            <option value="1" ${param.rating == '1' ? 'selected' : ''}>1 Star</option>
                            <option value="2" ${param.rating == '2' ? 'selected' : ''}>2 Stars</option>
                            <option value="3" ${param.rating == '3' ? 'selected' : ''}>3 Stars</option>
                            <option value="4" ${param.rating == '4' ? 'selected' : ''}>4 Stars</option>
                            <option value="5" ${param.rating == '5' ? 'selected' : ''}>5 Stars</option>
                        </select>
                    </div>

                    <!-- Price Filter -->
                    <div class="mb-3">
                        <label for="minPrice" class="form-label">Price</label>
                        <input type="number" class="form-control" id="minPrice" name="minPrice" placeholder="Min Price" value="${param.minPrice}">
                        <input type="number" class="form-control mt-2" id="maxPrice" name="maxPrice" placeholder="Max Price" value="${param.maxPrice}">
                    </div>

                    <!-- Quantity Filter -->
                    <div class="mb-3">
                        <label for="minQuantity" class="form-label">Quantity</label>
                        <input type="number" class="form-control" id="minQuantity" name="minQuantity" placeholder="Min Quantity" value="${param.minQuantity}">
                        <input type="number" class="form-control mt-2" id="maxQuantity" name="maxQuantity" placeholder="Max Quantity" value="${param.maxQuantity}">
                    </div>

                    <!-- Sort By -->
                    <div class="mb-3">
                        <label for="sortBy" class="form-label">Sort By</label>
                        <select class="form-select" id="sortBy" name="sortBy">
                            <option value="createdat" ${param.sortBy == 'createdat' ? 'selected' : ''}>Newest</option>
                            <option value="pprice" ${param.sortBy == 'pprice' ? 'selected' : ''}>Price</option>
                            <option value="prating" ${param.sortBy == 'prating' ? 'selected' : ''}>Rating</option>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="ascending" class="form-label">Sort Order</label>
                        <select class="form-select" id="ascending" name="ascending">
                            <option value="true" ${param.ascending == 'true' ? 'selected' : ''}>Ascending</option>
                            <option value="false" ${param.ascending == 'false' ? 'selected' : ''}>Descending</option>
                        </select>
                    </div>

                    <button type="submit" class="btn btn-primary">Apply Filters</button>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="container mt-4">
    <div class="row">
        <c:forEach var="product" items="${products}">
            <div class="col-12 col-md-4">
                <div class="card">
                	<c:url value="/image?fname=${product.ppicture}" var="imgUrl"></c:url>
                    <img src="${imgUrl}" class="card-img-top" alt="${product.pname}">
                    <div class="card-body">
                        <h5 class="card-title">${product.pname}</h5>
                        <p class="card-text">${product.pinfo}</p>
                        <p class="card-text">Price: ${product.pprice}</p>
                        <p class="card-text">Rating: ${product.prating}</p>
                        <p class="card-text">Available: ${product.pquantity}</p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>