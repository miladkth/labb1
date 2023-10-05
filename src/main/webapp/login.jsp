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
<body><form action="/login" method="post">

    <input type="email" name="email" placeholder="Email">
    <input type="password" name="password" placeholder="Password">

    <p>${error}</p>
    <button type="submit">Login</button>
</form>
</body>
</html>