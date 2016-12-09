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

<%--<script type="text/javascript">--%>
<%--function printpage() {--%>
<%--//Get the print button and put it into a variable--%>
<%--var printButton = document.getElementById("printpagebutton");--%>
<%--printButton.style.visibility = 'hidden';--%>
<%--printButton = document.getElementById("training");--%>
<%--printButton.style.visibility = 'hidden';--%>
<%--printButton = document.getElementById("trainings");--%>
<%--printButton.style.visibility = 'hidden';--%>
<%--//Print the page content--%>
<%--window.print()--%>
<%--//Set the print button to 'visible' again--%>
<%--//[Delete this line if you want it to stay hidden after printing]--%>
<%--var printButton = document.getElementById("printpagebutton");--%>
<%--printButton.style.visibility = 'visible';--%>
<%--printButton = document.getElementById("training");--%>
<%--printButton.style.visibility = 'visible';--%>
<%--printButton = document.getElementById("training");--%>
<%--printButton.style.visibility = 'visible';--%>
<%--}--%>

<%--</script>--%>
<c:set var="pageTitle" scope="request" value="Trainings"/>

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<div class="col-sm-9 col-md-offset-1">
    <div class="col-lg-offset-2">
        <h1><%= request.getAttribute("FullName") %>, <%= request.getAttribute("JobTitle") %>
        </h1>
        <h2 class="page-header">Training Record</h2>
        <!--Trainings table-->
        <table class="table">
            <thead>
            <tr>
                <th>Name</th>
                <th>Certificate</th>
                <th>Organization</th>
                <th>Entry date (YYYY-MM-DD)</th>
                <th>Finish date (YYYY-MM-DD)</th>
                <th>Number of hours</th>
                <th>Mark</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${trainVM}" var="train" varStatus="status">
                <tr>
                    <td>${train.name}</td>
                    <td>${train.certificateId}</td>
                    <td>${train.organization}</td>
                    <td>${train.dateFrom}</td>
                    <td>${train.dateTo}</td>
                    <td>${train.numberOfHours}</td>
                    <td>${train.mark}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <%--<input id="printpagebutton" type="button" style="color: #0c0c0c; visibility:hidden;"--%>
               <%--value="Print this page" onclick="printpage()"/>--%>
    </div>
</div>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
