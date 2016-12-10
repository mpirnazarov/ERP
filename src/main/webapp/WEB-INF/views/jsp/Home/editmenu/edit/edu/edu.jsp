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
<% request.setAttribute("Mode", 2); %>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<div class="col-sm-9 col-md-offset-1">
    <div class="col-lg-8 col-lg-offset-2">
        <%--<h1 class="page-header">${userProfile.firstName[2]} ${userProfile.lastName[2]}'s profile</h1>--%>
        <form:form modelAttribute="edu" cssClass="form-horizontal" method="post">
            <h3>Education certificate</h3>
            <p>Educations</p>
            <div class="form-horizontal">
                <div class="form-group">
                    <label class="control-label col-md-3">Name of School: <font color='red'>*</font></label>
                    <div class="col-lg-5">
                        <form:hidden path="id"/>
                        <form:input placeholder="Name of School" required="true"
                                    cssClass="form-control text-box single-line"
                                    path="name" value="${edu.name}"/></div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3">Major: <font color='red'>*</font></label>
                    <div class="col-lg-5"><form:input placeholder="Major" required="true"
                                                      cssClass="form-control text-box single-line"
                                                      path="major" value="${edu.major} "/></div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3">Degree: <font color='red'>*</font></label>
                    <div class="col-lg-5"><form:input placeholder="Degree" required="true"
                                                      cssClass="form-control text-box single-line"
                                                      path="degree" value="${edu.degree} "/></div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3">Entry Date: <font color='red'>*</font></label>
                    <div class="col-lg-5"><form:input path="startDate" type="date" value="${edu.startDate}"
                                                      cssClass="form-control text-box single-line requiredDate"/>

                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3">Graduate Date: <font color='red'>*</font></label>
                    <div class="col-lg-5"><form:input path="endDate" type="date" value="${edu.endDate}"
                                                      cssClass="form-control text-box single-line requiredDate"/>

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
