<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: EvilDecision
  Date: 25.08.2017
  Time: 10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="ERP | Contacts"/>
<c:set var="pageTitle" scope="request" value="Projects"/>

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/commonHead.jsp"></jsp:include>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>

<div class="container col-lg-10 col-lg-offset-1 col-sm-12">
    <div class="headerDiv">
        <div class="logoDiv" onclick="location='/login'"></div>
        <hr>
        <h1>Asset Management</h1>
    </div>

    <div class="mainBodyBlock">
        <form:form modelAttribute="AssetCategory" method="post" enctype="multipart/form-data">
            <form:input path="assetCategoryName" type="text" class="form-control"/>
            <div class="btn-group">
                <input type="submit" name="submit" value="Submit">
                <input type="submit" name="update" value="Update">
                <input type="submit" name="delete" value="Delete">
            </div>
        </form:form>
    </div>



</div>