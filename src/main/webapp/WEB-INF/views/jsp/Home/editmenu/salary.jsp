<%@ page import="java.text.DecimalFormat" %>
<%@ page import="com.lgcns.erp.tapps.viewModel.usermenu.AppointmentrecViewModel" %>
<%@ page import="com.lgcns.erp.tapps.viewModel.ProfileViewModel" %>
<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 25-Oct-16
  Time: 3:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Salary details"/>
<% request.setAttribute("Mode", 2); %>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>


    <div class="mainBodyBlock">
        <h1>${fullName}, ${jobTitle}</h1>
        <p style="font-family: 'Oswald', sans-serif; font-size:x-large;">${external}</p>
        <h2 class="headerText">Salary Details</h2>
        <!--Appointment summary table-->
        <table class="table sartable table-bordered">
            <thead>
            <tr>
                <th class="text-center">Date<br/><text class="small">(YYYY-MM-DD)</text></th>
                <th>Gross salary</th>
                <th>PIT</th>
                <th>INPS</th>
                <th>PF</th>
                <th>Net salary</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${salaryVM}" var="salary" varStatus="status">
                <tr>
                    <td class="col-md-2 text-center">${salary.date}</td>
                    <c:if test="${salary.currency==1}">
                        <td class="col-md-3">${salary.gross} UZS</td>
                    </c:if>
                    <c:if test="${salary.currency==2}">
                        <td class="col-md-3">${salary.gross} USD</td>
                    </c:if>
                    <td>${salary.pit}%</td>
                    <td>${salary.inps}%</td>
                    <td>${salary.pf}%</td>
                    <c:if test="${salary.currency==1}">
                        <td class="col-md-3">${salary.net} UZS</td>
                    </c:if>
                    <c:if test="${salary.currency==2}">
                        <td class="col-md-3">${salary.net} USD</td>
                    </c:if>
                    <td><a href="./Salary/updateSal/${salary.id}" class="btn btn-blue">Edit</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <a href="./Salary/addSal" class="btn btn-green">Add</a>
    </div>

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
