<%--
  Created by IntelliJ IDEA.
  User: taquang
  Date: 2023-10-05
  Time: 22:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
  <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body>
<div class="flex items-center justify-between p-6 fixed w-screen top-0 left-0 bg-white">
  <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRH7xrHqkvWlpD_z1VlB0HBSMz97qv8XTcHUO7ITp6yIijH5vvULsBd7Hehi7QOCXneRAg&usqp=CAU" class="w-20"/>
  <a data-bs-toggle="offcanvas" href="#offcanvasExample" role="button" aria-controls="offcanvasExample">
    <img src="https://i.pinimg.com/1200x/f2/12/4e/f2124e83e9fd8ddeb31ac7cdb59f544c.jpg" class="w-14"/>
  </a>
</div>

<div>

</div>

<div class="offcanvas offcanvas-end flex-col p-3" tabindex="-1" id="offcanvasExample" aria-labelledby="offcanvasExampleLabel">
  <div class="offcanvas-header mb-1 flex items-center justify-between px-3">
    <h5 class="offcanvas-title text-lg font-semibold" id="offcanvasExampleLabel">Cart</h5>
    <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
  </div>
  <hr class="border-1 border-black" />
  <div class="offcanvas-body flex flex-grow flex-col justify-between px-3">
    <div class="">
      <div class="mt-2 flex items-center">
        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcShoE-NaGC1h25wQXUgRIEjSmQf4Bff-hndu9nasRnaWF8N1zV82r2QSdLYSncqPzb1Ac4&usqp=CAU" class="w-24" />
        <div>
          <h4 class="font-semibold">A kids book about rasicm</h4>
          <p>2 x 34.99$</p>
          <a href="linkaaa" class="text-xs text-gray-500">Remove</a>
        </div>
      </div>
      <hr />
      <div class="mt-2 flex items-center">
        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcShoE-NaGC1h25wQXUgRIEjSmQf4Bff-hndu9nasRnaWF8N1zV82r2QSdLYSncqPzb1Ac4&usqp=CAU" class="w-24" />
        <div>
          <h4 class="font-semibold">A kids book about rasicm</h4>
          <p>2 x 34.99$</p>
          <a href="linkaaa" class="text-xs text-gray-500">Remove</a>
        </div>
      </div>
    </div>

    <div class="flex justify-end pt-2">
      <button class="rounded-full bg-black px-8 py-2 text-white">Checkout</button>
    </div>
  </div>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</html>
