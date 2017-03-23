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
<%--printButton = document.getElementById("jobex");--%>
<%--printButton.style.visibility = 'hidden';--%>
<%--printButton = document.getElementById("je");--%>
<%--printButton.style.visibility = 'hidden';--%>

<%--//Print the page content--%>
<%--window.print()--%>
<%--//Set the print button to 'visible' again--%>
<%--//[Delete this line if you want it to stay hidden after printing]--%>
<%--var printButton = document.getElementById("printpagebutton");--%>
<%--printButton.style.visibility = 'visible';--%>
<%--printButton = document.getElementById("jobex");--%>
<%--printButton.style.visibility = 'visible';--%>
<%--printButton = document.getElementById("je");--%>
<%--printButton.style.visibility = 'visible';--%>
<%--}--%>

<%--</script>--%>
<c:set var="pageTitle" scope="request" value="Job experience"/>

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<div class="col-sm-9 col-md-offset-1">
    <div class="col-lg-8 col-lg-offset-2">
        <h1><%= request.getAttribute("FullName") %>, <%= request.getAttribute("JobTitle") %>
        </h1>
        <p style="font-family: 'Oswald', sans-serif; font-size:x-large;"><%= request.getAttribute("External") %></p>
        <h2 class="page-header">Job Experience</h2>
        <!--Job experience table-->
        <table class="table sartable">
            <thead>
            <tr>
                <th class="col-md-3">Organization</th>
                <th class="col-md-2">Position</th>
                <th class="col-md-2">Contract type</th>
                <th class="col-md-2 text-center">Start date<br/>
                    <text class="small">(YYYY-MM-DD)</text>
                </th>
                <th class="col-md-2 text-center">End date<br/>
                    <text class="small">(YYYY-MM-DD)</text>
                </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${jobexpVM}" var="jobexp" varStatus="status">
                <tr>
                    <td>${jobexp.organization}</td>
                    <td>${jobexp.position}</td>
                    <td>${contracts.get(jobexp.contractType)}</td>
                    <td class="col-md-2 text-center">${jobexp.startDate}</td>
                    <td class="col-md-2 text-center">${jobexp.endDate}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <%--<input id="printpagebutton" type="button" style="color: #0c0c0c; visibility:hidden;"--%>
        <%--value="Print this page" onclick="printpage()"/>--%>
    </div>
</div>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
