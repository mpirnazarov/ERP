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
<%--<c:set var="pageTitle" scope="request" value="Home"/>--%>
<%
    request.setAttribute("Mode", 2);
    request.setAttribute("EditAdd", 1);
%>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>

    <div class="mainBodyBlock">
        <%--<h1 class="page-header">${userProfile.firstName[2]} ${userProfile.lastName[2]}'s profile</h1>--%>
        <form:form modelAttribute="appointmentVM" cssClass="form-horizontal" method="post">
            <h2 class="headerText">Appointment Record</h2>
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
                                <label class="control-label col-md-3">Job title: <font color='red'>*</font></label>
                                <div class="col-lg-5"><form:select path="postId" items="${posts}"
                                                                   cssClass="form-control text-box single-line"/></div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3">External: <font color='red'>*</font></label>
                                <div class="col-lg-5"><form:select path="externalId" items="${externals}"
                                                                   cssClass="form-control text-box single-line"/></div>
                            </div>

            </div>
            <div class="form-group">
                <div class="col-md-offset-3 col-md-9">
                    <input type="submit" value="Save" class="btn btn-green"/>
                    <input type="button" onclick="history.back()" value="Cancel"
                           class="btn btn-red"/>
                </div>
            </div>
        </form:form>
    </div>

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
