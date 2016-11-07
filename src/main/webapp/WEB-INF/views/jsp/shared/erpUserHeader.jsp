<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 25-Oct-16
  Time: 12:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${pageTitle} | ERP System</title>
    <spring:url value="/resources/core/css/normalize.css" var="normalizeCss" />
    <spring:url value="/resources/core/css/style.css" var="styleCss" />
    <spring:url value="/resources/core/css/bootstrap.css" var="bootstrapCss" />
    <spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapminCss" />
    <spring:url value="/resources/core/js/jquery-1.12.4.min.js" var="jquery" />
    <spring:url value="/resources/core/js/jquery.validate.js" var="jqueryValidation" />
    <link rel="icon" href="/resources/images/lg-2-multi-size.ico" type="image/x-icon">
    <link rel="stylesheet" href="${normalizeCss}" />
    <link rel="stylesheet" href="${bootstrapminCss}" />
    <%--<link rel='stylesheet prefetch' href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">--%>
    <link rel="stylesheet" href="${styleCss}" />
    <script src="${jquery}"></script>
    <script src="${jqueryValidation}"></script>
    <style>
        .error {
            color: #ff0000;
        }

        .errorblock {
            color: #000;
            background-color: #ffEEEE;
            border: 3px solid #ff0000;
            padding: 8px;
            margin: 16px;
        }
    </style>
</head>
<body>
