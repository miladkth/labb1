<%--
  Created by IntelliJ IDEA.
  User: taquang
  Date: 2023-09-30
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Help</title>
</head>
<body>
<!-- Expresstion tag, oneline expresstion + return -->
    <p>The max of 1 and 3 is <%= Math.max(1,3) %></p>
<h1>{}</h1>

<%-- Scriptlet tag, (no function) --%>
<%
    int a = 1;
    int b = 3;
    int max = Math.max(a, b);
%>
<p>The max of 1 and 3 is <%= max %></p>

<ul>
    <% for(int i = 0; i < 10; i++) {%>
        <li>#<%= i %></li>
    <%}%>
</ul>
<%-- Declaration tag --%>
<%!
    int max_calc (int num1, int num2) {
       return Math.max(num1, num2);
    };

    int a = 1;
    int b = 2;
    int max1 = max_calc(a, b);
%>

<%= a %>
<%= b %>
<%= max1 %>
<%= max %>

<%@include file="footer.jsp"%>



</body>
</html>
