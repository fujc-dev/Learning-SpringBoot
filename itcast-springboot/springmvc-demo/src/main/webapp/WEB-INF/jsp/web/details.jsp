<%--
  Created by IntelliJ IDEA.
  User: fjc.dane@gmail.com
  Date: 2021/1/4
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SpringMVC + JSP + Amaze UI</title>
</head>
<body>
<!-- 牛逼，直接都能找到user -->
<!-- ModelAndView.addObject 可以自定义key -->
<h1>${user.id}</h1>
<h1>${user.username}</h1>
<h1>${user.note}</h1>
<h1>${user.sex}</h1>
</body>
</html>
