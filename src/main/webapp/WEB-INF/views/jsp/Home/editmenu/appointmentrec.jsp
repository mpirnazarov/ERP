<%@ page import="com.lgcns.erp.tapps.viewModel.ProfileViewModel" %><%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 25-Oct-16
  Time: 3:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Appointment Record"/>
<% request.setAttribute("Mode", 2); %>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<div class="col-sm-9 col-md-offset-1">
    <div class="col-lg-offset-2">
        <h1>${fullName}, ${jobTitle}</h1>
        <h2 class="page-header">Appointment Record</h2>
        <table class="table">
            <thead>
            <tr>
                <th class="text-center">Appointment date<br/><text class="small">(YYYY-MM-DD)</text></th>
                <th>Contract type</th>
                <th>Department</th>
                <th>Job title</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${appointmentrecVM.appointmentSummaries}" var="appointment" varStatus="status">
                <tr>
                    <td class="col-md-2 text-center">${appointment.appointDate}</td>
                    <td class="col-md-3">${contracts.get(appointment.contractType)}</td>
                    <td class="col-md-5">${departments.get(appointment.departmentId)}</td>
                    <td class="col-md-4">${posts.get(appointment.postId)}</td>
                    <td><a href="./Appointment/Edit/${appointment.id}" class="btn btn-default">Edit</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <a href="Appointment/Add" class="btn btn-success">Add</a>

    </div>
</div>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
