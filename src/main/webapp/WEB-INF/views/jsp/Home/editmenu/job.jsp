<%@ page import="com.lgcns.erp.tapps.viewModel.ProfileViewModel" %><%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 25-Oct-16
  Time: 3:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" scope="request" value="Job experience"/>
<% request.setAttribute("Mode", 2); %>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<div class="col-sm-9 col-md-offset-1">
    <div class="col-lg-8 col-lg-offset-2">
        <h1>${fullName}, ${jobTitle}</h1>
        <h2 class="page-header">Job Experience</h2>
        <!--Job experience table-->
        <table class="table">
            <thead>
            <tr>
                <th>Organization</th>
                <th>Position</th>
                <th>Start date(YYYY-MM-DD)</th>
                <th>End date(YYYY-MM-DD)</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${jobexpVM}" var="jobexp" varStatus="status">
                <tr>
                    <td>${jobexp.organization}</td>
                    <td>${jobexp.position}</td>
                    <td>${jobexp.startDate}</td>
                    <td>${jobexp.endDate}</td>
                    <td><a href="./Jobexp/updateJob/${jobexp.id}" class="btn btn-default">Edit</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <a href="./Jobexp/add" class="btn btn-primary">Add</a>
    </div>
</div>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
