<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: EvilDecision
  Date: 24.08.2017
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:set var="pageTitle" scope="request" value="Asset Management"/>
    <jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>

 <%--   <script src="/resources/core/js/pace.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/pace/1.0.2/themes/black/pace-theme-barber-shop.css">
--%>

    <spring:url value="/resources/core/js/jquery.tokeninput.js" var="tokenInputJs"/>
    <script type="text/javascript" src="${tokenInputJs}"></script>

</head>
<body>
<div class="mainBodyBlock asset_index">
    <h2 class="headerText"><span class="fa fa-cubes" aria-hidden="true"></span> Asset Import</h2>


    <c:url value="/AssetManagement/import" var="uploadFileUrl" />
    <form method="post" enctype="multipart/form-data"
          action="${uploadFileUrl}">
        <input type="file" name="file" accept=".xls,.xlsx" /> <input
            type="submit" value="Upload file" />
    </form>



</div>
</body>


