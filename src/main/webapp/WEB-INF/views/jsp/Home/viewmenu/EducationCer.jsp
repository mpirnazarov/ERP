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
<%--printButton = document.getElementById("edCer");--%>
<%--printButton.style.visibility = 'hidden';--%>
<%--printButton = document.getElementById("eduprint");--%>
<%--//Set the print button visibility to 'hidden'--%>
<%--printButton.style.visibility = 'hidden';--%>
<%--printButton = document.getElementById("lansum");--%>
<%--printButton.style.visibility = 'hidden';--%>
<%--printButton = document.getElementById("cert");--%>
<%--printButton.style.visibility = 'hidden';--%>

<%--//Print the page content--%>
<%--window.print()--%>
<%--//Set the print button to 'visible' again--%>
<%--//[Delete this line if you want it to stay hidden after printing]--%>
<%--var printButton = document.getElementById("printpagebutton");--%>
<%--printButton.style.visibility = 'visible';--%>
<%--printButton = document.getElementById("edCer");--%>
<%--printButton.style.visibility = 'visible';--%>
<%--printButton = document.getElementById("eduprint");--%>
<%--//Set the print button visibility to 'hidden'--%>
<%--printButton.style.visibility = 'visible';--%>
<%--printButton = document.getElementById("lansum");--%>
<%--printButton.style.visibility = 'visible';--%>
<%--printButton = document.getElementById("cert");--%>
<%--printButton.style.visibility = 'visible';--%>
<%--}--%>

<%--</script>--%>
<c:set var="pageTitle" scope="request" value="Education certificates"/>
<% request.setAttribute("Mode", 1); %>
<%
    ProfileViewModel a = (ProfileViewModel) request.getAttribute("userProfileUser");
    request.setAttribute("FullName2", a.getFirstName()[0] + " " + a.getLastName()[0]);

%>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<div class="col-sm-9 col-md-offset-1">
    <div class="col-lg-8 col-lg-offset-2">
        <h1>${fullName}, ${jobTitle}</h1>
        <p style="font-family: 'Oswald', sans-serif; font-size:x-large;">${external}</p>
        <h2 class="page-header">Education Certificate</h2>

        <ul class="nav nav-tabs">
            <li id="eduprint" class="active"><a data-toggle="tab" href="#edu">Educations</a></li>
            <li id="lansum"><a data-toggle="tab" href="#langsum">Language summary</a></li>
            <li id="cert"><a data-toggle="tab" href="#cersum">Certificates</a></li>
        </ul>

        <div class="tab-content">
            <div id="edu" class="tab-pane fade in active">
                <h3>Educations</h3>
                <!--Educations table-->
                <table class="table">
                    <thead>
                    <tr>
                        <th>Name of school</th>
                        <th>Major</th>
                        <th>Degree</th>
                        <th>Entry date(YYYY-MM-DD)</th>
                        <th>Graduate date(YYYY-MM-DD)</th>
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
                <h3>Language scores</h3>
                <table class="table">
                    <thead>
                    <tr>
                        <th>Type</th>
                        <th>Score</th>
                        <th>Degree</th>
                        <th>Organization</th>
                        <th>Acquistion Date(YYYY-MM-DD)</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${eduVM.certificateList}" var="cer" varStatus="status">
                            <c:if test="${cer.type==2}">
                                <tr>
                                    <td>${cer.name}</td>
                                    <td>${cer.mark}</td>
                                    <td>${cer.degree}</td>
                                    <td>${cer.organization}</td>
                                    <td class="text-center">${cer.dateTime}</td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </tbody>
                </table>
                <div style="border: solid #ffffff; width: 17%; right: 0; bottom: 0; position: fixed; border-radius: 10px;">
                    <h3 style="text-align: center; text-decoration: underline">LEGEND:</h3>
                    <h4 style="margin-left: 20px">A1 and A2 - Basic</h4>
                    <h4 style="margin-left: 20px">B1 and B2 - Independent</h4>
                    <h4 style="margin-left: 20px">C1 and C2 - Proficient</h4>
                    <div></div>
                </div>
            </div>
            <div id="cersum" class="tab-pane fade">
                <h3>Certificates</h3>
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
                <%--<input id="printpagebutton" type="button" style="color: #0c0c0c; visibility:hidden;"--%>
                       <%--value="Print this page" onclick="printpage()"/>--%>
            </div>
        </div>
    </div>
</div>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
