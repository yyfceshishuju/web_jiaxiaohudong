<%--
  Created by IntelliJ IDEA.
  User: yyf
  Date: 2018/10/25
  Time: 下午2:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>test upload image</title>
</head>

<body>

<form action="/uploadImage" method="post" enctype="multipart/form-data">
    <input type="file" name="file" id="file">
    <input type="submit" value="test">
</form>
</body>

</html>

