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
<c:set var="pageTitle" scope="request" value="Appointment Record"/>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserLayout.jsp"></jsp:include>
<div class="container">
    <div class="row">
        <div class="col-lg-8 col-lg-offset-2">
            <h1 class="page-header">Appointment Record</h1>

            <ul class="nav nav-tabs">
                <li class="active"><a data-toggle="tab" href="#appointmentsummary">Appointment summary</a></li>
                <li><a data-toggle="tab" href="#salarydet">Salary details</a></li>
            </ul>

            <div class="tab-content">
                <div id="appointmentsummary" class="tab-pane fade in active">
                    <h3>Appointment summary</h3>
                    <!--Appointment summary table-->
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>Appointment date</th>
                            <th>Appointment type</th>
                            <th>Department</th>
                            <th>Role</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${appointmentrecVM}" var="appointment" varStatus="status">
                        <tr>
                            <td>${appointment.appointDate}</td>
                            <td>${appointment.appointmentType}</td>
                            <td>${appointment.department}</td>
                            <td>${appointment.role}</td>
                        </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div id="salarydet" class="tab-pane fade">
                    <h3>Salary details</h3>
                    <!--Salary details table-->
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>Date</th>
                            <th>Amount(before taxes)</th>
                            <th>NDFL</th>
                            <th>INPS</th>
                            <th>PF</th>
                            <th>Amount(after taxes)</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%--<c:forEach items="${appointmentList}" var="appointment" varStatus="status">--%>
                        <tr>
                            <td>Row 1 Data 1</td>
                            <td>Row 1 Data 2</td>
                            <td>Row 1 Data 2</td>
                            <td>Row 1 Data 2</td>
                            <td>Row 1 Data 2</td>
                            <td>Row 1 Data 2</td>
                        </tr>
                        <tr>
                            <td>Row 2 Data 1</td>
                            <td>Row 2 Data 2</td>
                            <td>Row 1 Data 2</td>
                            <td>Row 1 Data 2</td>
                            <td>Row 1 Data 2</td>
                            <td>Row 1 Data 2</td>
                        </tr>
                        <%--</c:forEach>--%>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
