<%@ page import="ui.DTOs.UserDTO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Products</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
  <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body>
<div style="z-index:999" class="flex items-center justify-between p-6 fixed w-screen top-0 left-0 bg-white index">
  <a href="/products">
    <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRH7xrHqkvWlpD_z1VlB0HBSMz97qv8XTcHUO7ITp6yIijH5vvULsBd7Hehi7QOCXneRAg&usqp=CAU" class="w-20"/>
  </a>

  <div class="flex items-center">

    <c:if test = "${user!=null && user.role eq 'admin'}">
      <a href="/warehouse/newProduct" class="border-solid border border-black rounded-full px-6 py-1 bg-black text-white mr-4 h-9 items-center">New Product</a>
      <a href="/warehouse/orders">
        <img src="https://cdn-icons-png.flaticon.com/512/3496/3496156.png" class="mr-4 h-6 w-6" />
      </a>

      <a href="/admin/users">
        <img src="https://cdn-icons-png.flaticon.com/512/78/78948.png" class="mr-4 h-6 w-6" />
      </a>
    </c:if>

    <c:if test = "${user!=null && user.role eq 'warehouse'}">
      <a href="/warehouse/newProduct" class="border-solid border border-black rounded-full px-6 py-1 bg-black text-white mr-4 h-9 items-center">New Product</a>
      <a href="/warehouse/orders">
        <img src="https://cdn-icons-png.flaticon.com/512/3496/3496156.png" class="mr-4 h-6 w-6" />
      </a>
    </c:if>

    <c:if test = "${user!=null && user.role eq 'customer'}">
      <a data-bs-toggle="offcanvas" href="#offcanvasExample" aria-controls="offcanvasExample">
        <img src="https://i.pinimg.com/1200x/f2/12/4e/f2124e83e9fd8ddeb31ac7cdb59f544c.jpg" class="w-14 mr-4 "/>
      </a>
    </c:if>


    <a href="/user/logout"><img src="https://cdn-icons-png.flaticon.com/512/25/25706.png" class="h-6 w-6" /></a>
  </div>
</div>



  <div class="max-w-4xl m-auto pt-56 pb-32">
    <h1 class="font-extrabold text-center text-6xl">Shop children's books for all<br/>
      topics.</h1>
    <p class="text-center text-gray-800 mt-14 text-xl">
      From board books for toddlers to hardback books for elementary students, these must-<br/>
      read books explore important topics that are relevant to our current times. Some cover<br/>
      difficult issues such as racism, money, and death and others touch on personal growth<br/>
      topics such as leadership, confidence, and grit. Find the right book for your budding<br/>
      bookworm with these parent-approved and kid-tested titles.
    </p>
    <div class="flex w-full justify-end mt-12">
      <span class="flex text-lg">
        <p class="font-bold">Category: </p>
        <p class="mx-2">Show All</p>
        <div class="dropdown-center flex">
          <button class="dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false"></button>
          <ul class="dropdown-menu">
              <li><a class="dropdown-item" href="/products">Show All</a></li>
            <c:forEach items="${categories}" var="category">
              <li><a class="dropdown-item" href="/products?category=${category}">${category}</a></li>
            </c:forEach>
          </ul>
      </div>
      </span>

    </div>
    <div class="grid grid-cols-3">
      <c:forEach items="${products}" var="product">
        <div class="flex flex-col w-48 items-center mt-20 mx-auto">
          <img src="${product.imgUrl}"/>
          <c:if test = "${product.isShown}">
            <p class="text-center font-bold leading-5 mt-5 w-36">${product.title}</p>
          </c:if>
          <c:if test = "${!product.isShown}">
            <p class="text-center font-bold leading-5 mt-5 w-36 line-through">${product.title}</p>
          </c:if>
          <p class="font-medium mt-2 text-gray-600">${product.price}$</p>
          <c:if test = "${user!=null && (user.role eq 'admin' || user.role eq 'warehouse')}">
            <a href="/warehouse/editProduct/${product.id}" class="bg-white border-solid border border-black rounded-full px-9 py-1 mt-4 cursor-pointer">Edit</a>
          </c:if>
          <c:if test = "${user!=null && user.role eq 'customer'}">
            <a href="/cart/addToCart/${product.id}" class="bg-white border-solid border border-black rounded-full px-9 py-1 mt-4 cursor-pointer">
              <c:if test = "${product.quantity == 0}">
                OoS
              </c:if>
              <c:if test = "${product.quantity > 0}">
                Buy
              </c:if>
            </a>
          </c:if>
        </div>
      </c:forEach>
    </div>
  </div>

  <div class="offcanvas offcanvas-end flex-col p-3" tabindex="-1" id="offcanvasExample" aria-labelledby="offcanvasExampleLabel">
  <div class="offcanvas-header mb-1 flex items-center justify-between px-3">
    <h5 class="offcanvas-title text-lg font-semibold" id="offcanvasExampleLabel">Cart</h5>
    <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
  </div>
  <hr class="border-1 border-black" />
  <div class="offcanvas-body flex flex-grow flex-col justify-between px-3">
    <div class="">
      <c:if test = "${empty(cart)}">
        <p class="mt-6 text-center">Cart empty</p>
      </c:if>
      <c:forEach items="${cart}" var="item">
        <div class="mt-2 flex items-center">
          <img src="${item.imgUrl}" class="w-24" />
          <div>
            <h4 class="font-semibold">${item.title}</h4>
            <p>${item.quantity} x ${item.price}$</p>
            <a href="/cart/remove/${item.id}" class="text-xs text-gray-500">Remove</a>
          </div>
        </div>
        <hr />
      </c:forEach>
    </div>
    <div class="flex justify-end pt-2">
      <a href="/checkout"><button class="rounded-full bg-black px-8 py-2 text-white">Checkout</button></a>
    </div>
  </div>
</div>
</body>
<script src="https://cdn.tailwindcss.com"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</html>
