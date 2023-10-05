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
<body><form action="/admin/newProduct" method="post">

  <input type="text" name="title" placeholder="Title">
  <input type="text" name="description" placeholder="Description">
  <input type="text" name="price" placeholder="Price">
  <input type="text" name="quantity" placeholder="Quantity">
  <input type="text" name="categories" placeholder="Category">
  <input type="file" name="image" placeholder="Image">
  <p>${error}</p>
  <button type="submit">Create</button>
</form>
</body>
</html>