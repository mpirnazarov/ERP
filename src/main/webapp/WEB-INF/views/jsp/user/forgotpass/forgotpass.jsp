<%--
  Created by IntelliJ IDEA.
  User: JAS SHAYKHOV
  Date: 11/10/2016
  Time: 10:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Reset password</title>
    <spring:url value="/resources/core/css/logincss.css" var="loginCss"></spring:url>
    <link rel="stylesheet" href="${loginCss}"/>
</head>
<body>
<div class="login">

    <h1>LG CNS UZBEKISTAN</h1>
    <h2>Forgot password</h2>
    <form action="<c:url value='/j_spring_security_check' />" method='POST'>

        <input type="text" name="username" placeholder="Username" required="required" />
        <button type="submit" class="btn btn-primary btn-block btn-large">Reset password</button>
    </form>
    <br>
</div>
</body>
</html>
