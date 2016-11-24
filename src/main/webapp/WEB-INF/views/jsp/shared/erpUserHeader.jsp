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
    <spring:url value="/resources/core/css/navbar-fixed-side.css" var="navbar" />
    <spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapminCss" />
    <spring:url value="/resources/core/js/jquery.min.js" var="jquery" />
    <spring:url value="/resources/core/js/jquery.validate.js" var="jqueryValidation" />
    <spring:url value="/resources/core/js/jquery.slimscroll.min.js" var="slimScroll"/>
    <spring:url value="/resources/core/css/datatablesCombined.min.css" var="allInOneCss"/>
    <spring:url value="/resources/core/js/datatablesCombined.min.js" var="allInOneJs"/>
    <spring:url value="/resources/core/css/editablegrid.css" var="editableCss"/>
    <spring:url value="/resources/core/js/editable/editablegrid.js" var="editablegridJs"/>
    <spring:url value="/resources/core/js/editable/editablegrid_charts.js" var="editablegridChart"/>
    <spring:url value="/resources/core/js/editable/editablegrid_charts_ofc.js" var="editablegridChartsofc"/>
    <spring:url value="/resources/core/js/editable/editablegrid_editors.js" var="editablegridEditors"/>
    <spring:url value="/resources/core/js/editable/editablegrid_renderers.js" var="editablegridRenderers"/>
    <spring:url value="/resources/core/js/editable/editablegrid_utils.js" var="editablegridUtils"/>
    <spring:url value="/resources/core/js/editable/editablegrid_validators.js" var="editablegridValidators"/>
    <script src="${jquery}"></script>
    <link rel="stylesheet" href="${navbar}" />
    <link rel="stylesheet" href="${editableCss}" />
    <link rel="icon" href="/resources/images/lg-2-multi-size.ico" type="image/x-icon">
    <link rel="stylesheet" href="${normalizeCss}" />
    <link rel="stylesheet" href="${bootstrapminCss}" />
    <%--<link rel='stylesheet prefetch' href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">--%>
    <link rel="stylesheet" href="${styleCss}" />
    <script src="${editablegridJs}"></script>
    <script src="${editablegridChart}"></script>
    <script src="${editablegridChartsofc}"></script>
    <script src="${editablegridEditors}"></script>
    <script src="${editablegridRenderers}"></script>
    <script src="${editablegridUtils}"></script>
    <script src="${editablegridValidators}"></script>
    <script src="${jqueryValidation}"></script>
    <link rel="stylesheet" type="text/css" href="${allInOneCss}"/>
    <script type="text/javascript" src="${allInOneJs}"></script>
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
