<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body>
<div class="relative">
    <div class="fixed left-0 top-0 flex w-screen items-center justify-between p-6">
        <a href="/products">
            <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRH7xrHqkvWlpD_z1VlB0HBSMz97qv8XTcHUO7ITp6yIijH5vvULsBd7Hehi7QOCXneRAg&usqp=CAU" class="w-20"/>
        </a>
    </div>

    <%
        Integer code = (Integer) request.getAttribute("code");
        String title = "";
        switch (code){
            case 400:
                title = "Bad request";
                break;
            case 403:
                title = "Forbiden";
                break;
            case 404:
                title = "Not Found";
                break;
            case 405:
                title = "Method Not Allowed";
                break;
            case 500:
                title = "Internal Server Error";
                break;
            default:
                title = "Some thing gone wrong !!!";
        }
    %>


    <div class="flex h-screen flex-col items-center justify-center">
        <h1 class="text-center text-9xl font-extrabold">${code}</h1>
        <p class="pt-9"><%= title %></p>
        <a href="/products" class="mt-5 flex h-12 w-44 items-center justify-center rounded-full border border-solid border-black bg-black px-7 py-3 text-white">Back to Shop</a>
    </div>
    <p class="absolute bottom-7 left-1/2 max-w-lg -translate-x-1/2 text-center font-thin text-gray-400">${message}</p>
</div>
</body>
<script src="https://cdn.tailwindcss.com"></script>
</html>
