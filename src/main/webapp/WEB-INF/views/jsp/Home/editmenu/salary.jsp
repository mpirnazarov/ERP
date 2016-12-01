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

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row">
        <jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpEditLayout.jsp"></jsp:include>
        <div class="col-sm-9 col-md-offset-1">
            <div class="col-lg-8 col-lg-offset-2">
                <h1>${fullName} ${jobTitle}</h1>
                <h2 class="page-header">Salary Details</h2>
                    <!--Appointment summary table-->
                    <table class="table">
                        <thead>
                        <tr>
                            <th>Date</th>
                            <th>Gross salary</th>
                            <th>PIT</th>
                            <th>INPS</th>
                            <th>PF</th>
                            <th>Net salary</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${salaryVM}" var="salary" varStatus="status">
                            <tr>
                                <td>${salary.date}</td>
                                <td>${salary.gross}</td>
                                <td>${salary.pit}%</td>
                                <td>${salary.inps}%</td>
                                <td>${salary.pf}%</td>
                                <td>${salary.net}</td>
                                <td><a href="./Salary/updateSal/${salary.id}" class="btn btn-default">Edit</a></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <a href="./Salary/addSal" class="btn btn-primary">Add</a>
            </div>
        </div>
    </div>
</div>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
