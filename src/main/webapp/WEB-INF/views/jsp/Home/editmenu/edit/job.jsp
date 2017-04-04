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
<c:set var="pageTitle" scope="request" value="Edit Job Experience"/>
<%
    request.setAttribute("Mode", 2);
    request.setAttribute("EditAdd", 1);
%>

    <div class="mainBodyBlock">
        <%--<h1 class="page-header">${userProfile.firstName[2]} ${userProfile.lastName[2]}'s profile</h1>--%>
        <form:form modelAttribute="jobexpVM" cssClass="form-horizontal" method="post">

            <!--General info/Family info Tab-->
            <h2 class="headerText">Job Experience</h2>
            <div class="form-horizontal">
                <div class="form-group">
                    <label class="control-label col-md-3">Organization: <font color='red'>*</font></label>
                    <div class="col-lg-5"><form:input path="organization" placeholder="LG CNS"
                                                      value="${jobexpVM.organization}"
                                                      cssClass="form-control text-box single-line"/></div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3">Position: <font color='red'>*</font></label>
                    <div class="col-lg-5"><form:input path="position" placeholder="Developer, BA"
                                                      value="${jobexpVM.position}"
                                                      cssClass="form-control text-box single-line"/></div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3">Start Date: <font color='red'>*</font></label>
                    <div class="col-lg-5"><form:input path="startDate" type="date" value="${jobexpVM.startDate}"
                                                      cssClass="form-control text-box single-line requiredDate"/>

                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3">End Date: </label>
                    <div class="col-lg-5"><form:input path="endDate" type="date" value="${jobexpVM.endDate}"
                                                      cssClass="form-control text-box single-line requiredDate"/>

                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3">Contract type: <font color='red'>*</font></label>
                    <div class="col-lg-5"><form:select path="contractType" items="${contractTypes}"
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

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
