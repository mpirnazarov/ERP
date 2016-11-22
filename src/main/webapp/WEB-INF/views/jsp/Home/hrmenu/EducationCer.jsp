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
        printButton = document.getElementById("edCer");
        printButton.style.visibility = 'hidden';
        printButton = document.getElementById("eduprint");
        //Set the print button visibility to 'hidden'
        printButton.style.visibility = 'hidden';
        printButton = document.getElementById("lansum");
        printButton.style.visibility = 'hidden';
        printButton = document.getElementById("cert");
        printButton.style.visibility = 'hidden';

        //Print the page content
        window.print()
        //Set the print button to 'visible' again
        //[Delete this line if you want it to stay hidden after printing]
        var printButton = document.getElementById("printpagebutton");
        printButton.style.visibility = 'visible';
        printButton = document.getElementById("edCer");
        printButton.style.visibility = 'visible';
        printButton = document.getElementById("eduprint");
        //Set the print button visibility to 'hidden'
        printButton.style.visibility = 'visible';
        printButton = document.getElementById("lansum");
        printButton.style.visibility = 'visible';
        printButton = document.getElementById("cert");
        printButton.style.visibility = 'visible';
    }

</script>
<c:set var="pageTitle" scope="request" value="Education certificates"/>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row">
        <jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpHRLayout.jsp"></jsp:include>
        <div class="col-sm-9 col-lg-10">
            <div class="col-lg-8 col-lg-offset-2">
                <h1><%= request.getAttribute("FullName") %>, <%= request.getAttribute("JobTitle") %></h1>
                <h2 class="page-header">Education Certificate</h2>

                <ul class="nav nav-tabs">
                    <li id="eduprint" class="active"><a data-toggle="tab" href="#edu">Educations</a></li>
                    <li id="lansum"><a data-toggle="tab" href="#langsum">Language summary</a></li>
                    <li id="cert"><a data-toggle="tab" href="#cersum">Certificates</a></li>
                </ul>

                <div class="tab-content">
                    <div id="edu" class="tab-pane fade in active">
                        <h3 >Educations</h3>
                        <!--Educations table-->
                        <table class="table">
                            <thead>
                            <tr>
                                <th>Name of school</th>
                                <th>Major</th>
                                <th>Degree</th>
                                <th>Entry date</th>
                                <th>Graduate date</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${eduVM.educationsList}" var="eduList" varStatus="status">
                                <tr>
                                    <td>${eduList.name}</td>
                                    <td>${eduList.major}</td>
                                    <td>${eduList.degree}</td>
                                    <td>${eduList.startDate}</td>
                                    <td>${eduList.endDate}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div id="langsum" class="tab-pane fade">
                        <h3>Language summary</h3>
                        <!--Language summary table-->
                        <table class="table">
                            <thead>
                            <tr>
                                <th>Language</th>
                                <th>Listening</th>
                                <th>Reading</th>
                                <th>Writing</th>
                                <th>Speaking</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${eduVM.languageSummaryList}" var="language" varStatus="status">
                                <tr>
                                    <td>${language.language}</td>
                                    <td>${language.listening}</td>
                                    <td>${language.reading}</td>
                                    <td>${language.writing}</td>
                                    <td>${language.speaking}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div id="cersum" class="tab-pane fade">
                        <h3 >Certificates</h3>
                        <!--Certificates table-->
                        <table class="table">
                            <thead>
                            <tr>
                                <th>Name</th>
                                <th>Organization</th>
                                <th>Number</th>
                                <th>Date</th>
                                <th>Mark</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${eduVM.certificateList}" var="cer" varStatus="status">
                                <tr>
                                    <td>${cer.name}</td>
                                    <td>${cer.organization}</td>
                                    <td>${cer.number}</td>
                                    <td>${cer.dateTime}</td>
                                    <td>${cer.mark}</td>
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
<% request.setAttribute("foo", "bar"); %>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
