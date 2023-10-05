<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: taquang
  Date: 2023-10-04
  Time: 12:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>items</title>
</head>
<body>
<ul>
<c:forEach items="${items}" var="item">
    <li>${item.id}, ${item.title}, ${item.price}</li>
    <p>
    <c:forEach items="${item.categories}" var="cat">
        ${cat},
    </c:forEach>
    </p>
</c:forEach>
</ul>
</body>
</html>
