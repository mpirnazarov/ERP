<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 25-Oct-16
  Time: 12:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${pageTitle} | ERP System</title>
    <spring:url value="/resources/core/css/normalize.css" var="normalizeCss" />
    <spring:url value="/resources/core/css/style.css" var="styleCss" />
    <spring:url value="/resources/core/css/bootstrap.css" var="bootstrapCss" />
    <spring:url value="/resources/core/css/navbar-fixed-side.css" var="navbar" />
    <spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapminCss" />
    <spring:url value="/resources/core/js/jquery.min.js" var="jquery" />
    <spring:url value="/resources/core/js/jquery.validate.js" var="jqueryValidation" />
    <spring:url value="/resources/core/js/jquery.slimscroll.min.js" var="slimScroll"/>
    <spring:url value="/resources/core/css/datatablesCombined.min.css" var="allInOneCss"/>
    <spring:url value="/resources/core/js/datatablesCombined.min.js" var="allInOneJs"/>
    <spring:url value="/resources/core/css/jquery.scrollbar.css" var="scrollCss"/>
    <spring:url value="/resources/core/js/jquery.scrollbar.min.js" var="scrollJs"/>
    <spring:url value="/resources/core/js/main.js" var="main"/>
    <script src="${jquery}"></script>
    <link rel="stylesheet" href="${navbar}" />
    <link rel="stylesheet" href="${scrollCss}" />
        <link rel="stylesheet" href="${editableCss}" />
    <link rel="icon" type="image/x-icon" href="<s:url value="/resources/images/favicon.ico"/>"/>
    <link rel="stylesheet" href="${normalizeCss}" />
    <link rel="stylesheet" href="${bootstrapminCss}" />
    <link rel="stylesheet" type="text/css" href="${allInOneCss}"/>
    <link rel="stylesheet" href="${styleCss}" />
<%--<link rel='stylesheet prefetch' href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">--%>
    <script src="${main}"></script>
    <script src="${jqueryValidation}"></script>
    <script type="text/javascript" src="${allInOneJs}"></script>
    <script src="${scrollJs}"></script>

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
<body class="__scrollBar">
