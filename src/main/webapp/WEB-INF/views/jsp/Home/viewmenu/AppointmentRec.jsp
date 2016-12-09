<%@ page import="java.text.DecimalFormat" %>
<%@ page import="com.lgcns.erp.tapps.viewModel.usermenu.AppointmentrecViewModel" %>
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
<%--<script type="text/javascript">--%>
    <%--function printpage() {--%>
        <%--//Get the print button and put it into a variable--%>
        <%--var printButton = document.getElementById("printpagebutton");--%>
        <%--printButton.style.visibility = 'hidden';--%>
        <%--printButton = document.getElementById("appRec");--%>
        <%--printButton.style.visibility = 'hidden';--%>
        <%--//Print the page content--%>
        <%--window.print()--%>
        <%--//Set the print button to 'visible' again--%>
        <%--//[Delete this line if you want it to stay hidden after printing]--%>
        <%--var printButton = document.getElementById("printpagebutton");--%>
        <%--printButton.style.visibility = 'visible';--%>
        <%--printButton = document.getElementById("appRec");--%>
        <%--printButton.style.visibility = 'visible';--%>
    <%--}--%>
<%--</script>--%>
<% request.setAttribute("Mode", 1); %>
<%
    ProfileViewModel a = (ProfileViewModel) request.getAttribute("userProfileUser");
    request.setAttribute("FullName2", a.getFirstName()[0] + " " + a.getLastName()[0]);

%>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<div class="col-sm-9 col-md-offset-1">
    <div class="col-lg-8 col-lg-offset-2">
        <h1><%= request.getAttribute("FullName2") %>
        </h1>
        <h2 class="page-header">Appointment Record</h2>
        <div class="tab-content">
            <div id="appointmentsummary" class="tab-pane fade in active">
                <h3>Appointment summary</h3>
                <!--Appointment summary table-->
                <table class="table">
                    <thead>
                    <tr>
                        <th>Appointment date</th>
                        <th>Contract type</th>
                        <th>Department</th>
                        <th>Role</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${appointmentrecVM.appointmentSummaries}" var="appointment" varStatus="status">
                        <tr>
                            <td>${appointment.appointDate}</td>
                            <td>${appointment.appointmentType}</td>
                            <td>${appointment.department}</td>
                            <td>${appointment.role}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <%--<input id="printpagebutton" type="button" style="color: #0c0c0c; visibility:hidden;"--%>
                       <%--value="Print this page" onclick="printpage()"/>--%>
            </div>
        </div>
    </div>
</div>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
