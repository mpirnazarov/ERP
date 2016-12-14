<%@ page import="com.lgcns.erp.tapps.viewModel.ProfileViewModel" %><%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 25-Oct-16
  Time: 12:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="pageTitle" scope="request" value="Edit Appointment Record"/>
<% request.setAttribute("Mode", 2); %>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<div class="col-sm-9 col-md-offset-1">
    <div class="col-lg-8 col-lg-offset-2">
        <form:form modelAttribute="appointmentVM" cssClass="form-horizontal" method="post">
            <h2 class="page-header">Appointment Record</h2>
            <div class="form-horizontal">
                <div class="form-group">
                    <label class="control-label col-md-3">Appointment Date: <font color='red'>*</font></label>
                    <div class="col-lg-5"><form:input path="appointDate" type="date"
                                                      value="${appointmentVM.appointDate}"
                                                      cssClass="form-control text-box single-line requiredDate"/>

                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3">Appointment Type: <font color='red'>*</font></label>
                    <div class="col-lg-5"><form:select path="contractType" items="${types}"
                                                       cssClass="form-control text-box single-line"/></div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3">Department: <font color='red'>*</font></label>
                    <div class="col-lg-5"><form:select path="departmentId" items="${departments}"
                                                       cssClass="form-control text-box single-line"/></div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3">Role: <font color='red'>*</font></label>
                    <div class="col-lg-5"><form:select path="roleId" items="${roles}"
                                                       cssClass="form-control text-box single-line"/></div>
                </div>

            </div>
            <div class="form-group">
                <div class="col-md-offset-3 col-md-9">
                    <input type="submit" value="Save" class="btn btn-default"/>
                    <input type="button" onclick="history.back()" value="Cancel"
                           class="btn btn-default"/>
                </div>
            </div>
        </form:form>
    </div>
</div>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
