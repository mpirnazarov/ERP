<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 25-Oct-16
  Time: 12:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Home"/>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserLayout.jsp"></jsp:include>
<div class="container">
    <div class="row">
        <div class="col-lg-8 col-lg-offset-2">
            <h1 class="page-header">User profile</h1>

<ul class="nav nav-tabs">
    <li class="active"><a data-toggle="tab" href="#headerinfo">Header information</a></li>
    <li><a data-toggle="tab" href="#personalinfo">Personal information</a></li>
    <li><a data-toggle="tab" href="#workingplace">Working place</a></li>
    <li><a data-toggle="tab" href="#familyinfo">Family information</a></li>
</ul>

<div class="tab-content">
    <div id="headerinfo" class="tab-pane fade in active">
        <h3>Header information</h3>
        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#ru">RU</a></li>
            <li><a data-toggle="tab" href="#en">EN</a></li>
            <li><a data-toggle="tab" href="#uz">UZ</a></li>
        </ul>
        <div id="ru" class="tab-pane fade in active">
            <p>На русском</p>
        </div>
        <div id="en" class="tab-pane fade">
            <p>На русском</p>
        </div>
        <div id="uz" class="tab-pane fade">
            <p>На русском</p>
        </div>
    </div>
    <div id="personalinfo" class="tab-pane fade">
        <h3>Personal information</h3>
        <p>Some content in menu 1.</p>
    </div>
    <div id="workingplace" class="tab-pane fade">
        <h3>Working place</h3>
        <p>Some content in menu 2.</p>
    </div>
    <div id="familyinfo" class="tab-pane fade">
        <h3>Family information</h3>
        <p>Some content in menu 2.</p>
    </div>
</div>
</div>
</div>
</div>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
