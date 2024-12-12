<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đánh giá sản phẩm</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="container mt-4">
		<h2 class="text-center mb-4">Đánh giá sản phẩm</h2>

		<!-- Hiển thị thông báo nếu có -->
		<c:if test="${not empty message}">
			<div class="alert alert-info">${message}</div>
		</c:if>

		<!-- Form đánh giá -->
		<form action="${pageContext.request.contextPath}/user/product/review"
			method="post" enctype="multipart/form-data" class="mb-5">
			<input type="hidden" name="pid" value="${product.pid}">

			<div class="mb-3">
				<label for="rating" class="form-label">Đánh giá (số sao):</label> <select
					name="rating" id="rating" class="form-select" required>
					<option value="5">Rất tốt</option>
					<option value="4">Tốt</option>
					<option value="3">Trung bình</option>
					<option value="2">Kém</option>
					<option value="1">Rất kém</option>
				</select>
			</div>

			<div class="mb-3">
				<label for="comment" class="form-label">Bình luận:</label>
				<textarea name="comment" id="comment" class="form-control" rows="4"
					required></textarea>
			</div>

			<div class="mb-3">
				<label for="mediaFile" class="form-label">Tệp đính kèm (hình
					ảnh/video):</label> <input type="file" name="mediaFile" id="mediaFile"
					class="form-control" accept="image/*,video/*">
			</div>

			<button type="submit" class="btn btn-primary">Gửi đánh giá</button>
		</form>

		<!-- Hiển thị các đánh giá trước đây -->
		<h3 class="mb-4">Đánh giá trước đây</h3>
		<c:forEach var="review" items="${reviews}">
			<div class="card mb-3">
				<div
					class="card-header d-flex justify-content-between align-items-center">
					<span><strong>${review.account.name}</strong></span> <span
						class="badge bg-warning text-dark">${review.rating} sao</span>
				</div>
				<div class="card-body">
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
								<img src="${pageContext.request.contextPath}${review.mediaUrl}" alt="Review Image" class="img-fluid">


							</c:otherwise>
						</c:choose>
					</c:if>
				</div>
				<div class="card-footer text-end">
<small class="text-muted">${review.createdAt != null ? review.createdAt : 'Ngày không xác định'}</small>
					<a
						href="${pageContext.request.contextPath}/user/product/review/delete?reviewId=${review.reviewId}"
						class="btn btn-sm btn-danger ms-3">Xóa</a>
				</div>
			</div>
		</c:forEach>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>