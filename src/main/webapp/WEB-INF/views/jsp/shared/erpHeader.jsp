<!--
  Created by IntelliJ IDEA.
  User: Rafatdin
  Date: 11.10.2016
  Time: 17:52
  To change this template use File | Settings | File Templates.
-->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>${pageTitle} | ERP System</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <spring:url value="/resources/core/css/Login.css" var="loginCss" />
        <spring:url value="/resources/core/css/bootstrap.css" var="bootstrapCss" />
        <spring:url value="/resources/core/css/Site.css" var="siteCss" />
        <spring:url value="/resources/core/js/jquery-1.12.4.min.js" var="jquery" />
        <spring:url value="/resources/core/js/notify.min.js" var="notifyMinJs" />
        <spring:url value="/resources/core/js/bootstrap.js" var="bootstrapJs" />
        <spring:url value="/resources/core/js/respond.js" var="respondJs" />
        <link rel="icon" href="/resources/images/lg-2-multi-size.ico" type="image/x-icon">
        <script src="${jquery}"></script>
        <script src="${notifyMinJs}"></script>
        <script src="${bootstrapJs}"></script>
        <script src="${respondJs}"></script>
        <link href="${loginCss}" rel="stylesheet" />
        <link href="${bootstrapCss}" rel="stylesheet" />
        <link href="${siteCss}" rel="stylesheet" />
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="/resources/demos/style.css">
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script>
            $( function() {
                $( "#tabs" ).tabs();
            } );
        </script>
    </head>

    <body>

