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
<c:set var="pageTitle" scope="request" value="Add New Education"/>
<%
    request.setAttribute("Mode", 2);
    request.setAttribute("EditAdd", 1);
%>


    <div class="mainBodyBlock">
        <%--<h1 class="page-header">${userProfile.firstName[2]} ${userProfile.lastName[2]}'s profile</h1>--%>
        <form:form modelAttribute="edu" cssClass="form-horizontal" method="post">
            <h2 class="headerText">Add New Education</h2>
            <div class="form-horizontal">
                <div class="form-group">
                    <label class="control-label col-md-3">Name of School: <font color='red'>*</font></label>
                    <div class="col-lg-5">
                        <form:hidden path="id"/>
                        <form:input placeholder="Name of School" required="true"
                                    cssClass="form-control text-box single-line"
                                    path="name"/></div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3">Major: <font color='red'>*</font></label>
                    <div class="col-lg-5"><form:input placeholder="Major" required="true"
                                                      cssClass="form-control text-box single-line"
                                                      path="major"/></div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3">Degree: <font color='red'>*</font></label>
                    <div class="col-lg-5"><form:input placeholder="Degree" required="true"
                                                      cssClass="form-control text-box single-line"
                                                      path="degree"/></div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3">Entry Date: <font color='red'>*</font></label>
                    <div class="col-lg-5"><form:input path="startDate" type="date"
                                                      cssClass="form-control text-box single-line requiredDate"/>

                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3">Graduate Date: <font color='red'>*</font></label>
                    <div class="col-lg-5"><form:input path="endDate" type="date"
                                                      cssClass="form-control text-box single-line requiredDate"/>

                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-offset-3 col-md-9">
                    <input type="submit" value="Add" class="btn btn-green"/>
                    <input type="button" onclick="history.back()" value="Cancel"
                           class="btn btn-red"/>
                </div>
            </div>
        </form:form>
    </div>

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
