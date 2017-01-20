<%@ page import="com.lgcns.erp.hr.enums.ProjectStatus" %>
<%--
  Created by IntelliJ IDEA.
  User: Rafatdin
  Date: 03.10.2016
  Time: 17:37
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="pageTitle" scope="request" value="Edit Project"/>
<c:set var="now" value="<%=new java.util.Date()%>"/>
<fmt:formatDate value="${now}" var="nowFormatted" pattern="yyyy-MM-dd"/>
<fmt:formatDate value="${viewModel.startDate}" var="yearOfProject" pattern="yyyy"/>
<script>
    function isValidDate()
    {
        var startDate = new Date(document.getElementById("startDate").value);
        var endDate = new Date(document.getElementById("endDate").value);

        if (startDate.getTime() >= endDate.getTime())
        {
            alert ("Error on date. Start date cannot be more recent than end date!");
            return false;
        }

    }
</script>

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<div class="col-sm-9 col-md-offset-1">
    <div class="col-lg-8 col-lg-offset-2">
        <h1 class="page-header">Edit Project</h1>
        <form:form modelAttribute="viewModel" cssClass="form-horizontal" method="post" action="/Projects/Edit" onsubmit="return isValidDate();"
                   id="myForm">

        <br/>

        <form:hidden path="id"/>
        <form:hidden path="code"/>
        <form:hidden path="type"/>

        <div class="row">

            <div class="form-group">
                <label class="control-label col-md-3">Project Code <font color='red'>*</font></label>
                <div class="col-lg-5">
                    <input type="text" id="code"
                           value="<c:out value="PJ ${yearOfProject}-${viewModel.code}-${viewModel.type}"/>"
                           readonly class="form-control text-box single-line">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-md-3">Name <font color='red'>*</font></label>
                <div class="col-lg-5">
                    <form:input path="name"
                                cssClass="form-control text-box single-line"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-md-3">Customer <font color='red'>*</font></label>
                <div class="col-lg-5">
                    <form:select path="customerId" id="custmerId"
                                 cssClass="form-control text-box single-line">
                        <form:options items="${customers}"/>
                    </form:select>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-3">Status <font color='red'>*</font></label>
                <div class="col-lg-5">
                    <form:select path="status" id="status" cssClass="form-control text-box single-line">
                        <form:options items="${projectStatuses}"/>
                    </form:select>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-md-3">UZS amount</label>
                <div class="col-lg-5">
                    <form:input path="moneyUzs" type="number" min="0"
                                cssClass="form-control single-line"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-md-3">Start Date <font color='red'>*</font></label>
                <div class="col-lg-5">
                    <form:input path="startDate" name="startDate" type="date"
                                cssClass="form-control text-box single-line requiredDate"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-md-3">End Date <font color='red'>*</font></label>
                <div class="col-lg-5">
                    <form:input path="endDate" name="startDate" type="date"
                                cssClass="form-control text-box single-line requiredDate"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-md-3">Manager <font color='red'>*</font></label>
                <div class="col-lg-5">
                    <form:select path="managerId" id="managerId"
                                 cssClass="form-control text-box single-line">
                        <form:options items="${managers}"/>
                    </form:select>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-md-3">USD amount</label>
                <div class="col-lg-5">
                    <form:input path="moneyUsd" type="number" min="0"
                                cssClass="form-control single-line"/>
                </div>
            </div>
        </div>

        <hr/>

        <div class="form-group">
            <div class="col-md-offset-3 col-md-9">
                <input type="submit" value="Confirm" class="btn btn-info"/>
                <input type="button" onclick="location.href='/Projects'" value="Cancel"
                       class="btn btn-default"/>
            </div>
            <div class="clearfix"></div>
            </form:form>
        </div>
    </div>
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">TAPPS</h4>
            </div>
            <div class="modal-body">
                <span id="message">
                    <c:forEach items="${errors}" var="error" varStatus="i">
                        <c:out value="${error.defaultMessage.toString()}"/> <br/>
                    </c:forEach>
                </span>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        if ($.trim($('#message').text()) != '') {
            $('#myModal').modal('show');
        }
    });
</script>

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>