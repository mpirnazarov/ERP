<%@ page import="com.lgcns.erp.hr.enums.ProjectStatus" %>
<%@ page import="com.lgcns.erp.tapps.model.DbEntities.UserInProjectsEntity" %>
<%@ page import="com.lgcns.erp.hr.enums.ProjectRole" %>
<%--
  Created by IntelliJ IDEA.
  User: Rafatdin
  Date: 08.11.2016
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="pageTitle" scope="request" value="Access denied"/>


<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>


<spring:url value="/resources/core/css/normalize.css" var="normalizeCss"/>
<spring:url value="/resources/core/css/style.css" var="styleCss"/>
<spring:url value="/resources/core/css/datatablesCombined.min.css" var="allInOneCss"/>
<spring:url value="/resources/core/js/datatablesCombined.min.js" var="allInOneJs"/>


<div class="container-fluid">
    <div class="row">
        <jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserLayout.jsp"></jsp:include>
        <div class="col-md-offset-1 col-sm-10">
            <div class=" col-lg-offset-2 col-lg-10">
                <h1 class="page-header">Are you new?</h1>
                <div class="row">
                    <%
                        if(((int)request.getAttribute("SystemRole")) == 1)
                            out.print("<div style=\"overflow: hidden\">\n" +
                                    "                    <input type=\"button\" value=\"Create new project\" class=\"btn btn-success\" style=\"float: left;\"  onclick=\"location.href='/Projects/Create'\">\n" +
                                    "                    <div style=\"clear: both;\">&nbsp;</div>\n" +
                                    "                </div>");
                    %>
                    <div class="col-md-1">
                        <i class="fa fa-exclamation-triangle fa-5x" aria-hidden="true"></i>
                    </div>
                    <div class="col-md-8">
                        The system could not find any data for the user <b><i><%= request.getAttribute("FullName") %>
                    </i></b>.
                        <br>
                        Are you new to the company/system, and are you the right personnel to take part in projects?
                        <br>
                        If you think so, please contact CTO and other responsible staff members.
                    </div>
                </div>
            </div>
        </div>
        <jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
