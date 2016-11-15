<%@ page import="com.lgcns.erp.hr.enums.ProjectStatus" %><%--
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
<c:set var="pageTitle" scope="request" value="Add New Project"/>
<c:set var="now" value="<%=new java.util.Date()%>" />
<fmt:formatDate value="${now}" var="nowFormatted" pattern="yyyy-MM-dd" />
<fmt:formatDate value="${now}" var="curYear" pattern="yyyy" />

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserLayout.jsp"></jsp:include>
<div class="container">
    <div class="row">
        <div class="col-lg-8 col-lg-offset-2">
            <h1 class="page-header">Add New Project</h1>

            <form class="form-horizontal">
                <div class="form-group">
                    <label class="control-label col-md-3">Project Name<font color='red'>*</font></label>
                    <div class="col-md-5">
                        <input type="text" id="projectName" class="form-control text-box single-line">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3">Customer <font color='red'>*</font></label>
                    <div class="col-md-5">
                        <select name="customerId" id="customerId" class="form-control text-box single-line">
                            <c:forEach items="${customers}" var="i">
                                <option value="${i.key}">${i.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-offset-3 col-md-9">
                        <input type="button" id="generate" value="Generate"
                               class="btn btn-default"/>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <div class="row">
        <div class="col-md-offset-2 col-md-9">
            <form:form modelAttribute="createVM" cssClass="form" method="post" action="/Projects/Create"
                       id="myForm">

                <c:forEach items="${createVM.projects}" var="project" varStatus="i">
                    <br/>

                    <form:hidden path="projects[${i.index}].name" cssClass="projectName"/>
                    <form:hidden path="projects[${i.index}].customerId" cssClass="projectCustomer"/>
                    <form:hidden path="projects[${i.index}].code" value="${project.code}"/>
                    <form:hidden path="projects[${i.index}].type" value="${project.type}"/>
                    <form:hidden path="projects[${i.index}].status" value="<%=ProjectStatus.Active.getValue()%>"/>
                    <div class="row">
                        <div class="form-group col-md-4">
                            <label class="control-label" for="code">Code</label>
                            <input type="text" id="code" value="<c:out value="PJ ${curYear}-${project.code}-${project.type}"/>"
                                   readonly class="form-control text-box single-line">
                        </div>
                        <div class="form-group col-md-offset-1 col-md-3">
                            <label class="control-label">UZS amount</label>
                            <form:input path="projects[${i.index}].moneyUzs" type="number"
                                        cssClass="form-control single-line"/>
                        </div>
                        <div class="form-group col-md-3">
                            <label class="control-label">Start Date <font color='red'>*</font></label>
                            <%--<form:input path="projects[${i.index}].startDate" type="date"
                                        cssClass="form-control text-box single-line requiredDate" value="${now}"/>--%>
                            <input name="projects[${i.index}].startDate" type="date" class="form-control text-box single-line requiredDate" value="<c:out value='${nowFormatted}' />">
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-4">
                            <label class="control-label">Manager <font color='red'>*</font></label>
                            <form:select path="projects[${i.index}].managerId" id="managerId" items="${users}"
                                         cssClass="form-control text-box single-line"/>
                        </div>
                        <div class="form-group col-md-offset-1 col-md-3">
                            <div>
                                <label class="control-label">USD amount</label>
                                <form:input path="projects[${i.index}].moneyUsd" type="number"
                                            cssClass="form-control single-line"/>
                            </div>
                        </div>
                        <div class="form-group col-md-3">
                            <label class="control-label">End Date <font color='red'>*</font></label>
                            <input name="projects[${i.index}].endDate" type="date" class="form-control text-box single-line requiredDate" value="<c:out value='${nowFormatted}' />">
                        </div>
                    </div>

                    <hr/>
                </c:forEach>

                <div class="form-group col-md-offset-10">
                    <div class="col-md-2">
                        <input type="submit" value="Create projects"
                               class="btn btn-default"/>
                    </div>
                </div>
                <div class="clearfix"></div>
            </form:form>
        </div>
    </div>
</div>
<%--<div class="form-horizontal">
    <br/>
    <div class="form-group  ${requestScope['org.springframework.validation.BindingResult.createVM'].hasFieldErrors('passportNumber') ? 'error' : ''}">
        <label class="control-label col-md-3">Code</label>
        <div class="col-md-5">
            <input type="text" value="${createVM.get(i).}">
        </div>
    </div>

    <div class="form-group ${requestScope['org.springframework.validation.BindingResult.user'].hasFieldErrors('MobilePhone') ? 'error' : ''}">
        <label class="control-label col-md-3">Mobile Phone </label>
        <div class="col-md-5">
            <form:input path="mobilePhone" placeholder="Mobile phone number" type="tel"
                        cssClass="telPhone form-control text-box single-line"/>
            <form:errors path="MobilePhone" cssClass="error field-validation-error"/>
        </div>
    </div>

    <div class="form-group ${requestScope['org.springframework.validation.BindingResult.user'].hasFieldErrors('homePhone') ? 'error' : ''}">
        <label class="control-label col-md-3">Home Phone</label>
        <div class="col-md-5">
            <form:input path="homePhone" placeholder="Home phone number"
                        cssClass="telPhone form-control text-box single-line"/>
            <form:errors path="HomePhone" cssClass="error field-validation-error"/>
        </div>
    </div>

    <div class="form-group ${requestScope['org.springframework.validation.BindingResult.user'].hasFieldErrors('hiringDate') ? 'error' : ''}">
        <label class="control-label col-md-3">Hiring Date <font color='red'>*</font></label>
        <div class="col-md-5">
            <form:input path="hiringDate" type="date"
                        cssClass="form-control text-box single-line requiredDate"/>
            <form:errors path="HiringDate" cssClass="error field-validation-error"/>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-md-3">Status <font color='red'>*</font></label>
        <div class="col-md-5">
            <form:select path="statusId" items="${statuses}"
                         cssClass="form-control text-box single-line"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-md-3">Department <font color='red'>*</font></label>
        <div class="col-md-5">
            <form:select path="departmentId" items="${departments}"
                         cssClass="form-control text-box single-line"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-md-3">Company Email <font color='red'>*</font></label>
        <div class="col-md-5">
            <form:input path="companyEmail" placeholder="Company email address" type="email"
                        cssClass="form-control single-line"/>
            <form:errors path="CompanyEmail" cssClass="error field-validation-error"/>

        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-md-3">Username <font color='red'>*</font></label>
        <div class="col-md-5">
            <form:input path="userName" placeholder="Username"
                        cssClass="form-control text-box single-line required"/>
            <form:errors path="UserName" cssClass="error field-validation-error"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-md-3">System role <font color='red'>*</font></label>
        <div class="col-md-5">
            <form:select path="roleId" items="${roles}"
                         cssClass="form-control text-box single-line"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-md-3">Password <font color='red'>*</font></label>
        <div class="col-md-5">
            <form:password path="password" placeholder="Password"
                           cssClass="form-control text-box single-line"/>
            <form:errors path="Password" cssClass="error field-validation-error"/>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-md-3">Repeat password <font color='red'>*</font></label>
        <div class="col-md-5">
            <form:password path="repeatPassword" placeholder="Repeat Password"
                           cssClass="form-control text-box single-line"/>
            <form:errors path="RepeatPassword" cssClass="error field-validation-error"/>
        </div>
    </div>

    <div class="form-group">
        <div class="col-md-offset-3 col-md-9">
            <input type="submit" value="Register" class="btn btn-default"/>
            <input type="button" onclick="location.href='/User'" value="Cancel"
                   class="btn btn-default"/>
        </div>
    </div>
</div>--%>
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
                        <%--<c:if test="${fn:contains(error.toString(),'Start')}"--%>
                        <c:out value="${error.defaultMessage.toString()}"/> <br/>
                    </c:forEach>
                </span>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        $('#myForm').hide();
        if($.trim($('#message').text())!=''){
            $('#myModal').modal('show');
        }
    });
    $(document).keypress(function(e) {
        if(e.which == 13) {
            $('#generate').click();
            return false;
        }
    });
    $('#generate').click(function () {
        if($.trim($('#projectName').val()) == '')
            return false;
        $('.projectName').each(function (i) {
            $(this).val($('#projectName').val());
        });
        $('.projectCustomer').each(function (i) {
            $(this).val($('#customerId').val());
        });
        $('#myForm').show();

    })
</script>

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>