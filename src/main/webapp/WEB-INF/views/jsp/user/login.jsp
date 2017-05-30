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
    <spring:url value="/resources/core/js/SO_loginPage_Scripts.js" var="loginScripts"/>
    <spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs"/>
    <script src="${jquery}"></script>
    <script src="${bootstrapJs}"></script>

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
    <div id="login_forgetPassword_dialogue">
        <span class="mail_reset_dialogue_close_span" onclick="forgetDialogue('hide')">❌</span>
        <label id="mail_reset_label" for="mail_reset_input">Please enter your email</label>
        <input id="mail_reset_input" type="email" placeholder="example@lgcns.uz">
        <button id="mail_reset_submit_button" type="button" class="btn btn-block login_forgetPassword_send_button" onclick="passwordRestartMailSend()">Send</button>
    </div>

    <form id="login_main_form" action="<c:url value='/j_spring_security_check' />" method='POST'>
        <input type="text" name="username" placeholder="Username" required="required"/>
        <input type="password" name="password" placeholder="Password" required="required"/>
        <div class="checkbox"></div>
        <button id="loginButton" type="submit" class="btn btn-block lgButton pulse">Sign in</button>
        <span class="login_forgetPasswordSpan" onclick="forgetDialogue()">Forget Password?</span>
    </form>
</div>
</body>
<script src="${loginScripts}"></script>
</html>
