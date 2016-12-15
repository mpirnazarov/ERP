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
<c:set var="pageTitle" scope="request" value="Edit Certificate"/>
<% request.setAttribute("Mode", 2); %>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<div class="col-sm-9 col-md-offset-1">
    <div class="col-lg-8 col-lg-offset-2">
        <%--<h1 class="page-header">${userProfile.firstName[2]} ${userProfile.lastName[2]}'s profile</h1>--%>
        <form:form modelAttribute="cert" cssClass="form-horizontal" method="post">
            <h2 class="page-header">Edit Certificate</h2>
            <div class="form-horizontal">
                <div class="form-group">
                    <label class="control-label col-md-3">Name: <font color='red'>*</font></label>
                    <div class="col-lg-5"><form:input placeholder="Name" required="true"
                                                      cssClass="form-control text-box single-line"
                                                      path="name" value="${cert.name}"/></div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3">Organization: <font color='red'>*</font></label>
                    <div class="col-lg-5"><form:input placeholder="Organization" required="true"
                                                      cssClass="form-control text-box single-line"
                                                      path="organization" value="${cert.organization}"/></div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3">Number: <font color='red'>*</font></label>
                    <div class="col-lg-5"><form:input placeholder="Number" required="true"
                                                      cssClass="form-control text-box single-line"
                                                      path="number" value="${cert.number}"/></div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3">Date: <font color='red'>*</font></label>
                    <div class="col-lg-5"><form:input path="dateTime" type="date" value="${cert.dateTime}"
                                                      cssClass="form-control text-box single-line requiredDate"/>

                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3">Mark: <font color='red'>*</font></label>
                    <div class="col-lg-5"><form:input placeholder="Mark" required="true"
                                                      cssClass="form-control text-box single-line"
                                                      path="mark" value="${cert.mark}"/></div>
                </div>
                <div class="form-group"><label class="control-label col-md-3">Is Passed: <font
                        color='red'>*</font></label>
                    <div class="col-lg-5">
                        <c:if test="${cert.passed}">
                            <form:checkbox path="passed" CHECKED="checked"/>
                        </c:if>
                        <c:if test="${!cert.passed}">
                            <form:checkbox path="passed"/>
                        </c:if>
                    </div>
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
