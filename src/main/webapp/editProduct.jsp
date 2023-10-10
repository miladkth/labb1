<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Edit product</title>
  <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body class="w-screen h-screen flex items-center">
<div class="flex items-center justify-between p-6 fixed w-screen top-0 left-0">
  <a href="/products">
    <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRH7xrHqkvWlpD_z1VlB0HBSMz97qv8XTcHUO7ITp6yIijH5vvULsBd7Hehi7QOCXneRAg&usqp=CAU" class="w-20"/>
  </a>
  <div class="flex items-center">
    <a href="/warehouse/newProduct" class="border-solid border border-black rounded-full px-6 py-1 bg-black text-white mr-4 h-9 items-center">New Product</a>
    <a href="/warehouse/orders">
      <img src="https://cdn-icons-png.flaticon.com/512/3496/3496156.png" class="mr-4 h-6 w-6" />
    </a>

    <c:if test = "${user!=null && user.role eq 'admin'}">
      <a href="/admin/users">
        <img src="https://cdn-icons-png.flaticon.com/512/78/78948.png" class="mr-4 h-6 w-6" />
      </a>
    </c:if>

    <a href="/user/logout"><img src="https://cdn-icons-png.flaticon.com/512/25/25706.png" class="h-6 w-6" /></a>
  </div>
</div>
<div style="width:800px;" class="m-auto">
  <h1 class="text-4xl font-semibold">Edit Product</h1>
  <div class="mt-12">
    <div class="flex justify-between">
      <c:if test = "${product.isShown}">
        <p class="text-lg font-semibold">${product.id}</p>
        <form action="/warehouse/editProduct/${product.id}" method="post">
          <input type="hidden" name="field" value="isShown"/>
          <input type="hidden" name="newValue" value="false" class="outline-none h-10 border-b-2 border-black text-lg w-72 mt-5"/>
          <button type="submit">
            <img class="h-6 w-6" src="https://cdn-icons-png.flaticon.com/512/5857/5857262.png" alt="" />
          </button>
        </form>
      </c:if>
      <c:if test = "${!product.isShown}">
        <p class="text-lg font-semibold line-through">${product.id}</p>
        <form action="/warehouse/editProduct/${product.id}" method="post">
          <input type="hidden" name="field" value="isShown"/>
          <input type="hidden" name="newValue" value="true" class="outline-none h-10 border-b-2 border-black text-lg w-72 mt-5"/>
          <button type="submit" >
            <img class="h-6 w-6" src="https://cdn-icons-png.flaticon.com/512/121/121094.png" alt="" />
          </button>
        </form>
      </c:if>
    </div>
    <ul class="list-disc pl-28">
      <div class="flex items-center justify-between">
        <li class="mt-2 text-lg"><span class="font-semibold">Title:</span> ${product.title}</li>
        <button type="button" data-bs-toggle="modal" data-bs-target="#changeTitle">
          <a><img class="w-6" src="https://static.thenounproject.com/png/1267569-200.png" /></a>
        </button>
      </div>
      <div class="flex items-center justify-between">
        <li class="mt-2 text-lg"><span class="font-semibold">Description:</span> ${product.description}</li>
        <button type="button" data-bs-toggle="modal" data-bs-target="#changeDescription">
          <img class="w-6" src="https://static.thenounproject.com/png/1267569-200.png" />
        </button>
      </div>
      <div class="flex items-center justify-between">
        <li class="mt-2 text-lg"><span class="font-semibold">Price:</span> ${product.price}$</li>
        <button type="button" data-bs-toggle="modal" data-bs-target="#changePrice">
        <a><img class="w-6" src="https://static.thenounproject.com/png/1267569-200.png" /></a>
        </button>
      </div>
      <div class="flex items-center justify-between">
        <li class="mt-2 text-lg"><span class="font-semibold">Quantity:</span> ${product.quantity}</li>
        <button type="button" data-bs-toggle="modal" data-bs-target="#changeQuantity">
          <a><img class="w-6" src="https://static.thenounproject.com/png/1267569-200.png" /></a>
        </button>
      </div>
      <div class="flex items-center justify-between">
        <li class="mt-2 text-lg"><span class="font-semibold">Categories:</span></li>
        <a><img class="w-6" src="https://static.thenounproject.com/png/1267569-200.png" /></a>
      </div>
      <div class="mt-3 flex">
      <c:forEach items="${product.categories}" var="category">
        <form method="post" action="/warehouse/editProduct/${product.id}">
          <input type="hidden" name="field" value="categories">
          <input type="hidden" name="newValue" value="${category}"/>
          <button type="submit" class="items-center rounded-full bg-black px-4 py-1 text-white mr-2">${category}<img class="ml-3 inline-block w-2 -translate-y-0.5" src="https://flaticons.net/icon.php?slug_category=mobile-application&slug_icon=close" /></button>
        </form>
      </c:forEach>
      </div>
      <form class="mt-6" method="post" action="/warehouse/editProduct/${product.id}">
        <input type="hidden" name="field" value="categories">
        <input type="text" name="newValue" placeholder="New category" class="rounded-full border border-black px-2 py-1 outline-none" />
        <button type="submit" class="rounded-full bg-black px-5 py-2 text-white">Add</button>
      </form>
    </ul>
  </div>
  <a href="/products" class="mt-6 text-black">Back</a>
