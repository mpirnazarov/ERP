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
<div class="col-sm-9 col-md-offset-1">
    <div class="col-lg-offset-2">
        <%--<h1 class="page-header">${userProfile.firstName[2]} ${userProfile.lastName[2]}'s profile</h1>--%>
        <form:form modelAttribute="salaryVM" cssClass="form-horizontal" method="post">

        <!--General info/Family info Tab-->
        <h2 class="page-header">Edit salary details</h2>
        <div class="form-horizontal">
            <div class="form-group">
                <label class="control-label col-md-3">Date: <font color='red'>*</font></label>
                <div class="col-lg-5"><form:input path="date" type="date" value="${salaryVM.date}" required="true"
                                                  cssClass="form-control text-box single-line requiredDate"/>

                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-md-3">Gross Salary: <font color='red'>*</font></label>
                <div class="col-lg-5"><form:input value="${salaryVM.salaryBefore}" path="salaryBefore"
                                                  placeholder="Gross salary"
                                                  cssClass="form-control text-box single-line"/></div>
            </div>
            <div class="form-group">
                <label class="control-label col-md-3">PIT: </label>
                <div class="col-sm-2"><form:input value="${salaryVM.pit}"
                                                  path="pit" placeholder="PIT"
                                                  cssClass="form-control text-box single-line"/>
                </div>
                <h4>%</h4>
            </div>
            <div class="form-group">
                <label class="control-label col-md-3">INPS: </label>
                <div class="col-sm-2"><form:input value="${salaryVM.inps}"
                                                  path="inps"
                                                  placeholder="INPS"
                                                  cssClass="form-control text-box single-line"/>
                </div>
                <h4>%</h4>
            </div>
            <div class="form-group">
                <label class="control-label col-md-3">PF: </label>
                <div class="col-sm-2"><form:input value="${salaryVM.pf}"
                                                  path="pf" placeholder="PF"
                                                  cssClass="form-control text-box single-line"/>
                </div>
                <h4>%</h4>
            </div>
            <div class="form-group">
                <label class="control-label col-md-3">Net Salary: <font color='red'>*</font></label>
                <div class="col-lg-5"><form:input path="salaryAfter" placeholder="Net salary"
                                                  cssClass="form-control text-box single-line"/></div>
            </div>
            <div class="form-group">
                <label class="control-label col-md-3">Currency: <font color='red'>*</font></label>
                <div class="col-lg-5"><form:select path="currencyId" items="${currencies}"
                                                   cssClass="form-control text-box single-line"/></div>
            </div>
            <div class="form-group">
                <div class="col-md-3"></div>
                <div class="col-lg-5">
                    <input type="submit" value="Save" class="btn btn-default"/>
                    <input type="button" onclick="history.back()" value="Cancel"
                           class="btn btn-default"/>
                </div>
            </div>
            </form:form>
        </div>
    </div>
</div>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
