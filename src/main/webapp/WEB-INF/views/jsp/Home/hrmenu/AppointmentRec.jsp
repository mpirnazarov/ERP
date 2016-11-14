<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 25-Oct-16
  Time: 3:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Appointment Record"/>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row">
        <jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpHRLayout.jsp"></jsp:include>
        <div class="col-sm-9 col-lg-10">
        <div class="col-lg-8 col-lg-offset-2">
            <h1 class="page-header">Appointment Record</h1>

            <ul class="nav nav-tabs">
                <li class="active"><a data-toggle="tab" href="#appointmentsummary">Appointment summary</a></li>
                <li><a data-toggle="tab" href="#salarydet">Salary details</a></li>
            </ul>

            <div class="tab-content">
                <div id="appointmentsummary" class="tab-pane fade in active">
                    <h3>Appointment summary</h3>
                </div>
                <div id="salarydet" class="tab-pane fade">
                    <h3>Salary details</h3>
                    <p>Some content in menu 1.</p>
                </div>
            </div>
        </div>
    </div>
</div>
    </div>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
