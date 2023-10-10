<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Management</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>

<div class="flex items-center justify-between p-6 fixed w-screen top-0 left-0 bg-white">
    <a href="/products">
        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRH7xrHqkvWlpD_z1VlB0HBSMz97qv8XTcHUO7ITp6yIijH5vvULsBd7Hehi7QOCXneRAg&usqp=CAU" class="w-20"/>
    </a>
    <div class="flex items-center">
        <a href="/warehouse/newProduct" class="border-solid border border-black rounded-full px-6 py-1 bg-black text-white mr-4 h-9 items-center">New Product</a>
        <a href="/warehouse/orders">
            <img src="https://cdn-icons-png.flaticon.com/512/3496/3496156.png" class="mr-4 h-6 w-6" />
        </a>
        <a href="/admin/users">
            <img src="https://cdn-icons-png.flaticon.com/512/78/78948.png" class="mr-4 h-6 w-6" />
        </a>
        <a href="/user/logout"><img src="https://cdn-icons-png.flaticon.com/512/25/25706.png" class="h-6 w-6" /></a>
    </div>
</div>





<div style="max-width:900px" class="mx-auto mt-56">
    <div class="flex justify-between pb-24">
        <h1 class="text-3xl font-bold">User Management</h1>
        <a href="/user/register">
            <img class="h-6 w-6" src="https://cdn-icons-png.flaticon.com/512/283/283234.png" alt="" />
        </a>
    </div>
<c:forEach items="${users}" var="u">
    <div class="pb-6">
        <div class="mb-3 flex justify-between">
            <c:if test = "${!u.isActive}">
                <p class="text-base font-bold line-through">${u.id} <span class="font-medium">(blocked)</span></p>
            </c:if>
            <c:if test = "${u.isActive}">
                <p class="text-base font-bold">${u.id}</p>
            </c:if>
            <c:if test = "${!u.isActive}">
            <img class="w-6" src="https://cdn-icons-png.flaticon.com/512/507/507210.png" />
            </c:if>
        </div>
        <p class="text-base"><span class="font-semibold">Email: </span> ${u.email}</p>
        <p class="text-base"><span class="font-semibold">Username: </span>${u.userName}</p>

        <div class="flex items-center">
            <p class="mr-3 text-base"><span class="font-semibold">Role: </span>${u.role}</p>
            <button type="button" data-bs-toggle="modal" data-bs-target="#a${u.id}">
                <img class="w-3" src="https://cdn-icons-png.flaticon.com/512/1659/1659764.png" />
            </button>
        </div>
        <c:if test = "${u.isActive}">
            <a class="cursor-pointer text-gray-500 hover:underline" href="/admin/users/block/${u.id}">Block user</a>
        </c:if>
        <c:if test = "${!u.isActive}">
            <a class="cursor-pointer text-gray-500 hover:underline" href="/admin/users/unblock/${u.id}">Unlock user</a>
        </c:if>
        <hr />
    </div>
    <!-- Button trigger modal (Description) -->
    <div class="modal fade" id="a${u.id}" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content flex items-center justify-center">
                <button type="button" class="btn-close absolute right-2 top-2" data-bs-dismiss="modal" aria-label="Close"></button>
                <div class="flex flex-col items-end mx-5 mt-6 mb-8">
                    <form action="/admin/user/role/${u.id}" method="post">
                        <h2 class="w-full text-2xl font-semibold mb-6">New role for ${u.id}</h2>
                        <input type="text" type="text" name="role" placeholder="Role(admin, warehouse, customer)" class="outline-none h-10 border-b-2 border-black text-lg w-72 mt-5"/>
                        <button type="submit" class="bg-black text-white w-32 h-10 rounded-full text-lg mt-12">Save</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</c:forEach>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>
