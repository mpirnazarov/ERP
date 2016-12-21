<%@ page import="com.lgcns.erp.hr.enums.ProjectStatus" %>
<%@ page import="com.lgcns.erp.tapps.model.DbEntities.UserInProjectsEntity" %>
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
<c:set var="pageTitle" scope="request" value="Edit contact in customer organizations"/>


<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<div class="col-sm-9 col-md-offset-1">
    <div class="col-lg-8 col-lg-offset-2">
        <h1 class="page-header">Edit contact in customer organizations</h1>
        <form:form modelAttribute="viewModel" cssClass="form-horizontal" method="post" action="/Contacts/Edit"
                   id="myForm">

            <br/>

            <form:hidden path="id"/>

            <div class="row">
                <div class="form-group">
                    <label class="control-label col-md-3">Organization <font color='red'>*</font></label>
                    <div class="col-lg-5">
                        <form:select path="organizationId" cssClass="form-control text-box single-line" items="${organizations}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3">Name <font color='red'>*</font></label>
                    <div class="col-lg-5">
                        <form:input path="name" cssClass="form-control text-box single-line"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-3">Mobile Phone </label>
                    <div class="col-md-5">
                        <form:input path="mobilePhone" placeholder="Mobile phone number" type="tel"
                                    cssClass="telPhone form-control text-box single-line"/>
                        <form:errors path="MobilePhone" cssClass="error field-validation-error"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-3">Work Phone </label>
                    <div class="col-md-5">
                        <form:input path="workPhone" placeholder="Mobile phone number" type="tel"
                                    cssClass="telPhone form-control text-box single-line"/>
                        <form:errors path="MobilePhone" cssClass="error field-validation-error"/>
                    </div>
                </div>
            </div>

            <hr/>

            <div class="form-group">
                <div class="col-md-offset-3 col-md-9">
                    <input type="submit" value="Confirm" class="btn btn-info"/>
                    <input type="button" onclick="location.href='/Contacts'" value="Cancel"
                           class="btn btn-default"/>
                </div>
                <div class="clearfix"></div>
            </div>
        </form:form>
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
                        <c:out value="${error}"/> <br/>
                    </c:forEach>
                    <c:out value="${error}"/> <br/>
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