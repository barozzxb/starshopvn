<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>

<div class="table-responsive">
	<table class="table">
		<tbody>

			<c:forEach items="" var="post">
				<tr>
					<td class="col-md-7 col-sm-6 col-xs-6">
						<div class="product-name">
							<input type="hidden" value="" /> <a href="#"
								class="section-title"></a>
						</div> <br></br>
						<div class="quantity">products remain</div>
					</td>

					<td class="col-md-7 col-sm-6 col-xs-6"><div class="price">
							VND <span>$900.00</span>
						</div></td>
					<td class="col-md-2 "><a
						href="${pageContext.request.contextPath }/admin/genre/edit?gid="
						class="btn-upper btn btn-primary">Edit genre</a></td>
					<td class="col-md-1 close-btn"><a
						href="${pageContext.request.contextPath }/admin/genre/delete?gid="
						class=""><i class="fa fa-times"></i></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>