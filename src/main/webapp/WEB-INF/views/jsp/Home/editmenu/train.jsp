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


<c:set var="pageTitle" scope="request" value="Trainings"/>
<% request.setAttribute("Mode", 2); %>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<div class="col-sm-9 col-md-offset-1">
    <div class="col-lg-offset-2">
        <h1>${fullName}, ${jobTitle}</h1>
        <p style="font-family: 'Oswald', sans-serif; font-size:x-large;">${external}</p>
        <h2 class="page-header">Training Record</h2>

        <div class="tab-content">
            <div id="train" class="tab-pane fade in active">

                <!--Trainings table-->
                <table class="table">
                    <thead>
                    <tr>
                        <th class="col-md-3">Name</th>
                        <th class="col-md-2">Certificate</th>
                        <th class="col-md-3">Organization</th>
                        <th class="col-md-2 text-center">Entry date<br/><text class="small">(YYYY-MM-DD)</text></th>
                        <th class="col-md-2 text-center">Finish date<br/><text class="small">(YYYY-MM-DD)</text></th>
                        <th class="col-md-1 text-center">Number of hours</th>
                        <th class="col-md-1">Mark</th>
                        <th class="col-md-1">Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${trainVM}" var="train" varStatus="status">
                        <tr>
                            <td>${train.name}</td>
                            <td>${train.certificateId}</td>
                            <td>${train.organization}</td>
                            <td class="text-center">${train.dateFrom}</td>
                            <td class="text-center">${train.dateTo}</td>
                            <td class="text-center">${train.numberOfHours}</td>
                            <td>${train.mark}</td>
                            <td><a href="./Train/edit/${train.id}" class="btn btn-default">Edit</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <a href="./Train/add" class="btn btn-success">Add</a>
            </div>
        </div>
    </div>
</div>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
