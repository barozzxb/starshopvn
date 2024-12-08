<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/my-styles.css">

<div class="container mt-4">
    <h2>Genres</h2>
    <div class="list-group-item">
        <c:forEach var="genre" items="${listgenre}">
            <a href="${pageContext.request.contextPath}/user/genres/products?gid=${genre.gid}" class="list-group-item list-group-item-action">
                ${genre.gname} 
            </a>
        </c:forEach>
    </div>
</div>
