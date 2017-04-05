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

    <div class="mainBodyBlock">

        <h2 class="headerText"><span class="fa fa-fw fa-book"></span> Training Record</h2>
        <!--Trainings table-->
        <table class="table table-bordered sartable">
            <thead>
            <tr>
                <th class="col-md-3">Name</th>
                <th class="col-md-2">Certificate</th>
                <th class="col-md-3">Organization</th>
                <th class="col-md-2 text-center">Entry date<br/><text class="small">(YYYY-MM-DD)</text></th>
                <th class="col-md-2 text-center">Finish date<br/><text class="small">(YYYY-MM-DD)</text></th>
                <th class="col-md-1 text-center">Number of hours</th>
                <th class="col-md-1">Mark</th>
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
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <%--<input id="printpagebutton" type="button" style="color: #0c0c0c; visibility:hidden;"--%>
               <%--value="Print this page" onclick="printpage()"/>--%>
    </div>

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
