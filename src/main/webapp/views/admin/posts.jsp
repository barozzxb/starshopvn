<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<div class="container mt-4">
    <h2 class="text-center mb-4">Admin Post Management</h2>

    <!-- Tabs Navigation -->
    <ul class="nav nav-tabs" id="postTabs" role="tablist">
        <li class="nav-item" role="presentation">
            <button class="nav-link active" id="pending-posts-tab" data-bs-toggle="tab" data-bs-target="#pending-posts" type="button" role="tab" aria-controls="pending-posts" aria-selected="true">Post Chưa Duyệt</button>
        </li>
        <li class="nav-item" role="presentation">
            <button class="nav-link" id="approved-posts-tab" data-bs-toggle="tab" data-bs-target="#approved-posts" type="button" role="tab" aria-controls="approved-posts" aria-selected="false">Post Đã Duyệt</button>
        </li>
    </ul>

    <!-- Tabs Content -->
    <div class="tab-content mt-3" id="postTabsContent">
        <!-- Pending Posts -->
        <div class="tab-pane fade show active" id="pending-posts" role="tabpanel" aria-labelledby="pending-posts-tab">
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead class="table-dark">
                        <tr>
                            <th>ID</th>
                            <th>Title</th>
                            <th>Author</th>
                            <th>Submitted At</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${uncensoredPost }" var="post">
	                        <tr>
	                            <td>post.poid</td>
	                            <td>post.title</td>
	                            <td>post.account.name</td>
	                            <td>post.createdat</td>
	                            <td>
	                                <button class="btn btn-success btn-sm">
	                                    <i class="bi bi-check-circle"></i> Approve
	                                </button>
	                                <button class="btn btn-danger btn-sm">
	                                    <i class="bi bi-x-circle"></i> Reject
	                                </button>
	                            </td>
	                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <!-- Approved Posts -->
        <div class="tab-pane fade" id="approved-posts" role="tabpanel" aria-labelledby="approved-posts-tab">
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead class="table-dark">
                        <tr>
                            <th>ID</th>
                            <th>Title</th>
                            <th>Author</th>
                            <th>Approved At</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${censoredPost }" var="post">
	                        <tr>
	                            <td>post.poid</td>
	                            <td>post.title</td>
	                            <td>post.account.name</td>
	                            <td>post.createdat</td>
	                            <td>
	                                <button class="btn btn-danger btn-sm">
	                                    <i class="bi bi-x-circle"></i> Reject
	                                </button>
	                            </td>
	                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>