<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 28-Oct-16
  Time: 4:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Login</title>
    <spring:url value="/resources/core/css/logincss.css" var="loginCss"></spring:url>
    <link rel="stylesheet" href="${loginCss}"/>
</head>
<body>
<div class="login">

    <h1>LG CNS UZBEKISTAN</h1>
    <h2>Login</h2>
    <form action="<c:url value='/j_spring_security_check' />" method='POST'>

        <input type="text" name="username" placeholder="Username" required="required" />
        <input type="password" name="password" placeholder="Password" required="required" />
        <div class="checkbox">
            <label>
                <input type="checkbox"> Remember me
            </label>
        </div>
        <button type="submit" class="btn btn-primary btn-block btn-large">Sign in</button>
    </form>
    <br>
    <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
    <!-- login bootsnipp -->
    <ins class="adsbygoogle"
         style="display:block"
         data-ad-client="ca-pub-9155049400353686"
         data-ad-slot="9589048256"
         data-ad-format="auto"></ins>
    <script>
        (adsbygoogle = window.adsbygoogle || []).push({});
    </script>
</div>
</body>
</html>
