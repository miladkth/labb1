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
  <title>New product</title>
  <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
  <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
<div class="flex items-center justify-between p-6 fixed w-screen top-0 left-0">
  <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRH7xrHqkvWlpD_z1VlB0HBSMz97qv8XTcHUO7ITp6yIijH5vvULsBd7Hehi7QOCXneRAg&usqp=CAU" class="w-20"/>
  <div class="flex items-center">
    <a href="/admin/newProduct" class="mr-4 h-9 items-center rounded-full border border-solid border-black bg-black px-6 py-1 text-white">New Product</a>
    <a href="/user/logout"><img src="https://cdn-icons-png.flaticon.com/512/25/25706.png" class="h-6 w-6" /></a>
  </div></div>
  <div class="flex items-center justify-center min-h-screen">
    <form action="/admin/newProduct" method="post" enctype="multipart/form-data" class="flex flex-col w-96">
      <h2 class="text-4xl font-bold mb-16">New product</h2>
      <p class="text-green-500 mb-4">${info}</p>
      <input type="text" name="title" placeholder="Title" class="outline-none h-10 border-b-2 border-black text-lg">
      <input type="text" name="description" placeholder="Description" class="outline-none h-10 border-b-2 border-black mt-6 text-lg">
      <input type="text" name="price" placeholder="Price" class="outline-none h-10 border-b-2 border-black mt-6 text-lg">
      <input type="text" name="quantity" placeholder="Quantity" class="outline-none h-10 border-b-2 border-black mt-6 text-lg">
      <input type="text" name="categories" placeholder="Category" class="outline-none h-10 border-b-2 border-black mt-6 text-lg">
      <input type="file" name="image" class="block w-full text-sm text-black file:mr-4 file:py-2 file:px-4 file:rounded-md file:border-0 file:text-sm file:font-semibold file:bg-gray-200 file:text-black hover:file:bg-gray-400 mt-10" />
      <p class="text-red-500 mt-7">${error}</p>
      <div class="flex items-center w-full justify-between mt-12">
        <a href="/products" class="cursor-pointer">Back</a>
        <button type="submit" class="bg-black text-white w-40 h-12 rounded-full text-lg">Create</button>
      </div>
    </form>
  </div>
</body>
</html>