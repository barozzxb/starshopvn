<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>

<div class="container mt-4">
	<h3>Các bài viết</h3>
	<div id="postsList">
	
	
	
	</div>
</div>


<!-- Button to Open the Modal -->
<button type="button" class="btn btn-primary fixed-bottom m-3" data-bs-toggle="modal" data-bs-target="#addPostModal">
  Thêm Bài Viết
</button>

<!-- Modal -->
<div class="modal fade" id="addPostModal" tabindex="-1" aria-labelledby="addPostModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="addPostModalLabel">Thêm Bài Viết</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form id="addPostForm">
          <div class="mb-3">
            <label for="title" class="form-label">Tiêu Đề</label>
            <input type="text" class="form-control" id="title" name="title" required>
          </div>
          <div class="mb-3">
            <label for="genre" class="form-label">Thể Loại</label>
            <input type="text" class="form-control" id="genre" name="genre" required>
          </div>
          <div class="mb-3">
            <label for="content" class="form-label">Nội Dung</label>
            <textarea class="form-control" id="content" name="content" rows="3" required></textarea>
          </div>
          <div class="mb-3">
            <label for="image" class="form-label">Hình Ảnh</label>
            <input type="file" class="form-control" id="image" name="image" accept="image/*">
          </div>
          <button type="submit" class="btn btn-primary">Thêm Bài Viết</button>
        </form>
      </div>
    </div>
  </div>
</div>