<%--
  Created by IntelliJ IDEA.
  User: beka
  Date: 23.04.23
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="blogPost" method="post">
    <div>
        <label> First name </label>
        <input type="text" name="first_name"/>
    </div>
    <div>
        <label> Last Name </label>
        <input type="text" name="last_name"/>
    </div>
    <div>
        <label> Title </label>
        <input type="text" name="title"/>
    </div>
    <div>
        <label> Full text </label>
        <textarea name="body"></textarea>
    </div>
    <div>
        <label></label>
        <input type="text" name="image_link" placeholder="https://example.com/example.png"/>
    </div>
    <div>
        <input type="submit">
    </div>
</form>
</body>
</html>
