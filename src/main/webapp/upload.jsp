<%--
  Created by IntelliJ IDEA.
  User: taquang
  Date: 2023-10-04
  Time: 22:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<img src="https://tquangsbucket.s3.eu-north-1.amazonaws.com/a395d2fd-7341-465f-83e6-77bb320a3249discimg.jpeg" />
    <form action="new-product-upload" method="post" enctype="multipart/form-data">
        <input type="text" name="description" />
        <input type="file" name="file" />
        <button type="submit" >Send</button>
    </form>
</body>
</html>