</div>


<!-- Button trigger modal (Description) -->
<div class="modal fade" id="changeQuantity" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content flex items-center justify-center">
      <button type="button" class="btn-close absolute right-2 top-2" data-bs-dismiss="modal" aria-label="Close"></button>
      <div class="flex flex-col items-end mx-5 mt-6 mb-8">
        <form action="/warehouse/editProduct/${product.id}" method="post">
          <h2 class="w-full text-2xl font-semibold mb-6">New quantity</h2>
          <input type="hidden" name="field" value="quantity"/>
          <input type="text" name="newValue" value="${product.quantity}" placeholder="Quantity" class="outline-none h-10 border-b-2 border-black text-lg w-72 mt-5"/>
          <button type="submit" class="bg-black text-white w-32 h-10 rounded-full text-lg mt-12">Save</button>
        </form>
      </div>
    </div>
  </div>
</div>

<!-- Button trigger modal (Description) -->
<div class="modal fade" id="changePrice" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content flex items-center justify-center">
      <button type="button" class="btn-close absolute right-2 top-2" data-bs-dismiss="modal" aria-label="Close"></button>
      <div class="flex flex-col items-end mx-5 mt-6 mb-8">
        <form action="/warehouse/editProduct/${product.id}" method="post">
          <h2 class="w-full text-2xl font-semibold mb-6">New price</h2>
          <input type="hidden" name="field" value="price"/>
          <input type="text" name="newValue" value="${product.price}" placeholder="Price" class="outline-none h-10 border-b-2 border-black text-lg w-72 mt-5"/>
          <button type="submit" class="bg-black text-white w-32 h-10 rounded-full text-lg mt-12">Save</button>
        </form>
      </div>
    </div>
  </div>
</div>

<!-- Button trigger modal (title) -->
<div class="modal fade" id="changeTitle" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content flex items-center justify-center">
      <button type="button" class="btn-close absolute right-2 top-2" data-bs-dismiss="modal" aria-label="Close"></button>
      <div class="flex flex-col items-end mx-5 mt-6 mb-8">
        <form action="/warehouse/editProduct/${product.id}" method="post">
          <h2 class="w-full text-2xl font-semibold mb-6">New title</h2>
          <input type="hidden" name="field" value="title"/>
          <input type="text" name="newValue" value="${product.title}" placeholder="Title" class="outline-none h-10 border-b-2 border-black text-lg w-72 mt-5"/>
          <button type="submit" class="bg-black text-white w-32 h-10 rounded-full text-lg mt-12">Save</button>
        </form>
      </div>
    </div>
  </div>
</div>

<!-- Button trigger modal (Description) -->
<div class="modal fade" id="changeDescription" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content flex items-center justify-center">
      <button type="button" class="btn-close absolute right-2 top-2" data-bs-dismiss="modal" aria-label="Close"></button>
      <div class="flex flex-col items-end mx-5 mt-6 mb-8">
        <form action="/warehouse/editProduct/${product.id}" method="post">
          <h2 class="w-full text-2xl font-semibold mb-6">New description</h2>
          <input type="hidden" name="field" value="description"/>
          <input type="text" name="newValue" value="${product.description}" placeholder="Description" class="outline-none h-10 border-b-2 border-black text-lg w-72 mt-5"/>
          <button type="submit" class="bg-black text-white w-32 h-10 rounded-full text-lg mt-12">Save</button>
        </form>
      </div>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>
