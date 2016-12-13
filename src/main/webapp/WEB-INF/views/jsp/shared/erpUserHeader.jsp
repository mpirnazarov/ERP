<%@ page import="com.lgcns.erp.tapps.viewModel.ProfileViewModel" %><%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 25-Oct-16
  Time: 12:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${pageTitle} | ERP System</title>
    <spring:url value="/resources/core/css/normalize.css" var="normalizeCss"/>
    <spring:url value="/resources/core/css/style.css" var="styleCss"/>
    <spring:url value="/resources/core/css/bootstrap.css" var="bootstrapCss"/>
    <spring:url value="/resources/core/css/navbar-fixed-side.css" var="navbar"/>
    <spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapminCss"/>
    <spring:url value="/resources/core/js/jquery.min.js" var="jquery"/>
    <%--<spring:url value="/resources/core/js/jquery.validate.js" var="jqueryValidation"/>--%>
    <spring:url value="/resources/core/css/datatablesCombined.min.css" var="allInOneCss"/>
    <spring:url value="/resources/core/js/datatablesCombined.min.js" var="allInOneJs"/>
    <spring:url value="/resources/core/js/jquery.slimscroll.min.js" var="slimScroll"/>
    <spring:url value="/resources/core/css/jquery.scrollbar.css" var="scrollCss"/>
    <spring:url value="/resources/core/js/jquery.scrollbar.min.js" var="scrollJs"/>
    <spring:url value="/resources/core/js/main.js" var="main"/>
    <script src="${jquery}"></script>
    <link rel="stylesheet" href="${navbar}"/>
    <link rel="stylesheet" href="${scrollCss}"/>
    <link rel="icon" type="image/x-icon" href="<s:url value="/resources/images/favicon.ico"/>"/>
    <link rel="stylesheet" href="${normalizeCss}"/>
    <link rel="stylesheet" href="${bootstrapminCss}"/>
    <link rel="stylesheet" type="text/css" href="${allInOneCss}"/>
    <link rel="stylesheet" href="${styleCss}"/>
    <script src="${main}"></script>
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
    <%
    if((ProfileViewModel) request.getAttribute("userProfile")!=null){
        ProfileViewModel a = (ProfileViewModel) request.getAttribute("userProfile");
        request.setAttribute("FullName", a.getFirstName()[0] + " " + a.getLastName()[0]);
        request.setAttribute("SystemRole", a.getRoleId());
        request.setAttribute("FirstName", a.getFirstName()[0]);
        request.setAttribute("JobTitle", a.getJobTitle());
        request.setAttribute("userId", a.getId());
    }
%>
<div class="container-fluid">
    <div class="row">
            <%
            if(request.getAttribute("SystemRole")!=null || request.getAttribute("mode")!=null){
                String pageType = "/WEB-INF/views/jsp/shared/erpUserLayout.jsp";
                if ((int) (request.getAttribute("SystemRole")) == 1)
                    pageType =  "/WEB-INF/views/jsp/shared/erpCTOLayout.jsp";
                else if ((int) (request.getAttribute("SystemRole")) == 3)
                    pageType =  "/WEB-INF/views/jsp/shared/erpHRLayout.jsp";
                if(request.getAttribute("Mode") != null){
                    if(((int)request.getAttribute("Mode"))==1)
                        pageType =  "/WEB-INF/views/jsp/shared/erpViewLayout.jsp";
                    else if(((int)request.getAttribute("Mode"))==2)
                        pageType =  "/WEB-INF/views/jsp/shared/erpEditLayout.jsp";
                }
                pageContext.setAttribute("pageType", pageType);
            }
        %>
        <jsp:include page='${pageType}' flush="true"/>
