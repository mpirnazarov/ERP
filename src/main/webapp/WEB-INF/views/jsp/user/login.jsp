<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 28-Oct-16
  Time: 4:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Sign in</title>
    <link rel="icon" type="image/x-icon" href="<s:url value="/resources/images/SmartOffice-Logo.png"/>"/>
    <spring:url value="/resources/core/css/logincss.css" var="loginCss"></spring:url>
    <link rel="stylesheet" href="${loginCss}"/>

    <spring:url value="/resources/core/js/jquery.min.js" var="jquery"/>
    <script src="${jquery}"></script>

</head>
<body>
<div class="login">

    <div id="loginPageLogo">
        <img src="/resources/images/SmartOffice-Logo.png">
    </div>

    <h1 class="inset-text">Smart Office</h1>
    <h2 class="inset-text-red">LG CNS Uzbekistan</h2>

    <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
        <font color="red">
            <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
        </font>
    </c:if>
    <form action="<c:url value='/j_spring_security_check' />" method='POST'>
        <input type="text" name="username" placeholder="Username" required="required"/>
        <input type="password" name="password" placeholder="Password" required="required"/>
        <div class="checkbox">

        </div>
        <button id="loginButton" type="submit" class="btn btn-block lgButton pulse">Sign in</button>
    </form>


</div>
</body>
<script>
    $('#loginButton').hover(function () {
        $('#loginPageLogo').toggleClass("buttonRotate");
    })

</script>

</html>
