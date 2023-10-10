<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body>

<div class="flex items-center justify-between p-6 fixed w-screen top-0 left-0">
    <a href="/products">
        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRH7xrHqkvWlpD_z1VlB0HBSMz97qv8XTcHUO7ITp6yIijH5vvULsBd7Hehi7QOCXneRAg&usqp=CAU" class="w-20"/>
    </a>
    <c:if test = "${user!=null && user.role eq 'admin'}">
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
    </c:if>
</div>

    <div class="flex items-center justify-center h-screen">
        <form action="/user/register" method="post" class="flex flex-col w-96">
            <h2 class="text-4xl font-bold mb-16">Register</h2>
            <p class="mb-5 text-green-500">${info}</p>
            <input type="email" name="email" placeholder="Email" class="outline-none h-10 border-b-2 border-black text-lg"/>
            <input type="text" name="name" placeholder="Name" class="outline-none h-10 border-b-2 border-black  mt-6 text-lg" />
            <c:if test = "${user!=null && user.role eq 'admin'}">
                <input type="text" name="role" placeholder="Role(customer, warehouse, admin)" class="outline-none h-10 border-b-2 border-black  mt-6 text-lg" />
            </c:if>
            <input type="password" name="password" placeholder="Password" class="outline-none h-10 border-b-2 border-black  mt-6 text-lg"/>
            <input type="password" name="confirmpassword" placeholder="Confirm Password" class="outline-none h-10 border-b-2 border-black  mt-6 text-lg"/>
            <p class="text-red-500 mt-7">${error}</p>
            <button type="submit" class="bg-black text-white w-40 h-12 rounded-full text-lg mt-12">Register</button>
            <c:if test = "${user!=null && user.role eq 'admin'}">
                <a href="/products" class="mt-6 cursor-pointer">Back</a>
            </c:if>
            <c:if test = "${user==null}">
                <a href="/user/login" class="mt-6 cursor-pointer">Login with existing account</a>
            </c:if>
        </form>
    </div>
</body>
</html>