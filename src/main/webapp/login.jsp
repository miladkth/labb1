<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body>
<div class="flex items-center justify-between p-6 fixed w-screen top-0 left-0">
    <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRH7xrHqkvWlpD_z1VlB0HBSMz97qv8XTcHUO7ITp6yIijH5vvULsBd7Hehi7QOCXneRAg&usqp=CAU" class="w-20"/>
</div>
    <div class="flex items-center justify-center h-screen">
        <form action="/user/login" method="post" class="flex flex-col w-96">
            <h2 class="text-4xl font-bold mb-16">Login</h2>
            <input type="email" name="email" placeholder="Email" class="outline-none h-10 border-b-2 border-black text-lg"/>
            <input type="password" name="password" placeholder="Password" class="outline-none h-10 border-b-2 border-black mt-6 text-lg"/>
            <p class="text-red-500 mt-7">${error}</p>
            <button type="submit" class="bg-black text-white w-40 h-12 rounded-full text-lg mt-12">Login</button>
            <a href="/user/register" class=" mt-6 cursor-pointer">Register new account</a>
        </form>
    </div>
</body>
</html>