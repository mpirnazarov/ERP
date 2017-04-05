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


<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>

    <div class="mainBodyBlock">

        <h2 class="headerText"><span class="fa fa-fw fa-hand-pointer-o"></span> Appointment Record</h2>
        <!--Appointment summary table-->
        <table class="table table-bordered sartable">
            <thead>
            <tr>
                <th class="text-center">Appointment date<br/><text class="small">(YYYY-MM-DD)</text></th>
                <th>Contract type</th>
                <th>Department</th>
                <th>Role</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${appointmentrecVM.appointmentSummaries}" var="appointment" varStatus="status">
                <tr>
                    <td class="col-md-2 text-center">${appointment.appointDate}</td>
                    <td class="col-md-3">${appointment.appointmentType}</td>
                    <td class="col-md-5">${appointment.department}</td>
                    <td class="col-md-4">${appointment.role}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <%--<input id="printpagebutton" type="button" style="color: #0c0c0c; visibility:hidden;"--%>
               <%--value="Print this page" onclick="printpage()"/>--%>
    </div>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
