<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<div class="container mt-3">
	<h1>Create New Post</h1>
	<form action="/submit-post" method="post">
		<div class="mb-3">
			<label for="title" class="form-label">Title</label> <input
				type="text" class="form-control" id="title" name="title"
				  
 placeholder="Enter your post title">
		</div>
		<div class="mb-3">
			<label for="genre" class="form-label">Genre</label> <input
				type="text" class="form-control" id="genre" name="genre"
				  
 placeholder="Enter the genre of your post">
		</div>
		<div class="mb-3">
			<label for="content" class="form-label">Content</label>
			<textarea class="form-control" id="content" name="content"
				  
 rows="5" placeholder="Write your post content here"></textarea>
		</div>
		<div class="mb-3">
			<label for="image" class="form-label">Image URL (optional)</label> <input
				type="url" class="form-control" id="image" name="image"
				placeholder="Enter the URL of your post image">
		</div>
		<div class="mb-3">
			<label for="tag" class="form-label">Tags (comma-separated)</label> <input
				type="text" class="form-control" id="tag" name="tag"
				placeholder="Enter tags for your post">
		</div>
		<input type="hidden" name="userid" id="userid"
			value="<%=session.getAttribute("userid")%>">
		<button type="submit" class="btn btn-primary">Create Post</button>
	</form>
</div>