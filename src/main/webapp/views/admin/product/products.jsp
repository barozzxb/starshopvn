<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url value="/" var="URL"></c:url>

<div class="body-content">
	<div class="container">
		<div class="sign-in-page">
			<div class="row">

				<div class="col-md-12 my-wishlist">
					<div class="table-responsive">
						<table class="table">
							<thead>
								<tr>
									<th colspan="4" class="heading-title">Products list</th>
									<th colspan="4" class="heading-title"><a
										href="${pageContext.request.contextPath }/admin/product/add"
										class="btn-upper btn btn-primary">Add product</a></th>
								</tr>
							</thead>
							<tbody>

								<c:forEach items="${listprod}" var="prod">
									<tr>
										<c:url value="/image?fname=${prod.ppicture}" var="imgUrl"></c:url>
										<td class="col-md-2 col-sm-6 col-xs-6"><img width=100 height=100
											src="${imgUrl}" alt="prodImage"></td>
										<td class="col-md-7 col-sm-6 col-xs-6">
											<div class="product-name">
												<input type="hidden" value="${prod.pid }" /> <a href="#"
													class="section-title">${prod.pname}</a>
											</div> <br></br>
											<div class="quantity">${prod.pquantity} products remain</div>
										</td>

										<td class="col-md-7 col-sm-6 col-xs-6"><div class="price">
												<p id="price">${prod.pprice} VND <span>$900.00</span> </p>
											</div></td>
										<td class="col-md-2 "><a
											href="${pageContext.request.contextPath }/admin/product/edit?pid=${prod.pid}"
											class="btn-upper btn btn-primary">Edit genre</a></td>
										<td class="col-md-1 close-btn"><a
											href="${pageContext.request.contextPath }/admin/product/delete?pid=${prod.pid}"
											class=""><i class="fa fa-times"></i></a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>