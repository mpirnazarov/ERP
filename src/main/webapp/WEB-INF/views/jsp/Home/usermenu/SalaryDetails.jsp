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
<%
    String b = request.getAttribute("name").toString();
    request.setAttribute("ProfileModel", b);
%>
<%
    ProfileViewModel a = (ProfileViewModel) request.getAttribute("userProfile");
    request.setAttribute("FullName", a.getFirstName()[2] + " " + a.getLastName()[2]);
    request.setAttribute("JobTitle", a.getJobTitle());
%>
<script type="text/javascript">
    function printpage() {
        //Get the print button and put it into a variable
        var printButton = document.getElementById("printpagebutton");
        printButton.style.visibility = 'hidden';
        printButton = document.getElementById("appRec");
        printButton.style.visibility = 'hidden';
        //Print the page content
        window.print()
        //Set the print button to 'visible' again
        //[Delete this line if you want it to stay hidden after printing]
        var printButton = document.getElementById("printpagebutton");
        printButton.style.visibility = 'visible';
        printButton = document.getElementById("appRec");
        printButton.style.visibility = 'visible';
    }
</script>

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row">
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserLayout.jsp"></jsp:include>
        <div class="col-sm-9 col-lg-10">
            <div class="col-lg-8 col-lg-offset-2">
                <h1><%= request.getAttribute("FullName") %>, <%= request.getAttribute("JobTitle") %></h1>
                <h2 class="page-header">Salary Details</h2>
            <div class="tab-content">
                <div id="salarydet" class="tab-pane fade in active">
                    <h3>Salary details</h3>
                    <!--Appointment summary table-->
                    <table class="table">
                        <thead>
                        <tr>
                            <th>Date</th>
                            <th>Amount(before taxes)</th>
                            <th>PIT</th>
                            <th>INPS</th>
                            <th>PF</th>
                            <th>Amount(after taxes)</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${salaryVM}" var="salary" varStatus="status">
                            <tr>
                                <td>${salary.date}</td>
                                <td>${salary.net}</td>
                                <td>${salary.pit}%</td>
                                <td>${salary.inps}%</td>
                                <td>${salary.pf}%</td>
                                <td>${salary.gross}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <input id="printpagebutton" type="button" style="color: #0c0c0c" value="Print this page" onclick="printpage()"/>
                </div>
            </div>
        </div>
    </div>
</div>
    </div>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
