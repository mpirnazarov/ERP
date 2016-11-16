<%--
  Created by IntelliJ IDEA.
  User: DS
  Date: 11/15/2016
  Time: 2:42 PM
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
    <spring:url value="/resources/core/css/normalize.css" var="normalizeCss"/>
    <spring:url value="/resources/core/css/style.css" var="styleCss"/>
    <%--<spring:url value="/resources/core/css/datatablesCombined.min.css" var="allInOneCss"/>--%>
    <%--<spring:url value="/resources/core/js/datatablesCombined.min.js" var="allInOneJs"/>--%>
    <spring:url value="/resources/core/css/bootstrap.css" var="bootstrapCss" />
    <spring:url value="/resources/core/css/navbar-fixed-side.css" var="navbar" />
    <spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapminCss" />
    <spring:url value="/resources/core/js/jquery-1.12.4.min.js" var="jquery" />
    <spring:url value="/resources/core/js/jquery.validate.js" var="jqueryValidation" />
    <link rel="stylesheet" href="${navbar}" />
    <link rel="icon" href="/resources/images/lg-2-multi-size.ico" type="image/x-icon">
    <link rel="stylesheet" href="${normalizeCss}" />
    <link rel="stylesheet" href="${bootstrapminCss}" />
    <%--<link rel='stylesheet prefetch' href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">--%>
    <link rel="stylesheet" href="${styleCss}" />
    <script src="${jquery}"></script>
    <script src="${jqueryValidation}"></script>
    <%--<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.12/css/dataTables.bootstrap.min.css"/>--%>
    <%--<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/1.2.2/css/buttons.bootstrap.min.css"/>--%>
    <%--<link rel="stylesheet" type="text/css" href="${allInOneCss}"/>--%>
    <link rel="stylesheet" href="${normalizeCss}"/>
    <link rel="stylesheet" href="${styleCss}"/>
    <%--<script type="text/javascript" src="${allInOneJs}"></script>--%>
</head>
<body>

