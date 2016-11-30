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
        printButton = document.getElementById("training");
        printButton.style.visibility = 'hidden';
        printButton = document.getElementById("trainings");
        printButton.style.visibility = 'hidden';
        //Print the page content
        window.print()
        //Set the print button to 'visible' again
        //[Delete this line if you want it to stay hidden after printing]
        var printButton = document.getElementById("printpagebutton");
        printButton.style.visibility = 'visible';
        printButton = document.getElementById("training");
        printButton.style.visibility = 'visible';
        printButton = document.getElementById("training");
        printButton.style.visibility = 'visible';
    }

</script>
<c:set var="pageTitle" scope="request" value="Trainings"/>

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row">
        <jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpEditLayout.jsp"></jsp:include>
        <div class="col-sm-9 col-md-offset-1">
            <div class="col-lg-8 col-lg-offset-2">
                <h1><%= request.getAttribute("FullName") %>, <%= request.getAttribute("JobTitle") %></h1>
                <h2 class="page-header">Training Record</h2>

                <div class="tab-content">
                    <div id="train" class="tab-pane fade in active">

                        <!--Trainings table-->
                        <table class="table">
                            <thead>
                            <tr>
                                <th>Name</th>
                                <th>Certificate</th>
                                <th>Organization</th>
                                <th>Entry date</th>
                                <th>Finish date</th>
                                <th>Number of hours</th>
                                <th>Mark</th>
                                <th></th>
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
                                    <td><a href="./trarec/updateTra/${train.id}/" class="btn btn-default">Edit</a></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <input id="printpagebutton" type="button" style="color: #0c0c0c; visibility:hidden;" value="Print this page" onclick="printpage()"/>
                        <a href="" class="btn btn-primary">Add</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
