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

    <div class="mainBodyBlock">
        <h1>${fullName}, ${jobTitle}</h1>
        <p style="font-family: 'Oswald', sans-serif; font-size:x-large;">${external}</p>
        <h2 class="headerText">Job Experience</h2>
        <!--Job experience table-->
        <table class="table sartable table-bordered">
            <thead>
            <tr>
                <th class="col-md-3">Organization</th>
                <th class="col-md-2">Position</th>
                <th class="col-md-2">Contract type</th>
                <th class="col-md-2 text-center">Start date<br/><text class="small">(YYYY-MM-DD)</text></th>
                <th class="col-md-2 text-center">End date<br/><text class="small">(YYYY-MM-DD)</text></th>
                <th class="col-md-1">Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${jobexpVM}" var="jobexp" varStatus="status">
                <tr>
                    <td class="col-md-3">${jobexp.organization}</td>
                    <td class="col-md-2">${jobexp.position}</td>
                    <td class="col-md-2">${contractTypes.get(jobexp.contractType)}</td>
                    <td class="col-md-2 text-center">${jobexp.startDate}</td>
                    <c:choose>
                        <c:when test="${jobexp.endDate != null}">
                            <td class="col-md-2 text-center">${jobexp.endDate}</td>
                        </c:when>
                        <c:otherwise>
                            <td class="col-md-2 text-center">Present</td>
                        </c:otherwise>
                    </c:choose>
                    <td class="col-md-1 text-center"><a href="./Jobexp/updateJob/${jobexp.id}" class="btn btn-blue">Edit</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <a href="./Jobexp/add" class="btn btn-green">Add</a>
    </div>

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
