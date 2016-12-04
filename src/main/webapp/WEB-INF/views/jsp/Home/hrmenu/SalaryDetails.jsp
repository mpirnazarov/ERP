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
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpHRLayout.jsp"></jsp:include>
        <div class="col-sm-9 col-md-offset-1">
            <div class="col-lg-8 col-lg-offset-2">
                <h1><%= request.getAttribute("FullName") %>, <%= request.getAttribute("JobTitle") %></h1>
                <h2 class="page-header">Salary Details</h2>
            <div class="tab-content">
                <div id="salarydet" class="tab-pane fade in active">

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
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${salaryVM}" var="salary" varStatus="status">
                            <tr>
                                <td>${salary.date}</td>
                                <c:if test="${salary.currency==1}">
                                    <td>${salary.gross} UZS</td>
                                </c:if>
                                <c:if test="${salary.currency==2}">
                                    <td>${salary.gross} USD</td>
                                </c:if>
                                <td>${salary.pit}%</td>
                                <td>${salary.inps}%</td>
                                <td>${salary.pf}%</td>
                                <c:if test="${salary.currency==1}">
                                    <td>${salary.net} UZS</td>
                                </c:if>
                                <c:if test="${salary.currency==2}">
                                    <td>${salary.net} USD</td>
                                </c:if>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <input id="printpagebutton" type="button" style="color: #0c0c0c; visibility:hidden;" value="Print this page" onclick="printpage()"/>
                </div>
            </div>
        </div>
    </div>
</div>
    </div>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
