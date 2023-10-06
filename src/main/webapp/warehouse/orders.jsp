<%--
  Created by IntelliJ IDEA.
  User: milad
  Date: 2023-10-06
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div class="mt-22 pt-40">
<c:forEach items="${orders}" var="order">
    <p>Id: ${order.id}</p>
    <p>UserId: ${order.userId}</p>
    <p>FullFilled: ${order.fullFilled}</p>
    <p>shippingAddress: ${order.shippingAddress}</p>
    <p>Categories:
    <c:forEach items="${order.products}" var="product">
        ${product.title},
    </c:forEach>
    </p>
    </c:forEach>
</div>

</body>
</html>
