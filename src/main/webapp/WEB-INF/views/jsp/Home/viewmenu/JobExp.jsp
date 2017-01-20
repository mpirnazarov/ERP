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
<% request.setAttribute("Mode", 1); %>
<%
    ProfileViewModel a = (ProfileViewModel) request.getAttribute("userProfileUser");
    request.setAttribute("FullName2", a.getFirstName()[0] + " " + a.getLastName()[0]);

%>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<div class="col-sm-9 col-md-offset-1">
    <div class="col-lg-offset-2">
        <h1>${fullName}, ${jobTitle}</h1>
        <p style="font-family: 'Oswald', sans-serif; font-size:x-large;">${external}</p>
        <h2 class="page-header">Job Experience</h2>


        <div class="tab-content">
            <div id="jobexp" class="tab-pane fade in active">
                <!--Job experience table-->
                <table class="table">
                    <thead>
                    <tr>
                        <th>Organization</th>
                        <th>Position</th>
                        <th>Start date(YYYY-MM-DD)</th>
                        <th>End date(YYYY-MM-DD)</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${jobexpVM}" var="jobexp" varStatus="status">
                        <tr>
                            <td>${jobexp.organization}</td>
                            <td>${jobexp.position}</td>
                            <td>${jobexp.startDate}</td>
                            <td>${jobexp.endDate}</td>
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
