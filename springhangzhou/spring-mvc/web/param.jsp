<%--
  Created by IntelliJ IDEA.
  User: javen
  Date: 2020/10/25
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试参数传入</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/hello">
    姓名：<input name="username" type="text">
    <p></p>
    <input name="提交" type="submit">
</form>
<br/>

<form method="post" action="${pageContext.request.contextPath}/hello2">
    姓名：<input name="name" type="text">
    <p></p>
    年龄：<input name="age" type="text">
    <p></p>
    <input name="提交" type="submit">
</form>

</body>
</html>
