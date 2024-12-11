<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="post-section">
    <div class="container">
        <div class="row">
            <c:forEach items="${posts}" var="post">
                <div class="col-12 mb-4">
                    <h3 class="post-title">${post.title}</h3>
                    <p class="post-author">By ${post.author} on ${post.createdat}</p>
                    <p class="post-content">${post.content}</p>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
