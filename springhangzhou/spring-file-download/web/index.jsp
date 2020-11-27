<%--
  Created by IntelliJ IDEA.
  User: javen
  Date: 2020/11/4
  Time: 6:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>文件上传测试</title>
  </head>
  <body>
  <form enctype="multipart/form-data" action="${pageContext.request.contextPath}/file/upload" method="post">
    <p>文件描述：<input type="text" name="desc"/></p>
    <p><input type="file" name="multipartFile" multiple="multiple"/></p>
    <p><input type="submit" value="提交"/></p>
  </form>
  </body>
</html>
