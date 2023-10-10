<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>

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

<div style="max-width:850px"  class=" m-auto pt-52">
    <h1 class="text-5xl font-bold mb-16 ">Orders:</h1>

<c:forEach items="${orders}" var="order">
    <div class="border-b py-3">
        <div class="flex  items-center justify-between" data-bs-toggle="collapse" href="#${order.id}" role="button" aria-expanded="false" aria-controls="collapseExample">
            <p class="text-lg font-bold mb-0 pb-0">${order.id}</p>
            <div class="flex items-center">
                <c:if test = "${order.fullFilled}">
                    <img class="w-7 h-7 mr-9" src="https://cdn-icons-png.flaticon.com/512/219/219311.png" />
                </c:if>
                <img class="w-4 h-4" src="https://cdn-icons-png.flaticon.com/512/25/25623.png">
            </div>
        </div>
        <div class="text-gray-500 collapse pt-8" id="${order.id}">
        <c:forEach items="${order.products}" var="product">
            <ul class="list-disc px-28">
                <div class="flex justify-between">
                    <li>${product.title}</li>
                    <span>${product.quantity}pcs</span>
                </div>
            </ul>
        </c:forEach>
            <form action="/warehouse/order/${order.id}" method="post" class="flex justify-end mb-4 mt-7 mr-20">
                <input type="hidden" name="action" value="fulfill"/>
                <button type="submit" class="bg-black px-4 py-2 rounded-full text-white">
                    <c:if test = "${order.fullFilled}">
                        Undo
                    </c:if>
                    <c:if test = "${!order.fullFilled}">
                        Fulfill order
                    </c:if>
                </button>
            </form>
        </div>
    </div>
</c:forEach>


</div>


</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</html>
