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
<% request.setAttribute("Mode", 2); %>
<c:set var="pageTitle" scope="request" value="Education certificates"/>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<div class="col-sm-9 col-md-offset-1">
    <div class="col-lg-offset-2">
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
                <!--Educations table-->
                <table class="table">
                    <thead>
                    <tr>
                        <th class="col-md-4 text-center">Name of school</th>
                        <th class="col-md-2">Major</th>
                        <th class="col-md-1">Degree</th>
                        <th class="col-md-2 text-center">Entry date<br/><text class="small">(YYYY-MM-DD)</text></th>
                        <th class="col-md-2 text-center">Graduate date<br/><text class="small">(YYYY-MM-DD)</text></th>
                        <th class="col-md-1">Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${eduVM.educationsList}" var="eduList" varStatus="status">
                        <tr>
                            <td class="col-md-4 text-center">${eduList.name}</td>
                            <td class="col-md-2 text-center">${eduList.major}</td>
                            <td class="col-md-1">${eduList.degree}</td>
                            <td class="col-md-2 text-center">${eduList.startDate}</td>
                            <td class="col-md-2 text-center">${eduList.endDate}</td>
                            <td class="col-md-1 text-center"><a href="./Edu/EditEdu/${eduList.id}" class="btn btn-default">Edit</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <a href="./Edu/AddEdu" class="btn btn-success">Add</a>
            </div>
            <div id="langsum" class="tab-pane fade">
                <h3>Language summary</h3>
                <!--Language summary table-->
                <table class="table">
                    <thead>
                    <tr>
                        <th class="col-md-3">Language</th>
                        <th class="text-center">Listening</th>
                        <th class="text-center">Reading</th>
                        <th class="text-center">Writing</th>
                        <th class="text-center">Speaking</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${eduVM.languageSummaryList}" var="language" varStatus="status">
                        <tr>
                            <td class="col-md-3">${language.language}</td>
                            <td class="text-center">${language.listening}</td>
                            <td class="text-center">${language.reading}</td>
                            <td class="text-center">${language.writing}</td>
                            <td class="text-center">${language.speaking}</td>
                            <td><a href="./Edu/EditLang/${language.id}" class="btn btn-default">Edit</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <a href="./Edu/AddLang" class="btn btn-success">Add</a>

                <h3>Language scores</h3>
                <table class="table">
                    <thead>
                    <tr>
                        <th class="col-md-3">Type</th>
                        <th class="col-md-1">Score</th>
                        <th class="col-md-1">Degree</th>
                        <th class="col-md-2">Organization</th>
                        <th class="col-md-2">Number</th>
                        <th class="col-md-2 text-center">Acquisition Date<br/><text class="small">(YYYY-MM-DD)</text></th>
                        <th class="col-md-1">Action</th>
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
                                    <td>${cer.number}</td>
                                    <td class="text-center">${cer.dateTime}</td>
                                    <td><a href="./Edu/EditLangScore/${cer.id}/" class="btn btn-default">Edit</a></td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </tbody>
                </table>
                <a href="./Edu/AddLangScore" class="btn btn-success">Add</a>

                <div style="border: solid #ffffff; width: 17%; right: 0; bottom: 0; position: fixed; border-radius: 10px;">
                    <h3 style="text-align: center; text-decoration: underline">LEGEND:</h3>
                    <h4 style="margin-left: 20px">A1 and A2 - Basic</h4>
                    <h4 style="margin-left: 20px">B1 and B2 - Independent</h4>
                    <h4 style="margin-left: 20px">C1 and C2 - Proficient</h4>
                    <div></div>
                </div>
            </div>
            <div id="cersum" class="tab-pane fade">
                            <!--Certificates table-->
                <table class="table">
                    <thead>
                    <tr>
                        <th class="col-md-3">Name</th>
                        <th class="col-md-4">Organization</th>
                        <th class="col-md-1">Number</th>
                        <th class="col-md-2">Date<br/><text class="small">(YYYY-MM-DD)</text></th>
                        <th class="col-md-1">Mark</th>
                        <th class="col-md-1">Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${eduVM.certificateList}" var="cer" varStatus="status">
                        <c:if test="${cer.type==1}">
                            <tr>
                                <td class="col-md-4">${cer.name}</td>
                                <td class="col-md-4">${cer.organization}</td>
                                <td class="col-md-1">${cer.number}</td>
                                <td class="col-md-2 text-center">${cer.dateTime}</td>
                                <td class="col-md-1">${cer.mark}</td>
                                <td class="col-md-1"><a href="./Edu/EditCert/${cer.id}/" class="btn btn-default">Edit</a></td>
                            </tr>
                        </c:if>
                    </c:forEach>
                    </tbody>
                </table>

                <a href="./Edu/AddCert" class="btn btn-success">Add</a>
            </div>
        </div>
    </div>
</div>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
