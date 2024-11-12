<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<header>
	<div id="top-header">
		<div class="container">
			<ul class="header-links pull-left">
				<li><a href="#"><i class="fa fa-phone"></i> +021-95-51-84</a></li>
				<li><a href="#"><i class="fa fa-envelope-o"></i>
						email@email.com</a></li>
				<li><a href="#"><i class="fa fa-map-marker"></i> 1734
						Stonecoal Road</a></li>
			</ul>
			<ul class="header-links pull-right">
				<li class="header_cart hidden-xs"><a
					href="${pageContext.request.contextPath}/admin/profile"><span>My
							Account</span></a></li>
				<li class="header_cart hidden-xs">			
				<c:choose>
					<c:when test="${sessionScope.account != null}">
						<a href="${pageContext.request.contextPath}/admin/profile"> 
						<span>
							<c:choose>
								<c:when test="${sessionScope.account.name == null}">
										${sessionScope.account.userid}
									</c:when>
								<c:otherwise>
										${sessionScope.account.name}
									</c:otherwise>
							</c:choose>
							</span>
						</a>
						<a href="${pageContext.request.contextPath }/logout">Đăng Xuất</a>
					</c:when>
				</c:choose>
				</li>
			</ul>
		</div>
	</div>
</header>