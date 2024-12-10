<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Uploaded Files</title>
</head>
<body>
    <h2>Uploaded Files</h2>
    <c:if test="${not empty files}">
        <ul>
            <c:forEach var="file" items="${files}">
                <li>
                    <a href="${pageContext.request.contextPath}/uploads/${file.name}" target="_blank">${file.name}</a>
                </li>
            </c:forEach>
        </ul>
    </c:if>
    <c:if test="${empty files}">
        <p>No files found in the upload directory.</p>
    </c:if>
</body>
</html>
