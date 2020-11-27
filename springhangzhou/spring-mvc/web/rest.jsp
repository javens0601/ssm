<%--
  Created by IntelliJ IDEA.
  User: javen
  Date: 2020/10/25
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rest Style</title>
</head>
<body>

<form action="/rest/user/1" method="get">
    <input type="submit" value="查询">
</form>

<form action="/rest/user/1" method="post">
    <input type="submit" value="增加">
</form>

<form action="/rest/user/1" method="post">
    <input type="hidden" value="put" name="_method">
    <input type="submit" value="修改">
</form>

<form action="/rest/user/1" method="post">
    <input type="hidden" value="delete" name="_method">
    <input type="submit" value="删除">
</form>

<img src="${pageContext.request.contextPath}/images/jinweib.jpg">
</body>
</html>
