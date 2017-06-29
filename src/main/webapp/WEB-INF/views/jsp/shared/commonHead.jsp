<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Sarvar
  Date: 23.06.2017
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width = device-width, initial-scale = 1">
<!--- CSS --->
<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapminCss"/>
<spring:url value="/resources/core/css/font-awesome-4.7.0/css/font-awesome.min.css" var="fontAwesomeCss"/>
<spring:url value="/resources/core/css/commonCss.css" var="CommonCss"/>

<link rel="stylesheet" href="${bootstrapminCss}"/>
<link rel="stylesheet" href="${fontAwesomeCss}"/>
<link rel="stylesheet" href="${CommonCss}"/>

<!--- JS --->
<spring:url value="/resources/core/js/jquery.min.js" var="jquery"/>
<spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs"/>

<script src="${jquery}"></script>
<script src="${bootstrapJs}"></script>






<%@ page contentType="text/html;charset=UTF-8" language="java" %>

