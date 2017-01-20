<%@ page import="com.lgcns.erp.tapps.viewModel.ProfileViewModel" %><%--
  Created by IntelliJ IDEA.
  User: JAS SHAYKHOV
  Date: 25-Oct-16
  Time: 12:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Home"/>

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>

<div class="col-sm-9 col-md-offset-1">
    <div class="col-lg-offset-2">
        <h1><%= request.getAttribute("FullName") %>, <%= request.getAttribute("JobTitle") %>
        </h1>
        <p style="font-family: 'Oswald', sans-serif; font-size:x-large;"><%= request.getAttribute("External") %></p>
        <h2 class="page-header">General Information</h2>

        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#headerinfo">Header information</a></li>
            <li><a data-toggle="tab" href="#personalinfo">Personal information</a></li>
            <li><a data-toggle="tab" href="#familyinfo">Family information</a></li>
        </ul>
        <div class="tab-content">
            <!--General info/Header information Tab-->
            <div id="headerinfo" class="tab-pane fade in active"><br/>
                <ul class="nav nav-tabs">
                    <li class="active"><a data-toggle="tab" href="#en">EN</a></li>
                    <li><a data-toggle="tab" href="#ru">RU</a></li>
                    <li><a data-toggle="tab" href="#uz">UZ</a></li>
                </ul>
                <!--English, Russian, Uzbek (Localizations)-->
                <div class="tab-content">
                    <div id="en" class="tab-pane fade in active">
                        <p>In english</p>
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="control-label col-md-3">Last name: </label>
                                <div class="col-md-2"><c:out value="${UserProfileUser.lastName[2]}"></c:out></div>
                                <label class="control-label col-md-3">First name: </label>
                                <div class="col-md-2"><c:out value="${UserProfileUser.firstName[2]}"></c:out></div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3">Father's name: </label>
                                <div class="col-md-2"><c:out
                                        value="${UserProfileUser.fathersName[2]}"></c:out></div>
                                <label class="control-label col-md-3">Address: </label>
                                <div class="col-md-4"><c:out value="${UserProfileUser.address[2]}"></c:out></div>
                            </div>
                        </div>
                    </div>
                    <div id="ru" class="tab-pane fade">
                        <p>На русском</p>
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="control-label col-md-3">Фамилия: </label>
                                <div class="col-md-2"><c:out value="${UserProfileUser.lastName[0]}"></c:out></div>
                                <label class="control-label col-md-3">Имя: </label>
                                <div class="col-md-2"><c:out value="${UserProfileUser.firstName[0]}"></c:out></div>
                            </div>
                            <div class="form-group"><label class="control-label col-md-3">Отчество: </label>
                                <div class="col-md-2"><c:out
                                        value="${UserProfileUser.fathersName[0]}"></c:out></div>
                                <label class="control-label col-md-3">Адрес: </label>
                                <div class="col-md-4"><c:out value="${UserProfileUser.address[0]}"></c:out></div>
                            </div>
                        </div>
                    </div>
                    <div id="uz" class="tab-pane fade">
                        <p>O'zbekchada</p>
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="control-label col-md-3">Familiya: </label>
                                <div class="col-md-2"><c:out value="${UserProfileUser.lastName[1]}"></c:out></div>
                                <label class="control-label col-md-3">Ism: </label>
                                <div class="col-md-2"><c:out value="${UserProfileUser.firstName[1]}"></c:out></div>
                            </div>
                            <div class="form-group"><label class="control-label col-md-3">Sharf: </label>
                                <div class="col-md-2"><c:out
                                        value="${UserProfileUser.fathersName[1]}"></c:out></div>
                                <label class="control-label col-md-3">Manzil: </label>
                                <div class="col-md-4"><c:out value="${UserProfileUser.address[1]}"></c:out></div>
                            </div>
                        </div>
                    </div>
                </div>
                <hr/>
                <!--Other information-->
                <div class="form-horizontal">
                    <div class="form-group"><label class="control-label col-md-3">Employee ID: </label>
                        <div class="col-sm-2"><c:out value="${UserProfileUser.id}"></c:out></div>
                        <label class="control-label col-md-3">Passport Number: </label>
                        <div class="col-sm-2"><c:out value="${UserProfileUser.passportNumber}"></c:out></div>
                    </div>
                    <div class="form-group"><label class="control-label col-md-3">Department: </label>
                        <div class="col-sm-2"><c:out value="${UserProfileUser.department}"></c:out></div>
                        <label class="control-label col-md-3">Job title: </label>
                        <div class="col-sm-2"><c:out value="${UserProfileUser.jobTitle}, ${UserProfileUser.external}"></c:out></div>
                    </div>
                    <div class="form-group"><label class="control-label col-md-3">Work type: </label>
                        <div class="col-sm-2"><c:out value="${UserProfileUser.jointType}"></c:out></div>
                        <label class="control-label col-md-3">Hiring date: </label>
                        <div class="col-sm-2"><c:out value="${UserProfileUser.entryDate}"></c:out></div>
                    </div>
                    <div class="form-group"><label class="control-label col-md-3">Is political: </label>
                        <div class="col-lg-5"><c:out value="${UserProfileUser.isPolitical}"></c:out></div>
                    </div>
                    <div class="form-group"><label class="control-label col-md-3">Status: </label>
                        <div class="col-sm-2"><c:out value="${UserProfileUser.status}"></c:out></div>
                        <label class="control-label col-md-3">Role: </label>
                        <div class="col-sm-2"><c:out value="${UserProfileUser.position}"></c:out></div>
                    </div>
                    <div class="form-group"><label class="control-label col-md-3">Vacation days: </label>
                        <div class="col-lg-5"><c:out
                                value="${UserProfileUser.vacationDaysLeft}/${UserProfileUser.vacationDaysAll}"></c:out></div>
                    </div>

                </div>

            </div>
            <!--General info/Personal info Tab-->
            <div id="personalinfo" class="tab-pane fade"><br/>
                <ul class="nav nav-tabs">
                    <li class="active"><a data-toggle="tab" href="#pien">EN</a></li>
                    <li><a data-toggle="tab" href="#piru">RU</a></li>
                    <li><a data-toggle="tab" href="#piuz">UZ</a></li>
                </ul>
                <!--English, Russian, Uzbek (Localizations)-->
                <div class="tab-content">
                    <div id="pien" class="tab-pane fade in active">
                        <p>In english</p>
                        <div class="form-horizontal">
                            <div class="form-group"><label class="control-label col-md-4">Birth place: </label>
                                <div class="col-lg-5"><c:out
                                        value="${UserProfileUser.personalInfo.birthPlace[2]}"></c:out></div>
                            </div>
                            <div class="form-group"><label class="control-label col-md-4">Date of
                                Birth: </label>
                                <div class="col-lg-5"><c:out
                                        value="${UserProfileUser.personalInfo.dateOfBirth}"></c:out></div>
                            </div>
                            <div class="form-group"><label class="control-label col-md-4">Mobile phone: </label>
                                <div class="col-lg-5"><c:out
                                        value="${UserProfileUser.personalInfo.mobilePhone}"></c:out></div>
                            </div>
                            <div class="form-group"><label class="control-label col-md-4">Home phone: </label>
                                <div class="col-lg-5"><c:out
                                        value="${UserProfileUser.personalInfo.homePhone}"></c:out></div>
                            </div>
                            <div class="form-group"><label class="control-label col-md-4">E-mail
                                (corporative): </label>
                                <div class="col-lg-5"><c:out
                                        value="${UserProfileUser.personalInfo.emailCompany}"></c:out></div>
                            </div>
                            <div class="form-group"><label class="control-label col-md-4">E-mail
                                (personal): </label>
                                <div class="col-lg-5"><c:out
                                        value="${UserProfileUser.personalInfo.emailPersonal}"></c:out></div>
                            </div>
                        </div>
                    </div>
                    <div id="piru" class="tab-pane fade">
                        <p>На русском</p>
                        <div class="form-horizontal">
                            <div class="form-group"><label class="control-label col-md-4">Место
                                рождения: </label>
                                <div class="col-lg-5"><c:out
                                        value="${UserProfileUser.personalInfo.birthPlace[0]}"></c:out></div>
                            </div>
                            <div class="form-group"><label class="control-label col-md-4">Дата
                                рождения: </label>
                                <div class="col-lg-5"><c:out
                                        value="${UserProfileUser.personalInfo.dateOfBirth}"></c:out></div>
                            </div>
                            <div class="form-group"><label class="control-label col-md-4">Домашний
                                телефон: </label>
                                <div class="col-lg-5"><c:out
                                        value="${UserProfileUser.personalInfo.homePhone}"></c:out></div>
                            </div>
                            <div class="form-group"><label class="control-label col-md-4">Мобильный
                                телефон: </label>
                                <div class="col-lg-5"><c:out
                                        value="${UserProfileUser.personalInfo.mobilePhone}"></c:out></div>
                            </div>
                            <div class="form-group"><label class="control-label col-md-4">E-mail
                                (рабочий): </label>
                                <div class="col-lg-5"><c:out
                                        value="${UserProfileUser.personalInfo.emailCompany}"></c:out></div>
                            </div>
                            <div class="form-group"><label class="control-label col-md-4">E-mail
                                (персональный): </label>
                                <div class="col-lg-5"><c:out
                                        value="${UserProfileUser.personalInfo.emailPersonal}"></c:out></div>
                            </div>
                        </div>
                    </div>
                    <div id="piuz" class="tab-pane fade">
                        <p>O'zbekchada</p>
                        <div class="form-horizontal">
                            <div class="form-group"><label class="control-label col-md-4">Tug'ilgan
                                joyi: </label>
                                <div class="col-lg-5"><c:out
                                        value="${UserProfileUser.personalInfo.birthPlace[1]}"></c:out></div>
                            </div>
                            <div class="form-group"><label class="control-label col-md-4">Tug'ilgan
                                sanasi: </label>
                                <div class="col-lg-5"><c:out
                                        value="${UserProfileUser.personalInfo.dateOfBirth}"></c:out></div>
                            </div>
                            <div class="form-group"><label class="control-label col-md-4">Uy telefon
                                raqami: </label>
                                <div class="col-lg-5"><c:out
                                        value="${UserProfileUser.personalInfo.homePhone}"></c:out></div>
                            </div>
                            <div class="form-group"><label class="control-label col-md-4">Uyali aloqa
                                raqami: </label>
                                <div class="col-lg-5"><c:out
                                        value="${UserProfileUser.personalInfo.mobilePhone}"></c:out></div>
                            </div>
                            <div class="form-group"><label class="control-label col-md-4">E-mail (ish): </label>
                                <div class="col-lg-5"><c:out
                                        value="${UserProfileUser.personalInfo.emailCompany}"></c:out></div>
                            </div>
                            <div class="form-group"><label class="control-label col-md-4">E-mail
                                (shaxsiy): </label>
                                <div class="col-lg-5"><c:out
                                        value="${UserProfileUser.personalInfo.emailPersonal}"></c:out></div>
                            </div>
                        </div>
                    </div>
                    <%
                        if((int)request.getAttribute("SystemRole")==2){
                    %>
                    <div class="col-sm-9"><a href="/User/Profile/editPersonal" class="btn btn-primary">Edit</a></div>
                    <% } %>
                </div>
            </div>
            <!--General info/Family info Tab-->
            <div id="familyinfo" class="tab-pane fade"><br/>
                <ul class="nav nav-tabs">
                    <li><a data-toggle="tab" id="fienref" href="#fien">EN</a></li>
                    <li><a data-toggle="tab" id="firuref" href="#firu">RU</a></li>
                    <li><a data-toggle="tab" id="fiuzref" href="#fiuz">UZ</a></li>
                </ul>
                <!--English, Russian, Uzbek (Localizations)-->
                <div class="tab-content">
                    <div id="fien" class="tab-pane fade in active">
                        <p>In english</p>
                        <div class="form-horizontal">
                            <!--Family table-->
                            <table class="table">
                                <thead>
                                <tr>
                                    <th>Relation</th>
                                    <th>Full name</th>
                                    <th>Date of birth</th>
                                    <th>Duties</th>
                                    <%
                                        if((int)request.getAttribute("SystemRole")==2){
                                    %>
                                    <th>Action</th>
                                    <% } %>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${UserProfileUser.familyLoc}" var="family" varStatus="status">
                                    <tr>
                                        <td>
                                            <div contenteditable="true">${family.relation[2]}</div>
                                        </td>
                                        <td>
                                            <div contenteditable="true">${family.firstName[2]} ${family.lastName[2]}</div>
                                        </td>
                                        <td>
                                            <div contenteditable="true">${family.dateOfBirth}</div>
                                        </td>
                                        <td>
                                            <div contenteditable="true">${family.jobTitle[2]}</div>
                                        </td>
                                        <%
                                            if((int)request.getAttribute("SystemRole")==2){
                                        %>
                                        <td><a href="User/Profile/updateFam/${family.id}/"
                                               class="btn btn-default">Edit</a>
                                            <a href="User/Profile/deleteFam/${family.id}/" class="btn btn-danger">Delete</a>
                                        </td>
                                        <% } %>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div id="firu" class="tab-pane fade in active">
                        <p>На русском</p>
                        <div class="form-horizontal">
                            <!--Family table-->
                            <table class="table">
                                <thead>
                                <tr>
                                    <th>Родство</th>
                                    <th>Ф.И.О.</th>
                                    <th>Дата рождения</th>
                                    <th>Вид деятельности</th>
                                    <%
                                        if((int)request.getAttribute("SystemRole")==2){
                                    %>
                                    <th>Action</th>
                                    <% } %>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${UserProfileUser.familyLoc}" var="family" varStatus="status">
                                    <tr>
                                        <td>
                                            <div contenteditable="true">${family.relation[0]}</div>
                                        </td>
                                        <td>
                                            <div contenteditable="true">${family.firstName[0]} ${family.lastName[0]}</div>
                                        </td>
                                        <td>
                                            <div contenteditable="true">${family.dateOfBirth}</div>
                                        </td>
                                        <td>
                                            <div contenteditable="true">${family.jobTitle[0]}</div>
                                        </td>
                                        <%
                                            if((int)request.getAttribute("SystemRole")==2){
                                        %>
                                        <td><a href="User/Profile/updateFam/${family.id}/"
                                               class="btn btn-default">Edit</a>
                                            <a href="User/Profile/deleteFam/${family.id}/" class="btn btn-danger">Delete</a>
                                        </td>
                                        <% } %>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div id="fiuz" class="tab-pane fade in active">
                        <p>O'zbekchada</p>
                        <div class="form-horizontal">
                            <!--Family table-->
                            <table class="table">
                                <thead>
                                <tr>
                                    <th>Qarindoshligi</th>
                                    <th>F.I.Sh.</th>
                                    <th>Tug'ilgan sanasi</th>
                                    <th>Faoliyati</th>
                                    <%
                                        if((int)request.getAttribute("SystemRole")==2){
                                    %>
                                    <th>Action</th>
                                    <% } %>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${UserProfileUser.familyLoc}" var="family" varStatus="status">
                                    <tr>
                                        <td>
                                            <div contenteditable="true">${family.relation[1]}</div>
                                        </td>
                                        <td>
                                            <div contenteditable="true">${family.firstName[1]} ${family.lastName[1]}</div>
                                        </td>
                                        <td>
                                            <div class="date" contenteditable="true">${family.dateOfBirth}</div>
                                        </td>
                                        <td>
                                            <div contenteditable="true">${family.jobTitle[1]}</div>
                                        </td>
                                        <%
                                            if((int)request.getAttribute("SystemRole")==2){
                                        %>
                                            <td><a href="./Profile/updateFam/${family.id}/"
                                                   class="btn btn-default">Edit</a>
                                                <a href="./Profile/deleteFam/${family.id}/" class="btn btn-danger">Delete</a>
                                            </td>
                                        <% }%>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <%
                        if((int)request.getAttribute("SystemRole")==2){
                    %>
                    <div class="col-sm-9"><a href="Profile/addFam" class="btn btn-primary">Add</a></div>
                    <% } %>
                </div>
            </div>
        </div>
    </div>
</div>
</div>

<script>
    $(document).ready(function () {
        $("#firuref").trigger('click');
        document.getElementById("firuref").click();
        $("#fienref").trigger('click');
        document.getElementById("fienref").click();
    });
</script>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
