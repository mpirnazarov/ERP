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
<c:set var="pageTitle" scope="request" value="Edit Training Record"/>
<%
    request.setAttribute("Mode", 2);
    request.setAttribute("EditAdd", 1);
%>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>

    <div class="mainBodyBlock">
        <%--<h1 class="page-header">${userProfile.firstName[2]} ${userProfile.lastName[2]}'s profile</h1>--%>
        <form:form commandName="trainVM" cssClass="form-horizontal" method="post">
            <h2 class="headerText">Training records</h2>
            <div class="form-horizontal">
                <div class="form-group">
                    <label class="control-label col-md-3">Name: <font color='red'>*</font></label>
                    <div class="col-lg-5"><form:input path="name" placeholder="Developer, DB" value="${trainVM.name}"
                                                      cssClass="form-control text-box single-line"/></div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3">Organization: <font color='red'>*</font></label>
                    <div class="col-lg-5"><form:input path="organization" placeholder="LG CNS"
                                                      value="${trainVM.organization}"
                                                      cssClass="form-control text-box single-line"/></div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3">Entry Date: <font color='red'>*</font></label>
                    <div class="col-lg-5"><form:input path="dateFrom" type="date" value="${trainVM.dateFrom}"
                                                      cssClass="form-control text-box single-line requiredDate"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3">Finish Date: <font color='red'>*</font></label>
                    <div class="col-lg-5"><form:input path="dateTo" type="date" value="${trainVM.dateTo}"
                                                      cssClass="form-control text-box single-line requiredDate"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3">Number of hours: <font color='red'>*</font></label>
                    <div class="col-lg-5"><form:input path="numberOfHours" placeholder="" type="number"
                                                      value="${trainVM.numberOfHours}"
                                                      cssClass="form-control text-box single-line"/></div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3">Mark: </label>
                    <div class="col-lg-5"><form:input path="mark" placeholder="" value="${trainVM.mark}"
                                                      cssClass="form-control text-box single-line"/></div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-3 col-md-9">
                        <input type="submit" value="Save" class="btn btn-green"/>
                        <input type="button" onclick="history.back()" value="Cancel"
                               class="btn btn-red"/>
                    </div>
                </div>
            </div>
        </form:form>
    </div>

<script>
    $(document).ready(function () {
        $("#firuref").trigger('click');
        document.getElementById("firuref").click();
        $("#fienref").trigger('click');
        document.getElementById("fienref").click();
    });
</script>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
